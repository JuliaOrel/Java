package com.itstep.hello_spring.controllers.toyota;

import com.itstep.hello_spring.models.toyota.ToyotaModel;
import com.itstep.hello_spring.repositories.toyota.ToyotaModelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/toyota/models")
public class ToyotaModelController {
    private final ToyotaModelRepository modelRepository;

    @Autowired
    public ToyotaModelController(ToyotaModelRepository modelRepository) {
        this.modelRepository = modelRepository;
    }

    @GetMapping
    public List<ToyotaModel> getAllModels() {
        return modelRepository.findAll();
    }

    @PostMapping
    public ResponseEntity<ToyotaModel> createModel(@RequestBody ToyotaModel model) {
        ToyotaModel savedModel = modelRepository.save(model);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedModel);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteModel(@PathVariable UUID id) {
        if (!modelRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        modelRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<ToyotaModel> updateModel(@PathVariable UUID id, @RequestBody ToyotaModel updatedModel) {
        if (!modelRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        updatedModel.setId(id);
        ToyotaModel savedModel = modelRepository.save(updatedModel);
        return ResponseEntity.ok(savedModel);
    }
}
