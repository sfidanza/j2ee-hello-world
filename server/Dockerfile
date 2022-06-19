# Stage Build
FROM openjdk:8-jdk-alpine AS build

# Install Maven
RUN apk add --no-cache curl tar bash
ARG MAVEN_VERSION=3.3.9
ARG USER_HOME_DIR="/root"
RUN mkdir -p /usr/share/maven && \
  curl -fsSL http://apache.osuosl.org/maven/maven-3/$MAVEN_VERSION/binaries/apache-maven-$MAVEN_VERSION-bin.tar.gz | tar -xzC /usr/share/maven --strip-components=1 && \
  ln -s /usr/share/maven/bin/mvn /usr/bin/mvn
ENV MAVEN_HOME /usr/share/maven
ENV MAVEN_CONFIG "$USER_HOME_DIR/.m2"
# speed up Maven JVM a bit
ENV MAVEN_OPTS="-XX:+TieredCompilation -XX:TieredStopAtLevel=1"

# Download and cache maven dependencies
WORKDIR /usr/src/app
COPY pom.xml .
RUN mvn install && rm -rf target

# copy source files and build
COPY src ./src
RUN mvn test
RUN mvn package -Dmaven.test.skip=true

# Stage Run
FROM tomcat:10
COPY --from=build /usr/src/app/webapps/helloWorld.war /usr/local/tomcat/webapps
CMD ["catalina.sh", "run"]
EXPOSE 8080
