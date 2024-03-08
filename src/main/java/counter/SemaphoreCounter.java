package counter;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SemaphoreCounter implements Runnable {
    private final Semaphore semaphore;
    private static int counter = 0;
    private static final int LIMIT = 1000;
    private static final int THREAD_POOL_SIZE = 5;

    Logger logger = Logger.getLogger(SemaphoreCounter.class.getName());

    public SemaphoreCounter(Semaphore semaphore) {
        this.semaphore = semaphore;
    }

    public static void main(String[] args) {
        Semaphore sharedSemaphore = new Semaphore(1);
        ExecutorService executorService = Executors.newFixedThreadPool(THREAD_POOL_SIZE);
        for (int i = 0; i < THREAD_POOL_SIZE; i++) {
            executorService.submit(new SemaphoreCounter(sharedSemaphore));
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
        try {
            semaphore.acquire();
            System.out.println(Thread.currentThread().getName() + " : " + counter);
            counter++;
        } catch (InterruptedException e) {
          logger.log(Level.WARNING, e.getCause(), String::new);
        } finally {
            semaphore.release();
        }
    }
}
