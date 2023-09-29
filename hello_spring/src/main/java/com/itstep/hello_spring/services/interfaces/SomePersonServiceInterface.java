package com.itstep.hello_spring.services.interfaces;

import com.itstep.hello_spring.models.relationship.one_many.SomePerson;

public interface SomePersonServiceInterface {
    public  Iterable<SomePerson> findAll();
}
