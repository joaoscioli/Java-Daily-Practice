package task_executorService;

import java.util.concurrent.PriorityBlockingQueue;

public class Worker implements Runnable {
    private final PriorityBlockingQueue<Task> queue;

    public Worker(PriorityBlockingQueue<Task> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            try {
                Task task = queue.take(); // Bloqueia até que uma tarefa esteja disponivel
                task.run();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }
        }
    }
}
