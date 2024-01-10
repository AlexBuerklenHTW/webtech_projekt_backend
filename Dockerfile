#
# Build stage
#
FROM gradle:jdk17-jammy AS build
COPY --chown=gradle:gradle . /home/gradle/src
WORKDIR /home/gradle/src
RUN gradle build --no-daemon

LABEL org.name="webtech_project_phillip_alex"
#
# Package stage
#
FROM eclipse-temurin:17.0.2_8-jdk
COPY --from=build /home/gradle/src/build/libs/webtechProjekt-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]