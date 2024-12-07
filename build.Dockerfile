FROM maven:3.8.5-eclipse-temurin-17 AS backend

WORKDIR /local/app/backend
COPY ./backend/pom.xml .
COPY ./backend/src ./src
RUN mvn clean install -DskipTests

FROM node:22.9.0-alpine AS frontend
RUN apk add --no-cache openjdk17

WORKDIR /local/app/frontend
COPY ./frontend/package*.json .
COPY ./frontend/src ./src
COPY ./frontend/vite.config.ts .
COPY ./frontend/tsconfig.json .
COPY ./frontend/tsconfig.app.json .
COPY ./frontend/tsconfig.node.json .
COPY ./frontend/index.html .
COPY --from=backend /local/app/backend/target/openapi/skillorakel-api.json /local/app/backend/target/openapi/
RUN npm ci
RUN npm run generate-client-code
RUN npm run build

FROM eclipse-temurin:17-jdk

WORKDIR /local/app

COPY --from=backend /local/app/backend/target/backend-0.0.1-SNAPSHOT.jar .
COPY --from=frontend /local/app/frontend/dist ./dist

ENTRYPOINT ["java", "-jar", "backend-0.0.1-SNAPSHOT.jar"]

EXPOSE 8080
