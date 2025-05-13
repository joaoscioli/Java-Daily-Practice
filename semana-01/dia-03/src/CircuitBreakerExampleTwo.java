import io.github.resilience4j.circuitbreaker.CircuitBreaker;
import io.github.resilience4j.circuitbreaker.CircuitBreakerConfig;
import io.github.resilience4j.retry.Retry;
import io.github.resilience4j.retry.RetryConfig;

import java.time.Duration;
import java.util.function.Supplier;

public class CircuitBreakerExampleTwo {
    public static void main(String[] args) {
        // Retry Configuration
        RetryConfig retryConfig = RetryConfig.custom()
                .maxAttempts(3) // Número máximo de tentativas
                .waitDuration(Duration.ofMillis(500)) // Duração da espera entre tentativas
                .retryOnException(e -> e instanceof RuntimeException)
                .build();
        Retry retry = Retry.of("myRetry", retryConfig);


        // Configuração do Circuit Breaker
        CircuitBreakerConfig config = CircuitBreakerConfig.custom()
                .failureRateThreshold(50) // abre se 50% das requisições falharem
                .slidingWindowSize(10) // monitora 10 chamadas no estado Closed
                .waitDurationInOpenState(Duration.ofSeconds(5)) // aguarda 5 segundos no estado Open antes de reabrir
                .permittedNumberOfCallsInHalfOpenState(3) // testa 3 chamadas no estado HalfOpen
                .build();
        CircuitBreaker circuitBreaker = CircuitBreaker.of("meuCircuito", config);

        // Função que simula uma chamada a um serviço
        Supplier<String> supplier = () -> {
            System.out.println("Tentando chamar o serviço...");
            if (Math.random() > 0.6) { // Simula 40% de sucesso
                return "Sucesso!";
            } else {
                throw new RuntimeException("Falha no serviço!");
            }
        };

        // Combinar Retry e Circuit Breaker
        Supplier<String> decorateSupplier = Retry.decorateSupplier(retry, CircuitBreaker.decorateSupplier(circuitBreaker, supplier));

        // Executar chamadas
        for(int i=0; i<20; i++) {
            try {
                String result = decorateSupplier.get();
                System.out.println("Resultado: " + result);
            } catch (Exception e) {
                System.out.println("Erro: " + e.getMessage());
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ignored) {
            }
        }
    }
}
