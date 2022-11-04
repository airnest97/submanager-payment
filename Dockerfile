FROM openjdk:17-alpine
MAINTAINER submanager-payment
EXPOSE 8081
ADD target/submanager-payment.jar submanager-payment.jar
ENTRYPOINT ["java", "-jar", "/submanager-payment.jar"]