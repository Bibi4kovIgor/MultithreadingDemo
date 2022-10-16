package demo;

import java.util.concurrent.Callable;

public class WordLengthCallable implements Callable<Integer> {
    private final String word;
    public WordLengthCallable(String word) {
        this.word = word;
    }

    @Override
    public Integer call() throws Exception {
        return word.length();
    }
}
