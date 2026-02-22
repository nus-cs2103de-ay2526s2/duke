package chatbox.main.commands;

import chatbox.main.Storage;
import chatbox.main.tasks.TaskList;
import chatbox.main.Ui;

/**
 * Lists all tasks currently stored in the task list.
 */
public class ListCommand extends Command {
    /**
     * Executes the command by iterating through the TaskList and formatting
     * the tasks into a numbered string.
     *
     * @param tasks   The list of tasks to be displayed.
     * @param ui      The user interface instance (not used in this command).
     * @param storage The storage instance (not used in this command).
     * @return A formatted string representing the entire list of tasks.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        StringBuilder result = new StringBuilder("Here are the tasks in your list:\n");
        for (int i = 0; i < tasks.size(); i++) {
            result.append((i + 1)).append(".").append(tasks.get(i)).append("\n");
        }
        return result.toString();
    }
}