package dill.task;

import java.time.LocalDate;

/**
 * Represents a container for fields used to update a task.
 */
public class UpdateFields {
    private String taskName;
    private LocalDate byDate;
    private LocalDate startDate;
    private LocalDate endDate;

    /**
     * Sets the new task name.
     *
     * @param taskName The new name to be given to the task.
     */
    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    /**
     * Sets the new deadline date.
     *
     * @param byDate The new deadline date to be given to the task.
     */
    public void setByDate(LocalDate byDate) {
        this.byDate = byDate;
    }

    /**
     * Sets the new event start date.
     *
     * @param startDate The new start date to be given to the task.
     */
    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    /**
     * Sets the new event end date.
     *
     * @param endDate The new start date to be given to the task.
     */
    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    /**
     * Returns the new task name to be given to the task.
     *
     * @return The new task name.
     */
    public String getTaskName() {
        return taskName;
    }

    /**
     * Returns the new deadline date to be given to the task.
     *
     * @return The new deadline date.
     */
    public LocalDate getByDate() {
        return this.byDate;
    }

    /**
     * Returns the new event start date to be given to the task.
     *
     * @return The new event start date.
     */
    public LocalDate getStartDate() {
        return this.startDate;
    }

    /**
     * Returns the new event end date to be given to the task.
     *
     * @return The new event end date.
     */
    public LocalDate getEndDate() {
        return this.endDate;
    }

    /**
     * Checks whether no update fields have been set.
     *
     * @return True if all fields are null, false otherwise.
     */
    public boolean isEmpty() {
        return (this.taskName == null) && (this.byDate == null) && (this.startDate == null) && (this.endDate == null);
    }
}
