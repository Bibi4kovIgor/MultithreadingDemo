package exchanger;

import java.util.concurrent.Exchanger;

public class UserString implements Runnable {
    private final Exchanger<String> stringExchanger;
    String str = "";
    public UserString(Exchanger<String> stringExchanger) {
        this.stringExchanger = stringExchanger;
        new Thread(this).start();
    }

    @Override
    public void run() {
        try {
            str = stringExchanger.exchange(str);
            System.out.println(str + " was received");
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }
    }
}
