package chatterbox.command;

import chatterbox.ChatterBotException;
import chatterbox.Storage;
import chatterbox.Task;
import chatterbox.TaskList;

/**
 * Marks a task as done by its 1-based index.
 */
public class MarkCommand implements Command {

    private final int index;

    public MarkCommand(int index) {
        this.index = index;
    }

    @Override
    public CommandResult execute(TaskList tasks, Storage storage) {
        try {
            Task task = tasks.get(index);
            task.markDone();
            storage.save(tasks.getTasks(), tasks.size());
            return new CommandResult("Nice! I've marked this task as done:\n  " + task);
        } catch (ChatterBotException e) {
            return new CommandResult(e.getMessage(), false, true);
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}