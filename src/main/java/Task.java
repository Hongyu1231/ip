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

    public void printTask() {
        System.out.println("." + "[" + getStatusIcon() + "] " + description);
    }

    public void printTaskStatus() {
        System.out.println("       [" + getStatusIcon() + "] " + description);
    }
}
