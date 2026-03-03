package duchess.command;

import java.util.List;
import java.util.Random;

import duchess.storage.Storage;
import duchess.task.TaskList;

/**
 * Class representing a command to display a random quote.
 */
public class DisplayQuoteCommand extends Command {
    private final Random random;

    /**
     * Constructor for DisplayQuoteCommand class.
     */
    public DisplayQuoteCommand() {
        random = new Random();
    }

    /**
     * Displays a random quote from the list of quotes.
     * @param tasks list of tasks that commands will operate on
     * @param storage storage for saving and loading task lists
     * @return quote to be displayed to the user
     */
    @Override
    public String execute(TaskList tasks, Storage storage) {
        List<String> quotes = storage.getQuotes();

        if (quotes.isEmpty()) {
            return "Hark! No sayings of yore be at hand!";
        }

        int randomIndex = random.nextInt(quotes.size());
        return quotes.get(randomIndex);
    }
}
