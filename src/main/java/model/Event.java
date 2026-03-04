package model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Event is task with start and end date
 */
public class Event extends DeadlineTask {
    // start in String
    private final LocalDateTime startDateTime;

    /**
     * Constructs an event
     * @param name name of event
     * @param priority priority of task
     * @param startDateTime start date time
     * @param endDateTime end date time
     */
    public Event(String name, Priority priority, LocalDateTime startDateTime, LocalDateTime endDateTime) {
        super(name, priority, endDateTime);
        this.startDateTime = startDateTime;
    }

    public LocalDateTime getStartDateTime() {
        return this.startDateTime;
    }
    /**
     * Creates a customised toString that shows status, start and end date, task name
     * @return toString of the task
     */
    @Override
    public String toString() {
        String MARKED_LABEL = "[X]";
        String UNMARKED_LABEL = "[ ]";

        String doneStatus = this.isDone() ? MARKED_LABEL : UNMARKED_LABEL;
        String byString = this.getEndDate().format(DateTimeFormatter.ofPattern("dd/MM/yy HH:mm"));
        String stString = this.getStartDateTime().format(DateTimeFormatter.ofPattern("dd/MM/yy HH:mm"));
        // returns [D][X] task name
        return String.format("[E]%s(%s) %s (st: %s | by: %s)", doneStatus, this.getPriority(), this.getTaskName(), stString, byString);
    }
}