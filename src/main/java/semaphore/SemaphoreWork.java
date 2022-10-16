package semaphore;

import java.util.concurrent.Semaphore;

public class SemaphoreWork {
    public static void main(String[] args) {
        Semaphore semaphore = new Semaphore(1);
        CommonResource resource = new CommonResource();
        new Thread(new CountThread(resource, semaphore, "CountThread 1")).start();
        new Thread(new CountThread(resource, semaphore, "CountThread 2")).start();
        new Thread(new CountThread(resource, semaphore, "CountThread 3")).start();

    }
}
