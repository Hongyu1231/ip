package task;

import batman.Parser;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * The class for event.
 */
public class Event extends Task {
    protected String fromStr;
    protected String toStr;
    protected LocalDateTime fromDate;
    protected LocalDateTime toDate;

    /**
     * Constructor.
     * @param description A String object describes the task.
     * @param from When will the event begin.
     * @param to When will the event end.
     */
    public Event(String description, String from, String to) {
        super(description);
        this.fromStr = from;
        this.toStr = to;
        this.fromDate = Parser.parseDateTime(from);
        this.toDate = Parser.parseDateTime(to);
    }

    /**
     * If the target date lies between the from date and the to date, return true.
     * @param date Target date.
     * @return A boolean value indicates if the task occurs in the target date.
     */
    @Override
    public boolean occursOn(LocalDate date) {
        if (this.fromDate == null || this.toDate == null) {
            return false;
        }

        LocalDate start = this.fromDate.toLocalDate();
        LocalDate end = this.toDate.toLocalDate();

        return !date.isBefore(start) && !date.isAfter(end);
    }

    /**
     * Returns the task in a format.
     * @return A String indicates the task.
     */
    @Override
    public String toString() {
        // Output format: Oct 15 2019, 6:00 PM
        DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("MMM d yyyy, h:mm a");

        String displayFrom = (this.fromDate != null) ? fromDate.format(outputFormatter) : fromStr;

        String displayTo = (this.toDate != null) ? toDate.format(outputFormatter) : toStr;

        return "[E]" + super.toString() + " (from: " + displayFrom + " to: " + displayTo + ")";
    }

    /**
     * Store the task in a file.
     * @return A String indicates the task.
     */
    @Override
    public String toFileString() {
        return "E" + super.toFileString() + " | " + fromStr + " | " + toStr;
    }
}
