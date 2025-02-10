package yeyAI;

/**
 * Handles user interactions such as displaying messages and errors.
 */
public class Ui {

    /**
     * Displays a welcome message to the user.
     */
    public void showWelcomeMessage() {
        System.out.println("Hello! I'm yeyAI\nWhat can I do for you?");
    }

    /**
     * Displays an exit message when the program terminates.
     */
    public void showExitMessage() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    /**
     * Displays the list of tasks.
     *
     * @param tasks The TaskList containing the tasks.
     */
    public void showTaskList(TaskList tasks) {
        if (tasks.getSize() == 0) {
            System.out.println("You have no tasks.");
            return;
        }
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < tasks.getSize(); i++) {
            System.out.printf("%d. %s\n", i + 1, tasks.getTask(i));
        }
    }

    /**
     * Displays a message when a task is added.
     *
     * @param task The task that was added.
     * @param size The total number of tasks after addition.
     */
    public void showTaskAdded(Task task, int size) {
        System.out.println("Got it. I've added this task:");
        System.out.println(task);
        System.out.printf("Now you have %d tasks in the list.\n", size);
    }

    /**
     * Displays a message when a task is deleted.
     *
     * @param task The task that was removed.
     */
    public void showTaskDeleted(Task task) {
        System.out.println("Noted. I've removed this task:");
        System.out.println(task);
    }

    /**
     * Displays a message when a task is marked as done.
     *
     * @param task The task that was marked as done.
     */
    public void showMarkedTask(Task task) {
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(task);
    }

    /**
     * Displays a message when a task is marked as not done.
     *
     * @param task The task that was marked as not done.
     */
    public void showUnmarkedTask(Task task) {
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println(task);
    }

    /**
     * Displays an error message.
     *
     * @param message The error message to display.
     */
    public void showError(String message) {
        System.out.println("Error: " + message);
    }
}
