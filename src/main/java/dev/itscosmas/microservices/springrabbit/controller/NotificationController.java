package dev.itscosmas.microservices.springrabbit.controller;

import dev.itscosmas.microservices.springrabbit.config.AppConfig;
import dev.itscosmas.microservices.springrabbit.config.RabbitMQConfig;
import dev.itscosmas.microservices.springrabbit.producer.RabbitProducer;
import dev.itscosmas.microservices.springrabbit.schema.BaseResponse;
import dev.itscosmas.microservices.springrabbit.schema.NotificationRequest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/v1/")
public class NotificationController {

  HttpHeaders responseHeaders;

  BaseResponse brs = new BaseResponse();

  final RabbitProducer rabbitProducer;
  final AppConfig cfg;

  public NotificationController(RabbitProducer rabbitProducer, AppConfig config) {
    responseHeaders = new HttpHeaders();
    responseHeaders.set("X-Content-Type-Options", "nosniff");
    responseHeaders.set("X-Frame-Options", "deny");
    responseHeaders.set("Content-Security-Policy", "default-src 'none'");

    this.rabbitProducer = rabbitProducer;
    this.cfg = config;
  }
  @PostMapping("/publish")
  public ResponseEntity<BaseResponse> sendNotification(@Validated @RequestBody NotificationRequest notificationRequest){
    // Send Message Here
    brs.withCode(HttpStatus.OK.value());
    brs.withMessage("Message Published");

    System.out.println(notificationRequest);

    rabbitProducer.publish(notificationRequest, cfg.getNotificationExchange(), cfg.getNotificationRoutingKey());

    return ResponseEntity.ok().headers(responseHeaders).body(brs);
  }
}
