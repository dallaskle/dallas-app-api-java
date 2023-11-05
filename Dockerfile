FROM maven:3.8.4-amazoncorretto-17 AS build
COPY ..
RUN mvn clean package -DskipTests

FROM amazoncorretto:17.0.4
COPY --from=build /target/dallas-app-api-java-0.0.1-SNAPSHOT.jar dallas-app-api-java.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "dallas-app-api-java.jar"]