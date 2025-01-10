import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class CustomSortingExample {
    public static void main(String[] args) {
        List<Employee> employees = Arrays.asList(
                new Employee("John Doe", 40000.0),
                new Employee("Jane Doe", 50000.0),
                new Employee("Jim Roe", 45000.0)
        );

        employees.stream()
                .sorted(Comparator.comparingDouble(Employee::getSalary))
                .forEach(System.out::println);
    }
}
