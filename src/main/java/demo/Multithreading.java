package demo;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.*;

public class Multithreading {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        final ExecutorService pool = Executors.newFixedThreadPool(3);
        final Set<Future<Integer>> futureSet = new HashSet<>();
        for (String arg : args) {
            final Callable<Integer> callable = new WordLengthCallable(arg);
            final Future<Integer> future = pool.submit(callable);
            futureSet.add(future);
        }
        int sum = 0;
        for (Future<Integer> future : futureSet) {
            sum += future.get();
        }

        System.out.println("The sum of length " + sum);
    }
}
