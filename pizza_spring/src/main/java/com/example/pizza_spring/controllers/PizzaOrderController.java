package com.example.pizza_spring.controllers;

import com.example.pizza_spring.models.Pizza;
import com.example.pizza_spring.models.PizzaOrder;
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
@RequestMapping("/api/order")
public class PizzaOrderController {
    private final PizzaOrderRepository pizzaOrderRepository;
    private final PizzaRepository pizzaRepository;
    @Autowired
    public PizzaOrderController(PizzaOrderRepository pizzaOrderRepository, PizzaRepository pizzaRepository){
        this.pizzaOrderRepository=pizzaOrderRepository;
        this.pizzaRepository=pizzaRepository;
    }
    @GetMapping("/orders")
    public List<PizzaOrder> getAllOrders() {
        return pizzaOrderRepository.findAll();
    }

    //для заполнения данных о заказчике - заказ таблицу pizza_orders
    @PostMapping("/orders")
    public ResponseEntity<PizzaOrder> createOrder(@RequestBody PizzaOrder order) {
        PizzaOrder savedOrder = pizzaOrderRepository.save(order);
        return new ResponseEntity<>(savedOrder, HttpStatus.CREATED);
    }

//    @PostMapping("/orders/{orderId}/addPizza")
//    public ResponseEntity<PizzaOrder> addPizzaToOrder(@PathVariable UUID orderId, @RequestBody Pizza pizzaToAdd) {
//        Optional<PizzaOrder> optionalOrder = pizzaOrderRepository.findById(orderId);
//
//        if (optionalOrder.isPresent()) {
//            PizzaOrder order = optionalOrder.get();
//            order.getSelectedPizzas().add(pizzaToAdd);
//            PizzaOrder updatedOrder = pizzaOrderRepository.save(order);
//            return new ResponseEntity<>(updatedOrder, HttpStatus.OK);
//        } else {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//    }

    //Это для того, чтобы скоординировать заказ и пиццу - добавляется запись в таблицу pizza_order_pizzas
    @PostMapping("/orders/{orderId}/addPizza/{pizzaId}")
    public ResponseEntity<PizzaOrder> addPizzaToOrder(@PathVariable UUID orderId, @PathVariable UUID pizzaId) {
        Optional<PizzaOrder> optionalOrder = pizzaOrderRepository.findById(orderId);
        Optional<Pizza> optionalPizza = pizzaRepository.findById(pizzaId);

        if (optionalOrder.isPresent() && optionalPizza.isPresent()) {
            PizzaOrder order = optionalOrder.get();
            Pizza pizza = optionalPizza.get();

            order.getSelectedPizzas().add(pizza);
            PizzaOrder updatedOrder = pizzaOrderRepository.save(order);
            return new ResponseEntity<>(updatedOrder, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
