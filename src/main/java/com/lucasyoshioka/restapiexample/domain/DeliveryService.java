package com.lucasyoshioka.restapiexample.domain;

import com.lucasyoshioka.restapiexample.infra.repository.DeliveryRepository;
import org.springframework.stereotype.Service;

@Service
public class DeliveryService {

    private final DeliveryRepository deliveryRepository;

    public DeliveryService(DeliveryRepository deliveryRepository) {
        this.deliveryRepository = deliveryRepository;
    }

    public void createDelivery(Order order) {
        deliveryRepository.save(Delivery.from(order));
    }

}
