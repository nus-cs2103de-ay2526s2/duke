package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Stores all current tasks
 */
public class TaskList {
    private static final int TASK_CAPACITY = 100;
    private static List<Task> tasks = new ArrayList<>(TASK_CAPACITY); //Maximum 100 task

    /**
     * adds task
     * @param task Task object
     */
    public static void addTask(Task task) {
        tasks.add(task);
    }

    /**
     * Getter
     * @param taskIndex task index in TaskList
     * @return task, Task object
     */
    public static Task getTask(int taskIndex) {
        assert tasks != null : "Task List should not be null!";
        return tasks.get(taskIndex);
    }

    public static List<Task> getTasks() {
        assert tasks != null : "Task List should not be null!";
        assert TaskList.getSize() >= 0 : "Task List size should not be zero";
        return Collections.unmodifiableList(tasks);
    }

    /**
    * Get the size of the tasklist
    * @return size of TaskList
    */
    public static int getSize() {
        assert tasks != null : "Task List should not be null!";
        return tasks.size();
    }

    /**
     * Removes the task from TaskList
     * @param task Task object
     */
    public static void remove(Task task) {
        tasks.remove(task);
    }

    /**
     * Finds task names in tasks for keyword
     * @param keyword String input by users
     * @return ArrayList of tasks that contains keyword
     */
    public static List<Task> find(String keyword) {
        List<Task> filteredTasks = new ArrayList<>();
        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.get(i);
            if (task.getTaskName().contains(keyword)) {
                filteredTasks.add(task);
            }
        }
        return filteredTasks;
    }

    public static void clear() {
        tasks.clear();
    }
}