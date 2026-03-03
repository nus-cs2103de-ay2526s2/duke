package duchess.task;

import java.time.LocalDate;

import duchess.parser.Utility;

/**
 * Deadline class for tasks with deadlines.
 */
public class Deadline extends Task {
    private final LocalDate deadline;

    /**
     * Constructor for Deadline class.
     *
     * @param taskName the name of the task
     * @param deadline the deadline for the task
     */
    public Deadline(String taskName, LocalDate deadline) {
        super(taskName);
        this.deadline = deadline;
    }

    /**
     * Constructor for Deadline class used for loading tasks from storage.
     *
     * @param taskName the name of the task
     * @param deadline the deadline for the task
     * @param isComplete the completion status of the task
     */
    public Deadline(String taskName, LocalDate deadline, boolean isComplete) {
        super(taskName);
        this.deadline = deadline;
        setComplete(isComplete);
    }

    /**
     * Returns true if the deadline is after the specified date and the task is not complete.
     * @param date the date to compare with the deadline
     * @return true if the deadline is after the specified date and the task is not complete, false otherwise
     */
    @Override
    public boolean isOutstanding(LocalDate date) {
        return deadline.isAfter(date) && !isComplete();
    }

    /**
     * Returns a string representation of the deadline task
     *
     * @return a string representation of the deadline task
     */
    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(), Utility.formatDate(deadline));
    }

    @Override
    public String toSaveString() {
        return String.format("D | %s | %s | %s", isComplete() ? "1" : "0", name, deadline);
    }
}
