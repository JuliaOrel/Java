package com.itstep.hello_spring.controllers.toyota;

import com.itstep.hello_spring.models.toyota.ToyotaColor;
import com.itstep.hello_spring.models.toyota.ToyotaModel;
import com.itstep.hello_spring.repositories.toyota.ToyotaModelRepository;
import com.itstep.hello_spring.services.toyota.ToyotaModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/toyota/models")
public class ToyotaModelController {
    private final ToyotaModelService modelService;

    @Autowired
    public ToyotaModelController(ToyotaModelService modelService) {
        this.modelService = modelService;
    }

    @GetMapping
    public List<ToyotaModel> getAllModels() {
        return modelService.getAllModels();
    }

    @PostMapping
    public ResponseEntity<ToyotaModel> createModel(@RequestBody ToyotaModel model) {
        ToyotaModel savedModel = modelService.createModel(model);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedModel);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteModel(@PathVariable UUID id) {
        modelService.deleteModel(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<ToyotaModel> updateModel(@PathVariable UUID id, @RequestBody ToyotaModel updatedModel) {
        Optional<ToyotaModel> model = modelService.updateModel(id, updatedModel);
        return model.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
