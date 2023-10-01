package com.itstep.hello_spring.repositories.toyota;

import com.itstep.hello_spring.models.toyota.ToyotaColor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ToyotaColorRepository extends JpaRepository<ToyotaColor, UUID> {
}
