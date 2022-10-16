package threadstop;

import java.util.concurrent.TimeUnit;

public class StopThread {
    private static boolean stopRequested;

    public static synchronized void requestStop() {
        stopRequested = true;
    }

    public static synchronized boolean isStopRequested() {
        return stopRequested;
    }

    public static void main(String[] args) throws InterruptedException {
        Thread backgroundThread = new Thread(() -> {
            int i = 0;
            while (!isStopRequested()) {
                i++;
                System.out.println(i);
            }
            // will be interpret by VM as:
            // if(!stopRequested) {
            //   while(true) {
            //     i++;
            //   }
            // } -> hoisting - поднятие
        });

        backgroundThread.start();
        TimeUnit.SECONDS.sleep(1);
        requestStop();
    }
}
