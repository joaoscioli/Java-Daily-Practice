import io.github.resilience4j.circuitbreaker.CircuitBreaker;
import io.github.resilience4j.circuitbreaker.CircuitBreakerConfig;
import io.github.resilience4j.circuitbreaker.CircuitBreakerRegistry;

import java.io.IOException;
import java.time.Duration;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Supplier;

public class ProjectCircuitBreakerRetry {
    public static void main(String[] args) {
        // Configurando o CircuitBreaker
        CircuitBreakerConfig breakerConfig = CircuitBreakerConfig.custom()
                .failureRateThreshold(50) // Abre se 50% das chamadas falharem
                .slidingWindowSize(3) // Monitora 3 chamadas
                .minimumNumberOfCalls(3) // Requer 3 chamadas antes de avaliar falhas
                .waitDurationInOpenState(Duration.ofSeconds(10)) // Espera 10s no estado aberto
                .build();
        CircuitBreaker circuitBreaker = CircuitBreakerRegistry.of(breakerConfig).circuitBreaker("meuCircuito");

        // Executor customizado para controle de threads
        Executor executor = Executors.newFixedThreadPool(4);

        // Supplier que simula uma chamada externa
        AtomicInteger attempts = new AtomicInteger(0);
        Supplier<String> task = () -> {
            int attempt = attempts.incrementAndGet();
            System.out.println("Tentativa #" + attempt);
            if (attempt < 3) {
                try {
                    throw new IOException("Erro temporário na tentativa #" + attempt);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            return "Sucesso na tentativa #" + attempt;
        };

        // Executando a chamada com retry e circuit breaker
        CompletableFuture<String> future = retryWithCircuitBreaker(task, circuitBreaker, 3, executor);
        future.thenAccept(System.out::println)
                .exceptionally(ex -> {
                    System.err.println("Falha final: " + ex.getMessage());
                    return null;
                });

        // Para fins de teste, esperar o resultado (remover em produção)
        try {
            Thread.sleep(5000); // Aguarda 5s para garantir que o futuro complete
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static <T> CompletableFuture<T> retryWithCircuitBreaker(
            Supplier<T> task, CircuitBreaker circuitBreaker, int maxRetries, Executor executor) {
        AtomicInteger attempts = new AtomicInteger(0);

        // Função recursiva para tentar a chamada
        CompletableFuture<T> attempt = new CompletableFuture<>();
        tryAsync(task, circuitBreaker, maxRetries, attempts, executor, attempt);
        return attempt;
    }

    private static <T> void tryAsync( Supplier<T> task, CircuitBreaker circuitBreaker, int maxRetries,AtomicInteger attempts, Executor executor, CompletableFuture<T> result) {
        int currentAttempt = attempts.incrementAndGet();

        // Verifica se excedeu o número máximo de tentativas
        if (currentAttempt > maxRetries) {
            result.completeExceptionally(new RuntimeException("Max retries reached"));
            return;
        }

        // Decora o Supplier com o CircuitBreaker
        Supplier<T> decoratedTask = CircuitBreaker.decorateSupplier(circuitBreaker, task);

        // Executa a chamada assincronamente
        CompletableFuture.supplyAsync(decoratedTask, executor)
                .whenComplete((value, ex) -> {
                    if (ex == null) {
                        // Sucesso: completa o resultado
                        result.complete(value);
                    } else {
                        // Falha: verifica se deve tentar novamente
                        if (shouldRetry(ex, circuitBreaker, currentAttempt, maxRetries)) {
                            // Atraso assíncrono de 1 segundo antes da próxima tentativa
                            CompletableFuture.delayedExecutor(Duration.ofSeconds(1).toMillis(), java.util.concurrent.TimeUnit.MILLISECONDS, executor)
                                    .execute(() -> tryAsync(task, circuitBreaker, maxRetries, attempts, executor, result));
                        } else {
                            // Não retry: propaga a exceção
                            result.completeExceptionally(ex);
                        }
                    }
                });
    }

    private static boolean shouldRetry(Throwable ex, CircuitBreaker circuitBreaker,
                                       int currentAttempt, int maxRetries) {
        // Não retry se o circuit breaker está aberto
        if (circuitBreaker.getState() == CircuitBreaker.State.OPEN) {
            return false;
        }
        // Não retry se excedeu o número de tentativas
        if (currentAttempt >= maxRetries) {
            return false;
        }
        // Retry apenas para exceções temporárias (ex.: IOException)
        return ex instanceof IOException;
    }
}
