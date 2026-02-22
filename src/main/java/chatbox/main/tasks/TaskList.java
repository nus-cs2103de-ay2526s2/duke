package chatbox.main.tasks;

import java.util.ArrayList;
/**
 * Manages the list of tasks.
 * Provides methods to add, delete, mark, unmark, and search for tasks within the list.
 */
public class TaskList {
    private ArrayList<Task> tasks;
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    // Constructor: Load from storage
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public void add(Task task) {
        tasks.add(task);
    }

    public void delete(int index) {
        tasks.remove(index);
    }

    public Task get(int index) {
        return tasks.get(index);
    }

    public int size() {
        return tasks.size();
    }

    public ArrayList<Task> getAllTasks() {
        return tasks;
    }

    public boolean hasDuplicate(Task newTask) {
        // A-Streams feature: Any match returns true if equals() evaluates to true
        return tasks.stream().anyMatch(task -> task.equals(newTask));
    }

    public String findTasks(String keyword) {
        StringBuilder sb = new StringBuilder();
        int count = 0;
        sb.append("Here are the matching tasks in your list:\n");

        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.get(i);
            // Check if the description contains the keyword
            if (task.getDescription().contains(keyword)) {
                count++;
                // Append the matching task to the result string
                sb.append(count).append(".").append(task.toString()).append("\n");
            }
        }

        if (count == 0) {
            return "No matching tasks found.";
        }

        return sb.toString();
    }
}