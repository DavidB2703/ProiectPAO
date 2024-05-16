import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;

public class AuditService {
    private static final String FILE_PATH = "src/audit.csv";
    public static void logAction(String action) {
        try (FileWriter fileWriter = new FileWriter(FILE_PATH, true)) {
            fileWriter.append(action);
            fileWriter.append(",");
            fileWriter.append(LocalDateTime.now().toString());
            fileWriter.append("\n");
        } catch (IOException e) {
            System.out.println("Could not log action: " + e.getMessage());
        }
    }
}