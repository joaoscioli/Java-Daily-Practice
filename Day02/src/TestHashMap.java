import java.util.HashMap;
import java.util.Map;

public class TestHashMap {
    public static void main(String[] args) {
        HashMap<String, Integer> mapa = new HashMap<>();
        mapa.put("Alice", 90);
        mapa.put("Bob", 85);
        mapa.put("Carol", 92);

        for (Map.Entry<String, Integer> entrada : mapa.entrySet()) {
            System.out.println(entrada.getKey() + " " + entrada.getValue());
        }
    }
}
