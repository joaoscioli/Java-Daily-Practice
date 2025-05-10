import java.util.*;
import java.util.concurrent.*;
import java.util.stream.*;

public class Main {
    public static void main(String[] args) {
        // Criar uma lista de números de 1 a 100 como exemplo
        List<Integer> numbers = IntStream.rangeClosed(1, 100)
                .boxed()
                .collect(Collectors.toList());

        // Dividir a lista em 4 sublistas para processamento paralelo
        int chunkSize = numbers.size() / 4;
        List<List<Integer>> sublists = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            int start = i * chunkSize;
            int end = (i == 3) ? numbers.size() : start + chunkSize;
            sublists.add(numbers.subList(start, end));
        }

        // Usar StructuredTaskScope com try-with-resources
        try (var scope = new StructuredTaskScope.ShutdownOnFailure()) {
            List<StructuredTaskScope.Subtask<Long>> futures = new ArrayList<>();

            // Submeter uma tarefa para cada sublista
            for (List<Integer> sublist : sublists) {
                futures.add(scope.fork(() -> processSublist(sublist)));
            }

            // Aguardar todas as tarefas terminarem
            scope.join();
            scope.throwIfFailed();

            // Somar os resultados das tarefas
            long totalSum = 0;
            for (StructuredTaskScope.Subtask<Long> future : futures) {
                totalSum += future.get();
            }

            System.out.println("Soma total dos quadrados pares: " + totalSum);
        } catch (InterruptedException | ExecutionException e) {
            Thread.currentThread().interrupt();
            System.err.println("Erro durante a execução: " + e.getMessage());
        }
    }

    // Função que processa uma sublista: calcula quadrados, filtra pares e soma
    private static long processSublist(List<Integer> sublist) {
        List<Integer> squares = calculateSquares(sublist);
        List<Integer> evenSquares = filterEvenNumbers(squares);
        return sumResults(evenSquares);
    }

    // Função 1: Calcula os quadrados dos números de uma lista
    private static List<Integer> calculateSquares(List<Integer> numbers) {
        return numbers.stream()
                .map(n -> n * n)
                .collect(Collectors.toList());
    }

    // Função 2: Filtra os números pares de uma lista
    private static List<Integer> filterEvenNumbers(List<Integer> numbers) {
        return numbers.stream()
                .filter(n -> n % 2 == 0)
                .collect(Collectors.toList());
    }

    // Função 3: Soma os números de uma lista
    private static long sumResults(List<Integer> numbers) {
        return numbers.stream()
                .mapToLong(Integer::longValue)
                .sum();
    }
}