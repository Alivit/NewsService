FROM openjdk:17-jdk

ADD build/libs/news-0.0.1-SNAPSHOT.jar news.jar

ENTRYPOINT ["java", "-jar", "news-service.jar"]

EXPOSE 8080