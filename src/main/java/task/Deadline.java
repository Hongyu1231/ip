package task;

import batman.Parser;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * The deadline class.
 */
public class Deadline extends Task {
    protected String byString;
    protected LocalDateTime byDate;

    /**
     * Constructor.
     * @param description A String object describes the task.
     * @param by The deadline of the task.
     */
    public Deadline(String description, String by) {
        super(description);
        this.byString = by;
        this.byDate = Parser.parseDateTime(by);
    }

    /**
     * Returns the task in a format.
     * @return A String indicates the task.
     */
    @Override
    public String toString() {
        if (this.byDate != null) {
            // Formats to: Oct 15 2019, 6:00 PM
            String formattedDate = byDate.format(DateTimeFormatter.ofPattern("MMM d yyyy, h:mm a"));
            return "[D]" + super.toString() + " (by: " + formattedDate + ")";
        } else {
            return "[D]" + super.toString() + " (by: " + byString + ")";
        }
    }

    /**
     * If the by time is the target date, return true.
     * @param date Target date.
     * @return A boolean value indicates if the task occurs in the target date.
     */
    @Override
    public boolean occursOn(LocalDate date) {
        if (this.byDate == null) {
            return false;
        }
        return this.byDate.toLocalDate().equals(date);
    }

    /**
     * Store the task in a file.
     * @return A String indicates the task.
     */
    @Override
    public String toFileString() {
        return "D" + super.toFileString() + " | " + byString;
    }
}
