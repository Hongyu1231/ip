package batman;

import java.util.ArrayList;

import commands.Command;
import task.*;
import exception.*;
import exception.BatmanException;

public class Batman {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Batman(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (BatmanException e) {
            tasks = new TaskList(new ArrayList<Task>());
        }
    }

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

    public static void main(String[] args) {
        new Batman("data/tasks.txt").run();
    }
    
}