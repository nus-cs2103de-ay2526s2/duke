package model;

/**
 * Task has 2 states: done and not done
 * Events are tasks with start and end date/time
 */
public class Task {
    private final String name;
    private boolean isDone = false;
    private Priority priority; // default
    /**
     * Constructs a new task with the given name.
     * @param name name of task
     * @param priority priority of task
     */
    public Task(String name, Priority priority) {
        this.name = name;
        this.priority = priority;
    }

    public String getTaskName() {
        return this.name;
    }

    public Priority getPriority() {
        return priority;
    }

    public void setPriority(Priority priority) {
        this.priority = priority;
    }
    /**
     * Returns true if this task is done.
     *
     * @return true if done, false otherwise
     */
    public boolean isDone() {
        return this.isDone;
    }

    /**
     * Converts the task status to string
     * @return string containing task status
     */
    public String toString() {
        String doneStatus = isDone ? "[X]" : "[ ]";
        // returns [T][X] task name
        return String.format("[T]%s(%s) %s", doneStatus, priority, name);
    }

    /**
     * Marks task as done
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Unmarks task as done
     */
    public void unmarkAsDone() {
        this.isDone = false;
    }
}