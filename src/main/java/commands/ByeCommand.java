package commands;

import batman.Storage;
import batman.TaskList;
import batman.Ui;

public class ByeCommand extends Command{
    @Override
    public boolean isExit() {
        return true;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.bye();
    }
}
