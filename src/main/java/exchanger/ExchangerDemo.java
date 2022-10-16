package exchanger;

import java.util.concurrent.Exchanger;

public class ExchangerDemo {
    public static void main(String[] args) {
        Exchanger<String> stringExchanger = new Exchanger<>();
        new UserString(stringExchanger);
        new MakeString(stringExchanger);
    }
}
