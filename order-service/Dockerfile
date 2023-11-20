# base image with Java and Maven installed
FROM openjdk:17-alpine as build
WORKDIR /app
COPY . /app
RUN ./mvnw package -DskipTests

FROM penjdk:17-alpine
WORKDIR /app
COPY --from=build /app/target/ordr-service.jar /app
EXPOSE 8080
CMD ["java", "-jar", "your-app.jar"]
