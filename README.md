Spring Email API
===================

It is an API to send email with Spring. To add to the project, only do this things.

### Add dependency to pom.xml

```xml
<dependency>
	<groupId>com.github.migangqui</groupId>
	<artifactId>spring-email-api-java</artifactId>
	<version>${currentVersion}</version>
</dependency>
```

```${currentVersion}``` is ```1.0.0```

### Add the following properties in application.yml of the project

```yaml
spring:
    mail:
        default-encoding: UTF-8
        host: #HOST
        username: #USERNAME
        password: #PASS
        port: 587
        properties:
         mail:
          transport.protocol: smtp
          smtp:
            ssl:
              trust: '*'
            auth: true
            starttls:
              enable: true
              required: true
```

## Enable async

Add ```@EnableAsync``` annotation in your Spring Application class to enable async send method.

## Component scan

You must add in your component scan configuration the package ```com.github.migangqui```.