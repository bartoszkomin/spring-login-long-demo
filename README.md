# spring-login-long-demo
Spring security - Example project how password encryption strength make login operation very slow

This is example project for post on blog [bartoszkomin.blogspot.com][1], please go there to find more informations.

To run project you need:
- maven
- java 8

You don't need to install any database, it works with embeded "in memory" HyperSQL Database. It contains also embeded tomcat server.

To run project follow steps:
- mvn install
- on project folder run: java -jar target/spring-login-1.0.0.jar
- use your favorite application to test API endpoints (for example: Advanced REST client in chrome, Postman, or simple curl command) for example: GET http://localhost:8080/insert

You can use this curl commands to test endpoints:

- To insert the user/password record to database

	    curl -i -H "Content-Type: application/json"  http://127.0.0.1:8080/insert

- To open secret resource without authentication

	    curl -i -H "Content-Type: application/json"  http://127.0.0.1:8080/secret

- To open secret resource with basic authentication

	    curl -i -H "Content-Type: application/json" -H "Authorization: Basic dXNlcjpwYXNzd29yZA=="  http://127.0.0.1:8080/secret

[1]: http://bartoszkomin.blogspot.com/2017/01/spring-security-takes-long-time-to-login.html   	