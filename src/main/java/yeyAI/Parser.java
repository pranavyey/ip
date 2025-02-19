package yeyAI;

/**
 * Parses and executes user commands.
 */
public class Parser {
    private final TaskList tasks;
    private final Ui ui;
    private final Storage storage;

    /**
     * Constructs a Parser with the required components.
     *
     * @param tasks The task list.
     * @param ui The user interface.
     * @param storage The storage handler.
     */
    public Parser(TaskList tasks, Ui ui, Storage storage) {
        this.tasks = tasks;
        this.ui = ui;
        this.storage = storage;
    }

    /**
     * Executes a user command for the CLI.
     *
     * @param input The user command string.
     * @return True to continue execution, false to exit.
     */
    public boolean execute(String input) {
        String response = processCommand(input);
        ui.showMessage(response);
        return !input.equalsIgnoreCase("bye");
    }

    /**
     * Processes a user command and returns a response.
     *
     * @param input The user command string.
     * @return The response message.
     */
    public String processCommand(String input) {
        assert tasks != null : "Tasks should not be null!";
        assert ui != null : "UI should not be null!";
        assert storage != null : "Storage should not be null!";

        String[] words = input.split(" ", 2);
        String commandWord = words[0];
        String arguments = words.length > 1 ? words[1] : "";

        try {
            switch (commandWord) {
                case "list":
                    return tasks.listTasks();
                case "mark":
                    int markIndex = Integer.parseInt(arguments) - 1;
                    assert markIndex >= 0 && markIndex < tasks.getSize() : "Mark index out of bounds!";
                    tasks.getTask(markIndex).setDone();
                    return "Marked task as done:\n" + tasks.getTask(markIndex);
                case "unmark":
                    int unmarkIndex = Integer.parseInt(arguments) - 1;
                    assert unmarkIndex >= 0 && unmarkIndex < tasks.getSize() : "Unmark index out of bounds!";
                    tasks.getTask(unmarkIndex).setUndone();
                    return "Unmarked task:\n" + tasks.getTask(unmarkIndex);
                case "todo":
                    if (arguments.isEmpty()) throw new YeyException("The description of a todo cannot be empty.");
                    tasks.addTask(new Todo(arguments));
                    return "Added new task:\n" + tasks.getLastTask();
                case "deadline":
                    if (arguments.isEmpty()) throw new YeyException("The description of a deadline cannot be empty.");
                    tasks.addTask(new Deadline(arguments));
                    return "Added new deadline:\n" + tasks.getLastTask();
                case "event":
                    if (arguments.isEmpty()) throw new YeyException("The description of an event cannot be empty.");
                    tasks.addTask(new Event(arguments));
                    return "Added new event:\n" + tasks.getLastTask();
                case "delete":
                    int deleteIndex = Integer.parseInt(arguments) - 1;
                    assert deleteIndex >= 0 && deleteIndex < tasks.getSize() : "Delete index out of bounds!";
                    Task deletedTask = tasks.getTask(deleteIndex);
                    tasks.removeTask(deleteIndex);
                    return "Deleted task:\n" + deletedTask;
                case "find":
                    String query = arguments.trim();
                    return tasks.findTasksString(query);
                case "bye":
                    storage.saveTasks(tasks);
                    return "Goodbye! Hope to see you again soon!";
                default:
                    return "Sorry, I don't know what that means.";
            }
        } catch (NumberFormatException e) {
            return "Invalid task number format.";
        } catch (IndexOutOfBoundsException e) {
            return "Task index out of range.";
        } catch (YeyException e) {
            return e.getMessage();
        }
    }

    /**
     * Generates a response for the GUI.
     *
     * @param input The user command string.
     * @return The response message.
     */
    public String getResponse(String input) {
        return processCommand(input);
    }

    public void saveTasks() {
        storage.saveTasks(tasks);
    }
}
