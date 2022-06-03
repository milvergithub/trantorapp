## RUN DOCKER COMPOSE

* First we need to generate jar files

```bash
cd auth-server/
mvn clean package -DskipTests
```

```bash
cd trantorapp/
mvn clean package -DskipTests
```

Then execute the next command to run containers

```bash
docker-compose up --build
```