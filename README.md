# admin-example-back
Spring Boot backend with Spring Security and JWT tokenization for crud APIs for an admin panel.

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
