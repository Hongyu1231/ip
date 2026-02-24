package commands;

import batman.Storage;
import batman.TaskList;
import batman.Ui;
import task.Task;

/**
 * Represents a command to add a task.
 * This command calls the addTask() method from TaskList.
 */
public class AddCommand extends Command{
    private Task task;

    public AddCommand(Task task) {
        this.task = task;
    }

    /**
     * Calls the addTask() method to add the task.
     * @param tasks    The current list of tasks to search through.
     * @param ui      The user interface to handle printing the results.
     * @param storage The storage utility.
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.addTask(task, storage, ui);
    }
}
