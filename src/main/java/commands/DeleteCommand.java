package commands;

import batman.Storage;
import batman.TaskList;
import batman.Ui;

/**
 * Represents a command to delete a task.
 * This command calls the deleteTask() method from TaskList.
 */
public class DeleteCommand extends Command{
    private int index;

    public DeleteCommand(int index) {
        this.index = index;
    }

    /**
     * Delete a task at a certain position in the list.
     * @param tasks    The current list of tasks to search through.
     * @param ui      The user interface to handle printing the results.
     * @param storage The storage utility.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.deleteTask(index, ui, storage);
    }
}
