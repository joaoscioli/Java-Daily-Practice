import java.util.concurrent.BlockingQueue;
import java.util.concurrent.PriorityBlockingQueue;

public class PriorityBlockingQueueExample {
    public static void main(String[] args) throws InterruptedException {
        BlockingQueue<Task> queue = new PriorityBlockingQueue<>();
        queue.put(new Task("right priority",1));
        queue.put(new Task("low priority", 5));
        queue.put(new Task("medium priority", 3));

        for (int i = 0; i < 3; i++) {
            queue.take().run();
        }
    }
}
