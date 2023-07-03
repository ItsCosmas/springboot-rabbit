package dev.itscosmas.microservices.springrabbit.config;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@AllArgsConstructor
@Getter
public class RabbitMQConfig {

  private final ConnectionFactory connectionFactory;

  private AppConfig cfg;


  @Bean
  public RabbitAdmin rabbitAdmin() {
    return new RabbitAdmin(connectionFactory);
  }

  @Bean
  public TopicExchange notificationExchange() {
    return new TopicExchange(cfg.getNotificationExchange());
  }

  @Bean
  public Queue notificationQueue() {
    return QueueBuilder.durable(cfg.getNotificationQueue())
            .withArgument("x-dead-letter-exchange", cfg.getDeadLetterExchange())
            .withArgument("x-dead-letter-routing-key", cfg.getDeadLetterRoutingKey())
            .build();
  }

  @Bean
  public Binding notificationBinding() {
    return BindingBuilder
            .bind(notificationQueue())
            .to(notificationExchange())
            .with(cfg.getNotificationRoutingKey());
  }


  @Bean
  public Queue deadLetterQueue() {
    return QueueBuilder.durable(cfg.getDeadLetterQueue()).build();
  }

  @Bean
  public DirectExchange deadLetterExchange() {
    return new DirectExchange(cfg.getDeadLetterExchange());
  }

  @Bean
  public Binding deadLetterBinding() {
    return BindingBuilder.bind(deadLetterQueue()).to(deadLetterExchange()).with(cfg.getDeadLetterRoutingKey());
  }

  @Bean
  public AmqpTemplate amqpTemplate() {
    RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
    rabbitTemplate.setMessageConverter(jacksonConverter());
    return rabbitTemplate;
  }

  @Bean
  public SimpleRabbitListenerContainerFactory simpleRabbitListenerContainerFactory() {
    SimpleRabbitListenerContainerFactory factory =
      new SimpleRabbitListenerContainerFactory();
    factory.setConnectionFactory(connectionFactory);
    factory.setMessageConverter(jacksonConverter());
    return factory;
  }

  @Bean
  public MessageConverter jacksonConverter() {
    return new Jackson2JsonMessageConverter();
  }

}
