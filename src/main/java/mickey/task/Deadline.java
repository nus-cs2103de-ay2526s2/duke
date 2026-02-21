package mickey.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Deadline class for deadline tasks
 * Extends from Task class
 */
public class Deadline extends Task {
    private LocalDate dateBy;

    /**
     * Constructor for Deadline
     */
    public Deadline(String description, LocalDate dateBy) {
        super(description);
        this.dateBy = dateBy;
        this.type = TaskTypes.DEADLINE;
    }

    /**
     * Returns the deadline task in a string format
     */
    @Override
    public String toString() {
        String newDateBy = dateBy.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
        return getTypeLetter() + " " + getStatus() + " " + description + " (by " + newDateBy + ")";
    }

    /**
     * Returns the deadline date
     */
    public LocalDate getDateBy() {
        return dateBy;
    }
}
