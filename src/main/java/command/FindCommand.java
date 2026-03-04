/**
 * @author WEI JIE
 * @since 08/02/2026
 */
package command;

import java.util.List;

import model.Task;
import model.TaskList;
import ui.Ui;

/**
 * Finds the task from tasklist based on user input token.
 * Returns the task status from tasklist
 */
public class FindCommand extends Command {
    private final String keyword;

    /**
     * Constructs FindCommand
     * @param keyword String from user input
     */
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    /**
     * Searches TaskList for task names with matching keyword
     * @return list of matching tasks
     */
    @Override
    public String execute() {
        List<Task> matchingTasks = TaskList.find(keyword);
        return Ui.taskstoString(" Here are the matching tasks in your list:", matchingTasks);
    }

}