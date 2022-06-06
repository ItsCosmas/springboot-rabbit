# Getting Started

## Hacking around Springboot and Rabbit MQ

**Start RabbitMQ**
```shell
docker-compose up
```
**Run App**
```shell
mvn spring-boot:run
```

**Test**
```shell
curl --location --request POST 'http://localhost:8000/api/v1/publish' \
--header 'Content-Type: application/json' \
--data-raw '{
    "fullName":"Cosmas Lorem",
    "phone":"25412345678",
    "email":"test@mail.dev"
}'
```

**Response**
```shell
{"code":200,"message":"Message Published"}
```

**stdout**
```shell
NotificationRequest(fullName=Cosmas Lorem, phone=25412345678, email=test@mail.dev)
------------------------
Publishing to internal.exchange using routingKey internal.notification.routing-key. Payload: NotificationRequest(fullName=Cosmas Lorem, phone=25412345678, email=test@mail.dev)
------------------------
------------------------
Published to internal.exchange using routingKey internal.notification.routing-key. Payload: NotificationRequest(fullName=Cosmas Lorem, phone=25412345678, email=test@mail.dev)
------------------------
------------------------
Consumed message from queue
NotificationRequest(fullName=Cosmas Lorem, phone=25412345678, email=test@mail.dev)
------------------------
```

Inspect at Rabbit Management UI
[Dashboard - http://localhost:15672/](http://localhost:15672/)

### Reference Documentation

For further reference, please consider the following sections:

* [Official Apache Maven documentation](https://maven.apache.org/guides/index.html)
* [Spring Boot Maven Plugin Reference Guide](https://docs.spring.io/spring-boot/docs/2.7.0/maven-plugin/reference/html/)
* [Create an OCI image](https://docs.spring.io/spring-boot/docs/2.7.0/maven-plugin/reference/html/#build-image)
* [Spring for RabbitMQ](https://docs.spring.io/spring-boot/docs/2.7.0/reference/htmlsingle/#messaging.amqp)
* [Spring Web](https://docs.spring.io/spring-boot/docs/2.7.0/reference/htmlsingle/#web)

### Guides

The following guides illustrate how to use some features concretely:

* [Messaging with RabbitMQ](https://spring.io/guides/gs/messaging-rabbitmq/)
* [Building a RESTful Web Service](https://spring.io/guides/gs/rest-service/)
* [Serving Web Content with Spring MVC](https://spring.io/guides/gs/serving-web-content/)
* [Building REST services with Spring](https://spring.io/guides/tutorials/bookmarks/)

