import java.util.ArrayList;
import java.util.List;

public class TaskManager {
    private List<Task> tasks;

    public TaskManager() {
        tasks = new ArrayList<>();
    }

    public void addTask(Task task) {
        tasks.add(task);
        System.out.println("Task added: " + task);
    }

    public void removeTask(int id) {
        tasks.removeIf(task -> task.getId() == id);
        System.out.println("Task with ID " + id + " removed.");
    }

    public void viewTasks() {
        if (tasks.isEmpty()) {
            System.out.println("No tasks available.");
        } else {
            tasks.forEach(System.out::println);
        }
    }

    public int getTaskCount() {
        return tasks.size();
    }
}
