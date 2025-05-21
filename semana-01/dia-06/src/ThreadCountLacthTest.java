import org.junit.jupiter.api.Test;

import java.util.concurrent.CountDownLatch;

public class ThreadCountLacthTest {

    @Test
    public void testCountDownLatch() throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(2);

        Runnable task = () -> {
            System.out.println(Thread.currentThread().getName() + " concluido");
            latch.countDown();
        };

        new Thread(task).start();
        new Thread(task).start();

        latch.await();  // Espera até que todas as threads concluam
        System.out.println("Todas as threads finalizaram!");
    }
}
