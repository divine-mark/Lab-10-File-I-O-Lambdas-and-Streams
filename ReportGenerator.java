import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

public class ReportGenerator {

    record Learner(String name, int roll, double gpa) {

        static Learner fromCsv(String line) {
            String[] p = line.split(",");
            return new Learner(p[0], Integer.parseInt(p[1]), Double.parseDouble(p[2]));
        }

        String toCsv() {
            return name + "," + roll + "," + gpa;
        }

        // Grade band: A >= 3.5, B >= 3.0, C otherwise
        String gradeBand() {
            if (gpa >= 3.5) return "A";
            if (gpa >= 3.0) return "B";
            return "C";
        }
    }

    public static void main(String[] args) throws IOException {

        // ---- 1. Load learners ----
        Path input = Path.of("learners.csv");
        if (!Files.exists(input)) {
            Files.write(input, List.of(
                "Ada,1,3.9",
                "Ben,2,3.6",
                "Cara,3,3.2",
                "Dan,4,2.9",
                "Eve,5,3.7",
                "Finn,6,2.5",
                "Gwen,7,3.4"
            ));
            System.out.println("Created sample input: " + input.toAbsolutePath());
        }

        List<Learner> learners = new ArrayList<>();
        for (String line : Files.readAllLines(input)) {
            if (!line.isBlank()) learners.add(Learner.fromCsv(line));
        }

        System.out.println("Loaded " + learners.size() + " learners");

        // ---- 2. Group by grade band ----
        Map<String, List<Learner>> byBand = learners.stream()
            .collect(Collectors.groupingBy(
                Learner::gradeBand,
                TreeMap::new,                    // sorted A, B, C
                Collectors.toList()
            ));

        // ---- 3. Write one report file per band ----
        for (Map.Entry<String, List<Learner>> entry : byBand.entrySet()) {
            String band = entry.getKey();
            List<Learner> group = entry.getValue();

            Path report = Path.of("report-" + band + ".csv");
            List<String> lines = new ArrayList<>();
            lines.add("name,roll,gpa"); // header
            for (Learner l : group) lines.add(l.toCsv());

            Files.write(report, lines);

            System.out.printf("Band %s: %d learner(s) -> %s%n",
                    band, group.size(), report);
        }

        System.out.println("\nReports written.");
    }
}