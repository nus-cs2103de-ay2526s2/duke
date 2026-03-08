package dill.command;

import java.util.List;

import dill.quote.QuoteList;
import dill.storage.Storage;
import dill.task.Task;
import dill.task.TaskList;

/**
 * Represents a command to list all current tasks in the task list.
 */
public class ListCommand extends Command {
    /**
     * Executes the list command by displaying all tasks to the user via the user interface.
     *
     * @param taskList The list of tasks to be executed on.
     * @param storage The data storage handler for saving and loading tasks.
     * @param quoteList The list of quotes containing motivational messages.
     * @return The post-execution message to be displayed to the user.
     */
    public String execute(TaskList taskList, Storage storage, QuoteList quoteList) {
        List<Task> tasks = taskList.getTaskList();
        StringBuilder output = new StringBuilder();
        if (tasks.isEmpty()) {
            output.append("Zero tasks on the list! You seem to be very free.");
            return output.toString();
        }

        output.append("I have pulled up the master list! Here is our current plan:\n");
        for (int i = 0; i < tasks.size(); i++) {
            output.append("  ")
                    .append(i + 1)
                    .append(". ")
                    .append(tasks.get(i));
            if (i < tasks.size() - 1) {
                output.append("\n");
            }
        }
        return output.toString();
    }
}
