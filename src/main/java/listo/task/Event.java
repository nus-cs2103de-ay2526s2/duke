package listo.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents an listo.task.Event task.
 * A task that occurs over a specific time period.
 */
public class Event extends Task {
    protected LocalDate fromDate;
    protected LocalDate toDate;
    protected String rawFrom;
    protected String rawTo;

    /**
     * Creates a new listo.task.Event task.
     *
     * @param description The event description.
     * @param from        The start date of the event.
     * @param to          The end date of the event.
     */
    public Event(String description, String from, String to) {
        super(description);
        this.rawFrom = from;
        this.rawTo = to;

        try {
            this.fromDate = LocalDate.parse(from, DateTimeFormatter.ofPattern("d/M/yyyy"));
            this.toDate = LocalDate.parse(to, DateTimeFormatter.ofPattern("d/M/yyyy"));
        } catch (DateTimeParseException e) {
            this.fromDate = null;
            this.toDate = null;
        }
    }

    /**
     * Checks if the event occurs on the specified date.
     * Returns true if the date is between fromDate and toDate (inclusive).
     *
     * @param date The date to check.
     * @return True if the event is happening on this date.
     */
    @Override
    public boolean isOccurringOn(LocalDate date) {
        if (fromDate != null && toDate != null) {
            // Check if date is equal to 'from', 'to', or is in between them
            return !date.isBefore(fromDate) && !date.isAfter(toDate);
        }
        return false;
    }

    /**
     * Returns the string representation of the listo.task.Event task.
     * Prepends "[E]" and appends the time period.
     *
     * @return The formatted string.
     */
    @Override
    public String toString() {
        if (fromDate != null && toDate != null) {
            // Format: "MMM d yyyy" -> "Oct 15 2019"
            String fromFormatted = fromDate.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
            String toFormatted = toDate.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
            return "[E]" + super.toString() + " (from: " + fromFormatted + " to: " + toFormatted + ")";
        } else {
            // Fallback to raw strings if parsing failed
            return "[E]" + super.toString() + " (from: " + rawFrom + " to: " + rawTo + ")";
        }
    }

    /**
     * Returns the string format of the listo.task.Event task for saving to a file.
     * Appends the type identifier "E", start time, and end time to the standard task format.
     *
     * @return A string representation of the listo.task.Event task for file storage.
     */
    @Override
    public String toFileFormat() {
        return "E | " + (isDone ? "1" : "0") + " | " + description + " | " + rawFrom + " | " + rawTo;
    }
}