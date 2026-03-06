package chatterbox.command;
import chatterbox.Storage;
import chatterbox.TaskList;

/**
 * A command that returns an encouraging message.
 */
public class CheerCommand implements Command {

    @Override
    public CommandResult execute(TaskList tasks, Storage storage) {
        return new CommandResult("You! Can! Do! It!");
    }

    @Override
    public boolean isExit() {
        return false;
    }
}