import java.util.Scanner;

public class Batman {
    private static final int MAX_TASKS = 100;


    public static void printLine() {
        String line = "    ____________________________________________________________";
        System.out.println(line);
    }


    public static void greeting() {
        String logo =
                "          _==ZA==_      \n"
                        + "        _/ ^  _  ^ \\_   \n"
                        + "       | BAT  -  MAN |  \n"
                        + "        \\_  _ _ _  _/   \n"
                        + "          \\_  W  _/     \n";

        printLine();
        System.out.println(logo);
        System.out.println("     Hello I'm Batman\n     What can I do for you?");
        printLine();
        System.out.println();
    }


    public static void bye() {
        System.out.println("     Bye. Hope to see you again soon!");
        printLine();
        System.out.println();
    }


    public static void talk() {
        Scanner scanner = new Scanner(System.in);
        Task[] list = new Task[MAX_TASKS];
        int taskCount = 0; // Number of total tasks in the list so far
        int index; // When marking or unmarking the task, the index of the task

        // Chat begins
        while(true) {
            String input = scanner.nextLine(); // Input by user
            String[] words = input.split(" "); // The split words inputted by user
            printLine();

            // End of the Chat
            if (input.equals("bye")) {
                break;
            }

            // List all the tasks
            else if (input.equals("list")) {
                System.out.println("     Here are the tasks in your list:");
                for (int i = 0; i < taskCount; i++) {
                    System.out.print("      " + (i+1));
                    list[i].printTask();
                }
            }

            // Mark tasks
            else if (words[0].equals("mark")){
                if (words.length < 2) { // Don't have an index
                    System.out.println();
                    continue;
                }
                try {
                    index = Integer.parseInt(words[1]) - 1;

                    if (index < 0 || index >= taskCount) { // The index out of bound
                        System.out.println();
                        continue;
                    }

                    list[index].setAsDone();
                    System.out.println("     Nice! I've marked this task as done:");
                    list[index].printTaskStatus();
                } catch (NumberFormatException e) { // The 2nd word is not a number
                    continue;
                }
            }

            // Unmark tasks
            else if (words[0].equals("unmark")){
                if (words.length < 2) { // Don't have an index
                    System.out.println();
                    continue;
                }
                try {
                    index = Integer.parseInt(words[1]) - 1;

                    if (index < 0 || index >= taskCount) { // The index out of bound
                        System.out.println();
                        continue;
                    }

                    list[index].setAsUndone();
                    System.out.println("     OK, I've marked this task as not done yet:");
                    list[index].printTaskStatus();
                } catch (NumberFormatException e) { // The 2nd word is not a number
                    continue;
                }
            }

            // Add tasks
            else {
                System.out.println("     " + "added: " + input);
                list[taskCount] = new Task(input);
                taskCount++;
            }
            printLine();
            System.out.println();
        }
    }


    public static void main(String[] args) {
        greeting();
        talk();
        bye();
    }
}