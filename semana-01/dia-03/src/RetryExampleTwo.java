import java.util.concurrent.CompletableFuture;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Supplier;

public class RetryExampleTwo {
    public static void main(String[] args) {
        AtomicInteger attempts = new AtomicInteger(0);

        CompletableFuture<String> future = retryAsync(() -> {
            int attempt =  attempts.incrementAndGet();
            System.out.println("Tentativa #" + attempt);
            if(attempt < 3) {
                throw new RuntimeException("Erro na tentativa #" + attempt);
            }
            return "Sucesso na tentativa #" + attempt;
        }, 3);
        future.thenAccept(System.out::println)
                .exceptionally(ex -> {
                    System.err.println("Todas as tentativas falharam: " + ex.getMessage());
                    return null;
                });
    }

    private static <T> CompletableFuture<T> retryAsync(Supplier<T> task, int maxRetries) {
        return CompletableFuture.supplyAsync(task)
                .exceptionally(ex -> {
            if(maxRetries > 1) {
                return retryAsync(task, maxRetries - 1).join();
            } else {
                throw new RuntimeException("Max retries reached", ex);
            }
        });
    }
}
