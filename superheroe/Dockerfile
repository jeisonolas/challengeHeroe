FROM eclipse-temurin:11
VOLUME /tmp
ADD target/*.jar apiHeroes.jar
ENV JAVA_OPTS=""
ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -jar /apiHeroes.jar" ]
