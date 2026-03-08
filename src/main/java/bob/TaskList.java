package bob;

import java.util.ArrayList;

/**
 * TaskList class
 */
public class TaskList {
    private ArrayList<Task> tasks;

    /**
     * Constructor class
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Handles adding of task
     * @param task the Task object to be added to the list
     */
    public void addTask (Task task) {
        tasks.add(task);
    }

    /**
     * Handles deleting of task
     * @param index the index of task to be deleted
     * @return new task list
     */
    public Task deleteTask(int index) {
        assert index >= 0 && index < tasks.size();
        return tasks.remove(index);
    }

    /**
     * Handles getting of task
     * @param index the index of the task wanted
     * @return the specific index of the task
     */
    public Task getTask(int index) {
        return tasks.get(index);
    }

    /**
     * Return size of task list
     * @return the size of task list in integer
     */
    public int size() {
        return tasks.size();
    }

    /**
     * Return the array list of tasks
     * @return the arraylist containing all tasks
     */
    public ArrayList<Task> getTasks() {
        return tasks;
    }

    /**
     * Handle mark task command
     * @param index the index of the task to mark as done
     */
    public void markTask(int index) {
        tasks.get(index).markAsDone();
    }

    /**
     * Handle unmark task command
     * @param index the index of the task to mark as not done yet
     */
    public void unmarkTask(int index) {
        tasks.get(index).markAsNotDone();
    }

    /**
     * Find and returns tasks whose description matches keyword
     * @param keyword the keyword to search for
     * @return a list of matching tasks
     */
    public ArrayList<Task> findTasks(String keyword) {
        ArrayList<Task> matches = new ArrayList<>();
        for (Task task : tasks) {
            if (task.getDescription().toLowerCase().contains(keyword.toLowerCase())) {
                matches.add(task);
            }
        }
        return matches;
    }

}
