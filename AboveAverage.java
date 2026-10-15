import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class AboveAverage {

    record Student(String name, int marks) { }

    public static void main(String[] args) {
        List<Student> students = List.of(
            new Student("Kuda", 72),
            new Student("Ada", 88),
            new Student("Lynn", 65),
            new Student("Ben", 91),
            new Student("Cara", 70),
            new Student("Dan", 58)
        );

        // Step 1: compute class average with a numeric stream
        double average = students.stream()
            .mapToInt(Student::marks)
            .average()
            .orElse(0);

        System.out.printf("Class average: %.2f%n", average);

        // Step 2: filter above average, map to name, sort, collect
        List<String> aboveAverage = students.stream()
            .filter(s -> s.marks() > average)
            .map(Student::name)
            .sorted()                     // alphabetical
            .collect(Collectors.toList());

        System.out.println("Above average (alphabetical): " + aboveAverage);
    }
}