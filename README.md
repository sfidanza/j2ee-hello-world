# java-test

This is a sample java j2ee app, with a dockerized build and run.

The build is done with Maven, then the resulting `war` is run by Tomcat. Both are handled through a multistage `DockerFile`.

## Develoment setup

There is no necessary setup. However, the local environment in VSCode will most likely rely on a locally installed jre. This will need to be addressed later. There should be no issue if VS Code does not understand the code (the container will), but as a dev env setup it may not be optimal.

### Run locally in development mode

Everything is started through `docker-compose`, so the usual commands are working:

    docker-compose build
    docker-compose up -d --build
    docker-compose down

Once started, you can access the application at <http://localhost:8080>. Additionally:

- Shell into the `build` container to manually launch `mvn package` or whatever command of this kind. Any update in the `war` will be picked up by Tomcat automatically.
- View logs on the `app` container to follow Tomcat engine logs (when it picks up an updated jar).
- Tomcat acces logs are inside the `app` container so shell into it to follow them.

### Use VS Code as a java editor

The point of this dockerized setup is to avoid installing dev tools on the local machine. However, since this is not really working at the moment, here is a simple setup:

- of course install the VSCode Extension Pack for Java
- install jdk-11 (needed by VSCode) and jdk-8 (for the project), for example from <https://adoptium.net/temurin/releases?version=8>
- in user settings (File > Preferences > Settings), search for "java home" and then "Edit in settings.json":

      "java.jdt.ls.java.home": "C:\\Program Files\\Eclipse Adoptium\\jdk-11.0.13.8-hotspot",
      "java.configuration.runtimes": [
          {
            "name": "JavaSE-1.8",
            "path": "C:\\Program Files\\Eclipse Adoptium\\jdk-8.0.312.7-hotspot",
            "default":  true
          }
        ]

## Production setup

To run on docker swarm, use the following commands (images must either be available in container repository or have been built locally before, using `docker-compose build` for example):

    docker-compose -f docker-compose.yml config | docker stack deploy -c - helloWorld
    docker stack rm helloWorld

## References

- <https://codefresh.io/blog/java_docker_pipeline/>
- <https://codefresh.io/blog/using-docker-maven-maven-docker/>
