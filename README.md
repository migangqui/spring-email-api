Spring Email API (Java/Kotlin)
===================

It is an API for Java and Kotlin to send emails with Spring. To add to the project, only do this things.

### Add dependency to pom.xml

For Java:

```xml
<dependency>
	<groupId>com.github.migangqui</groupId>
	<artifactId>spring-email-api-java</artifactId>
	<version>${currentVersion}</version>
</dependency>
```

For Kotlin:

```xml
<dependency>
	<groupId>com.github.migangqui</groupId>
	<artifactId>spring-email-api-kotlin</artifactId>
	<version>${currentVersion}</version>
</dependency>
```

```${currentVersion}``` is ```1.0.0```

### Add the following properties in application.yml of the project

```yaml
spring:
  mail:
    default-encoding: UTF-8
    host: # for example: smtp.gmail.com
    username: # Your email
    password: # Your email pass
    port: # SMPT port, for exaple: 25, 587
    properties:
      mail:
        transport.protocol: smtp
# Optional properties
#        smtp:
#          ssl:
#            trust: '*'
#          auth: true
#          starttls:
#            enable: true
#            required: true
```

## Enable async

Add ```@EnableAsync``` annotation in your Spring Application class to enable async send method.

## Component scan

You must add in your component scan configuration the package ```com.github.migangqui```.