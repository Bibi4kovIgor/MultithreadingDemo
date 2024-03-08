package counter;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class WrongCounter implements Runnable {
  private static volatile int counter = 0;
  private static final int LIMIT = 1001;
  private static final int THREAD_POOL_SIZE = 5;

  private static final Object monitor = new Object();

  public static void main(String[] args) throws InterruptedException {
    List<Thread> threads = new ArrayList<>();
    final int CORES_NUMBER = Runtime.getRuntime().availableProcessors();
    for (int i = 0; i < CORES_NUMBER; i++) {
      Thread thread = new Thread(() -> {
        WrongCounter counter1 = new WrongCounter();
        counter1.run();
      });

      threads.add(thread);
      thread.start();
    }

    for (Thread thread : threads) {
      thread.join();
    }
//        ExecutorService executorService = Executors.newFixedThreadPool(THREAD_POOL_SIZE);
//        for (int i = 0; i < THREAD_POOL_SIZE; i++) {
//            executorService.submit(new WrongCounter());
//        }
//        executorService.shutdown();

  }

  @Override
  public void run() {
    while (counter < LIMIT) {
      increaseCounter();
      printCounter();
    }
  }

  private synchronized void increaseCounter() {
//    synchronized (monitor) {
      counter++; // counter = counter + 1 -> 3 operations
//    }
  }


  private void printCounter() {
//    synchronized (monitor) {
      System.out.println(Thread.currentThread().getName() + " : " + counter);
//    }
  }
}
