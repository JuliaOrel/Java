package com.itstep.hello_spring.services.admin;

import com.itstep.hello_spring.models.relationship.one_many.SomePerson;
import com.itstep.hello_spring.services.SomePersonService;
import com.itstep.hello_spring.viewModels.admin.SomePersonAdminResponse;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class AdminSomePersonService {
    final SomePersonService personService;
    public AdminSomePersonService (SomePersonService personService){
        this.personService=personService;
    }

    @Cacheable(value="person", key="'alladminpersons'", unless = "#result==null")
    public SomePersonAdminResponse findAll() {
        return new SomePersonAdminResponse(
                true,
                personService.findAll()
        );
    }
}
