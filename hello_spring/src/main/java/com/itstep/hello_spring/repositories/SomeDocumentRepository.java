package com.itstep.hello_spring.repositories;

import com.itstep.hello_spring.models.relationship.one_many.SomeDocument;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface SomeDocumentRepository extends JpaRepository<SomeDocument, UUID> {
//    @Query("SELECT d FROM SomeDocument d WHERE d.person.id = :person_id")
//    HashSet<SomeDocument> findDocumentsByPersonId(@Param("person_id") UUID person_id);
}
