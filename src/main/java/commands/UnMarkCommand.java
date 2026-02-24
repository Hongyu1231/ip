package commands;

import batman.Storage;
import batman.TaskList;
import batman.Ui;

/**
 * Represents a command to mark a task.
 */
public class UnMarkCommand extends Command{
    private int index;

    public UnMarkCommand(int index) {
        this.index = index;
    }

    /**
     * Calls unmark() method of TaskList.
     * @param tasks    The current list of tasks to search through.
     * @param ui      The user interface to handle printing the results.
     * @param storage The storage utility.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.unmark(index, ui, storage);
    }
}
