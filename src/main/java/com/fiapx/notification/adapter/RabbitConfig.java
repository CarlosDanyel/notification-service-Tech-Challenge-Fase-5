package com.fiapx.notification.adapter;
import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
@Configuration
public class RabbitConfig {
    @Bean DirectExchange deadExchange() { return new DirectExchange("fiapx.dead", true, false); }
    @Bean Queue deadNotifications() { return QueueBuilder.durable("video.notifications.dead").build(); }
    @Bean Binding deadBinding() { return BindingBuilder.bind(deadNotifications()).to(deadExchange()).with("video.notifications"); }
    @Bean DirectExchange exchange() { return new DirectExchange("fiapx.events", true, false); }
    @Bean Queue notifications() { return QueueBuilder.durable("video.notifications").deadLetterExchange("fiapx.dead").deadLetterRoutingKey("video.notifications").build(); }
    @Bean Binding binding() {
        return BindingBuilder.bind(notifications()).to(exchange()).with("video.failed");
    }
}
