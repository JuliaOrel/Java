package com.example.hello_fractions_spring.controllers;

import com.example.hello_fractions_spring.models.Fraction;
import com.example.hello_fractions_spring.models.FractionRequest;
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
}
