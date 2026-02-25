package batman;

import java.util.Scanner;

/**
 * A class that interacts with user.
 */
public class Ui {
    private Scanner scanner;

    /**
     * Constructor.
     */
    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * Print the logo of chatbot and greet user.
     */
    public void greeting() {
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

    /**
     * Print a line to divide sentences.
     */
    public void printLine() {
        String line = "    ____________________________________________________________";
        System.out.println(line);
    }

    /**
     * Scan the input by user.
     * @return A Scanner object.
     */
    public String readCommand() {
        return scanner.nextLine();
    }

    /**
     * print bye command.
     */
    public void bye() {
        System.out.println("     Bye. Hope to see you again soon!");
    }

    /**
     * Print the message from chatbot to user.
     * @param message A String to be printed.
     */
    public void printMessage(String message) {
        System.out.println(message);
    }
}
