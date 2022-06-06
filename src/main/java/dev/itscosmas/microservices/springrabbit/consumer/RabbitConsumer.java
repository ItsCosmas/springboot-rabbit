package dev.itscosmas.microservices.springrabbit.consumer;

import dev.itscosmas.microservices.springrabbit.schema.NotificationRequest;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;


@Component
public class RabbitConsumer {
  @RabbitListener(queues = "${rabbitmq.queues.notification}")
  public void consumer(NotificationRequest notificationRequest) {
    System.out.println("------------------------");
    System.out.println("Consumed message from queue\n" + notificationRequest);
    System.out.println("------------------------");

  }
}
