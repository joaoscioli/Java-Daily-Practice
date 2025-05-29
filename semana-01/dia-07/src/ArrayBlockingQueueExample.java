import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class ArrayBlockingQueueExample {
    public static void main(String[] args) throws InterruptedException {
        BlockingQueue<Task> queue = new ArrayBlockingQueue<>(2);

        queue.put(new Task("Task 01", 1));
        queue.put(new Task("Task 02", 2));

        // Esta proxima vai esperar espaço
        new Thread(() -> {
            try {
                queue.put(new Task("Task 3", 3));
                System.out.println("Task 3 running");
            } catch (InterruptedException ignored) {}
        }).start();

        Task task = queue.take();  // Remove uma para liberar espaço
        task.run();

    }
}
