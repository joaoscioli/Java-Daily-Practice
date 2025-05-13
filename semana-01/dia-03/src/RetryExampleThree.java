import io.github.resilience4j.retry.Retry;
import io.github.resilience4j.retry.RetryConfig;

import java.time.Duration;
import java.util.function.Supplier;

public class RetryExampleThree {
    public static void main(String[] args) {
        // Retry Configuration
        RetryConfig config = RetryConfig.custom()
                .maxAttempts(3) // Número máximo de tentativas
                .waitDuration(Duration.ofMillis(500)) // Duração da espera entre tentativas
                .retryOnException(e -> e instanceof RuntimeException)
                .build();

        Retry retry = Retry.of("myRetry", config);

        // Função que simula uma chamada a um serviço
        Supplier<String> supplier = () -> {
            System.out.println("Tentando executar a operação...");
            throw new RuntimeException("Erro na operação");
        };

        // Decorar a função com Retry
        Supplier<String> retryableSupplier = Retry.decorateSupplier(retry, supplier);

        try {
            String result = retryableSupplier.get();
            System.out.println("Resultado: " + result);
        } catch (Exception e) {
            System.out.println("Falha após todas as tentativas " + e.getMessage());
            System.err.println("Erro na operação: " + e.getMessage());
        }
    }
}
