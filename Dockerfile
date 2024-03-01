FROM node:alpine as frontend-build
WORKDIR /workspace/app

COPY src/frontend/package.json .
COPY src/frontend/package-lock.json .
COPY src/frontend/public ./public
COPY src/frontend/src ./src

RUN npm install
RUN npm run build


FROM maven:latest as backend-build
WORKDIR /workspace/app

COPY .mvn/wrapper/maven-wrapper.jar .mvn/wrapper/maven-wrapper.properties .mvn/wrapper/
RUN chmod +x .mvn/wrapper/maven-wrapper.jar

COPY mvnw .
COPY pom.xml .
COPY src src

RUN ./mvnw install -DskipTests
RUN mkdir -p target/dependency && (cd target/dependency; jar -xf ../*.jar)

FROM eclipse-temurin:17-jdk-alpine
VOLUME /tmp
ARG DEPENDENCY=/workspace/app/target/dependency
COPY --from=backend-build ${DEPENDENCY}/BOOT-INF/lib /app/lib
COPY --from=backend-build ${DEPENDENCY}/META-INF /app/META-INF
COPY --from=backend-build ${DEPENDENCY}/BOOT-INF/classes /app

COPY --from=frontend-build /workspace/app/build /app/frontend

WORKDIR /app
EXPOSE 8080
ENTRYPOINT ["java","-cp","app:app/lib/*","org.triunfo.fetchaccount.FetchAccountApplication"]
