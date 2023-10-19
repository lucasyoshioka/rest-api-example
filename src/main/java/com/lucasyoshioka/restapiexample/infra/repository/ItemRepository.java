package com.lucasyoshioka.restapiexample.infra.repository;

import com.lucasyoshioka.restapiexample.domain.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item, Integer> {
}
