FROM openjdk:21
RUN mkdir /app
EXPOSE 8080:8080
COPY ./app.jar /app/app.jar
ENTRYPOINT ["java","-jar","/app/app.jar", "-p", "8080:8080"]

