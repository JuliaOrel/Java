package com.itstep.hello_spring.models.shop;

import com.itstep.hello_spring.models.toyota.ToyotaColor;
import com.itstep.hello_spring.models.toyota.ToyotaComplectation;
import com.itstep.hello_spring.models.toyota.ToyotaEngine;
import com.itstep.hello_spring.models.toyota.ToyotaModel;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name="shop_order_items")
public class OrderItems {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;

    @ManyToOne
    @JoinColumn (name = "model_id")
    private ToyotaModel model;

    @ManyToOne
    @JoinColumn (name = "complect_id")
    private ToyotaComplectation completion;

    @ManyToOne
    @JoinColumn (name = "engine_id")
    private ToyotaEngine engine;

    @ManyToOne
    @JoinColumn (name = "color_id")
    private ToyotaColor color;
}
