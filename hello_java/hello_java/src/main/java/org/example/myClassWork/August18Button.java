package org.example.myClassWork;

import org.example.buttons.Button;
import org.example.buttons.IClickable;
import org.example.entities.academy.Group;
import org.example.entities.academy.Student;
import org.example.exceptions.validation.NameLengthValidationMax;
import org.example.exceptions.validation.NameLengthValidationMin;

public class August18Button implements Runnable
{
    @Override
    public void run() {
        System.out.println("Button works");
        Button btn=new Button(new IClickable() {
            @Override
            public void click() {
                System.out.println("Click");
            }
        });
        btn.click();

    }


}
