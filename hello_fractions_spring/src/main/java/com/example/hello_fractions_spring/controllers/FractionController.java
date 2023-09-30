package com.example.hello_fractions_spring.controllers;

import com.example.hello_fractions_spring.models.Fraction;
import com.example.hello_fractions_spring.models.FractionRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/fractions")
public class FractionController {
    @GetMapping("/isProper")
    public boolean isFractionProper(@RequestParam int numerator, @RequestParam int denominator) {
        Fraction fraction = new Fraction(numerator, denominator);
        return fraction.isProper();
    }

    @PostMapping("/reduce")
    public Fraction reduceFraction(@RequestBody Fraction fraction) {
        fraction.reduce();
        return fraction;
    }

    @PostMapping("/add")
    public Fraction addFractions(@RequestBody FractionRequest request) {
        Fraction fraction1 = request.getFraction1();
        Fraction fraction2 = request.getFraction2();
        fraction1.add(fraction2);
        return fraction1;
    }

    @PostMapping("/subtract")
    public Fraction subtractFractions(@RequestBody FractionRequest request) {
        Fraction fraction1 = request.getFraction1();
        Fraction fraction2 = request.getFraction2();

        fraction1.subtract(fraction2);

        return fraction1;
    }

    @PostMapping("/multiply")
    public Fraction multiplyFractions(@RequestBody FractionRequest request) {
        Fraction fraction1 = request.getFraction1();
        Fraction fraction2 = request.getFraction2();

        fraction1.multiply(fraction2);

        return fraction1;
    }

    @PostMapping("/divide")
    public ResponseEntity<?> divideFractions(@RequestBody FractionRequest request) {
        Fraction fraction1 = request.getFraction1();
        Fraction fraction2 = request.getFraction2();
        try {
            fraction1.divide(fraction2);
            return ResponseEntity.ok(fraction1);
        } catch (ArithmeticException e) {
            // Обработка исключения при делении на ноль
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Error: Division by zero is not allowed.");
        }
    }
}
