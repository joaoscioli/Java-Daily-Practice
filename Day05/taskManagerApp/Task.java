import java.time.LocalDate;

public class Task {
    private int id;
    private String title;
    private LocalDate deadline;

    public Task(int id, String title, LocalDate deadline) {
        this.id = id;
        this.title = title;
        this.deadline = deadline;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public LocalDate getDeadline() {
        return deadline;
    }

    @Override
    public String toString() {
        return "ID: " + id + " Title: " + title + " Deadline: " + deadline;
    }
}
