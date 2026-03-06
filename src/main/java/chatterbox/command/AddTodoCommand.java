package chatterbox.command;

import chatterbox.Storage;
import chatterbox.TaskList;
import chatterbox.Todo;

/**
 * Adds a Todo task to the task list and persists it to storage.
 */
public class AddTodoCommand implements Command {

    private final String description;

    /**
     * Creates an AddTodoCommand.
     *
     * @param description Non-empty todo description.
     */
    public AddTodoCommand(String description) {
        this.description = description;
    }

    @Override
    public CommandResult execute(TaskList tasks, Storage storage) {
        Todo todo = new Todo(description);
        tasks.add(todo);
        storage.save(tasks.getTasks(), tasks.size());

        String message = "Got it. I've added this task:\n"
                + "  " + todo + "\n"
                + "Now you have " + tasks.size() + " tasks in the list.";
        return new CommandResult(message);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}