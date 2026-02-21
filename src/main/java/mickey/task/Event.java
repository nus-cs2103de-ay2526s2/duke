package mickey.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Event class for event tasks
 * Extends from task class
 */
public class Event extends Task {
    private LocalDateTime dateFrom;
    private LocalDateTime dateTo;

    /**
     * Constructor for event
     */
    public Event(String description, LocalDateTime from, LocalDateTime to) {
        super(description);
        this.dateFrom = from;
        this.dateTo = to;
        this.type = TaskTypes.EVENT;
    }

    /**
     * Returns event in string format
     */
    @Override
    public String toString() {
        String newDateFrom = dateFrom.format(DateTimeFormatter.ofPattern("MMM dd yyyy h:mma"));
        String newDateTo = dateTo.format(DateTimeFormatter.ofPattern("MMM dd yyyy h:mma"));

        return getTypeLetter() + " " + getStatus() + " " + description
                + " (from " + newDateFrom + " to " + newDateTo + ")";
    }

    /**
     * Returns event start date
     */
    public LocalDateTime getDateFrom() {
        return dateFrom;
    }

    /**
     * Returns event end date
     */
    public LocalDateTime getDateTo() {
        return dateTo;
    }
}
