package mickey.task;

/**
 * Todo class for todo tasks
 * Extends from Task class
 */
public class Todo extends Task {

    /**
     * Constructor for Todo
     */
    public Todo(String description) {
        super(description);
        this.type = TaskTypes.TODO;
    }

    /**
     * Returns todo in string format
     */
    @Override
    public String toString() {
        return getTypeLetter() + " " + getStatus() + " " + description;
    }
}
