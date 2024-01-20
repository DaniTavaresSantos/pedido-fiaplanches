FROM maven:latest

ENV SPRING_DATASOURCE_URL=jdbc:postgresql://postgres-db:5432/fiap-lanches-order
WORKDIR /app
RUN rm -rf /app/*
COPY . /app
RUN mvn clean install -DskipTests
RUN mkdir jar
RUN mv /app/target/fiap-lanches-order-0.0.1-SNAPSHOT.jar /app/jar/fiap-lanches-order-app.jar
EXPOSE 8080

ENTRYPOINT ["java", "-jar", "/app/jar/fiap-lanches-order-app.jar"]
