# Stage Build
FROM maven:3.9.11-eclipse-temurin-21-alpine AS build

# Download and cache maven dependencies
WORKDIR /usr/src/app
COPY pom.xml .
RUN mvn install && rm -rf target

# copy source files and build
COPY src ./src
RUN mvn test
RUN mvn package -Dmaven.test.skip=true

# Stage Run
FROM tomcat:11
COPY --from=build /usr/src/app/webapps/helloWorld.war /usr/local/tomcat/webapps
CMD ["catalina.sh", "run"]
EXPOSE 8080
