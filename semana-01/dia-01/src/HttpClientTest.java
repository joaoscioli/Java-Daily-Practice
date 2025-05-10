import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

public class HttpClientTest {
    public static void main(String[] args) throws InterruptedException {
        // Criar o HttpClient
        HttpClient client = HttpClient.newHttpClient();

        // Criar o HttpRequest para GET em http://localhost:8080
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:8080"))
                .GET()
                .build();

        // Contador para respostas bem-sucedidas
        AtomicInteger successCount = new AtomicInteger(0);

        // Criar ExecutorService com virtual threads
        try (ExecutorService executor = Executors.newVirtualThreadPerTaskExecutor()) {
            long startTime = System.currentTimeMillis();

            // Enviar 10.000 requisições simultâneas
            for (int i = 0; i < 10_000; i++) {
                executor.submit(() -> {
                    try {
                        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
                        if (response.statusCode() == 200 && response.body().equals("Hello, World!")) {
                            successCount.incrementAndGet();
                        } else {
                            System.err.println("Erro na requisição: status " + response.statusCode());
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                });
            }

            // Aguardar todas as tarefas terminarem
            executor.shutdown();
            executor.awaitTermination(60, java.util.concurrent.TimeUnit.SECONDS);

            long endTime = System.currentTimeMillis();
            System.out.println("Tempo total: " + (endTime - startTime) + " ms");
            System.out.println("Requisições bem-sucedidas: " + successCount.get() + "/10000");
        }
    }
}