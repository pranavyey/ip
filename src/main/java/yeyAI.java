import java.io.IOException;
import java.util.Scanner;

public class yeyAI {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private Parser parser;

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

    public void run() {
        ui.showWelcomeMessage();
        Scanner scanner = new Scanner(System.in);
        boolean isRunning = true;

        while (isRunning) {
            String input = scanner.nextLine();
            isRunning = parser.execute(input); // Let Parser handle everything
        }
    }

    public static void main(String[] args) {
        new yeyAI(System.getProperty("user.dir") + "/tasks.txt").run();
    }
}
