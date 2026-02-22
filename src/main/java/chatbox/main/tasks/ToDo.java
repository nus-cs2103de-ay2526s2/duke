package chatbox.main.tasks;
/**
 * Represents a "Todo" task, which is a basic task without any date or time attached.
 */
public class ToDo extends Task {
    public ToDo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}