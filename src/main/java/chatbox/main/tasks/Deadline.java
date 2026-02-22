package chatbox.main.tasks;
import java.time.LocalDateTime;
import chatbox.main.DateParser;
/**
 * Represents a task with a deadline.
 * Stores a description and a "by" date.
 */
public class Deadline extends Task {
    protected LocalDateTime by;

    public Deadline(String description, String by) {
        super(description);
        this.by = DateParser.parseDateTime(by);
        if (this.by == null) {
            throw new IllegalArgumentException("You key in the format that I don't understand! Please Use d/M/yyyy HHmm (e.g., 2/12/2019 1800)");
        }
    }
    public java.time.LocalDateTime getBy() {
        return by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + DateParser.format(by) + ")";
    }
}