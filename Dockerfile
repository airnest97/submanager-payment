FROM openjdk:19
MAINTAINER submanager-payment
EXPOSE 8080
ADD target/submanager-payment.jar submanager-payment.jar
ENTRYPOINT ["java", "-jar", "/submanager-payment.jar"]