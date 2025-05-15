import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;


public class CrawlerExampleOne {
    public static void main(String[] args) {
        try {
            Document doc = Jsoup.connect("https://vestibular.brasilescola.uol.com.br").get();
            Elements title = doc.select("div");    // Seletor CSS para titulos

            for (Element titles : title) {
                System.out.println(titles.text());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
