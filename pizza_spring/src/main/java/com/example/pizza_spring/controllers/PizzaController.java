package com.example.pizza_spring.controllers;

import com.example.pizza_spring.models.Pizza;
import com.example.pizza_spring.repositories.PizzaOrderRepository;
import com.example.pizza_spring.repositories.PizzaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/pizza")
public class PizzaController {
    private final PizzaRepository pizzaRepository;

    @Autowired
    public PizzaController(PizzaRepository pizzaRepository) {
        this.pizzaRepository = pizzaRepository;
    }

    //Controller for managing pizza
    @GetMapping
    public List<Pizza> getAllPizzas() {
        return pizzaRepository.findAll();
    }

    @PostMapping("/newPizza")
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

    @PutMapping("/{id}")
    public Pizza updatePizza(@PathVariable UUID id, @RequestBody Pizza pizza) {
        Pizza pizzaToUpdate = pizzaRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "entity not found"));
        pizzaToUpdate.setName(pizza.getName());
        pizzaToUpdate.setPrice(pizza.getPrice());
        return pizzaRepository.save(pizza);
    }

    @DeleteMapping("/{id}")
    public void deletePizza(@PathVariable UUID id) {
        Pizza bookToDelete = pizzaRepository.findById(id)
                .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "entity not found"));
        pizzaRepository.delete(bookToDelete);
    }
}

