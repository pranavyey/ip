import java.util.Scanner;

public class yeyAI {
    public static void main(String[] args) {
        String line = " ____________________________\n";
        Scanner scanner = new Scanner(System.in);
        String[] tasks = new String[100];
        Integer index = 1;

        System.out.println(line + "Hello! I'm yeyAI\n" + "What can I do for you?\n" + line);
        String input = scanner.nextLine();
        while (!input.equals("bye")) {
            if (!input.equals("list")) {
                tasks[index - 1] = (index).toString() + ". " + input;
                index++;
                System.out.println(line + "added: " + input + "\n" + line);
                input = scanner.nextLine();
            }
            if (input.equals("list")){
                for (Integer i = 0; i < index - 1; i++) {
                    System.out.println(tasks[i]);
                }
                input = scanner.nextLine();
            }
        }
        System.out.println("Bye. Hope to see you again soon!\n" + line);
    }
}
