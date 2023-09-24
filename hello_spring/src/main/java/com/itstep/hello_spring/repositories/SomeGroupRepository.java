package com.itstep.hello_spring.repositories;

import com.itstep.hello_spring.models.relationship.one_many.SomeGroup;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface SomeGroupRepository extends JpaRepository<SomeGroup, UUID> {
}
