package com.itstep.hello_spring.services.bar.interfaces;

import com.itstep.hello_spring.models.bar.Water;
import org.springframework.stereotype.Service;

@Service
public class IdenService extends SemService implements IdenChildInterface{

    @Override
    public Water getWater() {
        Water w = new Water();
        w.setName("Water");
        return w;
    }
}
