FROM maven:3.8.5-eclipse-temurin-17

WORKDIR /local/app/backend
COPY ./backend/pom.xml .
COPY ./backend/src ./src
RUN mvn test

CMD ["mvn", "test"]