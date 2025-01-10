public class Box <T> {
    private T content;

    public void add(T item) {
        this.content = item;
    }

    public T remove() {
        return this.content;
    }
}
