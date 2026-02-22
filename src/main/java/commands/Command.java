package commands;

import batman.Storage;
import batman.TaskList;
import batman.Ui;

public class Command {
    public Command() {}

    public void execute(TaskList tasks, Ui ui, Storage storage) {}

    public boolean isExit() {
        return false;
    }
}
