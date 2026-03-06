package chatterbox.command;

import chatterbox.Storage;
import chatterbox.TaskList;

/**
 * Represents the exit command.
 */
public class ByeCommand implements Command {

    @Override
    public CommandResult execute(TaskList tasks, Storage storage) {
        return new CommandResult("Bye. Hope to see you again soon!", true);
    }

    @Override
    public boolean isExit() {
        return true;
    }
}