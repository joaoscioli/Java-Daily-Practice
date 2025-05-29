package task_executorService;

import java.util.PriorityQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.TimeUnit;

public class ProjectQueueSystem {
    public static void main(String[] args) throws InterruptedException {
        // Fila de tarefas com prioridade
        PriorityBlockingQueue<Task> taskQueue = new PriorityBlockingQueue<>();

        // Adicionando 100 tarefas de exemplo com prioridades e tempos variados
        for (int i = 1; i <= 100; i++) {
            int priority = (int) (Math.random() * 10) + 1; // Prioridade entre 1 e 10
            long processingTime = (long) (Math.random() * 1000) + 500; // Tempo entre 500ms e 1500ms
            taskQueue.add(new Task(i, priority, processingTime));
        }

        // Teste com threads tradicionais
        System.out.println("Iniciando teste com threads tradicionais...");
        long startTimeTraditional = System.currentTimeMillis();
        ExecutorService traditionalExecutor = Executors.newFixedThreadPool(10); // 10 trabalhadores
        for (int i = 0; i < 10; i++) {
            traditionalExecutor.submit(new Worker(taskQueue));
        }
        traditionalExecutor.shutdown();
        traditionalExecutor.awaitTermination(1, TimeUnit.MINUTES); // Aguarda até 1 minuto
        long endTimeTraditional = System.currentTimeMillis();
        System.out.println("Tempo com threads tradicionais: " + (endTimeTraditional - startTimeTraditional) + " ms");

        // Reabastecendo a fila para o teste com virtual threads
        for (int i = 1; i <= 100; i++) {
            int priority = (int) (Math.random() * 10) + 1;
            long processingTime = (long) (Math.random() * 1000) + 500;
            taskQueue.add(new Task(i, priority, processingTime));
        }

        // Teste com virtual threads
        System.out.println("\nIniciando teste com virtual threads...");
        long startTimeVirtual = System.currentTimeMillis();
        ExecutorService virtualThreadExecutor = Executors.newVirtualThreadPerTaskExecutor();
        for (int i = 0; i < 10; i++) {
            virtualThreadExecutor.submit(new Worker(taskQueue));
        }
        virtualThreadExecutor.shutdown();
        virtualThreadExecutor.awaitTermination(1, TimeUnit.MINUTES); // Aguarda até 1 minuto
        long endTimeVirtual = System.currentTimeMillis();
        System.out.println("Tempo com virtual threads: " + (endTimeVirtual - startTimeVirtual) + " ms");
    }
}
