package chatbox.main;

import chatbox.main.commands.Command;
import chatbox.main.tasks.TaskList;

/**
 * The main logic class for the chatbot.
 * Now acts as the "brain" that receives input from the GUI and returns responses.
 */
public class ZhengjunChatbox {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    // Default constructor for GUI
    public ZhengjunChatbox() {
        this("data/ChatboxMemory.txt");
    }

    // Sets up the tools and tries to load the save file
    public ZhengjunChatbox(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (Exception e) {
            // If loading fails, start with an empty list
            tasks = new TaskList();
        }
    }

    public String getWelcome() {
        return ui.showWelcome();
    }
    public String getResponse(String input) {
        try {
            Command c = Parser.parse(input);
            return c.execute(tasks, ui, storage); // Returns the result String to the GUI
        } catch (ChatBoxException e) {
            return ui.showError(e.getMessage());
        } catch (Exception e) {
            return ui.showError(e.getMessage());
        }
    }
}