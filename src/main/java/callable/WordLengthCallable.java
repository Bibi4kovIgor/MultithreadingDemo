package callable;

import java.util.concurrent.Callable;

public class WordLengthCallable implements Callable<Integer> {
    private final String word;

    public WordLengthCallable(String word) {
        this.word = word;
    }

    /**
     * Computes a result, or throws an exception if unable to do so.
     *
     * @return computed result
     *
     */
    @Override
    public Integer call()  {
        return word.length();
    }
}
