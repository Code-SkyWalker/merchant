# GitHub Actions 部署配置说明

## 概述
此配置用于自动构建和部署 RuoYi-Vue-Plus Java 应用程序到远程服务器。

## 配置要求

### 1. GitHub Secrets 配置
在 GitHub 仓库设置中需要配置以下 Secrets：

- `HOST`: 远程服务器 IP 地址或域名
- `USERNAME`: 远程服务器用户名
- `PASSWORD`: 远程服务器密码 (推荐使用 SSH 密钥)
- `PORT`: SSH 端口 (通常为 22)

### 2. 远程服务器配置要求

在远程服务器上需要进行以下配置：

#### A. 创建应用目录
```bash
sudo mkdir -p /opt/ruoyi-vue-plus
sudo chown $USER:$USER /opt/ruoyi-vue-plus
```

#### B. 创建 systemd 服务 (可选，推荐)
创建服务文件 `/etc/systemd/system/ruoyi-vue-plus.service`:
```ini
[Unit]
Description=RuoYi-Vue-Plus Application
After=network.target

[Service]
Type=simple
User=your_user
ExecStart=/usr/bin/java -Dspring.profiles.active=prod -jar /opt/ruoyi-vue-plus/ruoyi-vue-plus.jar
Restart=always
RestartSec=10

[Install]
WantedBy=multi-user.target
```

然后启用服务：
```bash
sudo systemctl daemon-reload
sudo systemctl enable ruoyi-vue-plus
```

### 3. 部署流程

1. 当推送到 master/main 分支时，触发构建流程
2. 使用 Maven 打包项目 (使用 prod 配置文件)
3. 通过 SCP 将 JAR 文件上传到远程服务器
4. 通过 SSH 连接到远程服务器并重启应用

## 部署触发条件

- 推送到 master 或 main 分支时自动触发
- Pull Request 也会运行构建步骤，但不会执行部署

## 环境配置

当前配置使用 `prod` profile 进行构建和部署，这将使用 `application-prod.yml` 配置文件。

## 安全说明

- 所有敏感信息（如服务器密码）都应存储在 GitHub Secrets 中
- 建议使用 SSH 密钥而非密码进行身份验证
- 服务器上应配置防火墙以限制访问