package chatterbox.command;

import chatterbox.Storage;
import chatterbox.TaskList;

public interface Command {
    CommandResult execute(TaskList tasks, Storage storage);
    boolean isExit();
}