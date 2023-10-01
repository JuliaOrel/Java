package com.itstep.hello_spring.controllers.toyota;

import com.itstep.hello_spring.models.toyota.ToyotaColor;
import com.itstep.hello_spring.services.toyota.ToyotaColorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/toyota/colors")
public class ToyotaColorController {
    private final ToyotaColorService colorService;

    @Autowired
    public ToyotaColorController(ToyotaColorService colorService) {
        this.colorService = colorService;
    }

    @GetMapping
    public List<ToyotaColor> getAllColors() {
        return colorService.getAllColors();
    }
    @PostMapping
    public ResponseEntity<ToyotaColor> createColor(@RequestBody ToyotaColor color) {
        ToyotaColor createdColor = colorService.createColor(color);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdColor);
    }

@PutMapping("/{id}")
    public ResponseEntity<ToyotaColor> updateColor(@PathVariable UUID id, @RequestBody ToyotaColor updatedColor) {
        Optional<ToyotaColor> color = colorService.updateColor(id, updatedColor);
        return color.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteColor(@PathVariable UUID id) {
        colorService.deleteColor(id);
        return ResponseEntity.noContent().build();
    }
}
