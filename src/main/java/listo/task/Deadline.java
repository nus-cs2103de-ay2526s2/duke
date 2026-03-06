package listo.task;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a listo.task.Deadline task.
 * A task that needs to be done by a specific date and time.
 */
public class Deadline extends Task {
    /** The due date and time of the deadline. */
    protected LocalDateTime dueDateTime;

    /**
     * Creates a new listo.task.Deadline task.
     *
     * @param description The task description.
     * @param dueDateTime          The deadline date/time (must be in d/M/yyyy HHmm format).
     */
    public Deadline(String description, String dueDateTime) {
        super(description);
        // Define the format: 2/12/2019 1800 -> d/M/yyyy HHmm
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
        this.dueDateTime = LocalDateTime.parse(dueDateTime, formatter);
    }

    /**
     * Checks if the deadline falls on the specified date.
     *
     * @param date The date to check against.
     * @return true if the deadline date matches the given date.
     */
    @Override
    public boolean isOccurringOn(LocalDate date) {
        return this.dueDateTime.toLocalDate().equals(date);
    }

    /**
     * Returns the string format of the listo.task.Deadline task for saving to a file.
     * We save it in the same format (d/M/yyyy HHmm) so the constructor can load it back easily.
     */
    @Override
    public String toFileFormat() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
        return "D | " + super.toFileFormat() + " | " + dueDateTime.format(formatter);
    }

    /**
     * Returns the string representation of the listo.task.Deadline task.
     * Displays the date in a nice format like "Dec 02 2019, 6:00 pm".
     */
    @Override
    public String toString() {
        // Output format: Dec 02 2019, 6:00 pm
        return "[D]" + super.toString() + " (dueDateTime: " +
                dueDateTime.format(DateTimeFormatter.ofPattern("MMM dd yyyy, h:mm a")) + ")";
    }
}