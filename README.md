# spring-boot-43716
Minimal reproducible example for Spring Boot issue #43716

## Minimal reproducible example

Build the image:

```shell
./mvnw clean spring-boot:build-image
```

This will create a distroless-like image `kafka-streams-joiner:0.0.1-SNAPSHOT`

Start kafka:

```shell
docker compose up kafka -d
```

Start the java container:

```shell
docker compose up joiner
```

It will fail, because C++ libraries are not present:

```log
java.lang.UnsatisfiedLinkError: /tmp/librocksdbjni613869755614965871.so: libstdc++.so.6: cannot open shared object file: No such file or directory
```

Clean up running containers:

```shell
docker compose down
```
