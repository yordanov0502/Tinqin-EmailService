FROM amazoncorretto:21-alpine
LABEL authors="yordanov0502"
WORKDIR /app
EXPOSE 8085
COPY rest/target/rest-0.0.1-SNAPSHOT.jar /app/email.jar

ENTRYPOINT ["java", "-jar", "/app/email.jar"]