package com.itstep.hello_spring.controllers;


import com.itstep.hello_spring.models.relationship.one_many.SomeDocument;
import com.itstep.hello_spring.models.relationship.one_many.SomePerson;
import com.itstep.hello_spring.repositories.SomeDocumentRepository;
import com.itstep.hello_spring.repositories.SomePersonRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/some_document")
public class SomeDocumentController {
    final SomeDocumentRepository someDocumentRepository;
    SomePersonRepository somePersonRepository;
    public SomeDocumentController(SomeDocumentRepository someDocumentRepository, SomePersonRepository somePersonRepository){
        this.someDocumentRepository=someDocumentRepository;
        this.somePersonRepository=somePersonRepository;
    }

    @GetMapping("/")
    public Iterable <SomeDocument>getAllDocuments(){return someDocumentRepository.findAll();}

    @GetMapping("/{id}")
public ResponseEntity<SomeDocument>getById(@PathVariable UUID id){
        Optional<SomeDocument> document=someDocumentRepository.findById(id);
        if(document.isEmpty())
            return ResponseEntity
                    .status(404)
                    .body(null);
        return ResponseEntity
                .status(200)
                .body(document.get());
    }


    @GetMapping("2/{id}")
    public SomeDocument getById2(@PathVariable UUID id){
        SomeDocument document=someDocumentRepository.findById(id)
                .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "entity not found"));
        //List<SomeDocument> l=person.getDocuments().stream().toList();
        return document;
    }

    @PostMapping("/")
    public ResponseEntity<SomeDocument> createDocument(@RequestBody SomeDocument newDocument){
        SomePerson person=somePersonRepository.findById(newDocument.getPerson_id())
                .orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND, "person not found"));
        newDocument.setPerson(person);
        someDocumentRepository.save(newDocument);
        return ResponseEntity
                .status(201)
                .body(newDocument);
    }
}
