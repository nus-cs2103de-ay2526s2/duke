package chatterbox.command;

import chatterbox.Deadline;
import chatterbox.Storage;
import chatterbox.TaskList;

/**
 * Adds a Deadline task to the task list and persists it to storage.
 */
public class AddDeadlineCommand implements Command {

    private final String description;
    private final String by;

    /**
     * Creates an AddDeadlineCommand.
     *
     * @param description Non-empty deadline description.
     * @param by          Deadline time string.
     */
    public AddDeadlineCommand(String description, String by) {
        this.description = description;
        this.by = by;
    }

    @Override
    public CommandResult execute(TaskList tasks, Storage storage) {
        Deadline deadline = new Deadline(description, by);
        tasks.add(deadline);
        storage.save(tasks.getTasks(), tasks.size());

        String message = "Got it. I've added this task:\n"
                + "  " + deadline + "\n"
                + "Now you have " + tasks.size() + " tasks in the list.";
        return new CommandResult(message);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}