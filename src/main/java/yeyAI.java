import java.util.Scanner;

public class yeyAI {
    public static void main(String[] args) {
        String line = " ____________________________\n";
        Scanner scanner = new Scanner(System.in);

        System.out.println(line + "Hello! I'm yeyAI\n" + "What can I do for you?\n" + line);
        String input = scanner.nextLine();
        while (!input.equals("bye")) {
            System.out.println(line + input + "\n" + line);
            input = scanner.nextLine();
        }
        System.out.println("Bye. Hope to see you again soon!\n" + line);
    }
}
