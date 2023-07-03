package dev.itscosmas.microservices.springrabbit.config;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class AppConfig {

    @Value("${app.mq.notification.exchange}")
    private String notificationExchange;

    @Value("${app.mq.notification.queue}")
    private String notificationQueue;

    @Value("${app.mq.notification.routing-key}")
    private String notificationRoutingKey;

    @Value("${app.mq.deadLetter.exchange}")
    private String deadLetterExchange;

    @Value("${app.mq.deadLetter.queue}")
    private String deadLetterQueue;

    @Value("${app.mq.deadLetter.routing-key}")
    private String deadLetterRoutingKey;
}
