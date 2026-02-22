package chatbox.main.commands;

import chatbox.main.Storage;
import chatbox.main.Ui;
import chatbox.main.tasks.TaskList;
/**
 * Represents a command that provides a motivational cheer to the user.
 * This command interacts with the UI to retrieve a random encouraging message.
 */
public class CheerCommand extends Command {

    /**
     * Executes the cheer command and returns a motivational message.
     *
     * @param tasks   The list of tasks.
     * @param ui      The user interface instance used to retrieve the cheer message.
     * @param storage The storage instance.
     * @return A string containing a motivational quote or cheer.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return ui.showCheer();
    }
}