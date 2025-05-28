import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ConcurrentQueueExample {
    public static void main(String[] args) {
        Queue<Task> queue = new ConcurrentLinkedQueue<>();
        queue.add(new Task("Task 1", 1));
        queue.add(new Task("Task 2", 2));

        ExecutorService executorService = Executors.newFixedThreadPool(2);
        while (!queue.isEmpty()) {
            Task task = queue.poll();
            if (task != null) executorService.submit(task);
        }
        executorService.shutdown();
    }
}
