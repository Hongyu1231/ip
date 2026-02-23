package commands;

import batman.Storage;
import batman.TaskList;
import batman.Ui;

public class ListCommand extends Command{
    @Override
    public void execute(TaskList list, Ui ui, Storage storage) {
        if (list.isEmpty()) {
            ui.printMessage("     There is no task in your list.");
        }
        else {
            ui.printMessage("     Here are the tasks in your list:");
            for (int i = 0; i < list.size(); i++) {
                ui.printMessage("      " + (i + 1) + "." + list.get(i));
            }
        }
    }
}
