FROM maven:3.5-jdk-8-alpine as build
WORKDIR /app
COPY pom.xml pom.xml
COPY jpa jpa
COPY backend_restinventoryv1_457fca9c_ce61_4bc1_a475_fae49c197c27 backend_restinventoryv1_457fca9c_ce61_4bc1_a475_fae49c197c27
RUN mvn clean package -DnoTest=true

FROM openjdk:8-jre-alpine
WORKDIR /app
COPY --from=build /app/backend_restinventoryv1_457fca9c_ce61_4bc1_a475_fae49c197c27/target/restinventoryv1-0.0.1-SNAPSHOT.jar /app
EXPOSE 8080
ENTRYPOINT ["sh", "-c"]
CMD ["java -jar restinventoryv1-0.0.1-SNAPSHOT.jar"]