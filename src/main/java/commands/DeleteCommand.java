package commands;

import batman.Storage;
import batman.TaskList;
import batman.Ui;

public class DeleteCommand extends Command{
    private int index;

    public DeleteCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.deleteTask(index, ui, storage);
    }
}
