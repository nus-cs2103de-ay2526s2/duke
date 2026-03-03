package duchess.task;

/**
 * Class representing a task with a name and no additional information.
 */
public class ToDo extends Task {
    /**
     * Constructor for ToDo class.
     *
     * @param name the name of the ToDo task
     */
    public ToDo(String name) {
        super(name);
    }

    /**
     * Constructor for ToDo class used for loading tasks from storage.
     *
     * @param name the name of the ToDo task
     * @param isComplete the completion status of the task
     */
    public ToDo(String name, boolean isComplete) {
        super(name);
        setComplete(isComplete);
    }

    /**
     * Returns a string representation of the todo.
     *
     * @return a string representation of the todo
     */
    @Override
    public String toString() {
        return String.format("[T]%s", super.toString());
    }

    @Override
    public String toSaveString() {
        return String.format("T | %s | %s", isComplete() ? "1" : "0", name);
    }
}
