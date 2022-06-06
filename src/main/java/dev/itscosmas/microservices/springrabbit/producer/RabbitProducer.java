package dev.itscosmas.microservices.springrabbit.producer;

import lombok.AllArgsConstructor;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class RabbitProducer {
  private final AmqpTemplate amqpTemplate;

  public void publish(Object payload, String exchange, String routingKey) {
    System.out.println("------------------------");
    System.out.printf("Publishing to %s using routingKey %s. Payload: %s \n", exchange, routingKey, payload);
    System.out.println("------------------------");
    amqpTemplate.convertAndSend(exchange, routingKey, payload);
    System.out.println("------------------------");
    System.out.printf("Published to %s using routingKey %s. Payload: %s \n", exchange, routingKey, payload);
    System.out.println("------------------------");

  }
}
