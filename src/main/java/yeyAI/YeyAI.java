package yeyAI;

import java.io.IOException;

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
     * Runs the task manager in CLI mode.
     */
    public void run() {
        ui.showWelcomeMessage();
    }

    /**
     * Processes user input and returns the response string for the GUI.
     *
     * @param input User input command.
     * @return Response message from yeyAI.
     */
    public String getResponse(String input) {
        assert parser != null : "Parser should not be null!";
        if (input.equalsIgnoreCase("hello")) {
            return "Hello! I'm yeyAI\nWhat can I do for you?";
        }
        return parser.getResponse(input);
    }

    /**
     * Main method to launch the application.
     *
     * @param args Command-line arguments (not used).
     */
    public static void main(String[] args) {
        new YeyAI(System.getProperty("user.dir") + "/tasks.txt").run();
    }

    public void saveTasks() {
        parser.saveTasks();
    }
}

