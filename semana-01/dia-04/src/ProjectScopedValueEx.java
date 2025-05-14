import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.List;
import java.util.ArrayList;

public class ProjectScopedValueEx {
    private static final ScopedValue<String> STRING_ID_VALUE = ScopedValue.newInstance();

    public static void main(String[] args) {
        try {
            ScopedValue.where(STRING_ID_VALUE, "TX123").run(() -> {
                try (ExecutorService executor = Executors.newVirtualThreadPerTaskExecutor()) {
                    List<Future<?>> futures = new ArrayList<>();

                    // Tarefa 1: Verificar se o ID é válido
                    futures.add(executor.submit(() -> {
                        String transactionId = STRING_ID_VALUE.get();
                        boolean isValid = checkTransactionId(transactionId);
                        System.out.println("Transaction ID " + transactionId + " is valid: " + isValid);
                    }));

                    // Tarefa 2: Retornar especificações de um produto
                    futures.add(executor.submit(() -> {
                        String transactionId = STRING_ID_VALUE.get();
                        String productSpecs = getProductSpecifications(transactionId);
                        System.out.println("Product specs for " + transactionId + ": " + productSpecs);
                    }));

                    // Tarefa 3: Logar o ID de transação
                    futures.add(executor.submit(() -> {
                        String transactionId = STRING_ID_VALUE.get();
                        logTransaction(transactionId);
                    }));

                    // Esperar todas as tarefas terminarem
                    for (Future<?> future : futures) {
                        try {
                            future.get(); // Bloqueia até a tarefa estar concluída
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static boolean checkTransactionId(String transactionId) {
        return transactionId != null && transactionId.startsWith("TX");
    }

    private static String getProductSpecifications(String transactionId) {
        return "Product XYZ, Category: Electronics, Price: $100";
    }

    private static void logTransaction(String transactionId) {
        System.out.println("Logging transaction: " + transactionId);
    }
}