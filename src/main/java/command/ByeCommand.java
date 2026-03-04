package command;

import ui.Ui;

/**
 * Prints exit message
 */
public class ByeCommand extends Command {
    /**
     * prints exit message to show Star exited
     */
    @Override
    public String execute() {
        return Ui.getExitMessage(); // Prints goodbye message in UI
    }
}