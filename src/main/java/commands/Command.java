package commands;

import batman.Storage;
import batman.TaskList;
import batman.Ui;

/**
 * A parent class for different Command class to inherit.
 */
public class Command {
    public Command() {}

    /**
     * @param tasks    The current list of tasks to search through.
     * @param ui      The user interface to handle printing the results.
     * @param storage The storage utility.
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) {}

    public boolean isExit() {
        return false;
    }
}
