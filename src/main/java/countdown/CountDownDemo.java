package countdown;

import java.util.concurrent.CountDownLatch;

public class CountDownDemo {
    public static void main(String[] args) {
        CountDownLatch countDownLatch = new CountDownLatch(5);
        System.out.println("Countdown launched");
        new MyThread(countDownLatch);

        try {
            // threads added to awaiting list
            countDownLatch.await();
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }
        System.out.println("Countdown thread was over");
    }
}
