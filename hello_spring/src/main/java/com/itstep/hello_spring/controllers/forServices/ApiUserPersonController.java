package com.itstep.hello_spring.controllers.forServices;

import com.itstep.hello_spring.models.relationship.one_many.SomePerson;
import com.itstep.hello_spring.services.SomePersonService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/user/persons")
public class ApiUserPersonController {
    final SomePersonService personService;
    public ApiUserPersonController(SomePersonService personService){
        this.personService=personService;
    }

    @GetMapping("/")
    public Iterable<SomePerson>getAllPersons(){
        return personService.findAll();
    }
}
