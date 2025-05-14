public class VirtualThreadExampleOne {
    public static void main(String[] args) {
        Thread virtualThread = Thread.ofVirtual().start(() ->{
            System.out.println("Executando tarefa virtual em uma nova thread virtual..." + Thread.currentThread());
            });

        // Aguarda a execução da tarefa virtual
        try {
            virtualThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Tarefa virtual finalizada..." + Thread.currentThread());
    }
}
