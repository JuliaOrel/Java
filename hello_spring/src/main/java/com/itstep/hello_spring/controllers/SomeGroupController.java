package com.itstep.hello_spring.controllers;

import com.itstep.hello_spring.models.relationship.one_many.SomeGroup;
import com.itstep.hello_spring.models.relationship.one_many.SomePerson;
import com.itstep.hello_spring.repositories.SomeGroupRepository;
import com.itstep.hello_spring.repositories.SomePersonRepository;
import com.itstep.hello_spring.requests.PersonGroupRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/api/groups")
public class SomeGroupController {
    @PostMapping("/add_user")
    public ResponseEntity<SomeGroup> addPersonToGroup(
            @RequestBody PersonGroupRequest request){

        SomeGroup group = groupRepository.findById(request.getGroup_id())
                .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "Group not found"));

        SomePerson person = personRepository.findById(request.getPerson_id())
                .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "Person not found"));

//        group.getPersons().add(person);
//        groupRepository.save(group);

        person.getGroups().add(group);
        personRepository.save(person);


        return ResponseEntity
                .status(201)
                .body(group);
    }


    @PostMapping("/")
    public ResponseEntity<SomeGroup> create(@RequestBody SomeGroup group){
        groupRepository.save(group);
        return ResponseEntity
                .status(201)
                .body(group);
    }


    final SomeGroupRepository groupRepository;
    final SomePersonRepository personRepository;

    public SomeGroupController(
            SomeGroupRepository groupRepository,
            SomePersonRepository personRepository
    ) {
        this.groupRepository = groupRepository;
        this.personRepository = personRepository;
    }
}
