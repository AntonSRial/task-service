### Servicio task-Service
Servicio de ejemplo utilizando arquitectura hexagonal.

## Expone el servicio REST.
```
mvn clean install
mvn -pl infrastructure\launcher\ spring-boot:run 
```

## Levantar MongoDB (Docker)
docker run -d --name mongodb -p 27017:27017 mongo


Se sube un postman con los ejemplos de consumo del endpoint.

