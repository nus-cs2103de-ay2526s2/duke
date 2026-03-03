package duchess.command;

import duchess.storage.Storage;
import duchess.task.TaskList;

/**
 * Class representing a command to display the current task list.
 */
public class DisplayListCommand extends Command {
    /**
     * Prints the task list if the list is not empty. Otherwise, print an error message.
     *
     * @param tasks list of tasks that commands will operate on
     * @param storage storage for saving and loading task lists
     * @return message to be displayed to the user
     */
    @Override
    public String execute(TaskList tasks, Storage storage) {
        if (tasks.isEmpty()) {
            return "Hark, thy scroll be bare of any note!";
        }

        return String.format("Hark, attend to the tasks upon thy scroll:\n%s", tasks);
    }
}
