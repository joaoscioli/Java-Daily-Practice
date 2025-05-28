public class Task implements Runnable, Comparable<Task> {
    private final String name;
    private final int priority;

    public Task(String name, int priority) {
        this.name = name;
        this.priority = priority;
    }

    @Override
    public void run() {
        System.out.println("Running task " + name);
    }

    @Override
    public int compareTo(Task o) {
        return Integer.compare(this.priority, o.priority); // menor prioridade = mais urgente
    }

    @Override
    public String toString() {
        return name + " (priority: " + priority + ")";
    }
}
