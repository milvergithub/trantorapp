
FROM adoptopenjdk:8-jre-hotspot
ARG JAR_FILE=target/auth-server-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]