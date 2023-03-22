FROM amazoncorretto:17-alpine-jdk
MAINTAINER marinacarrizo
COPY target/portfolio-0.0.1-SNAPSHOT.jar mnc-porfolio.jar
ENTRYPOINT ["java","-jar","/mnc-porfolio.jar"]
EXPOSE 8080
