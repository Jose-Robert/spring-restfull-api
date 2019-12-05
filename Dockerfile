FROM openjdk:8-jdk-alpine

ENV TZ America/Recife

COPY /target/spring-course-0.0.1.war /usr/local/tomcat/webapps/spring-course-0.0.1.war

CMD ["catalina.sh","run"]