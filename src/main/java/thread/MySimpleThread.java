package thread;

import lombok.SneakyThrows;

public class MySimpleThread extends Thread {

    @SneakyThrows
    @Override
    public void run() {
        for (int i = 0; i < 5; i++) {
            System.out.println(super.getName() + " " + i);
            Thread.sleep(500);
        }
    }
}
