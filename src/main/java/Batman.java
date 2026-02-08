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


    public static void handleMarking(String[] words, Task[] list, int taskCount) {
        // 1. Validation: Check if index is provided
        if (words.length < 2) {
            System.out.println("     Please provide a task number.");
            return;
        }

        try {
            int index = Integer.parseInt(words[1]) - 1;

            // Execution: Check command type and perform action
            Task targetTask = list[index];
            boolean isMark = words[0].equals("mark");

            if (isMark) {
                targetTask.setAsDone();
                System.out.println("     Nice! I've marked this task as done:");
            } else {
                targetTask.setAsUndone();
                System.out.println("     OK, I've marked this task as not done yet:");
            }

            // 5. Shared print logic
            System.out.println("       " + targetTask);

        }
        catch (NumberFormatException e) { // Validation: Check if input is a valid number
            System.out.println("     Please provide a number for task index.");
        }
        catch (IndexOutOfBoundsException e) { // Validation: Check if index is within bounds
            System.out.println("     Task number is out of bound. Please provide a task number in correct range");
        }
    }


    public static int addTask(String input, String[] words, Task[] list, int taskCount) {
        // 1. Guard Clause: Check if list is full
        if (taskCount >= MAX_TASKS) {
            System.out.println("     Your task list is full (max " + MAX_TASKS + "). Try to delete some tasks.");
            return taskCount;
        }

        Task newTask = null;
        String command = words[0];

        try {
            switch (command) {
                case "todo":
                    // substring(5) skips "todo" (length 5)
                    if (input.length() <= 5) {
                        throw new BatmanException("     Error: The description of todo cannot be empty. Please add a description after todo.");
                    }
                    String todoDescription = input.substring(5).trim();
                    if (todoDescription.isEmpty()) {
                        throw new BatmanException("     Error: The description of todo cannot be empty. Please add a description after todo.");
                    }

                    newTask = new Todo(todoDescription);
                    break;

                case "deadline":
                    int byIndex = input.indexOf("/by");
                    if (byIndex == -1) {
                        throw new BatmanException("     Error: Can't find \"/by\". Please add \"by\" after description.");
                    }

                    // substring(9) skips "deadline " (length 9)
                    String deadlineDescription = input.substring(9, byIndex).trim();
                    String by = input.substring(byIndex + 4).trim(); // +4 skips "/by "

                    if (deadlineDescription.isEmpty()) {
                        throw new BatmanException("     Error: The description of deadline cannot be empty. Please add a description after deadline.");
                    }

                    if (by.isEmpty()) {
                        throw new BatmanException("     Error: The time of \"by\" cannot be empty. Please add a time after \"by\".");
                    }

                    newTask = new Deadline(deadlineDescription, by);
                    break;

                case "event":
                    int fromIndex = input.indexOf("/from");
                    int toIndex = input.indexOf("/to");
                    if (fromIndex == -1) {
                        throw new BatmanException("     Error: Can't find \"/from\". Please add \"/from\" after description.");
                    }
                    else if (toIndex == -1) {
                        throw new BatmanException("     Error: Can't find \"/to\". Please add \"/to\" after description.");
                    }

                    // substring(6) skips "event " (length 6)
                    String eventDescription = input.substring(6, fromIndex).trim();
                    String from = input.substring(fromIndex + 6, toIndex).trim(); // +6 skips "/from "
                    String to = input.substring(toIndex + 4).trim(); // +4 skips "/to "

                    if (eventDescription.isEmpty()) {
                        throw new BatmanException("     Error: The description of event cannot be empty. Please add a description after event.");
                    }

                    if (from.isEmpty()) {
                        throw new BatmanException("     Error: The time of \"from\" cannot be empty. Please add a time after \"from\".");
                    }

                    if (to.isEmpty()) {
                        throw new BatmanException("     Error: The time of \"to\" cannot be empty. Please add a time after \"to\".");
                    }

                    newTask = new Event(eventDescription, from, to);
                    break;

                default:
                    System.out.println("     I'm sorry, but I don't know what that means :-(");
                    return taskCount;
            }

            // Success logic
            list[taskCount] = newTask;
            taskCount++;

            System.out.println("     Got it. I've added this task: ");
            System.out.println("       " + newTask);

            // Corrected logical OR (||) and ternary operator
            System.out.println("     Now you have " + taskCount + ((taskCount == 1) ? " task" : " tasks") + " in the list.");

            return taskCount;

        }
        catch (BatmanException e) {
            System.out.println(e.getMessage());
            return taskCount;
        }
    }


    public static void talk() {
        Task[] list = new Task[MAX_TASKS];
        int taskCount = 0;

        try (Scanner scanner = new Scanner(System.in)) {
            while (true) {
                String input = scanner.nextLine();
                String[] words = input.split(" ");

                // Handle empty input (e.g., user just pressed Enter)
                if (input.trim().isEmpty()) {
                    System.out.println("     Please add a command.");
                    continue;
                }

                String command = words[0];
                printLine();

                if (input.equals("bye")) {
                    break;
                }

                else if (input.equals("list")) {
                    System.out.println("     Here are the tasks in your list:");
                    for (int i = 0; i < taskCount; i++) {
                        System.out.println("     " + (i + 1) + "." + list[i]);
                    }
                }

                // Handle marking / unmarking
                else if (command.equals("mark") || command.equals("unmark")) {
                    handleMarking(words, list, taskCount);
                }

                else {
                    // Let addTask handle 'todo', 'deadline', 'event' and unknown commands
                    taskCount = addTask(input, words, list, taskCount);
                }

                printLine();
                System.out.println();
            }
        }
    }


    public static void main(String[] args) {
        greeting();
        talk();
        bye();
    }
}