FROM openjdk:8-jdk-alpine
#captcher
RUN set -xe \
&& apk --no-cache add ttf-dejavu fontconfig
ARG JAR_FILE
COPY ${JAR_FILE} app.jar
EXPOSE 9090
ENTRYPOINT ["java","-Dfile.encoding=utf-8","-jar","/app.jar"]
