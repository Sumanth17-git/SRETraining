FROM openjdk:17-jdk-slim
WORKDIR /usr/app
ADD target/*.jar app.jar
ENV JAVA_OPTS=""
EXPOSE 9999
ENTRYPOINT ["java", "-jar", "app.jar"]
