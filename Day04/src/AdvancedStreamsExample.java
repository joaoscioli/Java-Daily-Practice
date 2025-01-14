import java.util.Arrays;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class AdvancedStreamsExample {
    public static void main(String[] args) {
        // Example 01 - Parallel Stream
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        numbers.parallelStream().forEach(System.out::println);

        // Example 02 - Custom Collectors
        Collector<Integer, ?, Integer> summingSquares = Collector.of(
                () -> new int[1],
                (a, t) -> a[0] += t * t,
                (a, b) -> {a[0] += b[0]; return a; },
                a -> a[0]
        );
        List<Integer> numbers2 = Arrays.asList(1, 2, 3, 4, 5);
        int sumOfSquares = numbers2.stream().collect(summingSquares);
        System.out.println(sumOfSquares);

        // Example 03 - Stream
        List<String> words = Arrays.asList("apple", "banana", "cherry");
        List<String> result = words.stream()
                .filter(s -> s.length() > 5)
                .map(String::toUpperCase)
                .collect(Collectors.toList());
        System.out.println(result);
    }
}
