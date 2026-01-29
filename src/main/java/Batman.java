import java.util.Scanner;

public class Batman {
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
    }

    public static void talk() {
        Scanner scanner = new Scanner(System.in);
        String[]list = new String[100];
        int taskNum;
        int numOfTask = 0;
        while(true) {
            String input = scanner.nextLine();
            printLine();
            if (input.equals("bye")) {
                break;
            } else if (input.equals("list")) {
                taskNum = 1;
                for (int i = 0; i < numOfTask; i++) {
                    System.out.println(" " + taskNum + ". " + list[i]);
                    taskNum++;
                }
            } else {
                System.out.println("     " + "added: " + input);
                list[numOfTask] = input;
                numOfTask++;
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