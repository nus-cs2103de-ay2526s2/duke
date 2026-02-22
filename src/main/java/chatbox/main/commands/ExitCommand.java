package chatbox.main.commands;

import chatbox.main.Storage;
import chatbox.main.tasks.TaskList;
import chatbox.main.Ui;

/**
 * Terminates the program.
 */
public class ExitCommand extends Command {

    /**
     * Executes the exit command and returns the final goodbye message.
     *
     * @param tasks   The list of tasks (not used in this command).
     * @param ui      The user interface instance (not used in this command).
     * @param storage The storage instance (not used in this command).
     * @return A string containing the goodbye message to be displayed in the GUI.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        // Return the goodbye message so it appears in the GUI
        return "Bye. Hope to see you again soon!";
    }

    @Override
    public boolean isExit() {
        return true;
    }
}