package chatterbox.command;

import chatterbox.ChatterBotException;
import chatterbox.Storage;
import chatterbox.Task;
import chatterbox.TaskList;

/**
 * Unmarks a task (marks it as not done) by its 1-based index.
 */
public class UnmarkCommand implements Command {

    private final int index;

    public UnmarkCommand(int index) {
        this.index = index;
    }

    @Override
    public CommandResult execute(TaskList tasks, Storage storage) {
        try {
            Task task = tasks.get(index);
            task.markNotDone();
            storage.save(tasks.getTasks(), tasks.size());
            return new CommandResult("OK, I've marked this task as not done yet:\n  " + task);
        } catch (ChatterBotException e) {
            return new CommandResult(e.getMessage(), false, true);
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}