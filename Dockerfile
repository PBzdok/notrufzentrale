FROM gradle:7.3.3-jdk17 AS build

COPY --chown=gradle:gradle . /home/gradle/src
WORKDIR /home/gradle/src
RUN gradle build --no-daemon


FROM adoptopenjdk/openjdk16:jre-16.0.1_9-alpine

RUN mkdir /app
COPY --from=build /home/gradle/src/build/libs/*.jar /app/rundfunk.jar
ENTRYPOINT ["java", "-jar", "-Xms128m","-Xmx256m", "/app/rundfunk.jar"]
