package streams;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.lang.Math.sqrt;
import static java.util.stream.LongStream.rangeClosed;

public class IntStreamPool {
    public static void main(String[] args) throws InterruptedException {
        final int parallelism = 4;
        ForkJoinPool forkJoinPool;

            forkJoinPool = new ForkJoinPool(parallelism);
            System.out.println(getExecutionTimeParallel(IntStreamPool::getPrimesParallel, forkJoinPool));
            Thread.sleep(1000);
            System.out.println(getExecutionTime(IntStreamPool::getPrimes));

    }

    private static List<Integer> getPrimesParallel(ForkJoinPool forkJoinPool) {
        List<Integer> primes;
        try {
            primes = forkJoinPool.submit(() ->
                    // Parallel task here, for example
                    IntStream.range(1, 1_000_000).parallel()
                            .filter(IntStreamPool::isPrime)
                            .boxed().collect(Collectors.toList())
                    ).get();
            } catch (InterruptedException | ExecutionException e) {
                throw new RuntimeException(e);
            } finally {
                if (forkJoinPool != null) {
                    forkJoinPool.shutdown();
                }
            }
        return primes;
    }

    private static List<Integer> getPrimes() {
        return IntStream.range(1, 1_000_000)
                .filter(IntStreamPool::isPrime)
                .boxed().toList();

    }

    private static boolean isPrime(long n) {
        return n > 1 && rangeClosed(2, (long) sqrt(n)).noneMatch(divisor -> n % divisor == 0);
    }

    private static long getExecutionTimeParallel(Function<ForkJoinPool, List<Integer>> method, ForkJoinPool forkJoinPool) {
        long startTime = System.nanoTime();
        method.apply(forkJoinPool);
        return System.nanoTime() - startTime;
    }

    private static long getExecutionTime(Supplier<List<Integer>> method) {
        long startTime = System.nanoTime();
        method.get();
        return System.nanoTime() - startTime;
    }
}
