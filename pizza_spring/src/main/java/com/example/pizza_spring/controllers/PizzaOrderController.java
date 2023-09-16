package com.example.pizza_spring.controllers;

import com.example.pizza_spring.models.Pizza;
import com.example.pizza_spring.repositories.PizzaOrderRepository;
import com.example.pizza_spring.repositories.PizzaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/pizza")
public class PizzaOrderController {
    private final PizzaRepository pizzaRepository;
    private final PizzaOrderRepository pizzaOrderRepository;
    @Autowired
    public PizzaOrderController(PizzaRepository pizzaRepository, PizzaOrderRepository orderRepository) {
        this.pizzaRepository = pizzaRepository;
        this.pizzaOrderRepository = orderRepository;
    }

    //Controller for managing pizza
    @GetMapping
    public List<Pizza> getAllPizzas() {
        return pizzaRepository.findAll();
    }
    @PostMapping("/pizzas")
    public ResponseEntity<Pizza> createPizza(@RequestBody Pizza pizza) {
        Pizza savedPizza = pizzaRepository.save(pizza);
        return new ResponseEntity<>(savedPizza, HttpStatus.CREATED);
    }

    @GetMapping("/pizzas/{pizzaId}")
    public ResponseEntity<Pizza> getPizzaById(@PathVariable UUID pizzaId) {
        Optional<Pizza> pizza = pizzaRepository.findById(pizzaId);
        return pizza.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}
