FROM eclipse-temurin:21-jre-jammy

WORKDIR /app

COPY build/libs/VKUserAndGroup-0.0.1-SNAPSHOT.jar app/vkuserandgroup-app.jar

ENTRYPOINT ["java", "-jar", "app/vkuserandgroup-app.jar"]