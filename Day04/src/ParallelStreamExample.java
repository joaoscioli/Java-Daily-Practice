import java.util.Arrays;
import java.util.List;

public class ParallelStreamExample {
    public static void main(String[] args) {

        // Example 01 - Parallel Stream
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        numbers.parallelStream().forEach(System.out::println);

    }
}