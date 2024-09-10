FROM eclipse-temurin:21-jdk-jammy AS base

# Backend build stage
FROM base AS builder
WORKDIR /app
COPY . .
RUN ./gradlew bootJar --no-daemon

# Production stage
FROM base AS production
WORKDIR /app

RUN addgroup --system --gid 1001 spring
RUN adduser --system --uid 1001 spring

COPY --from=builder /app/backend/build/libs/*.jar ./app.jar

USER spring

EXPOSE 8080

CMD ["java", "-jar", "app.jar"]
