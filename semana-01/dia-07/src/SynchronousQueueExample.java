import java.util.concurrent.BlockingQueue;
import java.util.concurrent.SynchronousQueue;

public class SynchronousQueueExample {
    public static void main(String[] args) {
        BlockingQueue<Task> queue = new SynchronousQueue<>();

        // Consumer
        new Thread(() -> {
            try {
                Task task = queue.take();
                task.run();
            } catch (InterruptedException ignored) {}
        }).start();

        // Producer
        new Thread(() -> {
            try {
                queue.put(new Task("Synchronize Task", 1));
                System.out.println("Task running");
            } catch (InterruptedException ignored) {}
        }).start();
    }
}
