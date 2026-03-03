package duchess.ui;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

import duchess.command.Command;
import duchess.exception.InvalidArgumentException;
import duchess.exception.MissingArgumentException;
import duchess.parser.CommandParser;
import duchess.storage.Storage;
import duchess.task.TaskList;

/**
 * The Duchess chatbot can manage tasks and their completion statuses.
 */
public class Duchess {
    private static final Path SAVE_FILE_PATH = Paths.get(".", "data", "tasks.txt");
    private static final Path QUOTES_FILE_PATH = Paths.get(".", "data", "cheer.txt");
    private final Storage storage;
    private TaskList tasks;
    private final Ui ui;

    /**
     * Constructor for Duchess class.
     * <p>
     * Tries to load tasks and quotes from files.
     * If loading fails, creates a new TaskList and empty list of quotes.
     * </p>
     */
    public Duchess(String... args) {
        storage = new Storage(SAVE_FILE_PATH, QUOTES_FILE_PATH);
        ui = new Ui();

        for (String arg : args) {
            if (arg.equals("noload")) {
                tasks = new TaskList();
                return;
            }
        }

        try {
            tasks = storage.loadTasksFromFile();
        } catch (IOException e) {
            ui.displayLoadingErrorMessage();
            tasks = new TaskList();
        }
    }

    /**
     * Runs the Duchess chatbot.
     */
    public void run() {
        boolean shouldTerminate = false;

        ui.displayWelcomeMessage();

        while (!shouldTerminate) {
            String input = ui.readCommand();
            Command command = CommandParser.getCommand(input);

            try {
                String result = command.execute(tasks, storage);
                ui.display(result);
                shouldTerminate = command.isTerminatingCommand();
            } catch (InvalidArgumentException | MissingArgumentException e) {
                ui.display(e.getMessage());
            } catch (Exception e) {
                ui.display(e.getMessage());
                ui.display(Arrays.toString(e.getStackTrace()));
            }
        }
    }

    /**
     * Main method for Duchess.
     *
     * @param args accepts "noload" as an argument to skip loading tasks from a file
     */
    public static void main(String... args) {
        new Duchess(args).run();
    }

    /**
     * Executes a command and returns a Response object containing the result of executing a command.
     * @param input the input string
     * @return a Response object containing the result of executing a command
     */
    public Response getResponse(String input) {
        Command command = CommandParser.getCommand(input);
        String result;
        boolean shouldTerminate = false;

        try {
            result = command.execute(tasks, storage);
            shouldTerminate = command.isTerminatingCommand();
        } catch (InvalidArgumentException | MissingArgumentException e) {
            result = e.getMessage();
        } catch (Exception e) {
            result = "A most grievous error hath befallen. Pray, try again anon. Error: " + e.getMessage();
        }

        return new Response(result, shouldTerminate);
    }
}
