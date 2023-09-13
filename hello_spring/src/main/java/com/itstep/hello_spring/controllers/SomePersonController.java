package com.itstep.hello_spring.controllers;

import com.itstep.hello_spring.models.relationship.one_many.SomeDocument;
import com.itstep.hello_spring.models.relationship.one_many.SomePerson;
import com.itstep.hello_spring.repositories.SomePersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Set;
import java.util.UUID;

@RestController
@RequestMapping("/api/some_person/")
public class SomePersonController {


    private SomePersonRepository somePersonRepository;

    public SomePersonController(SomePersonRepository somePersonRepository){
        this.somePersonRepository=somePersonRepository;
    }


    @GetMapping("/")
    public Iterable<SomePerson>getAllPerson(){
        return somePersonRepository.findAll();
    }

    @GetMapping("/{id}")
    public SomePerson getById(@PathVariable UUID id){
        SomePerson person=somePersonRepository.findById(id)
                .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "entity not found"));
        //List<SomeDocument> l=person.getDocuments().stream().toList();
        return person;
    }
}
