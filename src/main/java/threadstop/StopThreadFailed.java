package threadstop;

import java.util.concurrent.TimeUnit;

public class StopThreadFailed {
    private static boolean stopRequested;
    public static void main(String[] args) throws InterruptedException {
        Thread backgroundThread = new Thread(() -> {
            int i = 0;
            while (!stopRequested) {
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
        stopRequested = true;
    }
}
