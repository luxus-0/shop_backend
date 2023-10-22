FROM openjdk:latest
ADD target/Shop-0.0.1-SNAPSHOT.jar .
EXPOSE 8080
CMD java -jar Shop-0.0.1-SNAPSHOT.jar