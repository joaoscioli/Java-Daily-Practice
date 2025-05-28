import org.junit.jupiter.api.Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ProjectCounterServiceTest {
    @Test
    void testConcurrentIncrement() throws InterruptedException {
        ProjectCounterService  counterService = new ProjectCounterService();
        int numberOfTasks = 100;

        // Usando Virtual Threads
        try (ExecutorService executor = Executors.newVirtualThreadPerTaskExecutor()) {
            for (int i = 0; i < numberOfTasks; i++) {
                executor.submit(() -> counterService.increment());
            }
            // Aguarda todas as tarefas terminarem
            executor.shutdown();
            executor.awaitTermination(1, TimeUnit.MINUTES);
        }

        // Verifica se o contador atingiu o valor esperado
        assertEquals(numberOfTasks, counterService.getCount());
    }
}
