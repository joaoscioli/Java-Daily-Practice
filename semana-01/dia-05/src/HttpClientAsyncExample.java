import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.concurrent.CompletableFuture;

public class HttpClientAsyncExample {
    public static void main(String[] args) {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://api.github.com"))
                .GET()
                .build();

        CompletableFuture<HttpResponse<String>> futureResponse = client.sendAsync(request, HttpResponse.BodyHandlers.ofString());

        // Quando a resposta estiver disponível, executa o bloco de código
        futureResponse.thenAccept(response -> {
            System.out.println("Código de resposta: " + response.statusCode());
            System.out.println("Corpo da resposta: " + response.body());
        });

        System.out.println("Requisição enviada! O programa continua executando enquanto aguarda a resposta...");

        // Apenas para evitar que o programa termine antes da resposta ser processada
        futureResponse.join();
    }
}