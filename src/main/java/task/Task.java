package task;

import java.time.LocalDate;

/**
 * A parent class for different Task classes to inherit from.
 */
public class Task {

    protected String description;
    protected boolean isDone;

    // Constructor
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public void setAsDone() {
        this.isDone = true;
    }

    public void setAsUndone() {
        this.isDone = false;
    }

    /**
     * Default set to false. Will be overrided in the Deadline and Event classes.
     * @param date Target date.
     * @return A boolean that indicates if the task occurs on the target date.
     */
    public boolean occursOn(LocalDate date) {
        return false;
    }

    /**
     * Print the task with a standard format.
     * @return The String object to be printed.
     */
    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }

    /**
     * Save tasks in a .txt file in a standard pattern.
     * @return The String to be saved.
     */
    public String toFileString() {
        return " | " + (isDone ? "1" : "0") + " | " + description;
    }
}
