import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class FileStats {

    public static void main(String[] args) throws IOException {
        Path path = Path.of("sample.txt");

        // Create a sample file if it doesn't exist so the program is runnable
        if (!Files.exists(path)) {
            Files.write(path, List.of(
                "The quick brown fox",
                "jumps over the lazy dog",
                "The dog barks"
            ));
            System.out.println("Created sample file: " + path.toAbsolutePath());
        }

        List<String> lines = Files.readAllLines(path);

        int lineCount = lines.size();
        int wordCount = 0;
        int charCount = 0;

        for (String line : lines) {
            charCount += line.length();
            if (!line.isBlank()) {
                wordCount += line.trim().split("\\s+").length;
            }
        }

        System.out.println("File:       " + path);
        System.out.println("Lines:      " + lineCount);
        System.out.println("Words:      " + wordCount);
        System.out.println("Characters: " + charCount);
    }
}