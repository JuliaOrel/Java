package com.itstep.hello_spring.models.relationship.one_many;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name="some_documents")
public class SomeDocument {
    @Id
    @GeneratedValue(strategy= GenerationType.UUID)
    private UUID id;
    private String name;

    @Transient
    @JsonIgnore
    private UUID person_id;

    @ManyToOne
    @JoinColumn(name="person_id", nullable = false)
    private SomePerson person;
}
