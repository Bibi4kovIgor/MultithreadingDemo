package cyclicbarrier;

import java.util.concurrent.CyclicBarrier;

public class CyclicBarrierDemo {
    public static void main(String[] args) {
        final CyclicBarrier cyclicBarrier = new CyclicBarrier(3,
                () -> System.out.println("All parties was arrived at barrier, let's play"));
        Thread thread1 = new Thread(new Task(cyclicBarrier), "Thread 1");
        Thread thread2 = new Thread(new Task(cyclicBarrier), "Thread 2");
        Thread thread3 = new Thread(new Task(cyclicBarrier), "Thread 3");

        thread1.start();
        thread2.start();
        thread3.start();
    }

}
