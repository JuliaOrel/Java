package org.example.myClassWork;

import org.example.buttons.Button;
import org.example.buttons.IClickable;
import org.example.buttons.IDoubleClickable;
import org.example.entities.academy.Group;
import org.example.entities.academy.Student;
import org.example.exceptions.validation.NameLengthValidationMax;
import org.example.exceptions.validation.NameLengthValidationMin;

public class August18Button implements Runnable
{
    @Override
    public void run() {
        System.out.println("Button works");

        //Создание анонимного класса
        Button btn=new Button(new IClickable() {
            @Override
            public void click() throws Exception {
                System.out.println("Click");
                throw new Exception("Test exception");
            }
        });
        try {
            btn.click();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        Button btn2=new Button(new IDoubleClickable() {
            @Override
            public void doubleClick() {
                System.out.println("DoubleClick");
            }

            @Override
            public void click() {
                System.out.println("Click");
            }
        });

        try {
            btn2.click();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        btn2.doubleClick();

    }


}
