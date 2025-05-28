import java.util.LinkedList;
import java.util.Queue;

public class LinkedListQueueExample {
    public static void main(String[] args) {
        Queue<Task> queue = new LinkedList<>();
        queue.add(new Task("Task 1", 1));
        queue.add(new Task("Task 2", 2));

        while (!queue.isEmpty()) {
            Task task = queue.remove();
            task.run();
        }
    }
}
