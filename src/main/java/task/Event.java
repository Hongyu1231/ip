package task;

import batman.Parser;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    protected String fromStr;
    protected String toStr;
    protected LocalDateTime fromDate;
    protected LocalDateTime toDate;


    public Event(String description, String from, String to) {
        super(description);
        this.fromStr = from;
        this.toStr = to;
        this.fromDate = Parser.parseDateTime(from);
        this.toDate = Parser.parseDateTime(to);
    }

    @Override
    public boolean occursOn(LocalDate date) {
        if (this.fromDate == null || this.toDate == null) {
            return false;
        }

        LocalDate start = this.fromDate.toLocalDate();
        LocalDate end = this.toDate.toLocalDate();

        return !date.isBefore(start) && !date.isAfter(end);
    }

    @Override
    public String toString() {
        // Output format: Oct 15 2019, 6:00 PM
        DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("MMM d yyyy, h:mm a");

        String displayFrom = (this.fromDate != null) ? fromDate.format(outputFormatter) : fromStr;

        String displayTo = (this.toDate != null) ? toDate.format(outputFormatter) : toStr;

        return "[E]" + super.toString() + " (from: " + displayFrom + " to: " + displayTo + ")";
    }


    @Override
    public String toFileString() {
        return "E" + super.toFileString() + " | " + fromStr + " | " + toStr;
    }
}
