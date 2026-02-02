package model;
public class DeadlineTask extends Task {
    public final String by;

    public DeadlineTask(String name, String by) {
        super(name);
        this.by = by;
    }

    @Override
    public String toString() {
        String MARKED_LABEL = "[X]";
        String UNMARKED_LABEL = "[ ]";

        String doneStatus = this.isDone ? MARKED_LABEL : UNMARKED_LABEL;

        // returns [D][X] task name
        return String.format("[D]%s %s (by: %s)",doneStatus, this.name, this.by);
    }
}