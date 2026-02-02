package model;
/**
 * Event is task with start and end date
 */
public class Event extends DeadlineTask {
    // 3char with first letter caps to represent day "Mon", "Tue"
    public final String st;

    public Event(String name, String st, String end) {
        super(name, end);
        this.st = st;
    }

    @Override
    public String toString() {
        String MARKED_LABEL = "[X]";
        String UNMARKED_LABEL = "[ ]";

        String doneStatus = this.isDone ? MARKED_LABEL : UNMARKED_LABEL;

        // returns [D][X] task name
        return String.format("[E]%s %s (st %s | by: %s)",doneStatus, this.name, this.st, this.by);
    }
}