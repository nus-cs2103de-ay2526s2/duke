package dill.command;

import dill.quote.QuoteList;
import dill.storage.Storage;
import dill.task.TaskList;
import dill.userinterface.UiMessages;

/**
 * Represents a command to display the list of available commands and their syntaxes.
 */
public class HelpCommand extends Command {
    /**
     * Executes the help command by displaying a list of all supported operations.
     *
     * @param taskList The list of tasks to be executed on.
     * @param storage The data storage handler for saving and loading tasks.
     * @param quoteList The list of quotes containing motivational messages.
     * @return The post-execution message to be displayed to the user.
     */
    public String execute(TaskList taskList, Storage storage, QuoteList quoteList) {
        return UiMessages.getHelp();
    }
}
