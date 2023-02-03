FROM gradle:7.6.0-jdk17-focal AS build

WORKDIR /app

COPY gradlew *.gradle ./
COPY gradle ./gradle

RUN gradle dependencies --no-daemon

COPY . .
RUN gradle build --no-daemon 


FROM amazoncorretto:17.0.6-alpine3.17 as runtime

COPY --from=build /app/build/libs/*.jar /app/app.jar

ENTRYPOINT [ "java", '-jar', '/app/app.jar' ]
