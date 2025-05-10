import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

public class RetryExample {
    public static final int MAX_RETRIES = 3;
    public static final long DELAY_MS = 1000;

    public CompletableFuture<String> callServiceWithRetry() {
        return attemptServiceCall(0);
    }

    private CompletableFuture<String> attemptServiceCall(int attempt) {
        return CompletableFuture.supplyAsync(() -> {
            if(Math.random() < 0.7) { // Simula uma falha 70% das vezes
                throw new RuntimeException("Service failed");
            }
            return "Service response";
        }).exceptionally(ex -> {
            if(attempt < MAX_RETRIES) {
                try {
                    TimeUnit.MILLISECONDS.sleep(DELAY_MS);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
                return attemptServiceCall(attempt + 1).join();
            }
            throw new RuntimeException("Max retries reached", ex);
        });
    }


}
