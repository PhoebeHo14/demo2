FROM openjdk:11

WORKDIR /app

COPY target/demo2-0.0.1-SNAPSHOT.jar /app/demo2-0.0.1-SNAPSHOT.jar

ENV DB_HOST=mysql-dev-container
ENV DB_PORT=13306
ENV DB_NAME=db1
ENV DB_USERNAME=root
ENV DB_PASSWORD=1234

CMD java -jar /app/demo2-0.0.1-SNAPSHOT.jar
