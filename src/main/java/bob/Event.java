package bob;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Bob Event class
 * Represents a task with start and end date
 */
public class Event extends Task {
    protected LocalDate start;
    protected LocalDate end;

    /**
     * Constructor class for event
     * @param description the task description
     * @param startString the start date
     * @param endString   the end date
     */
    public Event(String description, String startString, String endString) {
        super(description);
        this.start = LocalDate.parse(startString);
        this.end = LocalDate.parse(endString);
    }

    /**
     * Convert date to string
     * @return string representation and add [E]
     */
    @Override
    public String toString() {
        // add format date
        String formattedStart = start.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
        String formattedEnd = end.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
        return "[E]" + getStatusIcon() + " " + description + " (from: " + formattedStart + " to: " + formattedEnd + ")";
    }

    /** Get the start date
     * @return start date in LocalDate
     */
    public LocalDate getStart() {
        return start;
    }

    /**
     * Get the end date
     * @return end date in LocalDate
     */
    public LocalDate getEnd() {
        return end;
    }

}
