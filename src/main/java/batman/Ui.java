package batman;

import java.util.Scanner;

public class Ui {
    private Scanner scanner;

    public Ui() {
        this.scanner = new Scanner(System.in);
    }

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

    public void printLine() {
        String line = "    ____________________________________________________________";
        System.out.println(line);
    }

    public String readCommand() {
        return scanner.nextLine();
    }

    public void bye() {
        System.out.println("     Bye. Hope to see you again soon!");
    }

    public void printMessage(String message) {
        System.out.println(message);
    }
}
