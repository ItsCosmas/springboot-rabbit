package dev.itscosmas.microservices.springrabbit.consumer;


import dev.itscosmas.microservices.springrabbit.config.AppConfig;
import dev.itscosmas.microservices.springrabbit.schema.NotificationRequest;
import lombok.AllArgsConstructor;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;



@Component
@AllArgsConstructor
public class RabbitConsumer {
    private final AmqpTemplate amqpTemplate;
    private AppConfig appConfig;
  @RabbitListener(queues = "${app.mq.notification.queue}")
  public void consumer(NotificationRequest notificationRequest) {

      System.out.println("------------------------");
      System.out.println("Consumed message from queue\n" + notificationRequest);
      System.out.println("------------------------");

      try {
        // Do Something here
        throw new IllegalArgumentException();
      }catch (Exception e){
          System.out.println("An error occurred");
          // Publish the message to the dead letter queue
          amqpTemplate.convertAndSend(appConfig.getDeadLetterExchange(),appConfig.getDeadLetterRoutingKey(), notificationRequest);
      }
  }
}
