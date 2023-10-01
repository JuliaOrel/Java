package com.itstep.hello_spring.repositories;

import com.itstep.hello_spring.models.toyota.ToyotaModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ToyotaModelRepository extends JpaRepository<ToyotaModel, UUID> {
}
