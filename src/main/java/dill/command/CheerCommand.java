package dill.command;

import dill.quote.QuoteList;
import dill.storage.Storage;
import dill.task.TaskList;

/**
 * Represents a command to output a random motivational message.
 */
public class CheerCommand extends Command {
    /**
     * Executes the cheer command by displaying a motivational message.
     *
     * @param taskList The list of tasks to be executed on.
     * @param storage The data storage handler for saving and loading tasks.
     * @param quoteList The list of quotes containing motivational messages.
     * @return The post-execution message to be displayed to the user.
     */
    public String execute(TaskList taskList, Storage storage, QuoteList quoteList) {
        return quoteList.getRandomQuote();

    }
}
