package org.example.myClassWork;

import org.example.threads.CalculatePiElement;
import org.example.threads.CalculatePiElementSemaphore;

import java.util.concurrent.*;

public class August26Threads implements Runnable
{
    @Override
    public void run() {
        System.out.println("Threads work");
        //startAsRunnable();
        //anonim();
        //startAsCallable();
        //calcPi();
        calcPiSemaphore();
    }

    private void calcPiSemaphore(){
        Semaphore s = new Semaphore(1);
        CalculatePiElementSemaphore el1 = new CalculatePiElementSemaphore(0, 100, s);
        CalculatePiElementSemaphore el2 = new CalculatePiElementSemaphore(101, 200, s);

        Thread t1 = new Thread(el1);
        t1.start();
        Thread t2 = new Thread(el2);
        t2.start();

        try {
            // join позволяет главному потоку main дождаться завершения потоков
            t1.join();
            t2.join();
            // Мы просто ждем - поток будет заблокирован
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }

    private void calcPi(){
        CalculatePiElement el=new CalculatePiElement(0,100);
        CalculatePiElement el2=new CalculatePiElement(101,200);

        ExecutorService executor = Executors.newFixedThreadPool(2);

        Future<?> future1=executor.submit(el);
        Future<?> future2=executor.submit(el2);

//        executor.submit(el);
//        executor.submit(el2);

        executor.shutdown(); //Завершение приема новых задач

        // Проверяем, завершились ли обе задачи
        do {
//            if((new Random()).nextInt(100)> 95) {
//                future1.cancel(true);
//            }
//            // System.out.print(".");
//            if (future1.isDone()) {
//                System.out.println(" 1 isDone. Status: " + future1.state());
//            }
//            if (future2.isDone()) {
//                System.out.println(" 2 isDone. Status: " + future2.state());
//            }
            // Таким образом я не блокирую поток main - и могу управлять всеми порожденными потоками
            // прерывать их - ставить на паузу и тд и тп
            System.out.print(".");
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                System.out.println(e.getMessage());
            }
        } while (!future1.isDone() || !future2.isDone());
        // Дожидаемся завершения всех потоков в пуле - это аналог join но сразу для всех в пуле потоков
        try {
            executor.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

//        Thread t=new Thread(el);
//        t.start();
//
//        Thread t2=new Thread(el2);
//        t2.start();

//        try{
//            t.join();
//            t2.join();
//        }catch (InterruptedException e){
//            System.out.println(e.getMessage());
//
//        }
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
                int counter=0;
                while(!future.isDone()){ //main не зависает
                    System.out.println(".");
                    if(counter++ <5){
                        future.cancel(true);
                    }
                    Thread.sleep(100);
                }

            //Ждем пока Callable завершится и получаем результат
            String result=future.get();

                Thread t=Thread.currentThread();
            System.out.println("\n\n" + t+ " Result: "+ result);
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
