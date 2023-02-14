# Java, Spring boot 3, Spring Security 6 with Multiple Entry Points (JWT and Forms), Spring Data JPA, Hibernate, Tomcat 10, Oracle 18c,  Thymeleft and Bootstrap

## Desarrollo
Modifica el archivo *application.properties* de la siguiente manera
```bash
spring.profiles.active=development
```
Ejecutar los comandos para correr el proyecto en el puerto por defecto: 8080
```bash
mvn install
mvn spring-boot:run
```
## Despliegue
Modifica el archivo *application.properties* de la siguiente manera
```bash
spring.profiles.active=production
```
Genera el war ejecutando el siguiente comando en el proyecto *blog*
```bash
mvn package
```
Despliega el war en el servidor de aplicaciones.

## Contribución

Se aceptan pull requests. Para cambios mayores, por favor abra un issue primero
para discutir lo que le gustaría cambiar.

## Licencia

[MIT](https://choosealicense.com/licenses/mit/)
