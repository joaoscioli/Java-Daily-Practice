import java.util.concurrent.atomic.AtomicInteger;

public class ProjectCounterService {
    private final AtomicInteger counter = new AtomicInteger(0);

    public void increment() {
        counter.incrementAndGet();
    }

    public int getCount() {
        return counter.get();
    }
}
