import java.util.concurrent.Callable;
import java.util.concurrent.StructuredTaskScope;

public class StructuredConcurrencyExample {
    public static void main(String[] args) throws Exception {
        try (var scope = new StructuredTaskScope.ShutdownOnFailure()) {
            Callable<String> task1 = () -> {
                Thread.sleep(1000);
                return "Resultado da tarefa 1";
            };
            Callable<String> task2 = () -> {
                Thread.sleep(500);
                return "Resultado da tarefa 2";
            };

            var subtask1 = scope.fork(task1);
            var subtask2 = scope.fork(task2);

            scope.join();   // Aguardan todas as subtarefas terminarem
            System.out.println(subtask1.get()); // Resultado da tarefa 1
            System.out.println(subtask2.get()); // Resultado da tarefa 2
        }
    }
}
