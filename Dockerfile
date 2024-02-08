FROM maven:3-openjdk-17
WORKDIR /to-build-app
COPY . .

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/app/app.jar"]
