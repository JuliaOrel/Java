package com.example.hello_fractions_spring.controllers;

import com.example.hello_fractions_spring.models.Fraction;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/fractions")
public class FractionController {
    @GetMapping("/isProper")
    public boolean isFractionProper(@RequestParam int numerator, @RequestParam int denominator) {
        Fraction fraction = new Fraction(numerator, denominator);
        return fraction.isProper();
    }
}
