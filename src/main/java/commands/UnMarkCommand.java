package commands;

import batman.Storage;
import batman.TaskList;
import batman.Ui;

public class UnMarkCommand extends Command{
    private int index;

    public UnMarkCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.unmark(index, ui, storage);
    }
}
