package chatbox.main.tasks;
import chatbox.main.DateParser;
import java.time.LocalDateTime;
/**
 * Represents an event task that occurs within a specific time range.
 * Stores a description, a start time (/from), and an end time (/to).
 */
public class Event extends Task {
    protected LocalDateTime from;
    protected LocalDateTime to;

    public Event(String description, String from, String to) {
        super(description);
        this.from = DateParser.parseDateTime(from);
        this.to = DateParser.parseDateTime(to);
        if (this.from == null || this.to == null) {
            throw new IllegalArgumentException("You key in the format that I don't understand! Please Use d/M/yyyy HHmm (e.g., 2/12/2019 1800)");
        }
    }
    public java.time.LocalDateTime getFrom() {
        return from;
    }

    public java.time.LocalDateTime getTo() {
        return to;
    }
    @Override
    public String toString() {
            return "[E]" + super.toString() + " (from: " + DateParser.format(from) + " to: " + DateParser.format(to) + ")";
    }
}