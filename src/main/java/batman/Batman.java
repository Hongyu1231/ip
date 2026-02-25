package batman;

import java.util.ArrayList;

import commands.Command;
import task.*;
import exception.*;
import exception.BatmanException;

/**
 * The main class to be run.
 */
public class Batman {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Constructor of Batman class.
     * @param filePath The path to store the list.
     */
    public Batman(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (BatmanException e) {
            tasks = new TaskList(new ArrayList<>());
        }
    }

    /**
     * The main function to be run in the 'main'.
     */
    public void run() {
        ui.greeting();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.printLine(); // show the divider line ("_______")
                Command c = Parser.parse(fullCommand, ui);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            }
            catch (BatmanException e) {
                ui.printMessage(e.getMessage());
            } finally {
                ui.printLine();
            }
        }
    }

    /**
     * Main function.
     * @param args Input from user.
     */
    public static void main(String[] args) {
        new Batman("data/tasks.txt").run();
    }
    
}