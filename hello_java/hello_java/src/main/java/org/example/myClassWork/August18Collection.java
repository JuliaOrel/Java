package org.example.myClassWork;

import org.example.buttons.Button;
import org.example.buttons.IClickable;
import org.example.buttons.IDoubleClickable;
import org.example.entities.academy.Group;
import org.example.entities.academy.Student;
import org.example.exceptions.validation.NameLengthValidationMax;
import org.example.exceptions.validation.NameLengthValidationMin;

import java.util.*;

public class August18Collection implements Runnable
{
    @Override
    public void run() {
        System.out.println("Collection work");
        try {
            workCollection();
        } catch (NameLengthValidationMin | NameLengthValidationMax e) {
            System.out.println(e.getMessage());
        }
    }

    public void workCollection() throws NameLengthValidationMin, NameLengthValidationMax {
        Group g=new Group("pv121");
        Student julia=new Student("Julia",g);
        Student marina=new Student("Marina",g);

        ArrayList<Student> arrayList=new ArrayList<>();
        arrayList.add(julia);
        arrayList.add(marina);

        List<Student> list=new ArrayList<>();
        list.add(julia);
        list.add(marina);

        arrayList.ensureCapacity(100);
        System.out.println(arrayList.size());

        Queue<Student>qStudent=new ArrayDeque<>();
        qStudent.add(julia);
        qStudent.add(marina);
    }

    private void collectionFromInterface(){
        Collection<Student> col=new Collection<Student>() {
            @Override
            public int size() {
                return 0;
            }

            @Override
            public boolean isEmpty() {
                return false;
            }

            @Override
            public boolean contains(Object o) {
                return false;
            }

            @Override
            public Iterator<Student> iterator() {
                return null;
            }

            @Override
            public Object[] toArray() {
                return new Object[0];
            }

            @Override
            public <T> T[] toArray(T[] a) {
                return null;
            }

            @Override
            public boolean add(Student student) {
                return false;
            }

            @Override
            public boolean remove(Object o) {
                return false;
            }

            @Override
            public boolean containsAll(Collection<?> c) {
                return false;
            }

            @Override
            public boolean addAll(Collection<? extends Student> c) {
                return false;
            }

            @Override
            public boolean removeAll(Collection<?> c) {
                return false;
            }

            @Override
            public boolean retainAll(Collection<?> c) {
                return false;
            }

            @Override
            public void clear() {

            }
        };
    }
}
