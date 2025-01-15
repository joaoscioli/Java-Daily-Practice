import java.time.LocalDate;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        TaskManager taskManager = new TaskManager();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nTask Manager Menu:");
            System.out.println("1. Add Task");
            System.out.println("2. Display Tasks");
            System.out.println("3. Remove Task");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Enter Task title: ");
                    String title = scanner.nextLine();
                    System.out.println("Enter deadline (YYYY-MM-DD): ");
                    LocalDate deadline = LocalDate.parse(scanner.nextLine());
                    taskManager.addTask(new Task(taskManager.getTaskCount() + 1, title, deadline));
                    break;
                case 2:
                    taskManager.viewTasks();
                    break;
                case 3:
                    System.out.print("Enter task ID to remove: ");
                    int id = scanner.nextInt();
                    taskManager.removeTask(id);
                    break;
                case 4:
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}
