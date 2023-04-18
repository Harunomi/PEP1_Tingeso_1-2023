FROM openjdk:17
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} proyecto1-0.0.1-SNAPSHOT
EXPOSE 8090
ENTRYPOINT ["java","-jar","/proyecto1-0.0.1-SNAPSHOT"]