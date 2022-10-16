package thread;

import lombok.SneakyThrows;

import java.util.concurrent.Callable;

public class SimpleCallable implements Callable<Model> {
    @SneakyThrows
    @Override
    public Model call() {
        for (int i = 0; i < 10; i++) {

        System.out.printf("Thread name %s, Thread priority %d, iteration number %d\n",
                Thread.currentThread().getName(), Thread.currentThread().getPriority(), i);
            Thread.sleep(500);
        }
        return new Model(1, "Ihor");
    }
}
