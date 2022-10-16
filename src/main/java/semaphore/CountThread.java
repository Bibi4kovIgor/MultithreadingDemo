package semaphore;

import java.util.concurrent.Semaphore;

public class CountThread implements Runnable {
    CommonResource resource;
    Semaphore semaphore;
    String name;
    public CountThread(CommonResource resource, Semaphore semaphore, String name) {
        this.resource = resource;
        this.semaphore = semaphore;
        this.name = name;
    }

    public void run() {
        try {
            System.out.println("Waiting for permission");
            semaphore.acquire();
            resource.x = 1;
            for (int i = 0; i < 5; i++) {
                System.out.println(this.name + ":" + resource.x);
                resource.x++;
                Thread.sleep(100);
            }
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }
        System.out.println("Release Permission");
        semaphore.release();

    }
}
