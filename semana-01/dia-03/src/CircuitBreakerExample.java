import io.github.resilience4j.circuitbreaker.CircuitBreaker;
import io.github.resilience4j.circuitbreaker.CircuitBreakerRegistry;

public class CircuitBreakerExample {
    public static void main(String[] args) {
        CircuitBreaker circuitBreaker = CircuitBreakerRegistry.ofDefaults().circuitBreaker("meuCircuito");

        Runnable tarefa = CircuitBreaker.decorateRunnable(circuitBreaker, () -> {
            System.out.println("Executando operação...");
            throw new RuntimeException("Erro na operação");
        });

        try {
            tarefa.run();
        } catch (Exception e) {
            System.out.println("Circuit Breaker ativado!");
        }
    }
}
