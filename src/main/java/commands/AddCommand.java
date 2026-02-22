package commands;

import batman.Storage;
import batman.TaskList;
import batman.Ui;
import task.Task;

public class AddCommand extends Command{
    private Task task;

    public AddCommand(Task task) {
        this.task = task;
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.addTask(task, storage, ui);
    }
}
