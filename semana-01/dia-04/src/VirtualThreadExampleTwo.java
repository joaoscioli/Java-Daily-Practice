import java.util.stream.IntStream;

public class VirtualThreadExampleTwo {
    public static void main(String[] args) {
        IntStream.range(0, 100).parallel().forEach(i -> {
            Thread.ofVirtual().start(() -> {
                System.out.println("Executando tarefa virtual:" +  i +" em uma nova thread virtual..." + Thread.currentThread());
            });
        });

        // Espera 10 segundos para garantir que todas as tarefas terminem
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Todas as tarefas finalizadas..." + Thread.currentThread());
    }
}
