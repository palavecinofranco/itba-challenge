## API REST Gestión Estudiantes.   
### Permite crear, obtener, actualizar y eliminar registros de estudiantes mediante endpoints REST.

#### Instrucciones para compilar y ejecutar:

Localmente (necesario jdk 21 o superior)  
`./mvnw clean install`  
`./mvnw spring-boot:run`

Usando Docker Compose  
`docker-compose up --build`

Ejemplos de uso (curl)  

##### GET ALL  
`curl -X GET http://localhost:8080/api/students`

##### GET BY ID  
`curl -X GET http://localhost:8080/api/students/1`

##### CREATE  
`curl -X POST http://localhost:8080/api/students/create \
-H "Content-Type: application/json" \
-d '{"name":"Usuario", "lastname":"Prueba", "email":"usuario.prueba@mail.com", "dni": "44451589", "address":"Calle Falsa 123"}'`

##### UPDATE  
`curl -X PUT http://localhost:8080/api/students/update/1 \-H "Content-Type: application/json" \
-d '{"name":"Franco", "lastname":"Prueba", "email":"franco.prueba@mail.com", "dni": "44451589", "address":"San Justo"}'`

##### DELETE  
`curl -X DELETE http://localhost:8080/api/students/delete/1`

Para Swagger UI, acceder a:
`http://localhost:8080/swagger-ui.html`

