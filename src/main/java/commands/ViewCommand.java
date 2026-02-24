package commands;

import batman.Storage;
import batman.TaskList;
import batman.Ui;
import task.Task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents a command to view the tasks taks place in a specific date.
 */
public class ViewCommand extends Command {
    private LocalDate targetDate;

    public ViewCommand(LocalDate targetDate) {
        this.targetDate = targetDate;
    }

    /**
     * Compares each task's date with the target date. If it matches, then prints the task.
     * @param tasks    The current list of tasks to search through.
     * @param ui      The user interface to handle printing the results.
     * @param storage The storage utility.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        String formattedDate = targetDate.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        ui.printMessage("     Here are the tasks occurring on " + formattedDate + ":");

        int count = 0;
        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.get(i);
            if (task.occursOn(targetDate)) {
                count++;
                ui.printMessage("      " + count + "." + task);
            }
        }

        if (count == 0) {
            ui.printMessage("     Looks like you have a free day! No tasks found.");
        }
    }
}