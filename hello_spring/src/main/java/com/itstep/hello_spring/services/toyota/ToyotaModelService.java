package com.itstep.hello_spring.services.toyota;

import com.itstep.hello_spring.models.toyota.ToyotaColor;
import com.itstep.hello_spring.models.toyota.ToyotaModel;
import com.itstep.hello_spring.repositories.toyota.ToyotaColorRepository;
import com.itstep.hello_spring.repositories.toyota.ToyotaModelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ToyotaModelService {
    private final ToyotaModelRepository modelRepository;

    @Autowired
    public ToyotaModelService(ToyotaModelRepository modelRepository) {
        this.modelRepository = modelRepository;
    }

    public List<ToyotaModel> getAllModels() {
        return modelRepository.findAll();
    }

    public ToyotaModel createModel(ToyotaModel model) {
        return modelRepository.save(model);
    }

    public Optional<ToyotaModel> updateModel(UUID id, ToyotaModel updatedModel) {
        if (modelRepository.existsById(id)) {
            updatedModel.setId(id);
            return Optional.of(modelRepository.save(updatedModel));
        }
        return Optional.empty();
    }
    public void deleteModel(UUID id) {
        modelRepository.deleteById(id);
    }
}
