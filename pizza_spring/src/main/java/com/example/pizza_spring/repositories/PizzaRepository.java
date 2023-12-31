package com.example.pizza_spring.repositories;

import com.example.pizza_spring.models.Pizza;
import com.example.pizza_spring.models.Topping;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface PizzaRepository extends JpaRepository<Pizza, UUID> {
    List<Pizza> findByToppingsContaining(Topping topping);
}
