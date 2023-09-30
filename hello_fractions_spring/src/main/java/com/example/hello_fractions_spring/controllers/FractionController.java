package com.example.hello_fractions_spring.controllers;

import com.example.hello_fractions_spring.models.Fraction;
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
}
