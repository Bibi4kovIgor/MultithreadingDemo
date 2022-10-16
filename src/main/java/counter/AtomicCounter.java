package counter;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

public class AtomicCounter implements Runnable {
    private static AtomicInteger counter;
    private static final int LIMIT = 1000;
    private static final int THREAD_POOL_SIZE = 5;

    public static void main(String[] args) {
        counter = new AtomicInteger(0);
        ExecutorService executorService = Executors.newFixedThreadPool(THREAD_POOL_SIZE);
        for (int i = 0; i < THREAD_POOL_SIZE; i++) {
            executorService.submit(new Counter());
        }
        executorService.shutdown();
    }
    @Override
    public void run() {
        while (counter.get() < LIMIT) {
            increaseCounter();
        }
    }

    private void increaseCounter() {
        System.out.println(Thread.currentThread().getName() + " : " + counter.getAndIncrement());
    }
}
