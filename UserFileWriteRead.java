import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class UserFileWriteRead {

    public static void main(String[] args) {
        Path path = Path.of("user-lines.txt");
        List<String> typed = new ArrayList<>();

        // ---- WRITE: read 5 lines from the user and save ----
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.println("Enter 5 lines:");
            for (int i = 1; i <= 5; i++) {
                System.out.print("Line " + i + ": ");
                typed.add(scanner.nextLine());
            }
        } // scanner closed automatically

        try {
            Files.write(path, typed);
            System.out.println("Saved " + typed.size() + " lines to " + path.toAbsolutePath());
        } catch (IOException e) {
            System.out.println("Write failed: " + e.getMessage());
            return;
        }

        // ---- READ: load them back and print ----
        try {
            List<String> loaded = Files.readAllLines(path);
            System.out.println();
            System.out.println("Read back from file:");
            for (int i = 0; i < loaded.size(); i++) {
                System.out.println((i + 1) + ": " + loaded.get(i));
            }
        } catch (IOException e) {
            System.out.println("Read failed: " + e.getMessage());
        }
    }
}