package com.itstep.hello_spring.controllers.bar;

import com.itstep.hello_spring.models.bar.Coffee;
import com.itstep.hello_spring.models.interfaces.ModelInterface;
import com.itstep.hello_spring.services.IdenService;
import com.itstep.hello_spring.services.SemService;
import com.itstep.hello_spring.services.bar.interfaces.SemParentInterface;
import org.springframework.http.ResponseEntity;

public class BarController {

    public ResponseEntity getCoffee(){
        SemService sem=new SemService();
        IdenService iden=new IdenService();

        Coffee c1=getCoffee(sem);
        Coffee c2=getCoffee(iden);
        return ResponseEntity
                .status(200)
                .body(c1);
    }

    public ResponseEntity getModelNameByInterface(){
        SemService sem=new SemService();
        IdenService iden=new IdenService();

//         ModelInterface m1=sem.getCoffee();
//         ModelInterface m2=iden.getCoffee();
         ModelInterface m3=iden.getWater();

         //m3.getName();
        return ResponseEntity
                .status(200)
                .body(m3);
    }
    private Coffee getCoffee(SemParentInterface service){
        return  service.getCoffee();
    }
}
