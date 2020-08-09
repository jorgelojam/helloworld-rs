# Ejemplo simple de JAX-RS (Java API for RESTful Web Services)

Toma un ejemplo basico de JAX-RS basado en el quickstar de wildfly, se agrega el perfil de microprofile con la finalidad de utilizar caracteristicas como openapi, opentracing entre otras.

Para verificacion simple:

```bash
mvn verify
```

Para compilar se incluye el plugins de docker para empaquetar todo el proyecto:

```bash
mvn clean package docker:build
```

Para correr el contenedor se debe ejecutar lo siguiente:

```bash
docker run -p 8080:8080 -p 9990:9990 --rm -it helloworld-rs
```

En una terminal distinta se puede verificar los endpoints por medio de:

```bash
curl http://localhost:8080/helloworld-rs/rest/json
curl http://localhost:8080/helloworld-rs/rest/xml
curl http://localhost:8080/helloworld-rs/rest/echo/foo
```

Para acceder a la definicion openapi puede acceder a:

```bash
curl http://localhost:8080/openapi
```

Para acceder a las metricas del servidor y de la aplicacion puede acceder a:

```bash
curl http://localhost:9990/metrics
```