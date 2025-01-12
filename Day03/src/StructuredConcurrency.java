import java.util.concurrent.*;

public class StructuredConcurrency {
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        try (var scope = new StructuredTaskScope.ShutdownOnFailure()) {
            StructuredTaskScope.Subtask<String> future1 = scope.fork(() -> {
                return "Result 1";
            });
            StructuredTaskScope.Subtask<String> future2 = scope.fork(() -> {
                return "Result 2";
            });
            scope.join();
            scope.throwIfFailed();

            System.out.println("Structured Concurrency - " + future1.get() + ", " + future2.get());
        }
    }
}