import java.util.concurrent.Executors;

public class VirtualThreadExample {
    public static void main(String[] args) {
        try (var executor = Executors.newVirtualThreadPerTaskExecutor()) {
            for (int i = 0; i < 10_000; i++) {
                executor.submit(() -> {
                    System.out.println("Task running in virtual thread: " + Thread.currentThread());
                    try {
                        Thread.sleep(1000);
                    } catch (Exception e) {}
                });
            }
        }
    }
}
