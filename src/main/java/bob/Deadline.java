package bob;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


/**
 * Deadline class
 * Represents a task with a deadline date
 */
public class Deadline extends Task {
    protected LocalDate date;

    /**
     * Deadline constructor with description and date
     * @param description the task description
     * @param dateString the deadline date in yyyy-mm-dd format
     */
    public Deadline (String description, String dateString) {
        super(description);
        this.date = LocalDate.parse(dateString);
    }

    /**
     * Return string representation and add [D] in front
     * @return formatted deadline task string
     */
    @Override
    public String toString() {
        String formattedDate = date.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
        return "[D]" + getStatusIcon() + " " + description + " (by: " + formattedDate + ")";
    }

    /**
     * Get deadline date
     * @return the deadline date
     */
    public LocalDate getDate() {
        return date;
    }
}
