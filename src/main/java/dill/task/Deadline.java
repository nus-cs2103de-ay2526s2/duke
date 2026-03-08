package dill.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents a named task with a specified deadline.
 */
public class Deadline extends Task {
    private static final String TASK_TYPE = "D";
    private LocalDate byDate;

    /**
     * Creates an instance of Deadline, and initializes its name and deadline.
     *
     * @param taskName The description of the task.
     * @param byDate The deadline of the task.
     */
    public Deadline(String taskName, LocalDate byDate) {
        super(taskName);
        this.byDate = byDate;
    }

    /**
     * Returns the task type identifier for Deadline tasks.
     *
     * @return The task type as a string.
     */
    public String getTaskType() {
        return TASK_TYPE;
    }

    /**
     * Returns the deadline date of this deadline task as a string array.
     *
     * @return A single element array containing the deadline date.
     */
    public String[] getDates() {
        return new String[]{byDate.toString()};
    }

    /**
     * Checks whether the deadline occurs on the specified date.
     *
     * @param date The date to check against the task's deadline.
     * @return True if the deadline matches the given date, false otherwise.
     */
    public boolean isOccurringOn(LocalDate date) {
        assert date != null : "LocalDate object should not be null";
        return this.byDate.equals(date);
    }

    /**
     * Updates this deadline task using the provided update fields.
     *
     * @param updateFields The UpdateFields object containing new values for this task.
     */
    public void updateTask(UpdateFields updateFields) {
        if (updateFields.getStartDate() != null || updateFields.getEndDate() != null) {
            throw new IllegalArgumentException("Hold on! A deadline task does not have a start or end date!");
        }

        String newTaskName = updateFields.getTaskName();
        if (newTaskName != null) {
            setTaskName(newTaskName);
        }

        LocalDate newByDate = updateFields.getByDate();
        if (newByDate != null) {
            this.byDate = newByDate;
        }
    }

    /**
     * Creates and returns a copy of this deadline task.
     *
     * @return A new Deadline object with the same name and deadline date.
     */
    public Task cloneTask() {
        return new Deadline(this.getTaskName(), this.byDate);
    }

    /**
     * Returns a string representation of the task for displaying in the user interface.
     *
     * @return A formatted string representation of the task.
     */
    public String toString() {
        return "[" + TASK_TYPE + "]" + super.toString()
                + " (by: " + byDate.format(DateTimeFormatter.ofPattern("dd MMM yyyy")) + ")";
    }
}
