FROM maven:latest

ENV SPRING_DATASOURCE_URL=jdbc:postgresql://postgres-db-order:5432/fiap-lanches-order
ENV SPRING_KAFKA_BOOTSTRAP_SERVERS=kafka1:19091
ENV REST_CLIENTS_ENDPOINT=http://fiap-lanches-client-api:8085/v1/client
ENV REST_PRODUCTS_ENDPOINT=http://fiap-lanches-product-api:8082/v1/product
WORKDIR /app
RUN rm -rf /app/*
COPY . /app
RUN mvn clean install -DskipTests
RUN mkdir jar
RUN mv /app/target/fiap-lanches-order-0.0.1-SNAPSHOT.jar /app/jar/fiap-lanches-order-app.jar
EXPOSE 8080

ENTRYPOINT ["java", "-jar", "/app/jar/fiap-lanches-order-app.jar"]
