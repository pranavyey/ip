package yeyAI;

public class Ui {
    public void showWelcomeMessage() {
        System.out.println("Hello! I'm yeyAI.yeyAI\nWhat can I do for you?");
    }

    public void showExitMessage() {
        System.out.println("Bye. Hope to see you again soon!");
    }

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

    public void showTaskAdded(Task task, int size) {
        System.out.println("Got it. I've added this task:");
        System.out.println(task);
        System.out.printf("Now you have %d tasks in the list.\n", size);
    }

    public void showTaskDeleted(Task task) {
        System.out.println("Noted. I've removed this task:");
        System.out.println(task);
    }

    public void showMarkedTask(Task task) {
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(task);
    }

    public void showUnmarkedTask(Task task) {
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println(task);
    }

    public void showError(String message) {
        System.out.println("Error: " + message);
    }
}
