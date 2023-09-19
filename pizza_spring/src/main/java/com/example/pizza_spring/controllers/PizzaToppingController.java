package com.example.pizza_spring.controllers;

import com.example.pizza_spring.models.Pizza;
import com.example.pizza_spring.models.PizzaOrder;
import com.example.pizza_spring.models.Topping;
import com.example.pizza_spring.repositories.PizzaRepository;
import com.example.pizza_spring.repositories.ToppingRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/topping")
public class PizzaToppingController {

    private final PizzaRepository pizzaRepository;
    private final ToppingRepository toppingRepository;

    public PizzaToppingController(PizzaRepository pizzaRepository, ToppingRepository toppingRepository){
        this.pizzaRepository=pizzaRepository;
        this.toppingRepository=toppingRepository;
    }

    //Показывает все топпинги  конкретной пиццы
    @GetMapping("/{pizzaId}/toppings")
    public ResponseEntity<List<Topping>> getToppingsForPizza(@PathVariable UUID pizzaId) {
        Optional<Pizza> pizza = pizzaRepository.findById(pizzaId);
        if (pizza.isPresent()) {
            return new ResponseEntity<>(pizza.get().getToppings(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    //Показывает все топпинги
    @GetMapping
    public List<Topping> getAllToppings() {
        return toppingRepository.findAll();
    }

    //Add new topping
    @PostMapping("/toppings")
    public ResponseEntity<Topping> createTopping(@RequestBody Topping topping) {
        Topping savedOrder = toppingRepository.save(topping);
        return new ResponseEntity<>(savedOrder, HttpStatus.CREATED);
    }

    //add new topping to pizza"
    @PostMapping("/toppings/{pizzaId}/addTopping/{toppingId}")
    public ResponseEntity<Pizza> addToppingToPizza(@PathVariable UUID toppingId, @PathVariable UUID pizzaId) {
        Optional<Topping> optionalTopping = toppingRepository.findById(toppingId);
        Optional<Pizza> optionalPizza = pizzaRepository.findById(pizzaId);

        if (optionalTopping.isPresent() && optionalPizza.isPresent()) {
            Topping topping = optionalTopping.get();
            Pizza pizza = optionalPizza.get();

            pizza.getToppings().add(topping);
            Pizza updatedPizza = pizzaRepository.save(pizza);
            return new ResponseEntity<>(updatedPizza, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    //Удаляет из topping and pizza_topping
    @DeleteMapping("/toppings/{toppingId}")
    public ResponseEntity<Void> deleteTopping(@PathVariable UUID toppingId) {
        try {
            Optional<Topping> optionalTopping = toppingRepository.findById(toppingId);

            if (optionalTopping.isPresent()) {
                Topping topping = optionalTopping.get();

                // Находим все Pizza, связанные с Topping
                List<Pizza> pizzasWithTopping = pizzaRepository.findByToppingsContaining(topping);

                // Удаляем связи Topping с Pizza
                for (Pizza pizza : pizzasWithTopping) {
                    pizza.getToppings().remove(topping);
                    pizzaRepository.save(pizza);
                }

                // Теперь можно удалить сам Topping
                toppingRepository.deleteById(toppingId);

                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            // Обработка исключения
            e.printStackTrace(); // Вывод информации об ошибке в консоль (в реальном приложении лучше использовать логгирование)
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/toppings/{toppingId}") //вносит изменения только в данные topping
    public ResponseEntity<Topping> updateOrder(@PathVariable UUID toppingId, @RequestBody Topping updatedTopping) {
        if (!toppingRepository.existsById(toppingId)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        updatedTopping.setId(toppingId);
        Topping savedTopping = toppingRepository.save(updatedTopping);
        return new ResponseEntity<>(savedTopping, HttpStatus.OK);
    }
}
