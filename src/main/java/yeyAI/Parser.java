package yeyAI;

public class Parser {
    private TaskList tasks;
    private Ui ui;
    private Storage storage;

    public Parser(TaskList tasks, Ui ui, Storage storage) {
        this.tasks = tasks;
        this.ui = ui;
        this.storage = storage;
    }

    public boolean execute(String input) {
        String[] words = input.split(" ", 2);
        String commandWord = words[0];
        String arguments = words.length > 1 ? words[1] : "";

        try {
            switch (commandWord) {
                case "list":
                    ui.showTaskList(tasks);
                    break;
                case "mark":
                    int markIndex = Integer.parseInt(arguments) - 1;
                    tasks.getTask(markIndex).setDone();
                    ui.showMarkedTask(tasks.getTask(markIndex));
                    break;
                case "unmark":
                    int unmarkIndex = Integer.parseInt(arguments) - 1;
                    tasks.getTask(unmarkIndex).setUndone();
                    ui.showUnmarkedTask(tasks.getTask(unmarkIndex));
                    break;
                case "todo":
                    if (arguments.isEmpty()) throw new YeyException("The description of a todo cannot be empty.");
                    tasks.addTask(new Todo(arguments));
                    ui.showTaskAdded(tasks.getLastTask(), tasks.getSize());
                    break;
                case "deadline":
                    if (arguments.isEmpty()) throw new YeyException("The description of a deadline cannot be empty.");
                    tasks.addTask(new Deadline(arguments));
                    ui.showTaskAdded(tasks.getLastTask(), tasks.getSize());
                    break;
                case "event":
                    if (arguments.isEmpty()) throw new YeyException("The description of an event cannot be empty.");
                    tasks.addTask(new Event(arguments));
                    ui.showTaskAdded(tasks.getLastTask(), tasks.getSize());
                    break;
                case "delete":
                    int deleteIndex = Integer.parseInt(arguments) - 1;
                    ui.showTaskDeleted(tasks.getTask(deleteIndex));
                    tasks.removeTask(deleteIndex);
                    break;
                case "bye":
                    ui.showExitMessage();
                    storage.saveTasks(tasks);
                    return false; // Signals termination
                default:
                    ui.showError("Sorry, I don't know what that means.");
                    break;
            }
        } catch (NumberFormatException e) {
            ui.showError("Invalid task number format.");
        } catch (IndexOutOfBoundsException e) {
            ui.showError("yeyAI.Task index out of range.");
        } catch (YeyException e) {
            ui.showError(e.getMessage());
        }
        return true; // Continue running
    }
}
