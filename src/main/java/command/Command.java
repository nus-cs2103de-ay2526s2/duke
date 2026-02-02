package command;

import java.util.ArrayList;
import model.Task;

/**
 * Executes command based on the first token in message
 */
public abstract class Command {
    public abstract void execute(ArrayList<Task> tasks);
}