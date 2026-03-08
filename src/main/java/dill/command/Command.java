package dill.command;

import java.util.List;

import dill.exception.DillException;
import dill.quote.QuoteList;
import dill.storage.Storage;
import dill.task.TaskList;

/**
 * A general abstract class that represents a command that can be executed.
 * Serves as the foundation for all specific commands.
 */
public abstract class Command {
    /**
     * Executes the specific logic of the command.
     *
     * @param taskList The list of tasks to be executed on.
     * @param storage The data storage handler for saving and loading tasks.
     * @param quoteList The list of quotes containing motivational messages.
     * @return The post-execution message to be displayed to the user.
     * @throws DillException If an error occurs during execution.
     */
    public abstract String execute(
            TaskList taskList, Storage storage, QuoteList quoteList) throws DillException;

    /**
     * Indicates if the program should terminate.
     *
     * @return True if the command is the exit command.
     */
    public boolean isExit() {
        return false;
    }

    /**
     * Formats a list of matching tasks into its string representation.
     *
     * @param matchingTasks The list of matching tasks.
     * @return The formatted string representation of the matching tasks.
     */
    protected String formatMatchingTasks(List<TaskList.MatchingTask> matchingTasks) {
        StringBuilder output = new StringBuilder();
        for (int i = 0; i < matchingTasks.size(); i++) {
            output.append("  ")
                    .append(matchingTasks.get(i).getIndex() + 1)
                    .append(". ")
                    .append(matchingTasks.get(i).getTask());
            if (i < matchingTasks.size() - 1) {
                output.append("\n");
            }
        }
        return output.toString();
    }
}
