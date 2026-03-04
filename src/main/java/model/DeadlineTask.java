package model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Creates a task with deadline denoted by /by
 */
public class DeadlineTask extends Task {
    private final LocalDateTime endDateTime;

    /**
     * Constructor for Deadline Task
     * @param name of the task
     * @param priority of task
     * @param endDateTime deadline of the task
     */
    public DeadlineTask(String name, Priority priority, LocalDateTime endDateTime) {
        super(name, priority);
        this.endDateTime = endDateTime;
    }

    public LocalDateTime getEndDate() {
        return endDateTime;
    }

    /**
     * Creates a customised toString that shows status, deadline date and task name
     * @return toString of the task
     */
    @Override
    public String toString() {
        final String MARKED_LABEL = "[X]";
        final String UNMARKED_LABEL = "[ ]";

        String doneStatus = this.isDone() ? MARKED_LABEL : UNMARKED_LABEL;
        String byString = this.endDateTime.format(DateTimeFormatter.ofPattern("dd/MM/yy HH:mm"));
        // returns [D][X] task name
        return String.format("[D]%s(%s) %s (by: %s)", doneStatus, this.getPriority(), this.getTaskName(), byString);
    }
}