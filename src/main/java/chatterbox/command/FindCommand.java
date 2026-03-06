package chatterbox.command;

import chatterbox.Storage;
import chatterbox.Task;
import chatterbox.TaskList;

/**
 * Represents a command that finds tasks whose descriptions contain a given keyword.
 */
public class FindCommand implements Command {

    private final String keyword;

    /**
     * Constructs a FindCommand with the keyword to search for.
     *
     * @param keyword Keyword to match (non-empty).
     */
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public CommandResult execute(TaskList tasks, Storage storage) {
        TaskList.FindResult result = tasks.findByKeyword(keyword);

        if (result.count == 0) {
            return new CommandResult("No matching tasks found.");
        }

        StringBuilder sb = new StringBuilder("Here are the matching tasks:\n");
        for (int i = 0; i < result.count; i++) {
            Task match = result.matches[i];
            sb.append(i + 1).append(". ").append(match).append("\n");
        }

        return new CommandResult(sb.toString().trim());
    }

    @Override
    public boolean isExit() {
        return false;
    }
}