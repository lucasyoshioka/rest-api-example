package com.lucasyoshioka.restapiexample.infra.repository;

import com.lucasyoshioka.restapiexample.domain.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Integer> {
}
