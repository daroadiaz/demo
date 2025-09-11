FROM maven:3.9.5-eclipse-temurin-17 AS builder
WORKDIR /app
COPY pom.xml .
COPY mvnw .
COPY mvnw.cmd .
COPY .mvn .mvn
RUN mvn dependency:go-offline -B
COPY src ./src
RUN mvn clean package -DskipTests

FROM eclipse-temurin:17-jre
RUN apt-get update && \
    apt-get install -y curl && \
    rm -rf /var/lib/apt/lists/*
RUN groupadd -r spring && useradd -r -g spring spring
WORKDIR /app
COPY --from=builder /app/target/*.jar app.jar
COPY src/main/resources/wallet /app/wallet
RUN chown -R spring:spring /app
USER spring
ENV ORACLE_TNS_ADMIN=/app/wallet
ENV TNS_ADMIN=/app/wallet
EXPOSE 8080
HEALTHCHECK --interval=30s --timeout=3s --start-period=40s --retries=3 \
  CMD curl -f http://localhost:8080/test-connection || exit 1
ENTRYPOINT ["java", \
  "-Doracle.net.tns_admin=/app/wallet", \
  "-Doracle.net.wallet_location=/app/wallet", \
  "-Djavax.net.ssl.trustStore=/app/wallet/truststore.jks", \
  "-Djavax.net.ssl.trustStorePassword=Pass1163Meth", \
  "-Djavax.net.ssl.keyStore=/app/wallet/keystore.jks", \
  "-Djavax.net.ssl.keyStorePassword=Pass1163Meth", \
  "-Djava.security.egd=file:/dev/./urandom", \
  "-jar", \
  "app.jar"]