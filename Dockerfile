FROM maven:3.9.5-sapmachine-21 as spring-keycloak-oauth2-example
LABEL authors="ruben.morchon"

COPY . .
RUN mvn clean install

EXPOSE 8080

ENTRYPOINT [ "java", "-jar", "./target/spring-keycloak-oauth2-example-1.0-SNAPSHOT.jar" ]