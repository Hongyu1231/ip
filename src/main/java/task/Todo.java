package task;

/**
 * The class for todo.
 */
public class Todo extends Task {

    /**
     * Constructor.
     * @param description A String object describes the task.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Print the task with a standard format.
     * @return The String object to be printed.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * Save tasks in a .txt file in a standard pattern.
     * @return The String to be saved.
     */
    @Override
    public String toFileString() {
        return "T" + super.toFileString();
    }
}
