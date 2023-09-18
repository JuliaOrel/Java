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

    //для заполнения данных о заказчике - заказ в таблицу pizza_orders
    @PostMapping("/orders")
    public ResponseEntity<PizzaOrder> createOrder(@RequestBody PizzaOrder order) {
        PizzaOrder savedOrder = pizzaOrderRepository.save(order);
        return new ResponseEntity<>(savedOrder, HttpStatus.CREATED);
    }

    //Это для того, чтобы объединить заказ и пиццу - добавляется запись в таблицу pizza_order_pizzas
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
    //Удаляет из pizza_orders and pizza_order_pizzas
    @DeleteMapping("/orders/{orderId}")
    public ResponseEntity<Void> deleteOrder(@PathVariable UUID orderId) {
        if (!pizzaOrderRepository.existsById(orderId)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        pizzaOrderRepository.deleteById(orderId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    //Нужно отправлять запрос типа для обновления данных
    //{"id": "46ca558f-b4b4-403f-920f-ac0598a6fdf0",
    //        "selectedPizzas": [
    //            {
    //                "id": "9ab713fa-8460-4694-8ae2-0345a41e4d6b",
    //                "name": "Haw",
    //                "price": 144.35,
    //                "toppings": []
    //            }
    //        ],
    //        "customer_name": "Old man",
    //        "phone_number": "0934561236",
    //        "email": "george@gmail.com",
    //        "delivery_address": "self"
    //    }
    //тогда меняется
    @PutMapping("/orders/{orderId}")
    public ResponseEntity<PizzaOrder> updateOrder(@PathVariable UUID orderId, @RequestBody PizzaOrder updatedOrder) {
        if (!pizzaOrderRepository.existsById(orderId)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        updatedOrder.setId(orderId);
        PizzaOrder savedOrder = pizzaOrderRepository.save(updatedOrder);
        return new ResponseEntity<>(savedOrder, HttpStatus.OK);
    }
}
