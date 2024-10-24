FROM amazoncorretto:17-alpine-jdk
MAINTAINER example.com
COPY target/cep-0.0.1-SNAPSHOT.jar cep-0.0.1-SNAPSHOT.jar
ENTRYPOINT [ "java","-jar","/cep-0.0.1-SNAPSHOT.jar" ]