package org.example.entities.files;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

@Data
public class Human implements Serializable {
    private String name;
    private int age;
    private boolean isMarried;
    @JsonCreator
    public Human(
            @JsonProperty("name") String name,
            @JsonProperty("age") int age,
            @JsonProperty("isMarried") boolean isMarried
    )
    {
        this.name=name;
        this.age=age;
        this.isMarried=isMarried;

    }
}
