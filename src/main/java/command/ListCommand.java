package command;

import model.TaskList;
import ui.Ui;

/**
 * Lists out all existing task in Tasklist
 */
public class ListCommand extends Command {
    /**
     * Calls Ui.TaskstoString() to print task.toString.
     * return String containing list of task
     */
    @Override
    public String execute() {
        return Ui.taskstoString(" Tasklist:", TaskList.getTasks());
    }
}