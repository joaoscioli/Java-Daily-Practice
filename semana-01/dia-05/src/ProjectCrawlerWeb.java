import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public class ProjectCrawlerWeb {
    public static void main(String[] args) throws IOException, InterruptedException {
        List<String> urls = List.of(
                "https://oglobo.globo.com/blogs/a-hora-da-ciencia/",
                "https://www.estadao.com.br/ultimas/",
                "https://www.cnnbrasil.com.br/economia/",
                "https://comunidade.tecnoblog.net/",
                "https://noticias.uol.com.br/politica/"
        );
        HttpClient client = HttpClient.newHttpClient();
        List<Thread> threads = new ArrayList<>();

        urls.forEach(url -> {
            Thread vt = Thread.ofVirtual().start(() -> {
                try {
                    HttpRequest request = HttpRequest.newBuilder().uri(URI.create(url)).GET().build();
                    HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
                    Document doc = Jsoup.parse(response.body());
                    String title = doc.select("title").text();
                    System.out.println("URL: " + url + " | Título: " + title);
                } catch (Exception e) {
                    System.err.println("Erro ao processar " + url + ": " + e.getMessage());
                }
            });
            threads.add(vt);
        });

    // Esperar todas as threads terminarem
        threads.forEach(thread -> {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
    }
}