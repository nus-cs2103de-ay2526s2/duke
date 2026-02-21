package mickey.task;

import java.util.ArrayList;

/**
 * Class for managing the list of all tasks
 */
public class TaskList {
    private ArrayList<Task> tasks;

    /**
     * Creates empty task list
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Creates list with existing tasks
     *
     * @param tasks the list of tasks to initialise with
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Adds task to the list
     *
     * @param tasks tasks to add
     */
    public void addTask(Task... tasks) {
        assert tasks != null : "Tasks array cannot be null";
        for (Task task : tasks) {
            assert task != null : "Task should not be null";
            this.tasks.add(task);
        }
    }

    /**
     * Deletes task from the list
     *
     * @param index the index of the task to delete
     * @return the deleted task
     */
    public Task deleteTask(int index) {
`       assert index >= 0 && index < tasks.size() : "Index should be within range of number of tasks";
        return tasks.remove(index);
    }

    /**
     * Gets a task from the list
     *
     * @param index the index of the task
     * @return the task at the specified index
     */
    public Task getTask(int index) {
        assert index >= 0 && index < tasks.size() : "Index should be within range of number of tasks";
        return tasks.get(index);
    }

    /**
     * Marks a task as done
     *
     * @param index the index of the task to mark
     */
    public void markTask(int index) {
        assert index >= 0 && index < tasks.size() : "Index should be within range of number of tasks";
        tasks.get(index).markDone();
    }

    /**
     * Marks a task as not done
     *
     * @param index the index of the task to unmark
     */
    public void unmarkTask(int index) {
        assert index >= 0 && index < tasks.size() : "Index should be within range of number of tasks";
        tasks.get(index).markUndone();
    }

    /**
     * Gets the number of tasks in the list
     *
     * @return the size of the task list
     */
    public int size() {
        return tasks.size();
    }

    /**
     * Gets all tasks in the list
     *
     * @return all tasks
     */
    public ArrayList<Task> getAllTasks() {
        return tasks;
    }

    /**
     * Checks if the task list is empty
     *
     * @return true if empty, false if it is not
     */
    public boolean isEmpty() {
        return tasks.isEmpty();
    }
}
