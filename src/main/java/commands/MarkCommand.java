package commands;

import batman.Storage;
import batman.TaskList;
import batman.Ui;

public class MarkCommand extends Command{
    private int index;

    public MarkCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.mark(index, ui, storage);
    }
}
