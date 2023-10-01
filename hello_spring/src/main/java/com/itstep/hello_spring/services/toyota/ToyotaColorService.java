package com.itstep.hello_spring.services.toyota;

import com.itstep.hello_spring.models.toyota.ToyotaColor;
import com.itstep.hello_spring.repositories.toyota.ToyotaColorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ToyotaColorService {

    private final ToyotaColorRepository colorRepository;
    @Autowired
    public ToyotaColorService(ToyotaColorRepository colorRepository) {
        this.colorRepository = colorRepository;
    }

    public List<ToyotaColor> getAllColors() {
        return colorRepository.findAll();
    }

    public ToyotaColor createColor(ToyotaColor color) {
        return colorRepository.save(color);
    }

    public Optional<ToyotaColor> updateColor(UUID id, ToyotaColor updatedColor) {
        if (colorRepository.existsById(id)) {
            updatedColor.setId(id);
            return Optional.of(colorRepository.save(updatedColor));
        }
        return Optional.empty();
    }
    public void deleteColor(UUID id) {
        colorRepository.deleteById(id);
    }
}
