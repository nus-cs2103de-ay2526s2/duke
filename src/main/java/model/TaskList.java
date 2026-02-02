package model;

import java.util.ArrayList;

/**
 * Stores all current tasks
 */
public class TaskList {

    public static final ArrayList<Task> tasks = new ArrayList<>(100); //Maximum 100 tasks

    // Adds task
    public static void addTask(Task task){
        tasks.add(task);
    }

    public static Task getTask(int taskIndex){
        return tasks.get(taskIndex);
    }

    // Returns number of existing tasks
    public static int getSize(){
        return tasks.size();
    }

}