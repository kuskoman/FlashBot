FROM gradle:7.6.0-jdk17-focal AS build

WORKDIR /app

COPY gradlew *.gradle ./
COPY gradle ./gradle

RUN gradle dependencies --no-daemon

COPY . .
RUN gradle jar --no-daemon 


FROM amazoncorretto:17.0.6 as runtime

RUN yum install -y opus lame flac fdk-aac libvorbis libogg

COPY --from=build /app/build/libs/*.jar /app/app.jar

CMD java -jar /app/app.jar 
