package chatterbox.command;

import chatterbox.ChatterBotException;
import chatterbox.Storage;
import chatterbox.Task;
import chatterbox.TaskList;

/**
 * Deletes a task by its 1-based index.
 */
public class DeleteCommand implements Command {

    private final int index;

    /**
     * Creates a DeleteCommand.
     *
     * @param index 1-based task index.
     */
    public DeleteCommand(int index) {
        this.index = index;
    }

    @Override
    public CommandResult execute(TaskList tasks, Storage storage) {
        try {
            Task removed = tasks.delete(index);
            storage.save(tasks.getTasks(), tasks.size());

            String message = "Noted. I've removed this task:\n"
                    + "  " + removed + "\n"
                    + "Now you have " + tasks.size() + " tasks in the list.";
            return new CommandResult(message);
        } catch (ChatterBotException e) {
            return new CommandResult(e.getMessage(), false, true);
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}