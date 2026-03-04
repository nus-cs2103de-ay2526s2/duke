/**
 * @author Your Name
 * @since 08/02/2026
 */
package command;

import model.Quotes;
import ui.Ui;

/**
 * Gives users motivational quote
 * Output random String quote into Ui from a quote bank
 */
public class CheerCommand extends Command {
    private final String quote;

    /**
     * Initialize a random String quote from quote bank
     */
    public CheerCommand() {
        this.quote = Quotes.getRandomQuote();
    }

    public String getQuote() {
        return this.quote;
    }
    /**
     * Ui prints quote
     * @return the quote
     */
    @Override
    public String execute() {
        return Ui.getMessageWithBotName(this.quote);
    }

}