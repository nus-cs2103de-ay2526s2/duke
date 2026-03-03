package duchess.task;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import duchess.exception.InvalidArgumentException;

/**
 * Class representing a list of tasks.
 */
public class TaskList {
    private final List<Task> tasks;

    /**
     * Constructor for TaskList class.
     */
    public TaskList() {
        tasks = new ArrayList<>();
    }

    public TaskList(List<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Adds a task to the list.
     *
     * @param task the task to add
     */
    public void addTask(Task task) {
        tasks.add(task);
    }

    /**
     * Removes a task from the list.
     *
     * @param index the index of the task to remove
     */
    public Task removeTask(int index) throws InvalidArgumentException {
        if (isInvalidIndex(index)) {
            throw new InvalidArgumentException("Invalid task index!");
        }

        Task task = tasks.get(index - 1);
        tasks.remove(index - 1);
        return task;
    }

    /**
     * Marks a task as complete.
     *
     * @param index the index of the task to mark as complete
     */
    public Task markTaskAsComplete(int index) throws InvalidArgumentException {
        if (isInvalidIndex(index)) {
            throw new InvalidArgumentException("Invalid task index!");
        }

        Task task = tasks.get(index - 1);
        task.markAsComplete();
        return task;
    }

    /**
     * Marks a task as incomplete.
     *
     * @param index the index of the task to mark as incomplete
     */
    public Task markTaskAsIncomplete(int index) throws InvalidArgumentException {
        if (isInvalidIndex(index)) {
            throw new InvalidArgumentException("Invalid task index!");
        }

        Task task = tasks.get(index - 1);
        task.markAsIncomplete();
        return task;
    }

    /**
     * Checks if the specified index is valid.
     *
     * @param index the index to check
     * @return  true if the index is valid, false otherwise
     */
    private boolean isInvalidIndex(int index) {
        return index <= 0 || index > tasks.size();
    }

    /**
     * Returns the size of the list.
     *
     * @return the size of the list
     */
    public int getSize() {
        return tasks.size();
    }

    /**
     * Checks if the list is empty.
     *
     * @return true if the list is empty, false otherwise
     */
    public boolean isEmpty() {
        return tasks.isEmpty();
    }

    /**
     * Returns a list of outstanding tasks.
     * @param date the date to check for
     * @return a list of outstanding tasks
     */
    public TaskList getOutstandingTasks(LocalDate date) {
        List<Task> outstandingTasks = tasks.stream()
                .filter(task -> task.isOutstanding(date))
                .toList();

        return new TaskList(outstandingTasks);
    }

    /**
     * Finds tasks matching a keyword.
     *
     * @param keyword the keyword to search for
     * @return a list of matching tasks
     */
    public TaskList getMatchingTasks(String keyword) {
        List<Task> matchedTasks = tasks.stream()
                .filter(task -> task.getName()
                        .toLowerCase()
                        .contains(keyword))
                .toList();

        return new TaskList(matchedTasks);
    }

    /**
     * Returns a string representation of the list.
     *
     * @return a string representation of the list
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        int taskIndex = 1;

        for (Task task : tasks) {
            sb.append(String.format("%d. %s\n", taskIndex++, task.toString()));
        }

        return sb.toString().strip().trim();
    }

    /**
     * Returns a string representation of the list for saving to file.
     * @return a string representation of the list for saving to file
     */
    public String toSaveString() {
        StringBuilder sb = new StringBuilder();

        for (Task task : tasks) {
            sb.append(task.toSaveString()).append("\n");
        }

        return sb.toString().strip().trim();
    }
}
