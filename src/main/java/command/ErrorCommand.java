package command;

import java.util.ArrayList;
import model.Task;
import ui.Ui;

// Error
public class ErrorCommand extends Command {
    public final String errorMsg;

    public ErrorCommand(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    @Override
    public void execute(ArrayList<Task> tasks) {
        Ui.printErrorMsg(errorMsg);
    }
}