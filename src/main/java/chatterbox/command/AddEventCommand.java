package chatterbox.command;

import chatterbox.Event;
import chatterbox.Storage;
import chatterbox.TaskList;

/**
 * Adds an Event task to the task list and persists it to storage.
 */
public class AddEventCommand implements Command {

    private final String description;
    private final String from;
    private final String to;

    /**
     * Creates an AddEventCommand.
     *
     * @param description Non-empty event description.
     * @param from        Start time string.
     * @param to          End time string.
     */
    public AddEventCommand(String description, String from, String to) {
        this.description = description;
        this.from = from;
        this.to = to;
    }

    @Override
    public CommandResult execute(TaskList tasks, Storage storage) {
        Event event = new Event(description, from, to);
        tasks.add(event);
        storage.save(tasks.getTasks(), tasks.size());

        String message = "Got it. I've added this task:\n"
                + "  " + event + "\n"
                + "Now you have " + tasks.size() + " tasks in the list.";
        return new CommandResult(message);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}