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
        String[] words = input.split(" ", 2);
        String commandWord = words[0];
        String arguments = words.length > 1 ? words[1] : "";

        try {
            switch (commandWord) {
                case "list":
                    return listTasks();
                case "mark":
                    return markTask(arguments);
                case "unmark":
                    return unmarkTask(arguments);
                case "todo":
                    return todoTask(arguments);
                case "deadline":
                    return deadlineTask(arguments);
                case "event":
                    return eventTask(arguments);
                case "delete":
                    return deleteTask(arguments);
                case "find":
                    return findTask(arguments);
                case "bye":
                    return bye();
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
    private String listTasks() {
        return tasks.listTasks();
    }
    private String markTask(String arguments) {
        int markIndex = Integer.parseInt(arguments) - 1;
        tasks.getTask(markIndex).setDone();
        return "Marked task as done:\n" + tasks.getTask(markIndex);
    }
    private String unmarkTask(String arguments) {
        int unmarkIndex = Integer.parseInt(arguments) - 1;
        tasks.getTask(unmarkIndex).setUndone();
        return "Unmarked task:\n" + tasks.getTask(unmarkIndex);
    }
    private String todoTask(String arguments) throws YeyException {
        if (arguments.isEmpty()) throw new YeyException("The description of a todo cannot be empty.");
        tasks.addTask(new Todo(arguments));
        return "Added new task:\n" + tasks.getLastTask();
    }
    private String deadlineTask(String arguments) throws YeyException {
        if (arguments.isEmpty()) throw new YeyException("The description of a deadline cannot be empty.");
        tasks.addTask(new Deadline(arguments));
        return "Added new deadline:\n" + tasks.getLastTask();
    }
    private String eventTask(String arguments) throws YeyException {
        if (arguments.isEmpty()) throw new YeyException("The description of a event cannot be empty.");
        tasks.addTask(new Event(arguments));
        return "Added new event:\n" + tasks.getLastTask();
    }
    private String deleteTask(String arguments) throws YeyException {
        if (tasks.getSize() == 0) throw new YeyException("There are no tasks in the List.");
        int deleteIndex = Integer.parseInt(arguments) - 1;
        Task deletedTask = tasks.getTask(deleteIndex);
        tasks.removeTask(deleteIndex);
        return "Deleted task:\n" + deletedTask;
    }
    private String findTask(String arguments) {
        String query = arguments.trim();
        return tasks.findTasksString(query);
    }
    private String bye() {
        storage.saveTasks(tasks);
        return "Goodbye! Hope to see you again soon!";
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
