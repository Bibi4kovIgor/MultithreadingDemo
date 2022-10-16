package thread;

import lombok.SneakyThrows;

import java.util.concurrent.TimeUnit;

public class SimpleThread {
    @SneakyThrows
    public static void main(String[] args) {

//        AtomicInteger i = new AtomicInteger();
        Thread thread1 = new Thread(() -> {
//            for (i.set(0); i.get() < 10; i.incrementAndGet()) {
            for (int i = 0; i < 10; i++) {


                System.out.println("Thread 1 " + i);
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        Thread thread2 = new Thread("Thread 2") {
            @SneakyThrows
            @Override
            public void run() {
                System.out.println(isAlive());
//                System.out.println("Thread 2 is daemon " + isDaemon());
                setPriority(8);
                for (int i = 0; i < 10; i++) {
                    Thread.sleep(500);
                    Thread.yield();
                    System.out.println(getName() + " " + getId());
                }
                System.out.println(isAlive());
            }
        };

        Thread thread2_5 = new Thread("Thread 2_5") {
            @SneakyThrows
            @Override
            public void run() {
                System.out.println(isAlive());
//                System.out.println("Thread 2 is daemon " + isDaemon());
                setPriority(8);
                for (int i = 0; i < 10; i++) {
                    Thread.sleep(500);
                    System.out.println(getName() + " " + getId());
                }
            }
        };

        Runnable thread3 = () -> {
            for (int j = 0; j < 10; j++) {

                System.out.println("Thread 3 " + j);
                try {
                    TimeUnit.MILLISECONDS.sleep(400);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        };


        SimpleRunnable thread4 = new SimpleRunnable("Thread 4", 500);
        MySimpleThread thread5 = new MySimpleThread();
        thread5.setName("Thread 5");
//        thread2.setDaemon(true);

//        Runnable runnable = () -> System.out.println(Thread.currentThread().getName());
//        Thread thread = new Thread(runnable);
//        SimpleCallable simpleCallable = new SimpleCallable();
////        System.out.println(simpleCallable.call());
//        ExecutorService executorService = Executors.newFixedThreadPool(4);
//        executorService.submit(simpleCallable);
//
//        thread.start();

        thread1.setPriority(8);

        thread1.start();
        thread3.run();
        thread2.start();
        thread2_5.start();
        thread4.run();
        thread5.start();


//        System.out.println("Thread 2 is daemon " + thread2.isDaemon());
        System.out.println("Thread 2 is alive " + thread2.isAlive());
//
//        executorService.shutdown();

    }


}
