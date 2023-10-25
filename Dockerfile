FROM maven:3.9.5-sapmachine-21 as spring-keycloak-oauth2-example
LABEL authors="ruben.morchon"
# Copies all the code to the container
COPY . .
# Uses maven to generate the jar.
RUN mvn clean install
# Exposing default port.
EXPOSE 8080
# Launches the generated jar.
ENTRYPOINT [ "java", "-jar", "./target/spring-keycloak-oauth2-example-1.2.0.jar" ]