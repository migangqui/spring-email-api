Spring Email API (Java/Kotlin)
==============================

![GitHub last commit](https://img.shields.io/github/last-commit/migangqui/spring-email-api?style=for-the-badge)
![Maven Central](https://img.shields.io/maven-central/v/com.github.migangqui/spring-email-api-java?style=for-the-badge)


It is an API for Java and Kotlin to send emails with Spring. To add to your project...

### Add dependency to Maven or Gradle:

For Java:

```xml
<dependency>
	<groupId>com.github.migangqui</groupId>
	<artifactId>spring-email-api-java</artifactId>
	<version>${currentVersion}</version>
</dependency>
```
```groovy
implementation 'com.github.migangqui:spring-email-api-java:${currentVersion}'
```

For Kotlin:

```xml
<dependency>
	<groupId>com.github.migangqui</groupId>
	<artifactId>spring-email-api-kotlin</artifactId>
	<version>${currentVersion}</version>
</dependency>
```
```groovy
implementation 'com.github.migangqui:spring-email-api-kotlin:${currentVersion}'
```

```${currentVersion}``` is ```1.2.0```

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

It's not necessary add the package to component scan with this new version.

## How to use

You have to inject ```EmailSender``` as dependency in your Spring component. The service provide these methods:

##### Java
```java
public interface EmailSender {
    SendEmailResult send(Email email);
        
    Future<SendEmailResult> sendAsync(Email email);
}
```
##### Kotlin
```kotlin
interface EmailSender {
    fun send(email: Email): SendEmailResult

    fun sendAsync(email: Email): Future<SendEmailResult>
}
```

