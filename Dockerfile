FROM eclipse-temurin:17-jdk-jammy as build-image
WORKDIR /app

COPY .mvn/ .mvn
COPY mvnw pom.xml ./
#Baixa dependencias do maven para aproveitar o cache do docker, main muda com mais frequencia que as dependencias
RUN ./mvnw dependency:go-offline

COPY ./src/main/ ./src/main/

RUN ./mvnw clean package


FROM eclipse-temurin:17-jre-jammy as deploy-image

COPY --from=build-image /app/target/*.jar /app/app.jar

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/app/app.jar"]