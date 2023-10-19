package com.lucasyoshioka.restapiexample.infra.repository;

import com.lucasyoshioka.restapiexample.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {
}
