package com.itstep.hello_spring.models.relationship.one_many;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name="some_persons")
public class SomePerson {
    @Id
    @GeneratedValue(strategy= GenerationType.UUID)
    private UUID id;
    private String name;

    @OneToMany(mappedBy="person")
    private Set<SomeDocument> documents=new HashSet<>();
}

