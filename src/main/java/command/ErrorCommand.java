package command;

import ui.Ui;

/**
 * Prints error message
 */
public class ErrorCommand extends Command {
    public final String errorMsg;

    /**
     * constructs with error message
     * @param errorMsg error message
     */
    public ErrorCommand(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    /**
     * print error message
     * @return String error message
     */
    @Override
    public String execute() {
        return Ui.getMessageWithBotName(" ERROR! " + errorMsg);
    }
}