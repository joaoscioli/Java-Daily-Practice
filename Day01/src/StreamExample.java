import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamExample {
    public static void main(String[] args) {
        System.out.println("Tutorial de stream Java!! #João");

        // Lista de Numeros da Mega Sena
        List<Integer> numeroDaMegaSena = Arrays.asList(11, 12, 23, 32, 44, 50);
        List<Integer> repeatNumeroDaMegaSena = Arrays.asList(11, 12, 12, 23, 32, 44, 44, 50);
        List<Integer> numerosDaMegaSenha = Arrays.asList(11, 17, 23, 32, 43, 50);
        List<String> names = List.of("Alice", "Bob", "Charlie", "David", "Eve", "Fred", "Alice","Charlie", "Christian");
        // Java 8
        Optional<Integer> maximo =numeroDaMegaSena.stream().max(Comparator.naturalOrder());

        // Original array
        System.out.println(numeroDaMegaSena);

        // Resultados
        System.out.println("Máximo: " + maximo.get());
        numeroDaMegaSena.stream().skip(2).forEach(e -> System.out.print(e + " "));
        System.out.println();
        numeroDaMegaSena.stream().limit(2).forEach(e -> System.out.print(e + " "));
        System.out.println();
        repeatNumeroDaMegaSena.stream().limit(2).forEach(e -> System.out.print(e + " "));
        System.out.println();
        numerosDaMegaSenha.stream().distinct().filter(e -> e % 10 == 3 || e % 10 == 7).forEach(e -> System.out.print(e + " "));
        System.out.println();
        numeroDaMegaSena.stream().distinct().map(e -> e * 2).forEach(e -> System.out.print(e + " "));
        System.out.println();
        names.stream().filter(name -> name.startsWith("C")).distinct().forEach(e -> System.out.print(e + " "));
        System.out.println();
        numeroDaMegaSena.parallelStream().forEach(e -> System.out.print(e + " "));
        System.out.println();
        List<Integer> taken = numeroDaMegaSena.stream().takeWhile(e -> e < 35).collect(Collectors.toList());
        System.out.println(taken);
    }
}
