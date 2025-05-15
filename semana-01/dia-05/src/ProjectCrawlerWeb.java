import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public class ProjectCrawlerWeb {
    public static void main(String[] args) {
        List<String> urls = List.of(
                "https://oglobo.globo.com/blogs/a-hora-da-ciencia/",
                "https://www.estadao.com.br/ultimas/",
                "https://www.cnnbrasil.com.br/economia/",
                "https://comunidade.tecnoblog.net/",
                "https://noticias.uol.com.br/politica/"
        );

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://www.google.com/search?q=inurl:"))
                .GET()
                .build();

        CompletableFuture<HttpResponse<String>> future = client.sendAsync(request, HttpResponse.BodyHandlers.ofString());
        future.thenAccept(response -> {
            System.out.println(response.statusCode());
            System.out.println(response.body());
        });
        future.join();


        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        System.out.print("");
    }
}
