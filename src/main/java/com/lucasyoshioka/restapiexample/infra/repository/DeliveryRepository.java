package com.lucasyoshioka.restapiexample.infra.repository;

import com.lucasyoshioka.restapiexample.domain.Delivery;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeliveryRepository extends JpaRepository<Delivery, Integer> {
}
