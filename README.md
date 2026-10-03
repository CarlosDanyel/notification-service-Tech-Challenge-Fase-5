# FIAP X Notification Service

Java 21 / Spring Boot consumer for video failure events. It sends email through SMTP and stores delivery records with `created_at` and `updated_at` in a dedicated PostgreSQL database. Event IDs prevent redelivered messages from creating a second notification after successful delivery.

Java sources and tests live under `src/main/java/techchallenge/fiapx/notification` and `src/test/java/techchallenge/fiapx/notification`. The package root is `techchallenge.fiapx.notification`.

## Run and test

Start infra dependencies, copy `.env.example` to `.env`, set `DB_NAME=fiapx_notifications`, `SMTP_HOST` and credentials, export them with `set -a; source .env; set +a`, then run `JAVA_HOME=$(/usr/libexec/java_home -v 21) ./gradlew bootRun`. Run `./gradlew clean test`. Flyway creates the schema from [`V1__notifications.sql`](src/main/resources/db/migration/V1__notifications.sql). Local mail is visible in Mailpit at `http://localhost:8025`; production should point SMTP to a real provider.

Metrics: `/actuator/prometheus` on port 8082. The release branch tests and publishes a GHCR image; master deploys after promotion from release when deployment variables are configured. See the [infra documentation](https://github.com/CarlosDanyel/INFRA-Tech-Challenge-Fase-5).
