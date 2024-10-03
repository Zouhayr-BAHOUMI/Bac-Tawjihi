FROM openjdk:17
EXPOSE 8080
ADD target/Tawjih-0.0.1-SNAPSHOT.jar tawjih.jar
ENTRYPOINT ["java", "-jar", "/tawjih.jar"]