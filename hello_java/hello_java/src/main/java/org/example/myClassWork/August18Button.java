package org.example.myClassWork;

import org.example.entities.academy.Group;
import org.example.entities.academy.Student;
import org.example.exceptions.validation.NameLengthValidationMax;
import org.example.exceptions.validation.NameLengthValidationMin;

public class August18Button implements Runnable
{
    @Override
    public void run() {
        System.out.println("Academy works");
        createEntities();
    }

    private void createEntities(){

        try{
            Group g=new Group("pv121");
            Student julia=new Student("J", g);
            Student marina=new Student("Marina", g);
            g.getStudents().add(julia.clone());
            g.getStudents().add(marina);

            System.out.println("Output of the original group");
            System.out.println(g);

            julia.setName("Юлия");
            marina.setName("Марина");

            System.out.println("Output of the changed group");
            System.out.println(g);

            g.getStudents().get(0).setName("Юлия Владимировна");
            g.getStudents().get(1).setName("Марина Вадимовна");

            System.out.println("Output of the changed group");
            System.out.println(g);
        }catch(NameLengthValidationMin exMin){
            System.out.println(exMin.getMessage());
        }catch (NameLengthValidationMax exMax){
            System.out.println(exMax.getMessage());
        }

    }
}
