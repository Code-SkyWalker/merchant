# 使用官方 OpenJDK 17 镜像作为基础镜像
FROM openjdk:17-jdk-slim

# 设置工作目录
WORKDIR /app

# 创建非 root 用户用于运行应用
RUN groupadd -r appuser && useradd -r -g appuser -m -s /bin/bash appuser

# 复制 JAR 文件到容器中
COPY ruoyi-admin/target/ruoyi-admin.jar app.jar

# 更改文件所有者
RUN chown appuser:appuser app.jar

# 暴露应用端口（默认8080）
EXPOSE 8080

# 设置健康检查
HEALTHCHECK --interval=30s --timeout=3s --start-period=60s --retries=3 \
  CMD curl -f http://localhost:8080/actuator/health/ || exit 1

# 使用非 root 用户运行应用
USER appuser

# 启动应用
ENTRYPOINT ["java", "-Dspring.profiles.active=prod", "-jar", "app.jar"]