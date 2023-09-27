package com.itstep.hello_spring.services;

import com.itstep.hello_spring.models.bar.Water;
import com.itstep.hello_spring.services.bar.interfaces.IdenChildInterface;
import org.springframework.stereotype.Service;

@Service
public class IdenService extends SemService implements IdenChildInterface {
    @Override
    public Water getWater(){
        Water w=new Water();
        w.setName("Coffee");
        return w;
    }
}
