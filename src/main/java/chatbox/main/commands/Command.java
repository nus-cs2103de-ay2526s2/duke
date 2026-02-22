package chatbox.main.commands;

import chatbox.main.ChatBoxException;
import chatbox.main.Storage;
import chatbox.main.tasks.TaskList;
import chatbox.main.Ui;
/**
 * Represents an abstract command that can be executed by the chatbox.
 * This class serves as a base for all specific command types (e.g., AddCommand, ExitCommand).
 */
public abstract class Command {
    public abstract String execute(TaskList tasks, Ui ui, Storage storage) throws ChatBoxException;

    public boolean isExit() {
        return false;
    }
}