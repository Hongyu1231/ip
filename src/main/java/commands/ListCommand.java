package commands;

import batman.Storage;
import batman.TaskList;
import batman.Ui;

/**
 * Represents a command to list tasks in the current list.
 */
public class ListCommand extends Command{

    /**
     * Print all tasks in the list one by one.
     * @param list    The current list of tasks to search through.
     * @param ui      The user interface to handle printing the results.
     * @param storage The storage utility.
     */
    @Override
    public void execute(TaskList list, Ui ui, Storage storage) {
        if (list.isEmpty()) {
            ui.printMessage("     There is no task in your list.");
        }
        else {
            ui.printMessage("     Here are the tasks in your list:");
            for (int i = 0; i < list.size(); i++) {
                ui.printMessage("      " + (i + 1) + "." + list.get(i));
            }
        }
    }
}
