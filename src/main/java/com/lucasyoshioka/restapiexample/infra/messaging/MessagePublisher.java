package com.lucasyoshioka.restapiexample.infra.messaging;

import com.lucasyoshioka.restapiexample.domain.Order;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MessagePublisher {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private Queue queue;

    public void send(Order order) {
        rabbitTemplate.convertAndSend(this.queue.getName(), order);
    }

}
