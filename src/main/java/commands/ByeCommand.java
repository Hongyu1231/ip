package commands;

import batman.Storage;
import batman.TaskList;
import batman.Ui;

/**
 * Represents a command to exit the program.
 * This command calls the bye() method from Ui class.
 */
public class ByeCommand extends Command{
    /**
     * Exit the program.
     * @return Always return true.
     */
    @Override
    public boolean isExit() {
        return true;
    }

    /**
     * Print bye message.
     * @param tasks    The current list of tasks to search through.
     * @param ui      The user interface to handle printing the results.
     * @param storage The storage utility.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.bye();
    }
}
