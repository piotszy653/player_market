#
# Build stage
#
FROM maven:3.6.0-jdk-11-slim AS build
COPY src /home/app/src
COPY pom.xml /home/app
RUN mvn -f /home/app/pom.xml clean package

#
# Package stage
#
FROM openjdk:8-jre-slim
COPY --from=build /home/app/target/player_market-0.0.1-SNAPSHOT.jar /home/player-market.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/home/player-market.jar"]