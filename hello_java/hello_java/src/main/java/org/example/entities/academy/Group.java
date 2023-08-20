package org.example.entities.academy;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

@Data
@AllArgsConstructor
//@NoArgsConstructor //Иначе я не смогу создать сущность
public class Group {
    //Или создать свой конструктор(if no NoArgsConstructor)
    public Group(String name){
        this.setName(name);
    }
    private String name;
    private ArrayList<Student> students=new ArrayList<>();

    public String toString() {
        String var10000 = this.getName();
        StringBuilder students=new StringBuilder();

        for (Student s:this.getStudents()){
            students.append(s.getName()).append("\n");
        }
        return "Group(name=" + var10000 + ", students=\n" + students.toString() + ")";
    }
}
