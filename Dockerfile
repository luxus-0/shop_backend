FROM openjdk:latest
ADD target/Shop-0.0.1-SNAPSHOT.jar .
EXPOSE 8080
COPY src/main/resources/db/changelog /liquibase/changelog
CMD java -jar Shop-0.0.1-SNAPSHOT.jar