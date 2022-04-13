#准备JDK环境
FROM adoptopenjdk/openjdk11

#复制jar
COPY build/libs/bitfactory-0.0.1-SNAPSHOT.jar bitfactory.jar

# 容器启动时第一个运行的命令及其参数：java -jar
ENTRYPOINT ["java","-jar","/bitfactory.jar"]

EXPOSE 8080