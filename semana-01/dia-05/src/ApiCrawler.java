import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;
import java.util.zip.InflaterInputStream;

public class ApiCrawler {
    public static void main(String[] args) {
        try {
            URL url = new URL("https://jsonplaceholder.typicode.com/todos/1");
            HttpURLConnection coon = (HttpURLConnection) url.openConnection();
            coon.setRequestMethod("GET");

            Scanner scanner = new Scanner(new InputStreamReader(coon.getInputStream()));
            while (scanner.hasNext()) {
                System.out.println(scanner.nextLine());
            }
            scanner.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
