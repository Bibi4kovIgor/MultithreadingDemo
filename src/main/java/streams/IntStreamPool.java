package streams;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static java.lang.Math.sqrt;
import static java.util.stream.LongStream.rangeClosed;

public class IntStreamPool {
    public static void main(String[] args) {
        final int parallelism = 4;
        ForkJoinPool forkJoinPool = null;
        try {
            forkJoinPool = new ForkJoinPool(parallelism);
            final List<Integer> primes = forkJoinPool.submit(() ->
                    // Parallel task here, for example
                    IntStream.range(1, 1_000_000).parallel()
                            .filter(IntStreamPool::isPrime)
                            .boxed().collect(Collectors.toList())
            ).get();
            System.out.println(primes);
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        } finally {
            if (forkJoinPool != null) {
                forkJoinPool.shutdown();
            }
        }
    }
    private static boolean isPrime(long n) {
        return n > 1 && rangeClosed(2, (long) sqrt(n)).noneMatch(divisor -> n % divisor == 0);

    }
}
