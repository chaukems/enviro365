FROM openjdk:22-jdk
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} report-service.jar
ENTRYPOINT ["java","-jar","/report-service.jar"]