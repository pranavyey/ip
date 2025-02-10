package yeyAI;

import java.io.IOException;
import java.util.Scanner;

/**
 * The main entry point for the yeyAI application.
 * Initializes the necessary components and runs the task manager.
 */
public class yeyAI {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private Parser parser;

    /**
     * Constructs a new yeyAI instance with the given file path for storage.
     *
     * @param filePath The file path where tasks are stored.
     */
    public yeyAI(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
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
        new yeyAI(System.getProperty("user.dir") + "/tasks.txt").run();
    }
}
