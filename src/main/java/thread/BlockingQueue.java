package thread;

import lombok.Getter;
import lombok.SneakyThrows;

import java.util.LinkedList;
import java.util.Queue;

@Getter
public class BlockingQueue<T> {

    private final Queue<T> queue = new LinkedList<>();
    private final int capacity;

    public int getSize() {
        return queue.size();
    }

    public BlockingQueue(int capacity) {
        this.capacity = capacity;
    }

    public synchronized void put(T element) throws InterruptedException {
        while(queue.size() == capacity) {
            wait();
        }

        queue.add(element);
        notify(); // notifyAll() for multiple producer/consumer threads
    }

    public synchronized T take() throws InterruptedException {
        while(queue.isEmpty()) {
            wait();
        }

        T item = queue.remove();
        notify(); // notifyAll() for multiple producer/consumer threads
        return item;
    }

    @SneakyThrows
    public static void main(String[] args) {
        BlockingQueue<Model> blockingQueue = new BlockingQueue<>(5);
        blockingQueue.put(new Model(2, "John"));
        blockingQueue.put(new Model(3, "Morris"));
        System.out.println(blockingQueue.getSize());
        System.out.println(blockingQueue.take());
        System.out.println(blockingQueue.getCapacity());
        System.out.println(blockingQueue.getSize());
        System.out.println(blockingQueue.take());
        System.out.println(blockingQueue.getCapacity());
        System.out.println(blockingQueue.getSize());
    }
}