package com.itstep.hello_spring.services;

import com.itstep.hello_spring.repositories.SomePersonRepository;
import com.itstep.hello_spring.services.interfaces.AddonSomePersonServiceInterface;

public class AddSomePersonService extends SomePersonService implements AddonSomePersonServiceInterface {

    public AddSomePersonService(SomePersonRepository personRepository) {
        super(personRepository);
    }

    @Override
    public void addSomeFunction(){

    }
}
