import java.util.ArrayList;
import java.util.Scanner;
public class yeyAI {
    public static void main(String[] args) {
        String line = " ____________________________\n";
        Scanner scanner = new Scanner(System.in);
        ArrayList<Task> tasks = new ArrayList<>();
        int index = 1;

        System.out.println(line + "Hello! I'm yeyAI\n" + "What can I do for you?\n" + line);
        String input = scanner.nextLine();
        while (!input.equals("bye")) {
            try {
                if (input.equals("list")) { //list branch
                    System.out.println("Here are the tasks in your list:");
                    for (int i = 0; i < index - 1; i++) {
                        int display = i + 1;
                        System.out.printf("%d.%s\n", display, tasks.get(i).toString());
                    }
                    input = scanner.nextLine();
                } else if (input.startsWith("mark")) { //mark branch
                    int taskIndex = Integer.parseInt(input.split(" ")[1]) - 1;
                    tasks.get(taskIndex).setDone();
                    System.out.println("Nice! I've marked this task as done:");
                    System.out.println(tasks.get(taskIndex).toString());
                    input = scanner.nextLine();
                } else if (input.startsWith("unmark")) { //unmark branch
                    int taskIndex = Integer.parseInt(input.split(" ")[1]) - 1;
                    tasks.get(taskIndex).setUndone();
                    System.out.println("OK, I've marked this task as not done yet:");
                    System.out.println(tasks.get(taskIndex).toString());
                    input = scanner.nextLine();
                } else if (input.startsWith("todo")) { //new to-do task branch
                    String[] parts = input.split(" ", 2);
                    if (parts.length < 2 || parts[1].trim().isEmpty()) {
                        throw new YeyException("Description of a todo cannot be empty. Try again with a description");
                    }
                    Task t = new Todo(parts[1]);
                    tasks.add(t);
                    System.out.println(line + "Got it. I've added this task:");
                    System.out.println(t);
                    System.out.printf("Now you have %d tasks in the list.\n", index);
                    index++;
                    input = scanner.nextLine();
                } else if (input.startsWith("deadline")) {
                    String[] parts = input.split(" ", 2);
                    if (parts.length < 2 || parts[1].trim().isEmpty()) {
                        throw new YeyException("Description of a deadline cannot be empty. Try again with a description");
                    }
                    Task t = new Deadline(parts[1]);
                    tasks.add(t);
                    System.out.println(line + "Got it. I've added this task:");
                    System.out.println(t);
                    System.out.printf("Now you have %d tasks in the list.\n", index);
                    index++;
                    input = scanner.nextLine();
                } else if (input.startsWith("event")) {
                    String[] parts = input.split(" ", 2);
                    if (parts.length < 2 || parts[1].trim().isEmpty()) {
                        throw new YeyException("Description of a event cannot be empty. Try again with a description");
                    }
                    Task t = new Event(parts[1]);
                    tasks.add(t);
                    System.out.println(line + "Got it. I've added this task:");
                    System.out.println(t);
                    System.out.printf("Now you have %d tasks in the list.\n", index);
                    index++;
                    input = scanner.nextLine();
                } else if (input.startsWith("delete")) {
                    String[] parts = input.split(" ", 2);
                    if (parts.length < 2 || parts[1].trim().isEmpty()) {
                        throw new YeyException("Specify the task to be deleted.");
                    }
                    if (Integer.parseInt(parts[1]) > index - 1) {
                        throw new YeyException("Task to be deleted must exist within list");
                    }
                    tasks.remove(Integer.parseInt(parts[1]) - 1);
                    System.out.println("Task deleted!");
                    System.out.printf("Now you have %d tasks in the list.\n", index);
                    index--;
                    input = scanner.nextLine();
                } else {
                    throw new YeyException("Sorry, I don't know what that means. Try again");
                }
            } catch (YeyException e) {
                System.out.println(e.getMessage());
                input = scanner.nextLine();
            }
        }
        System.out.println("Bye. Hope to see you again soon!\n" + line); // bye/exit branch
    }
}
