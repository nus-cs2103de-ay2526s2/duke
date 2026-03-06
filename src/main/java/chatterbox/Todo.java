package chatterbox;

public class Todo extends Task {

    /**
     * Marks a todo task with the given description.
     */

    public Todo(String description) {
        super(description);
    }

    @Override
    protected String typeIcon() {
        return "T";
    }
}
