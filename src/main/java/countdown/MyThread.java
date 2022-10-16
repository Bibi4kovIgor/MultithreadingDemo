package countdown;

import java.util.concurrent.CountDownLatch;

public class MyThread implements Runnable {
    CountDownLatch countDownLatch;
    public MyThread(CountDownLatch countDownLatch) {
        this.countDownLatch = countDownLatch;
        new Thread(this).start();
    }

    @Override
    public void run() {
        for (int i = 0; i < 5; i++) {
            System.out.println(i);
            countDownLatch.countDown(); // countdowning
        }
    }
}
