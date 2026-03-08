package dill.command;

import dill.quote.QuoteList;
import dill.storage.Storage;
import dill.task.TaskList;
import dill.userinterface.UiMessages;

/**
 * Represents a command to terminate the application.
 */
public class ExitCommand extends Command {
    /**
     * Executes the exit command by displaying an exit message.
     *
     * @param taskList The list of tasks to be executed on.
     * @param storage The data storage handler for saving and loading tasks.
     * @param quoteList The list of quotes containing motivational messages.
     * @return The post-execution message to be displayed to the user.
     */
    public String execute(TaskList taskList, Storage storage, QuoteList quoteList) {
        return UiMessages.getExit();
    }

    /**
     * Signals the program to terminate.
     * @return Always true, as this is the exit command.
     */
    public boolean isExit() {
        return true;
    }
}
