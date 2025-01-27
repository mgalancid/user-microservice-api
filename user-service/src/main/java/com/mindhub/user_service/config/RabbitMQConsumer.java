package com.mindhub.user_service.config;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class RabbitMQConsumer {

    @RabbitListener(queues = "testQueue")
    public void consumeMessage(String message) {
        System.out.println("Message received: " + message);
    }
}
