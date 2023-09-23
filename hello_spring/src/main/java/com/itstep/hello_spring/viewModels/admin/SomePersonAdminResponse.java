package com.itstep.hello_spring.viewModels.admin;

import com.itstep.hello_spring.models.relationship.one_many.SomePerson;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SomePersonAdminResponse implements Serializable {
    private boolean success;
    private Iterable<SomePerson>data;

}
