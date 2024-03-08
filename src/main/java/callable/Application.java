package callable;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.*;

public class Application {
    public static void main(String... args) throws ExecutionException, InterruptedException {
        int CPU_COUNT = Runtime.getRuntime().availableProcessors();
        ExecutorService executor = Executors.newFixedThreadPool(CPU_COUNT);
        Set<Future<Integer>> result = new HashSet<>();
        for (String word : args) {
            Callable<Integer> callable = new WordLengthCallable(word);
            Future<Integer> wordLength = executor.submit(callable);
            result.add(wordLength);
        }
        int wordLength = 0;
        for (Future<Integer> integerFuture : result) {
            wordLength += integerFuture.get();
        }

        System.out.println(wordLength);

        executor.shutdown();
    }
}
