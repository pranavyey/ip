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
            if (input.equals("list")) { //list branch
                System.out.println("Here are the tasks in your list:");
                for (int i = 0; i < index - 1; i++) {
                    int display = i + 1;
                    System.out.printf("%d.%s\n", i, tasks[i].toString());
                }
                input = scanner.nextLine();
            } else if (input.startsWith("mark")) { //mark branch
                int taskIndex = Integer.parseInt(input.split(" ")[1]) - 1;
                tasks[taskIndex].setDone();
                System.out.println("Nice! I've marked this task as done:");
                System.out.println(tasks[taskIndex].toString());
                input = scanner.nextLine();
            } else if (input.startsWith("unmark")) { //unmark branch
                int taskIndex = Integer.parseInt(input.split(" ")[1]) - 1;
                tasks[taskIndex].setUndone();
                System.out.println("OK, I've marked this task as not done yet:");
                System.out.println(tasks[taskIndex].toString());
                input = scanner.nextLine();
            } else if (input.startsWith("todo")){ //new to-do task branch
                Task t = new Todo(input.split(" ", 2)[1]);
                tasks[index - 1] = t;
                System.out.println(line + "Got it. I've added this task:");
                System.out.println(t.toString());
                System.out.printf("Now you have %d tasks in the list.\n", index);
                index++;
                input = scanner.nextLine();
            } else if (input.startsWith("deadline")) {
                Task t = new Deadline(input.split(" ", 2)[1]);
                tasks[index - 1] = t;
                System.out.println(line + "Got it. I've added this task:");
                System.out.println(t.toString());
                System.out.printf("Now you have %d tasks in the list.\n", index);
                index++;
                input = scanner.nextLine();
            } else if (input.startsWith("event")) {
                Task t = new Event(input.split(" ", 2)[1]);
                tasks[index - 1] = t;
                System.out.println(line + "Got it. I've added this task:");
                System.out.println(t.toString());
                System.out.printf("Now you have %d tasks in the list.\n", index);
                index++;
                input = scanner.nextLine();
            } else {
                System.out.println("Invalid input format.");
                input = scanner.nextLine();
            }
        }
        System.out.println("Bye. Hope to see you again soon!\n" + line); // bye/exit branch
    }
}
