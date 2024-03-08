package callable;

import java.util.concurrent.Callable;
import java.util.concurrent.Executor;

public class WordLengthCallable implements Callable<Integer> {
    private final String word;

    public WordLengthCallable(String word) {
        this.word = word;
    }

    /**
     * Computes a result, or throws an exception if unable to do so.
     *
     * @return computed result
     * @throws Exception if unable to compute a result
     */
    @Override
    public Integer call() throws Exception {
        return word.length();
    }
}
