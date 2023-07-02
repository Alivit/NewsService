FROM openjdk:17-jdk

ADD spring-cloud-service/build/libs/spring-cloud-service-0.0.1-SNAPSHOT.jar spring-cloud-service.jar

ENTRYPOINT ["java", "-jar", "spring-cloud-config.jar"]

EXPOSE 8888