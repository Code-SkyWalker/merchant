# RuoYi-Vue-Plus GitHub Actions 部署配置说明

## 概述

本项目提供了多种 GitHub Actions 部署配置，以满足不同场景下的部署需求。所有配置都基于 Java 17 和 Maven 构建。

## 部署配置文件说明

### 1. deploy.yml
- **用途**: 基础部署配置，使用密码认证
- **触发条件**: 推送到 master/main 分支时自动部署到生产环境
- **特点**: 简单直接，适合单环境部署

### 2. ssh-key-deploy.yml
- **用途**: 使用 SSH 密钥认证的部署配置
- **触发条件**: 推送或手动触发，支持 staging 和 production 环境
- **特点**: 更安全，推荐使用

### 3. multi-env-deploy.yml
- **用途**: 多环境部署配置
- **触发条件**: 
  - develop 分支推送部署到 staging
  - master/main 分支推送部署到 production
  - 手动触发可选择环境
- **特点**: 支持多环境，可手动触发

### 4. quick-deploy.yml
- **用途**: 快速部署配置，适合 RuoYi-Vue-Plus 项目
- **触发条件**: 推送或手动触发
- **特点**: 简化配置，自动创建 systemd 服务

## 配置步骤

### 1. 配置 GitHub Secrets

在 GitHub 仓库的 Settings > Secrets and variables > Actions 中添加以下 Secrets：

#### 基础部署 (deploy.yml)
```
HOST: 你的服务器IP地址
USERNAME: 服务器用户名
PASSWORD: 服务器密码
PORT: SSH端口 (通常是22)
```

#### SSH 密钥部署 (ssh-key-deploy.yml, multi-env-deploy.yml)
```
PROD_HOST: 生产服务器IP地址
PROD_USERNAME: 生产服务器用户名
PROD_SSH_KEY: 生产服务器私钥内容
PROD_PORT: 生产服务器SSH端口

STAGING_HOST: 预发布服务器IP地址
STAGING_USERNAME: 预发布服务器用户名
STAGING_SSH_KEY: 预发布服务器私钥内容
STAGING_PORT: 预发布服务器SSH端口
```

#### 快速部署 (quick-deploy.yml)
```
SERVER_HOST: 服务器IP地址
SERVER_USERNAME: 服务器用户名
SERVER_SSH_KEY: 服务器私钥内容
SERVER_PORT: SSH端口 (可选，默认22)
DEPLOY_USER: 部署用户 (可选，默认appuser)
```

### 2. 服务器端配置

在目标服务器上进行以下配置：

#### A. 创建应用目录
```bash
sudo mkdir -p /opt/ruoyi-vue-plus
sudo chown $USER:$USER /opt/ruoyi-vue-plus
```

#### B. (可选) 配置 systemd 服务
对于 quick-deploy.yml，脚本会自动创建 systemd 服务文件。如果要手动配置：

创建 `/etc/systemd/system/ruoyi-vue-plus.service`:
```ini
[Unit]
Description=RuoYi-Vue-Plus Application
After=network.target

[Service]
Type=simple
User=appuser
ExecStart=/usr/bin/java -Dspring.profiles.active=prod -jar /opt/ruoyi-vue-plus/ruoyi-admin.jar
Restart=always
RestartSec=10
StandardOutput=journal
StandardError=journal

[Install]
WantedBy=multi-user.target
```

然后启用服务：
```bash
sudo systemctl daemon-reload
sudo systemctl enable ruoyi-vue-plus
```

## 部署流程说明

### 1. 构建阶段
- 检出代码
- 设置 Java 17 环境
- 缓存 Maven 依赖
- 使用 Maven 打包 (根据环境使用不同 profile)

### 2. 部署阶段
- 通过 SCP 将 JAR 文件上传到服务器
- 备份当前版本
- 停止现有应用
- 启动新版本应用
- 验证应用状态

## 环境配置

- **开发环境 (dev)**: 使用 `application-dev.yml` 配置
- **生产环境 (prod)**: 使用 `application-prod.yml` 配置

## 安全建议

1. 使用 SSH 密钥而非密码进行认证
2. 限制服务器访问权限
3. 定期轮换 SSH 密钥
4. 在服务器上配置防火墙

## 故障排除

### 1. 构建失败
- 检查 Maven 配置
- 确认依赖是否正确

### 2. 部署失败
- 检查服务器连接信息
- 确认 SSH 密钥权限
- 检查目标目录权限

### 3. 应用启动失败
- 查看服务器日志
- 检查配置文件
- 确认端口占用情况