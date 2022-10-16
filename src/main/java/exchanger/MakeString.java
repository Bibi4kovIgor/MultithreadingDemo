package exchanger;

import java.util.concurrent.Exchanger;

public class MakeString implements Runnable {
    private final Exchanger<String> stringExchanger;
    StringBuilder str = new StringBuilder();
    public MakeString(Exchanger<String> stringExchanger) {
        this.stringExchanger = stringExchanger;
        new Thread(this).start();
    }

    @Override
    public void run() {
        char ch = 'A';
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 5; j++) {
                str.append(ch++);
            }
            try {
                str.append(stringExchanger.exchange(str.toString()));
            } catch (InterruptedException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
