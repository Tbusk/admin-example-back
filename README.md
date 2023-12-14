# admin-example-back
This project utilizes RESTful APIs using Spring Boot with JWT Tokenization implemented to secure the APIs with Spring Security.
To create the APIs, Spring Data JPA and Lombok were used.  This project was created with a React front-end in mind, but can be tweaked for various other projects
assuming the cross-origin requirements are satisfied and that all requests use a JWT token gotten from the login API.  

A tutorial that was followed for setting up JWT Tokenization is available here: https://medium.com/spring-boot/spring-boot-3-spring-security-6-jwt-authentication-authorization-98702d6313a5. 
Some updates were made to make the tokenization work with the latest spring security and spring boot version as well as simplification done.  

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
