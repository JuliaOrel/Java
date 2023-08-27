package org.example.myClassWork;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class August26Threads implements Runnable
{
    @Override
    public void run() {
        System.out.println("Threads work");
        //startAsRunnable();
        //anonim();
        startAsCallable();
    }

    private void startAsCallable(){

        //Создадим реализацию интерфейса, которая буудет возвращать значение из потока
        Callable<String> c=new Callable<String>() {
            @Override
            public String call() throws Exception {
                System.out.println("Hello Callable");
                return "Hello Callable";
            }
        };
        //но тогда такой тип запуска не применим для такой операции
        //Thread t=new Thread();
        //t.start();
       //Создадим ExecutorService с одним потоком
        ExecutorService executor= Executors.newSingleThreadExecutor();

        //Запускаем Callable и получаем Future объект
        Future<String>future=executor.submit(c);
        try {
            //Ждем пока Callable завершится и получаем результат
            String result=future.get();

            System.out.println("Result: "+ result);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        executor.shutdown();
    }

    private void startAsRunnable(){


        //Thread t=
                (new Thread(  //создает экземпляр класса поток
                new Runnable() {  //в качестве аргумента передаем аноноимный класс, который реализует интерфейс
                    @Override
                    public void run() {
                        System.out.println("Hello Runnable ");
                    }
                }
        )).start();

                Runnable r=()->{
                    System.out.println("Hello Runnable from -> ");
                };
                r.run();
                System.out.println("Class of Runnable r: "+r.getClass());

                Thread t=new Thread(r);
                t.start();

    }

    private void anonim(){
        Object o=new Object(){
            private final String var="Some Var";
            @Override
            public String toString(){
                return this.var;
            }
        };
        System.out.println(o);
    }
}
