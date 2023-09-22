package com.itstep.hello_spring.models.toyota;

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
@Table(name="toyota_car_complectations")
public class ToyotaComplectation {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String name;

    @ManyToOne
    @JoinColumn(name="model_id", nullable=false)
    ToyotaModel model;

    @ManyToMany
    @JoinTable(
            name="x_complect_engines",
            joinColumns = @JoinColumn(name="complectation_id"),
            inverseJoinColumns = @JoinColumn(name="engine_id")
    )
    Set<ToyotaEngine>engines=new HashSet<>();

    @ManyToMany
    @JoinTable(
            name = "x_complect_colors",
            joinColumns = @JoinColumn( name = "complectation_id"),
            inverseJoinColumns = @JoinColumn(name = "color_id")
    )
    Set<ToyotaColor> colors = new HashSet<>();



}
