package task;

import batman.Parser;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    protected String byString;
    protected LocalDateTime byDate;

    public Deadline(String description, String by) {
        super(description);
        this.byString = by;
        this.byDate = Parser.parseDateTime(by);
    }

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

    @Override
    public boolean occursOn(LocalDate date) {
        if (this.byDate == null) {
            return false;
        }
        return this.byDate.toLocalDate().equals(date);
    }

    @Override
    public String toFileString() {
        return "D" + super.toFileString() + " | " + byString;
    }
}
