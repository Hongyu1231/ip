package commands;

import batman.Storage;
import batman.TaskList;
import batman.Ui;
import task.Task;

import java.util.ArrayList;

/**
 * Represents a command to find tasks that contain a specific keyword.
 * This command searches through the user's task list and prints all matching tasks.
 */
public class FindCommand extends Command{
    private String keyWord;

    public FindCommand(String keyWord) {
        this.keyWord = keyWord;
    }

    /**
     * Iterate through the whole list to find if it is matching. If it is matching, then add it to matchTasks.
     * @param list      The current list of tasks to search through.
     * @param ui        The user interface to handle printing the results.
     * @param storage   The storage utility.
     */
    @Override
    public void execute(TaskList list, Ui ui, Storage storage) {
        if (list.isEmpty()) {
            ui.printMessage("     There is no task in your list.");
        }
        else {
            ArrayList<Task> matchTasks = new ArrayList<Task>();
            // Iterate through the whole list to find if each task contains the key word
            for (int i = 0; i < list.size(); i++) {
                if (list.get(i).toString().toLowerCase().contains(keyWord.toLowerCase())) {
                    matchTasks.add(list.get(i));
                }
            }

            if (matchTasks.isEmpty()) {
                ui.printMessage("    There is no matching task in your list.");
            }
            else {
                ui.printMessage("    Here are the matching tasks in your list:");
                for (int i = 0; i < matchTasks.size(); i++) {
                    ui.printMessage("      " + (i + 1) + "." + matchTasks.get(i));
                }
            }
        }
    }
}
