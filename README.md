## Demo Application using spring-boot + vaadin 8

A simple full-stack-starter for using spring-boot with vaadin

- spring-boot 1.5.4.RELEASE 
- spring-boot-security
- vaadin 8

## Configuration and run 

Default admin access:
```
user: admin
password: 123456
```

Default user access:
```
user: user
password: 123456
```

Database configuration
```
Using default schema of postgres in localhost - with user and password is postgres/postgres
```
But you can change this on _*applications.properties*_ in _resources_ folder

For run, just type
```
mvn spring-boot:run
```
