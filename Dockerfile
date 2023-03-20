FROM gradle:jdk19-alpine AS build
WORKDIR /app
COPY . .
RUN gradle bootJar



FROM openjdk:19 AS runtime
WORKDIR /app
ENV SPRING_ACTIVE_PROFILES "prod"


EXPOSE 8080

ENTRYPOINT ["java","-jar","app.jar"]
ARG JAR_FILE=/app/build/libs/*.jar
COPY --from=build ${JAR_FILE} /app/app.jar

