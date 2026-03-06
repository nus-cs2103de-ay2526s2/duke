package chatterbox.command;

import chatterbox.ChatterBotException;
import chatterbox.Storage;
import chatterbox.Task;
import chatterbox.TaskList;

/**
 * Represents a command to list all tasks currently stored.
 */
public class ListCommand implements Command {

    @Override
    public CommandResult execute(TaskList tasks, Storage storage) {
        if (tasks.size() == 0) {
            return new CommandResult("You have no tasks in your list.");
        }

        StringBuilder sb = new StringBuilder("Here are the tasks in your list:\n");

        for (int i = 0; i < tasks.size(); i++) {
            int displayIndex = i + 1;
            try {
                Task task = tasks.get(displayIndex); // TaskList is 1-based
                sb.append(displayIndex).append(". ").append(task).append("\n");
            } catch (ChatterBotException e) {
                // Should not happen if size() is correct, but handle defensively.
                return new CommandResult(e.getMessage(), false, true);
            }
        }

        return new CommandResult(sb.toString().trim());
    }

    @Override
    public boolean isExit() {
        return false;
    }
}