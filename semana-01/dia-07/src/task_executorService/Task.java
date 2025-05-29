package task_executorService;

public class Task implements Runnable, Comparable<Task> {
    private final int id;
    private final int priority; // Maior valor = maior prioridade
    private final long processingTimeMs;

    public Task(int id, int priority, long processingTimeMs) {
        this.id = id;
        this.priority = priority;
        this.processingTimeMs = processingTimeMs;
    }

    @Override
    public void run() {
        try {
            System.out.println("Task " + id +  " (Priority: " + priority + ") started.");
            Thread.sleep(processingTimeMs); // Simula processamento
            System.out.println("Task " + id +  " completed.");
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.out.println("Task " + id +  " interrupted.");
        }
    }

    @Override
    public int compareTo(Task other) {
        return Integer.compare(other.priority, this.priority);  // Maior prioridade primeiro
    }

    public int getId() {
        return id;
    }
}
