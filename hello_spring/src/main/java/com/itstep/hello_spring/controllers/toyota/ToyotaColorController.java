package com.itstep.hello_spring.controllers.toyota;

import com.itstep.hello_spring.models.toyota.ToyotaColor;
import com.itstep.hello_spring.repositories.toyota.ToyotaColorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/toyota/colors")
public class ToyotaColorController {
    private final ToyotaColorRepository colorRepository;

    @Autowired
    public ToyotaColorController(ToyotaColorRepository colorRepository) {
        this.colorRepository = colorRepository;
    }

    @GetMapping
    public List<ToyotaColor> getAllColors() {
        return colorRepository.findAll();
    }
    @PostMapping
    public ResponseEntity<ToyotaColor> createColor(@RequestBody ToyotaColor color) {
        ToyotaColor savedColor = colorRepository.save(color);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedColor);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteColor(@PathVariable UUID id) {
        if (!colorRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        colorRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
    @PutMapping("/{id}")
    public ResponseEntity<ToyotaColor> updateColor(@PathVariable UUID id, @RequestBody ToyotaColor updatedColor) {
        if (!colorRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        updatedColor.setId(id);
        ToyotaColor savedColor = colorRepository.save(updatedColor);
        return ResponseEntity.ok(savedColor);
    }
}
