import java.util.Scanner;

public class yeyAI {
    public static void main(String[] args) {
        String line = " ____________________________\n";
        Scanner scanner = new Scanner(System.in);
        Task[] tasks = new Task[100];
        int index = 1;

        System.out.println(line + "Hello! I'm yeyAI\n" + "What can I do for you?\n" + line);
        String input = scanner.nextLine();
        while (!input.equals("bye")) {
            if (input.equals("list")) {
                System.out.println("Here are the tasks in your list:");
                for (int i = 0; i < index - 1; i++) {
                    int display = i + 1;
                    String status = tasks[i].getStatusIcon();
                    String description = tasks[i].getDescription();
                    System.out.printf("%d.[%s] %s\n", display, status, description);
                }
                input = scanner.nextLine();
            } else if (input.startsWith("mark")) {
                int taskIndex = Integer.parseInt(input.split(" ")[1]) - 1;
                tasks[taskIndex].setDone();
                System.out.println("Nice! I've marked this task as done:");
                System.out.println("["+tasks[taskIndex].getStatusIcon()+"] "+tasks[taskIndex].getDescription());
                input = scanner.nextLine();
            } else if (input.startsWith("unmark")) {
                int taskIndex = Integer.parseInt(input.split(" ")[1]) - 1;
                tasks[taskIndex].setUndone();
                System.out.println("OK, I've marked this task as not done yet:");
                System.out.println("["+tasks[taskIndex].getStatusIcon()+"] "+tasks[taskIndex].getDescription());
                input = scanner.nextLine();
            } else {
                Task t  = new Task(input);
                tasks[index - 1] = t;
                index++;
                System.out.println(line + "added: " + input + "\n" + line);
                input = scanner.nextLine();
            }
        }
        System.out.println("Bye. Hope to see you again soon!\n" + line);
    }
}
