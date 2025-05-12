import io.github.resilience4j.circuitbreaker.CircuitBreaker;
import io.github.resilience4j.circuitbreaker.CircuitBreakerRegistry;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Supplier;

public class CircuitBreakerRetry {
    public static void main(String[] args) {
        CircuitBreaker circuitBreaker = CircuitBreakerRegistry.ofDefaults().circuitBreaker("meuCircuito");
        AtomicInteger attempts = new AtomicInteger(0);

        Supplier<String> task = () -> {
            int attempt =  attempts.incrementAndGet();
            System.out.println("Tentativa #" + attempt);
            if(attempt < 3) {
                throw new RuntimeException("Erro na tentativa #" + attempt);
            }
            return "Sucesso na tentativa #" + attempt;
        };

        CompletableFuture<String> future = retryWithCircuitBreaker(task, circuitBreaker, 3);
        future.thenAccept(System.out::println)
                .exceptionally(ex -> {
                    System.err.println("Todas as tentativas falharam: " + ex.getMessage());
                    return null;
                });
    }

    public static <T> CompletableFuture<T> retryWithCircuitBreaker(Supplier<T> task, CircuitBreaker circuitBreaker, int maxRetries) {
        return CompletableFuture.supplyAsync(CircuitBreaker.decorateSupplier(circuitBreaker, task))
                .exceptionally(ex -> {
                    if(maxRetries > 1 && circuitBreaker.getState() != CircuitBreaker.State.OPEN) {
                        return retryWithCircuitBreaker(task, circuitBreaker, maxRetries - 1).join();
                    } else {
                        throw new RuntimeException("Max retries reached", ex);
                    }
                });
    }
}
