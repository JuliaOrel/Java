package com.itstep.hello_spring.services;

import com.itstep.hello_spring.models.relationship.one_many.SomePerson;
import com.itstep.hello_spring.repositories.SomePersonRepository;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class SomePersonService {
    final SomePersonRepository personRepository;

    public SomePersonService(SomePersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @Cacheable(value="person", key="'allpersons'", unless = "#result==null")
    public  Iterable<SomePerson> findAll(){
        return personRepository.findAll();
    }

}
