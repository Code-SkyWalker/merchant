#!/bin/bash

# RuoYi-Vue-Plus 部署脚本
# 用于在 CentOS 7.9 服务器上部署应用

set -e  # 遇到错误时退出

# 配置变量
APP_NAME="ruoyi-vue-plus"
JAR_FILE="ruoyi-admin.jar"
DEPLOY_PATH="/opt/ruoyi-vue-plus"
DEPLOY_USER="appuser"
SERVICE_NAME="ruoyi-vue-plus"

# 远程服务器配置（可选）
SERVER_HOST="${SERVER_HOST:-}"
SERVER_USER="${SERVER_USER:-}"
SERVER_PASSWORD="${SERVER_PASSWORD:-}"
SERVER_PORT="${SERVER_PORT:-22}"

# 颜色输出
RED='\033[0;31m'
GREEN='\033[0;32m'
YELLOW='\033[1;33m'
NC='\033[0m' # No Color

# 日志函数
log_info() {
    echo -e "${GREEN}[INFO]${NC} $1"
}

log_warn() {
    echo -e "${YELLOW}[WARN]${NC} $1"
}

log_error() {
    echo -e "${RED}[ERROR]${NC} $1"
}

# 检查是否以 root 身份运行
check_root() {
    if [[ $EUID -eq 0 ]]; then
        log_warn "建议不要以 root 身份运行此脚本"
    fi
}

# 检查 Java 是否已安装
check_java() {
    if ! command -v java &> /dev/null; then
        log_error "Java 未安装，请先安装 Java 17 或更高版本"
        log_info "安装命令: sudo yum install -y java-17-openjdk java-17-openjdk-devel"
        exit 1
    fi
    
    JAVA_VERSION=$(java -version 2>&1 | head -n1 | cut -d'"' -f2)
    if [[ "$JAVA_VERSION" < "17" ]]; then
        log_error "Java 版本过低，需要 Java 17 或更高版本，当前版本: $JAVA_VERSION"
        exit 1
    fi
    
    log_info "Java 版本检查通过: $JAVA_VERSION"
}

# 创建部署用户
create_deploy_user() {
    if ! id "$DEPLOY_USER" &>/dev/null; then
        log_info "创建部署用户: $DEPLOY_USER"
        sudo useradd -m -s /bin/bash "$DEPLOY_USER"
    else
        log_info "部署用户已存在: $DEPLOY_USER"
    fi
}

# 创建部署目录
create_deploy_dir() {
    if [ ! -d "$DEPLOY_PATH" ]; then
        log_info "创建部署目录: $DEPLOY_PATH"
        sudo mkdir -p "$DEPLOY_PATH"
    fi
    
    sudo chown "$DEPLOY_USER":"$DEPLOY_USER" "$DEPLOY_PATH"
    log_info "设置部署目录权限"
}

# 检查 JAR 文件是否存在
copy_jar_to_server() {
    if [ ! -f "$JAR_FILE" ]; then
        log_error "JAR 文件不存在: $JAR_FILE"
        log_info "请确保 JAR 文件位于当前目录"
        exit 1
    fi
    
    log_info "找到 JAR 文件: $JAR_FILE"
    
    if [ -n "$SERVER_HOST" ] && [ -n "$SERVER_USER" ] && [ -n "$SERVER_PASSWORD" ]; then
        log_info "通过 SSH 复制 JAR 文件到服务器: $SERVER_HOST"
        # 使用 sshpass 复制文件
        if command -v sshpass &> /dev/null; then
            sshpass -p "$SERVER_PASSWORD" scp -P "${SERVER_PORT:-22}" "$JAR_FILE" "$SERVER_USER@$SERVER_HOST:$DEPLOY_PATH/"
            log_info "JAR 文件已复制到服务器"
        else
            log_error "sshpass 未安装，无法通过 SSH 复制文件"
            log_info "请安装 sshpass 或手动复制 JAR 文件到服务器"
            exit 1
        fi
    else
        log_warn "未设置服务器信息，跳过远程复制"
    fi
}

# 备份当前版本
backup_current_version() {
    if [ -f "$DEPLOY_PATH/$JAR_FILE" ]; then
        log_info "备份当前版本"
        sudo mv "$DEPLOY_PATH/$JAR_FILE" "$DEPLOY_PATH/${JAR_FILE}.bak"
    fi
}

# 部署 JAR 文件
deploy_jar() {
    log_info "部署 JAR 文件到: $DEPLOY_PATH"
    sudo cp "$JAR_FILE" "$DEPLOY_PATH/"
    sudo chown "$DEPLOY_USER":"$DEPLOY_USER" "$DEPLOY_PATH/$JAR_FILE"
    sudo chmod +x "$DEPLOY_PATH/$JAR_FILE"
}

# 创建 systemd 服务文件
create_systemd_service() {
    log_info "创建 systemd 服务文件"
    
    sudo tee "/etc/systemd/system/$SERVICE_NAME.service" > /dev/null <<EOF
[Unit]
Description=RuoYi-Vue-Plus Application
After=network.target

[Service]
Type=simple
User=$DEPLOY_USER
Group=$DEPLOY_USER
ExecStart=/usr/bin/java -Dspring.profiles.active=prod -jar $DEPLOY_PATH/$JAR_FILE
ExecReload=/bin/kill -HUP \$MAINPID
Restart=always
RestartSec=10
StandardOutput=journal
StandardError=journal
WorkingDirectory=$DEPLOY_PATH

# Security settings
NoNewPrivileges=true
PrivateTmp=true
ProtectSystem=strict
ProtectHome=true
ReadOnlyDirectories=/
ReadWriteDirectories=$DEPLOY_PATH

[Install]
WantedBy=multi-user.target
EOF

    # 重新加载 systemd 配置
    sudo systemctl daemon-reload
    log_info "systemd 配置已重新加载"
}

# 启动应用服务
start_service() {
    log_info "启动服务: $SERVICE_NAME"
    
    # 如果服务正在运行，先停止
    if sudo systemctl is-active --quiet "$SERVICE_NAME"; then
        log_info "停止现有服务"
        sudo systemctl stop "$SERVICE_NAME"
        sleep 10
    fi
    
    # 启动服务
    sudo systemctl start "$SERVICE_NAME"
    sleep 15
    
    # 检查服务状态
    if sudo systemctl is-active --quiet "$SERVICE_NAME"; then
        log_info "服务启动成功"
        sudo systemctl enable "$SERVICE_NAME"  # 设置开机自启
        sudo systemctl status "$SERVICE_NAME" --no-pager
    else
        log_error "服务启动失败"
        sudo journalctl -u "$SERVICE_NAME" --no-pager -l
        exit 1
    fi
}

# 显示应用状态
show_status() {
    log_info "应用状态信息:"
    echo "----------------------------------------"
    echo "应用名称: $APP_NAME"
    echo "JAR 文件: $JAR_FILE"
    echo "部署路径: $DEPLOY_PATH"
    echo "运行用户: $DEPLOY_USER"
    echo "服务名称: $SERVICE_NAME"
    echo "----------------------------------------"
    
    if sudo systemctl is-active --quiet "$SERVICE_NAME"; then
        echo "服务状态: 运行中"
        sudo systemctl status "$SERVICE_NAME" --no-pager
    else
        echo "服务状态: 未运行"
    fi
}

# 主函数
main() {
    log_info "开始部署 $APP_NAME"
    
    case "${1:-deploy}" in
        "deploy")
            check_root
            check_java
            create_deploy_user
            create_deploy_dir
            copy_jar_to_server
            backup_current_version
            deploy_jar
            create_systemd_service
            start_service
            show_status
            log_info "部署完成!"
            ;;
        "status")
            show_status
            ;;
        "start")
            sudo systemctl start "$SERVICE_NAME"
            log_info "服务已启动"
            ;;
        "stop")
            sudo systemctl stop "$SERVICE_NAME"
            log_info "服务已停止"
            ;;
        "restart")
            sudo systemctl restart "$SERVICE_NAME"
            log_info "服务已重启"
            ;;
        "logs")
            sudo journalctl -u "$SERVICE_NAME" -f
            ;;
        *)
            echo "用法: $0 [deploy|status|start|stop|restart|logs]"
            echo "  deploy  - 部署应用 (默认)"
            echo "  status  - 查看应用状态"
            echo "  start   - 启动应用"
            echo "  stop    - 停止应用"
            echo "  restart - 重启应用"
            echo "  logs    - 查看应用日志"
            exit 1
            ;;
    esac
}

# 执行主函数
main "$@"