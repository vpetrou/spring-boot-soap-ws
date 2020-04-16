FROM java:8-jdk-alpine
WORKDIR /app
EXPOSE 8080
ADD target/soap-ws-1.0.0.jar /app/app.jar
CMD sleep 40 && java -jar /app/app.jar