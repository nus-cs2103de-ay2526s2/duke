package command;

import java.io.IOException;

import exception.CustomException;
import storage.Storage;
import ui.Ui;

/**
 * Saves taskList into data/StarTasks.txt
 */
public class SaveCommand extends Command {

    /**
     * Tries to save txt file.
     * Catches the exception thrown by save().
     * @return String stating save was successful
     * @throws CustomException if save was not successful
     */
    @Override
    public String execute() throws CustomException {
        try {
            Storage.save();
            return Ui.getMessageWithBotName(" Tasks Saved\n");
        } catch (IOException e) {
            throw new CustomException(" Unable to save file " + e.getMessage());
        }
    }
}