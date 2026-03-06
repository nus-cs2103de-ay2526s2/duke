package chatterbox.command;
import chatterbox.Storage;
import chatterbox.TaskList;

/**
 * A command that displays usage instructions and available commands.
 */
public class HelpCommand implements Command {

    private static final String HELP_MESSAGE = """
            ChatterBox Help Guide

            ChatterBox helps you manage and track your tasks.

            Available commands:

            list
                Displays all tasks.

            todo <description>
                Adds a todo task.

            deadline <description> /by <date>
                Adds a deadline task.

            event <description> /from <date> /to <date>
                Adds an event task.

            mark <index>
                Marks a task as done.

            unmark <index>
                Marks a task as not done.

            delete <index>
                Deletes a task.

            find <keyword>
                Finds tasks containing a keyword.

            cheer
                Displays a motivational message.

            help
                Shows this help message.

            bye
                Exits the application.
            """;

    @Override
    public CommandResult execute(TaskList tasks, Storage storage) {
        return new CommandResult(HELP_MESSAGE);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}