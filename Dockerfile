FROM openjdk:21

COPY ./build/libs/*.jar /usr/app/

WORKDIR /usr/app

EXPOSE 8080

CMD ["java", "-jar", "account-0.0.1.jar"]