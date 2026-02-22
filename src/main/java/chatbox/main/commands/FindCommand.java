package chatbox.main.commands;

import chatbox.main.Storage;
import chatbox.main.Ui;
import chatbox.main.tasks.TaskList;

/**
 * Represents a command to find tasks by searching for a keyword.
 */
public class FindCommand extends Command {
    private String keyword;

    public FindCommand(String keyword) {
        this.keyword = keyword;
    }
    /**
     * Executes the search by delegating to the TaskList and returns the matches.
     *
     * @param tasks   The list of tasks to search through.
     * @param ui      The user interface instance (not used in this command).
     * @param storage The storage instance (not used in this command).
     * @return A string listing all tasks that contain the specified keyword.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return tasks.findTasks(keyword);
    }
}