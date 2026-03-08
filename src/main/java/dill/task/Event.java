package dill.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents a named task with a specified given start and end date.
 */
public class Event extends Task {
    private static final String TASK_TYPE = "E";
    private LocalDate startDate;
    private LocalDate endDate;

    /**
     * Creates an instance of Event, initializing its name along with its start and end date.
     *
     * @param taskName The description of the task.
     * @param startDate The start date of the task.
     * @param endDate The end date of the task.
     */
    public Event(String taskName, LocalDate startDate, LocalDate endDate) {
        super(taskName);
        this.startDate = startDate;
        this.endDate = endDate;
    }

    /**
     * Returns the task type identifier for Event tasks.
     *
     * @return The task type as a string.
     */
    public String getTaskType() {
        return TASK_TYPE;
    }

    /**
     * Returns the start and end dates of this event task as a string array.
     *
     * @return A two element array containing the start and end dates.
     */
    public String[] getDates() {
        return new String[]{startDate.toString(), endDate.toString()};
    }

    /**
     * Checks whether the event occurs on the specified date.
     *
     * @param date The date to check against the task's start and end date.
     * @return True if the specified date is within the start and end date (inclusive), false otherwise.
     */
    public boolean isOccurringOn(LocalDate date) {
        assert date != null : "LocalDate object should not be null";

        boolean isBeforeStart = date.isBefore(startDate);
        boolean isAfterEnd = date.isAfter(endDate);
        return !isBeforeStart && !isAfterEnd;
    }

    /**
     * Updates this event task using the provided update fields.
     *
     * @param updateFields The UpdateFields object containing new values for this task.
     */
    public void updateTask(UpdateFields updateFields) {
        if (updateFields.getByDate() != null) {
            throw new IllegalArgumentException("Hold on! An event task does not have a deadline!");
        }

        LocalDate updateStartDate = updateFields.getStartDate();
        LocalDate updateEndDate = updateFields.getEndDate();
        LocalDate newStartDate = (updateStartDate == null) ? this.startDate : updateStartDate;
        LocalDate newEndDate = (updateEndDate == null) ? this.endDate : updateEndDate;
        if (newStartDate.isAfter(newEndDate)) {
            throw new IllegalArgumentException("Wait a second... are we time travelling?"
                    + " The start date cannot be after the end date!");
        }
        this.startDate = newStartDate;
        this.endDate = newEndDate;

        String newTaskName = updateFields.getTaskName();
        if (newTaskName != null) {
            setTaskName(newTaskName);
        }
    }

    /**
     * Creates and returns a copy of this event task.
     *
     * @return A new Event object with the same name, start date, and end date.
     */
    public Task cloneTask() {
        return new Event(this.getTaskName(), this.startDate, this.endDate);
    }

    /**
     * Returns a string representation of the task for displaying in the user interface.
     *
     * @return A formatted string representation of the task.
     */
    public String toString() {
        return "[" + TASK_TYPE + "]" + super.toString()
                + " (start: " + startDate.format(DateTimeFormatter.ofPattern("dd MMM yyyy"))
                + ", end: " + endDate.format(DateTimeFormatter.ofPattern("dd MMM yyyy")) + ")";
    }
}
