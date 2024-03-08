package thread;

import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class SimpleRunnable implements Runnable {
    private String name;
    private long sleepTime;
    private int i;

    @SneakyThrows
    @Override
    public void run() {
        for (i = 0; i < 5; i++) {
            System.out.println(this);
            Thread.sleep(sleepTime);
        }
    }
}
