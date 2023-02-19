FROM openjdk:11-jdk-slim
RUN addgroup -S heroes && adduser -S admin -G heroes
USER admin:heroes
VOLUME /tmp
ARG JAR_FILE=target/*.jar
ADD target/${JAR_FILE} app.jar
ENV JAVA_OPTS=""
ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -jar /app.jar" ]