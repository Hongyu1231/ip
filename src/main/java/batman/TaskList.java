package batman;

import java.util.ArrayList;

import exception.BatmanException;
import task.*;

public class TaskList {
    private ArrayList<Task> list;


    public TaskList(ArrayList<Task> list) {
        this.list = list;
    }

    public void mark(int index, Ui ui, Storage storage) {
        try {
            Task targetTask = list.get(index);
            targetTask.setAsDone();
            ui.printMessage("     Nice! I've marked this task as done:");
            ui.printMessage("       " + targetTask);
            storage.save(list);
        }
        catch (IndexOutOfBoundsException e) { // Validation: Check if index is within bounds
            ui.printMessage("     Task number is out of bound. Please provide a task number in correct range");
        }
    }

    public void unmark(int index, Ui ui, Storage storage) {
        try {
            Task targetTask = list.get(index);
            targetTask.setAsUndone();
            ui.printMessage("     OK, I've marked this task as not done yet:");
            ui.printMessage("       " + targetTask);
            storage.save(list);
        }
        catch (IndexOutOfBoundsException e) { // Validation: Check if index is within bounds
            ui.printMessage("     Task number is out of bound. Please provide a task number in correct range");
        }
    }

    public void addTask(Task task, Storage storage, Ui ui) {
            list.add(task);
            ui.printMessage("     Got it. I've added this task: ");
            ui.printMessage("       " + task);
            ui.printMessage("     Now you have " + list.size() + ((list.size() == 1) ? " task" : " tasks") + " in the list.");
            storage.save(list);
    }

    public void deleteTask(int index, Ui ui, Storage storage) {
        try {
            Task targetTask = list.get(index);
            ui.printMessage("     Noted. I've removed this task:");
            ui.printMessage("       " + targetTask);
            list.remove(index);
            ui.printMessage("     Now you have " + list.size() + ((list.size() == 1 || list.isEmpty()) ? " task" : " tasks") + " in the list.");
            storage.save(list);
        } catch (IndexOutOfBoundsException e) { // Validation: Check if index is within bounds
            ui.printMessage("     Task number is out of bound. Please provide a task number in correct range");
        }
    }

    public boolean isEmpty() {
        return list.isEmpty();
    }

    public int size() {
        return list.size();
    }

    public Task get(int index) {
        return list.get(index);
    }
}
