package yeyAI;

import java.io.IOException;
import java.util.Scanner;

/**
 * The main entry point for the yeyAI application.
 * Initializes the necessary components and runs the task manager.
 */
public class YeyAI {
    private final Ui ui;
    private final Parser parser;

    /**
     * Constructs a new yeyAI instance with the given file path for storage.
     *
     * @param filePath The file path where tasks are stored.
     */
    public YeyAI(String filePath) {
        ui = new Ui();
        Storage storage = new Storage(filePath);
        TaskList tasks;
        try {
            tasks = new TaskList(storage.load());
        } catch (IOException e) {
            ui.showError("Error loading tasks. Starting with an empty list.");
            tasks = new TaskList();
        }
        parser = new Parser(tasks, ui, storage); // Inject dependencies
    }

    /**
     * Runs the task manager, continuously reading user input and executing commands.
     */
    public void run() {
        ui.showWelcomeMessage();
        Scanner scanner = new Scanner(System.in);
        boolean isRunning = true;

        while (isRunning) {
            String input = scanner.nextLine();
            isRunning = parser.execute(input); // Let Parser handle everything
        }
    }

    /**
     * Main method to launch the application.
     *
     * @param args Command-line arguments (not used).
     */
    public static void main(String[] args) {
        new YeyAI(System.getProperty("user.dir") + "/tasks.txt").run();
    }
}
