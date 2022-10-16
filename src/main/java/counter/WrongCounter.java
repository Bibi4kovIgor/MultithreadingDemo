package counter;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class WrongCounter implements Runnable {
    private static int counter = 0;
    private static final int LIMIT = 1000;
    private static final int THREAD_POOL_SIZE = 5;

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(THREAD_POOL_SIZE);
        for (int i = 0; i < THREAD_POOL_SIZE; i++) {
            executorService.submit(new WrongCounter());
        }
        executorService.shutdown();
    }

    @Override
    public void run() {
        while (counter < LIMIT) {
            increaseCounter();
        }
    }

    private void increaseCounter() {
        System.out.println(Thread.currentThread().getName() + " : " + counter);
        counter++; // counter = counter + 1 -> 3 operations
    }
}
