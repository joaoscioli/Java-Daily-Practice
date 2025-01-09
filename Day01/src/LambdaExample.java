import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class LambdaExample {
    public static void main(String[] args) {
        // example 01
        System.out.println("Example 01:");
        Function<Integer, Integer> square = (x) -> x * x;
        System.out.print(square.apply(10));
        System.out.println();

        // example 02
        System.out.println("Example 02:");
        List<String> names = List.of("John", "Jane", "Jack", "Bob");
        names.stream().distinct().filter(name -> name.startsWith("J")).forEach(System.out::print);
        System.out.println();

        // example 03
        System.out.println("Example 03:");
        List<String> cities = new ArrayList<>(List.of("Bangalore", "London", "New York"));
        cities.sort((a, b) -> b.compareTo(a));
        cities.forEach(System.out::print);
        System.out.println();
    }
}

