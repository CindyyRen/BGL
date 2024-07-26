# 使用包含 Java 运行时的基础镜像
FROM openjdk:21-jdk-alpine # 替换为所需的 JDK 版本

# 设置容器内的工作目录
WORKDIR /app

# 将 Spring Boot jar 文件复制到容器中
COPY target/spring-boot-docker-test.jar /app/spring-boot-docker-test.jar

# 公开 Spring Boot 应用程序运行的端口
EXPOSE 8080

# 运行 Spring Boot 应用程序
ENTRYPOINT [ "java" , "-jar" , "/app/spring-boot-docker-example.jar" ]