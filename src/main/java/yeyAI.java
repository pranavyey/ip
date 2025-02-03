import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.nio.file.*;

public class yeyAI {
    public static void main(String[] args) {
        ArrayList<Task> tasks = new ArrayList<>();
        String home = System.getProperty("user.home");
        java.nio.file.Path path = java.nio.file.Paths.get(home, "Documents", "2103T", "ip", "tasks.txt");
        if (Files.exists(path)) {
            try {
                List<String> lines = Files.readAllLines(path);
                for (String taskLine : lines){
                    if (taskLine.startsWith("todo")) {
                        tasks.add(new Todo(taskLine.split("todo ")[1]));
                    } else if (taskLine.startsWith("deadline")) {
                        tasks.add(new Deadline(taskLine.split("deadline ")[1]));
                    } else if (taskLine.startsWith("event")) {
                        tasks.add(new Event(taskLine.split("event ")[1]));
                    }
                }
            } catch (IOException e) {
                System.out.println("Error reading file:" + e.getMessage());
            }
        }

        String line = " ____________________________\n";
        Scanner scanner = new Scanner(System.in);


        System.out.println(line + "Hello! I'm yeyAI\n" + "What can I do for you?\n" + line);
        String input = scanner.nextLine();
        while (!input.equals("bye")) {
            try {
                if (input.equals("list")) { //list branch
                    if (tasks.isEmpty()) {
                        System.out.println("You have no tasks! Start by adding one");
                    } else {
                        System.out.println("Here are the tasks in your list:");
                        for (int i = 0; i < tasks.size(); i++) {
                            int displayIndex = i + 1;
                            System.out.printf("%d.%s\n", displayIndex, tasks.get(i).toString());
                        }
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
                    System.out.printf("Now you have %d tasks in the list.\n", tasks.size());
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
                    System.out.printf("Now you have %d tasks in the list.\n", tasks.size());
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
                    System.out.printf("Now you have %d tasks in the list.\n", tasks.size());
                    input = scanner.nextLine();
                } else if (input.startsWith("delete")) {
                    String[] parts = input.split(" ", 2);
                    if (parts.length < 2 || parts[1].trim().isEmpty()) {
                        throw new YeyException("Specify the task to be deleted.");
                    }
                    if (Integer.parseInt(parts[1]) > tasks.size()) {
                        throw new YeyException("Task to be deleted must exist within list");
                    }
                    tasks.remove(Integer.parseInt(parts[1]) - 1);
                    System.out.println("Task deleted!");
                    System.out.printf("Now you have %d tasks in the list.\n", tasks.size());
                    input = scanner.nextLine();
                } else {
                    throw new YeyException("Sorry, I don't know what that means. Try again");
                }
            } catch (YeyException e) {
                System.out.println(e.getMessage());
                input = scanner.nextLine();
            }
        }
        scanner.close();
        List<String> saveTasks = new ArrayList<>();
        for (Task task : tasks) {
            saveTasks.add(task.toCommand());
        }
        try {
            Files.write(path, saveTasks, StandardCharsets.UTF_8, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
        } catch (IOException e) {
            System.out.println("Error writing file:" + e.getMessage());
        }
        System.out.println("Bye. Hope to see you again soon!\n" + line); // bye/exit branch
    }
}
