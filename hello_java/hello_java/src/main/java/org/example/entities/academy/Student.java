package org.example.entities.academy;

import lombok.Data;
import org.example.exceptions.validation.NameLengthValidationMax;
import org.example.exceptions.validation.NameLengthValidationMin;

@Data
public class Student implements Cloneable {
    private String name;
    private Group group;

    public Student(String name, Group g) throws NameLengthValidationMin, NameLengthValidationMax {
       this.setName(name);
       this.setGroup(g);
    }

    public void setName(String name) throws NameLengthValidationMin {
        if(name.length()<3){
            System.out.println("Name must be longer than 2 characters");
            throw new NameLengthValidationMin("Name must be longer than 2 characters");
        }
        if(name.length()>10){
            System.out.println("Name mustn't be longer than 10 characters");
            throw new NameLengthValidationMin("Name mustn't be longer than 10 characters");
        }
        this.name = name;
    }

    @Override
    public Student clone() {
        try {
            Student clone = (Student) super.clone();
            // TODO: copy mutable state here, so the clone can't change the internals of the original
            return clone;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }
}
