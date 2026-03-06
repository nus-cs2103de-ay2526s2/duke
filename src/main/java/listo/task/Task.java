package listo.task;

import java.time.LocalDate;

/**
 * Represents a generic task.
 * Serves as the base class for listo.task.Todo, listo.task.Deadline, and listo.task.Event.
 */
public class Task {
    /** The description of the task. */
    protected String description;
    /** The status of the task (true if done, false otherwise). */
    protected boolean isDone;

    /**
     * Creates a new listo.task.Task with a description.
     * The task is initially marked as not done.
     *
     * @param description The description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Checks if the task occurs on the specified date.
     * Default implementation returns false (for Todos).
     */
    public boolean isOccurringOn(LocalDate date) {
        return false;
    }

    /**
     * Returns the status icon for the task.
     *
     * @return "X" if done, " " (space) if not done.
     */
    public String getStatusIcon() {
        if (this.isDone) {
            return "X";
        } else {
            return " ";
        }
    }

    /**
     * Marks the task as completed.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Marks the task as incomplete.
     */
    public void markAsNotDone() {
        this.isDone = false;
    }

    /**
     * Returns the string representation of the task.
     *
     * @return A string containing status icon and description.
     */
    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }

    /**
     * Returns the string format of the task for saving to a file.
     *
     * @return The formatted string representation.
     */
    public String toFileFormat() {
        return (isDone ? "1" : "0") + " | " + description;
    }

    /**
     * Checks if the task occurs on the specified date.
     * Default implementation returns false.
     *
     * @param date The date to check against.
     * @return true if the task occurs on the date, false otherwise.
     */
    public boolean isOn(LocalDate date) {
        return false;
    }
}
