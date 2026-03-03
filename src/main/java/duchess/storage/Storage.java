package duchess.storage;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import duchess.exception.InvalidArgumentException;
import duchess.exception.MissingArgumentException;
import duchess.parser.FileParser;
import duchess.task.Task;
import duchess.task.TaskList;

/**
 * Storage class for loading and saving information to a file.
 */
public class Storage {
    private static final String DEFAULT_QUOTES = """
        All the world’s a stage, and all the men and women merely players. — As You Like It, Shakespeare
        Not all those who wander are lost. — J.R.R. Tolkien
        In a time of deceit telling the truth is a revolutionary act. — George Orwell
        Time is the longest distance between two places. — Tennessee Williams
        Be yourself; everyone else is already taken. — Oscar Wilde
        """;
    private final File taskListFile;
    private final File quotesFile;
    private List<String> quotes;

    /**
     * Constructor for Storage class.
     *
     * <p>
     * The storage will attempt to load quotes from the specified file.
     * If the file does not exist, an empty list of quotes will be initialised for the current session.
     * Thereafter, a new set of default quotes will be created in a new file.
     * </p>
     *
     * @param taskListFilePath the path to the task list file
     * @param quotesFilePath the path to the quotes file
     */
    public Storage(Path taskListFilePath, Path quotesFilePath) {
        this.taskListFile = taskListFilePath.toAbsolutePath()
                .normalize()
                .toFile();
        this.quotesFile = quotesFilePath.toAbsolutePath()
                .normalize()
                .toFile();

        try {
            List<String> quotesFromFile = loadQuotesFromFile();
            setQuotes(quotesFromFile);
        } catch (IOException e) {
            setQuotes(new ArrayList<>());
        }

        if (getQuotes().isEmpty()) {
            quotes = getDefaultQuotes();
            try {
                saveDefaultQuotesToFile();
            } catch (IOException ignored) {
                // Ignored
            }
        }
    }

    /**
     * Returns a list of default quotes from a string.
     * @return a list of default quotes
     */
    private List<String> getDefaultQuotes() {
        String[] quotes = DEFAULT_QUOTES.split("\n");
        return Arrays.asList(quotes);
    }

    /**
     * Returns the list of quotes.
     *
     * @return the list of quotes
     */
    public List<String> getQuotes() {
        return quotes;
    }

    /**
     * Loads tasks from a file and saves it to a TaskList.
     * @return a TaskList containing the tasks loaded from the file
     * @throws IOException if the file cannot be found or read
     */
    public TaskList loadTasksFromFile() throws IOException {
        String rawTask;
        TaskList tasks = new TaskList();

        BufferedReader reader = new BufferedReader(new FileReader(taskListFile.getPath()));

        while ((rawTask = reader.readLine()) != null) {
            try {
                Task task = FileParser.getTask(rawTask);
                if (task != null) {
                    tasks.addTask(task);
                }
            } catch (InvalidArgumentException | MissingArgumentException e) {
                // Ignore invalid tasks
            }
        }

        reader.close();
        return tasks;
    }

    /**
     * Saves a TaskList to a file.
     * @param tasks the TaskList to be saved
     * @throws IOException if the file cannot be created or written to
     */
    public void saveTasksToFile(TaskList tasks) throws IOException {
        if (!taskListFile.exists()) {
            createFile(taskListFile);
        }

        String fileString = tasks.toSaveString();
        Files.writeString(Path.of(taskListFile.getPath()), fileString, StandardCharsets.UTF_8);
    }

    /**
     * Creates a file and its parent directory if it does not exist.
     *
     * @throws IOException if the file or parent directory cannot be created
     */
    private void createFile(File file) throws IOException {
        boolean isFolderCreated = true;
        boolean isFileCreated = true;

        if (!file.getParentFile().exists()) {
            isFolderCreated = file.getParentFile().mkdirs();
        }

        isFileCreated = file.createNewFile();

        if (!isFolderCreated) {
            throw new IOException("Failed to create folder for file.");
        }

        if (!isFileCreated) {
            throw new IOException("Failed to create file.");
        }
    }

    /**
     * Loads quotes from a file and saves it to Storage.
     *
     * @return a list of quotes loaded from the file
     * @throws IOException when the file cannot be found or read
     */
    private List<String> loadQuotesFromFile() throws IOException {
        String quote;
        List<String> quotes = new ArrayList<>();

        BufferedReader reader = new BufferedReader(new FileReader(quotesFile.getPath()));

        while ((quote = reader.readLine()) != null) {
            try {
                quotes.add(quote.strip());
            } catch (Exception e) {
                //Ignore invalid quotes
            }
        }

        reader.close();
        return quotes;
    }

    /**
     * Saves default quotes to a file if it does not exist.
     *
     * @throws IOException when the file cannot be created or written to
     */
    private void saveDefaultQuotesToFile() throws IOException {
        if (!quotesFile.exists()) {
            createFile(quotesFile);
        }

        Files.writeString(Path.of(quotesFile.getPath()), DEFAULT_QUOTES, StandardCharsets.UTF_8);
    }

    /**
     * Sets the list of quotes.
     *
     * @param quotes the list of quotes
     */
    private void setQuotes(List<String> quotes) {
        this.quotes = quotes;
    }
}
