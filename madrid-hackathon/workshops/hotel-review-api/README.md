# Hotel Review Template Application

> Working example of SpringBoot + Feign already configured to connect to the hackathon portal's Hotel Review Analyzer Service.

## Details

This template contains a Maven project object model file which includes all required dependencies to create a simple [SpringBoot](https://projects.spring.io/spring-boot/) application that interfaces with the Hotel Review Analysis service. The interface is done using [Feign](https://github.com/OpenFeign/feign) (a simple mapping tool to REST endpoints -- [integrated to SpringBoot](http://projects.spring.io/spring-cloud/spring-cloud.html#spring-cloud-feign)).

You also get the Swagger integration automatically, you simply need to provide the right annotations in your classes.


## Technology Stack

- Java 1.8
- Maven 3.3.X
- Spring Boot Application (v1.5.2)
- Integrated Tomcat Web Server
- Feign/Ribbon/Hystrix for REST endpoint mapping
- Swagger 2.0 UI

# Usage

## 1. Clone the repository

```bash
git clone https://github.com/ExpediaInc/exp-connectivity-util.git
cd exp-connectivity-util/madrid-hackathon/workshops/hotel-review-api
```

## 2. Authentication parameters

**Make sure to enter your Hackathon username/password in the first lines of the application.yml file.**

```yaml
# Hackathon Portal Authentication
hackathon:
  username: PLEASE_REPLACE_ME
  password: PLEASE_REPLACE_ME
```
```bash
vi src/main/resources/application.yml
```

## 3. Build

```bash
mvn clean package
```

## 4. Start locally

```bash
mvn -Dapplication.home=. spring-boot:run
```
_this command will start the application using the application.yml file_

## 5. Test it out !

You can simply go to the generated Swagger-UI page here: [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)

Or use the CURL command:

```bash
curl -X POST --header 'Content-Type: application/json' --header 'Accept: application/json' -d 'I love this hotel.' 'http://localhost:8080/service/dosomething'
```
