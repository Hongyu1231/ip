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
            // 2. Validation: Check if input is a valid number
            int index = Integer.parseInt(words[1]) - 1;

            // 3. Validation: Check if index is within bounds
            if (index < 0 || index >= taskCount) {
                System.out.println("     Invalid task number.");
                return;
            }

            // 4. Execution: Check command type and perform action
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

        } catch (NumberFormatException e) {
            System.out.println("     Please provide a number.");
        }
    }


    public static int addTask(String input, String[] words, Task[] list, int taskCount) {
        // 1. Guard Clause: Check if list is full
        if (taskCount >= MAX_TASKS) {
            System.out.println("     Error: Your task list is full (max " + MAX_TASKS + ").");
            return taskCount;
        }

        Task newTask = null;
        String command = words[0];

        try {
            switch (command) {
                case "todo":
                    // substring(5) skips "todo" (length 5)
                    if (input.length() <= 5) {
                        throw new StringIndexOutOfBoundsException();
                    }
                    String todoDescription = input.substring(5).trim();
                    if (todoDescription.isEmpty()) {
                        throw new StringIndexOutOfBoundsException();
                    }

                    newTask = new Todo(todoDescription);
                    break;

                case "deadline":
                    int byIndex = input.indexOf("/by");
                    if (byIndex == -1) {
                        throw new StringIndexOutOfBoundsException();
                    }

                    // substring(9) skips "deadline " (length 9)
                    String deadlineDescription = input.substring(9, byIndex).trim();
                    String by = input.substring(byIndex + 4).trim(); // +4 skips "/by "

                    newTask = new Deadline(deadlineDescription, by);
                    break;

                case "event":
                    int fromIndex = input.indexOf("/from");
                    int toIndex = input.indexOf("/to");
                    if (fromIndex == -1 || toIndex == -1) {
                        throw new StringIndexOutOfBoundsException();
                    }

                    // substring(6) skips "event " (length 6)
                    String eventDescription = input.substring(6, fromIndex).trim();
                    String from = input.substring(fromIndex + 6, toIndex).trim(); // +6 skips "/from "
                    String to = input.substring(toIndex + 4).trim(); // +4 skips "/to "

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

        } catch (StringIndexOutOfBoundsException e) {
            // Specific error handling based on command
            System.out.println("     Error: The description or date for " + command + " cannot be empty.");
            System.out.println("     Please check your formatting.");
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