package yeyAI;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.ArrayList;

/**
 * Handles loading and saving tasks from a file.
 */
public class Storage {
    private final Path filePath;

    /**
     * Constructs a new Storage instance for the given file path.
     *
     * @param filePath The file path where tasks are stored.
     */
    public Storage(String filePath) {
        assert filePath != null && !filePath.isEmpty() : "File path cannot be null or empty";
        this.filePath = Path.of(filePath);
    }

    /**
     * Loads tasks from the storage file.
     *
     * @return A list of tasks loaded from the file.
     * @throws IOException If the file cannot be read.
     */
    public ArrayList<Task> load() throws IOException {
        assert filePath != null : "File path should not be null!";

        ArrayList<Task> tasks = new ArrayList<>();
        if (Files.exists(filePath)) {
            List<String> lines = Files.readAllLines(filePath, StandardCharsets.UTF_8);
            for (String line : lines) {
                try {
                    assert line != null && !line.trim().isEmpty() : "Invalid line in tasks file!";
                    tasks.add(Task.fromString(line));
                } catch (YeyException e) {
                    System.out.println("Skipping invalid task: " + e.getMessage());
                }
            }
        }
        return tasks;
    }

    /**
     * Saves the current list of tasks to the storage file.
     *
     * @param tasks The list of tasks to be saved.
     */
    public void saveTasks(TaskList tasks) {
        assert tasks != null : "Task list cannot be null!";
        List<String> lines = new ArrayList<>();
        for (int i = 0; i < tasks.getSize(); i++) {
            lines.add(tasks.getTask(i).toCommand());
        }
        try {
            Files.write(filePath, lines, StandardCharsets.UTF_8);
        } catch (IOException e) {
            System.out.println("Error writing file: " + e.getMessage());
        }
    }
}
