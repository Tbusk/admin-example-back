# admin-example-back
This project utilizes RESTful APIs using Spring Boot with JWT Tokenization implemented to secure the APIs with Spring Security.
To create the APIs, Spring Data JPA and Lombok were used.  This project was created with a React front-end in mind, but can be tweaked for various other projects
assuming the cross-origin requirements are satisfied and that all requests use a JWT token gotten from the login API.  

Add the following to your application.properties:
```Java
# Initialization
spring.jpa.defer-datasource-initialization = true
spring.jpa.generate-ddl=true
spring.jpa.hibernate.ddl-auto=none
# spring.jpa.show-sql=true
server.servlet.context-path=/

# Database connection settings
spring.datasource.url=jdbc:mariadb://localhost:3306/finalproject
spring.datasource.username=
spring.datasource.password=
spring.datasource.driver-class-name=org.mariadb.jdbc.Driver

# JWT Token Auth
app.auth.secret=
```
Additionally, to create the required database and tables used, use the create statements located in the backup in the file finalProject.sql.
