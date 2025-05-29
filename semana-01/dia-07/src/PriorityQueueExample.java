import java.util.PriorityQueue;
import java.util.Queue;

public class PriorityQueueExample {
    public static void main(String[] args) {
        Queue<Task> queue = new PriorityQueue<>();
        queue.add(new Task("Right Priority", 1));
        queue.add(new Task("Low Priority 3", 5));
        queue.add(new Task("Medium Priority", 3));

        while (!queue.isEmpty()) {
            queue.poll().run();
        }
    }
}
