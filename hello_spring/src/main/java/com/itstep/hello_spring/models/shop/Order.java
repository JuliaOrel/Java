package com.itstep.hello_spring.models.shop;

import com.itstep.hello_spring.models.toyota.ToyotaColor;
import com.itstep.hello_spring.models.toyota.ToyotaComplectation;
import com.itstep.hello_spring.models.toyota.ToyotaEngine;
import com.itstep.hello_spring.models.toyota.ToyotaModel;
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
@Table(name="shop_orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String name;

    @OneToMany(mappedBy = "order")
    Set<OrderItems> items = new HashSet<>();
}
