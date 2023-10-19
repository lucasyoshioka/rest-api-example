package com.lucasyoshioka.restapiexample.infra.messaging;

import com.lucasyoshioka.restapiexample.domain.DeliveryService;
import com.lucasyoshioka.restapiexample.domain.Order;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class MessageListener {

    private final DeliveryService deliveryService;

    public MessageListener(DeliveryService deliveryService) {
        this.deliveryService = deliveryService;
    }

    @RabbitListener(queues = {"${app-config.queues.order}"})
    public void receive(Order order) {
        deliveryService.createDelivery(order);
    }
}
