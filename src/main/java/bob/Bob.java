package bob;

/**
 * This is the bob.Main class for the Bob chatbot
 * Starts chatbot and manages the main run loop
 * Supports both CLI and GUI mode
 */

public class Bob {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private boolean isExit = false;

    /**
     * Constructor class with specified file path for storage
     * This is the case where there is gui
     * @param filePath the file path where tasks will be stored
     * @param isGui true if chatbot is running on GUI, false if CLI
     */
    public Bob(String filePath, boolean isGui) {
        ui = new Ui(isGui);
        storage = new Storage(filePath);
        tasks = new TaskList();
        storage.load(tasks.getTasks());
    }

    /**
     * Constructor if CLI
     * @param filePath
     */
    public Bob(String filePath) {
        this(filePath, false);
    }

    /**
     * CLI run loop
     * Run main method to keep the chatbot alive
     * Shows welcome message and continues to process until user exit
     */
    public void run() {
        ui.showWelcome();
        boolean isRunning = true;

        while (isRunning) {
            String input = ui.readCommand();

            if (input.toLowerCase().equals("bye")) {
                ui.showGoodbye();
                isRunning = false;
            }
            else {
                Parser.parse(input, tasks, ui, storage);
            }
    }
}

    /**
     * Getresponse helper method for GUI
     *
     * This method is used by GUI to obtain responses
     * without printing to console
     *
     * @param input user input from GUI text field
     * @return the response string by chatbot
     */
    public String getResponse(String input) {
        if (input == null) {
            return "";
        }

        String trimmed = input.trim();

        if(trimmed.isEmpty()) {
            return "";
        }

        if (trimmed.equalsIgnoreCase("bye")) {
            return "Bye. See you soon!";
        }

        Parser.parse(trimmed, tasks, ui, storage);
        return ui.getOutputAndClear();
    }

    /**
     * Welcome message for GUI
     * @return chatbot welcome message
     */
    public String getWelcomeMessage () {
        return "Hi! I'm BOB\nWhat can I do for you today?";
    }

    /**
     * Gui check to see if it should exit
     *
     * @return true if chatbot should exit
     * and false otherwise
     */
    public boolean isExit () {
        return isExit;
    }

    /**
     *  bob.Main method that runs the chatbot
     * @param args command line argument
     */
    public static void main (String[] args) {
        new Bob("./data/bob.txt").run();
    }


}
