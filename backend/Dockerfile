FROM eclipse-temurin:17-jdk-alpine as builder

WORKDIR /app

COPY . .

RUN chmod +x gradlew

RUN ./gradlew bootJar -x test --no-daemon

FROM eclipse-temurin:17-jdk-alpine
WORKDIR /app

COPY --from=builder /app/build/libs/*.jar app.jar

EXPOSE 5101

CMD ["java", "-jar", "app.jar"]