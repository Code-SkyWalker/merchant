# GitHub Actions 远程部署到 CentOS 7.9 服务器

本文档详细介绍如何使用 GitHub Actions 自动部署 RuoYi-Vue-Plus Spring Boot 项目到 CentOS 7.9 服务器。

## 部署流程概述

1. GitHub Actions 自动构建项目（使用 Maven 打包）
2. 将生成的 JAR 文件通过 SCP 传输到目标服务器
3. 在目标服务器上配置 systemd 服务并启动应用

## 前置条件

### 服务器要求
- CentOS 7.9 服务器
- SSH 访问权限
- Java 17 或更高版本已安装
- sudo 权限用于系统服务配置

### GitHub 仓库配置
- 项目代码已推送至 GitHub
- GitHub Secrets 已配置（详见下文）

## 服务器端配置

在部署之前，需要在目标 CentOS 7.9 服务器上进行以下配置：

### 1. 安装 Java 17

```bash
# 安装 OpenJDK 17
sudo yum install -y java-17-openjdk java-17-openjdk-devel

# 验证安装
java -version
```

### 2. 创建应用目录

```bash
sudo mkdir -p /opt/ruoyi-vue-plus
sudo chown $USER:$USER /opt/ruoyi-vue-plus
```

### 3. （可选）配置防火墙

如果需要从外部访问应用：

```bash
# 假设应用运行在8080端口
sudo firewall-cmd --permanent --add-port=8080/tcp
sudo firewall-cmd --reload
```

## GitHub Secrets 配置

在 GitHub 仓库中配置以下 Secrets（Settings > Secrets and variables > Actions）：

| Secret Name | Description | Example |
|-------------|-------------|---------|
| `SERVER_HOST` | 目标服务器 IP 地址 | `192.168.1.100` |
| `SERVER_USERNAME` | 服务器用户名 | `deploy` |
| `SERVER_PASSWORD` | 服务器用户密码 | `your_password` |
| `SERVER_PORT` | SSH 端口（可选，默认22） | `22` |

### SSH 密码配置

确保服务器用户具有适当的权限来执行部署操作。如果需要，可以配置 sudo 权限：

```bash
# 编辑 sudoers 文件
sudo visudo

# 添加以下行（替换 deploy 为实际用户名）
# deploy ALL=(ALL) NOPASSWD: ALL
```

## 部署配置文件说明

创建的 [deploy-centos.yml](file:///E:/compeny-code/RuoYi-Vue-Plus/.github/workflows/deploy-centos.yml) 文件包含以下功能：

### 触发条件
- 推送到 `main` 或 `master` 分支时自动触发
- 手动触发（通过 GitHub Actions 界面）

### 构建步骤
1. 检出代码
2. 设置 Java 17 环境
3. 缓存 Maven 依赖
4. 使用 Maven 打包（`-Pprod` profile）
5. 验证 JAR 文件生成

### 部署步骤
1. 通过 SCP 将 JAR 文件传输到服务器
2. 配置 systemd 服务
3. 启动/重启应用服务

## Maven 依赖缓存和构建优化

为了加速构建过程并避免每次都重新下载依赖包，部署流程采用了以下优化策略：

### 1. Maven 依赖缓存
- 使用 GitHub Actions 缓存功能缓存 `~/.m2` 目录
- 基于 `pom.xml` 文件内容生成缓存键
- 支持缓存恢复，提高构建效率

### 2. Maven Wrapper
- 使用 Maven Wrapper 确保构建环境一致性
- 避免因 Maven 版本差异导致的构建问题
- 提供更快的构建速度

### 3. 优化的 Maven 设置
- 配置了镜像仓库（华为云、阿里云）以加速依赖下载
- 预配置的 settings.xml 文件用于优化构建过程

### 4. 构建命令配置
工作流使用以下 Maven 命令进行打包：

```bash
./mvnw clean package -DskipTests -Pprod
```

这将使用 `prod` profile 构建生产环境的 JAR 文件。

## 应用配置

部署脚本会自动创建 systemd 服务配置，使用以下参数：

- 服务名称：`ruoyi-vue-plus`
- 用户：`appuser`（如不存在会自动创建）
- 启动命令：`java -Dspring.profiles.active=prod -jar /opt/ruoyi-vue-plus/ruoyi-admin.jar`
- 自动重启：启用
- 日志输出：systemd journal

## 部署验证

部署完成后，可以通过以下方式验证应用状态：

```bash
# 检查服务状态
sudo systemctl status ruoyi-vue-plus

# 查看服务日志
sudo journalctl -u ruoyi-vue-plus -f

# 检查进程
ps aux | grep java | grep ruoyi
```

## 故障排除

### 构建失败
- 检查 Java 版本是否为 17
- 确认 Maven 依赖是否正确
- 查看 GitHub Actions 构建日志

### 部署失败
- 验证服务器连接信息
- 检查 SSH 密钥权限
- 确认目标目录权限

### 应用启动失败
- 查看服务器日志：`sudo journalctl -u ruoyi-vue-plus`
- 检查配置文件
- 确认端口占用情况

### 常见错误及解决方案

1. **Permission denied (publickey)**
   - 确认 SSH 公钥已正确添加到服务器
   - 检查 `~/.ssh/authorized_keys` 权限设置

2. **Connection timeout**
   - 确认服务器防火墙允许 SSH 连接
   - 检查服务器网络连接

3. **Java process fails to start**
   - 检查服务器是否有足够内存
   - 确认 Java 环境已正确安装

## 安全建议

1. 考虑使用 SSH 密钥而非密码进行认证（更安全）
2. 限制服务器访问权限
3. 定期轮换密码
4. 在服务器上配置防火墙
5. 使用专用部署用户运行应用
6. 定期更新系统和软件包
7. 避免在代码仓库中暴露敏感信息

## 自定义配置

如果需要自定义部署配置，可以修改 `.github/workflows/deploy-centos.yml` 文件：

- 更改部署路径：修改 `DEPLOY_PATH` 环境变量
- 更改 JAR 文件名：修改 `JAR_FILE` 环境变量
- 更改应用用户：修改 `DEPLOY_USER` 环境变量
- 更改启动参数：修改 systemd 服务文件中的 `ExecStart` 行