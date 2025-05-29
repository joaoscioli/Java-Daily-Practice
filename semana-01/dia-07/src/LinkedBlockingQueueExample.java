import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class LinkedBlockingQueueExample {
    public static void main(String[] args) {
        BlockingQueue<Task> queue = new LinkedBlockingQueue<>();

        // Productor
        new Thread(() -> {
            try {
                queue.put(new Task("Task 01", 1));
                queue.put(new Task("Task 02", 2));
            } catch (InterruptedException ignored) {}
        }).start();

        // Consumer
        new Thread(() -> {
            try {
                while (true) {
                    Task task = queue.take();
                    task.run();
                }
            } catch (InterruptedException ignored) {}
        }).start();

    }
}
