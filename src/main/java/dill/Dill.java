package dill;

import java.util.List;

import dill.command.Command;
import dill.exception.CorruptedFileException;
import dill.exception.DillException;
import dill.exception.StorageException;
import dill.parser.Parser;
import dill.quote.QuoteList;
import dill.storage.Storage;
import dill.task.Task;
import dill.task.TaskList;
import dill.userinterface.UiMessages;
import dill.userinterface.textui.UserInterface;

/**
 * Main class for the Dill chatbot application.
 */
public class Dill {
    private static final String PATH_TASKS = "./data/dill.txt";
    private static final String PATH_QUOTES = "./data/quotes.txt";
    private TaskList taskList;
    private UserInterface textUi;
    private Storage storage;
    private QuoteList quoteList;
    private String loadMessage;

    /**
     * Creates a new instance of Dill and initializes core components.
     */
    public Dill() {
        StringBuilder messageBuilder = new StringBuilder();
        this.textUi = new UserInterface();
        this.storage = new Storage(PATH_TASKS, PATH_QUOTES);
        this.taskList = initTaskList(messageBuilder);
        this.quoteList = initQuoteList(messageBuilder);
        loadMessage = messageBuilder.toString();

        assert textUi != null : "textUi must be initialized";
        assert storage != null : "storage must be initialized";
        assert taskList != null : "taskList must be initialized";
        assert quoteList != null : "quoteList must be initialized";
    }

    /**
     * Returns the greeting message to be displayed on GUI startup.
     *
     * @return The startup greeting message.
     */
    public String getGuiBootMessage() {
        return UiMessages.getGreeting(loadMessage);
    }

    /**
     * Processes the user input from the GUI and returns Dill's response
     *
     * @param userInput The user input from the GUI.
     * @return The response message.
     */
    public String getResponse(String userInput) throws DillException {
        assert userInput != null : "User input should not be null";
        Command cmd = Parser.parse(userInput);
        return cmd.execute(taskList, storage, quoteList);
    }

    /**
     * Sets the name of the user.
     *
     * @param userInput The name of the user.
     * @return The response message from Dill acknowledging the username.
     */
    public String setName(String userInput) {
        assert userInput != null : "User input should not be null";
        return UiMessages.setUsername(userInput);
    }

    /**
     * Starts the main loop of the text-ui chatbot.
     * Displays a greeting message, then reads, parses, and executes inputs until an exit command ("bye") is given.
     */
    public void start() {
        textUi.displayMessage(UiMessages.getGreeting(loadMessage));
        boolean isExit = false;
        while (!isExit) {
            try {
                String userInput = textUi.readInput();
                Command cmd = Parser.parse(userInput);
                String output = cmd.execute(taskList, storage, quoteList);
                textUi.displayMessage(output);
                isExit = cmd.isExit();
            } catch (StorageException e) {
                textUi.displayMessage(UiMessages.getTasksSaveError());
            } catch (DillException e) {
                textUi.displayMessage(e.getMessage());
            }
        }
    }

    private TaskList initTaskList(StringBuilder messageBuilder) {
        try {
            List<Task> tasks = storage.loadTasks();
            messageBuilder.append(UiMessages.getLoadTasksSuccess(tasks.size()));
            return new TaskList(tasks);
        } catch (CorruptedFileException e) {
            storage.setTaskWritable(true); // Enable write access to overwrite corrupted file
            messageBuilder.append(UiMessages.getTasksLoadCorrupt());
            return new TaskList();
        } catch (StorageException e) {
            messageBuilder.append(UiMessages.getLoadTasksError());
            return new TaskList();
        }
    }

    private QuoteList initQuoteList(StringBuilder messageBuilder) {
        try {
            return new QuoteList(storage.loadQuotes());
        } catch (StorageException e) {
            messageBuilder.append("\n")
                    .append(e.getMessage());
            return new QuoteList();
        }
    }

    // text-ui entry point
    public static void main(String[] args) {
        new Dill().start();
    }
}
