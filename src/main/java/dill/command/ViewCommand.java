package dill.command;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import dill.quote.QuoteList;
import dill.storage.Storage;
import dill.task.TaskList;

/**
 * Represents a command to view the tasks occurring on a specific date.
 */
public class ViewCommand extends Command {
    private LocalDate date;

    /**
     * Creates an instance of ViewCommand and initializes it with a LocalDate object.
     *
     * @param date The date to filter tasks by.
     */
    public ViewCommand(LocalDate date) {
        this.date = date;
    }

    /**
     * Returns the date associated with this ViewCommand.
     *
     * @return The date to filter tasks by.
     */
    public LocalDate getDate() {
        return this.date;
    }

    /**
     * Executes the view command by
     *
     * @param taskList The list of tasks to be executed on.
     * @param storage The data storage handler for saving and loading tasks.
     * @param quoteList The list of quotes containing motivational messages.
     * @return The post-execution message to be displayed to the user.
     */
    public String execute(TaskList taskList, Storage storage, QuoteList quoteList) {
        List<TaskList.MatchingTask> matchingTasks = taskList.filterByDate(date);
        String formattedDate = date.format(DateTimeFormatter.ofPattern("dd MMM yyyy"));
        StringBuilder output = new StringBuilder();
        if (matchingTasks.isEmpty()) {
            output.append("There are no tasks occurring on ")
                    .append(formattedDate);
            return output.toString();
        }

        output.append("Here are the tasks occurring on ")
                .append(formattedDate)
                .append(":\n")
                .append(formatMatchingTasks(matchingTasks));
        return output.toString();
    }
}
