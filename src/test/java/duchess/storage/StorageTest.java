package duchess.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import duchess.task.TaskList;
import duchess.task.ToDo;

/**
 * Tests for the Storage class.
 */
public class StorageTest {
    @TempDir
    Path tempDir;

    private Path taskListPath;
    private Path quotesPath;
    private Storage storage;

    /**
     * Sets up the test environment by creating temporary files.
     */
    @BeforeEach
    public void setUp() {
        taskListPath = tempDir.resolve("tasks.txt");
        quotesPath = tempDir.resolve("quotes.txt");
        storage = new Storage(taskListPath, quotesPath);
    }

    /**
     * Cleans up after each test.
     */
    @AfterEach
    public void tearDown() {
        storage = null;
    }

    /**
     * Tests that default quotes are created if the quotes file is missing.
     */
    @Test
    public void testConstructor_createsDefaultQuotes_success() throws IOException {
        assertTrue(Files.exists(quotesPath), "Quotes file should be created");
        List<String> quotes = Files.readAllLines(quotesPath);
        assertFalse(quotes.isEmpty(), "Quotes file should not be empty");
    }

    /**
     * Tests that tasks can be saved to and loaded from a file.
     */
    @Test
    public void testSaveAndLoadTasks_success() throws IOException {
        TaskList tasks = new TaskList();
        tasks.addTask(new ToDo("Test Task 1"));
        tasks.addTask(new ToDo("Test Task 2", true));

        storage.saveTasksToFile(tasks);
        assertTrue(Files.exists(taskListPath), "Task list file should be created");

        TaskList loadedTasks = storage.loadTasksFromFile();
        assertEquals(2, loadedTasks.getSize(), "Should load 2 tasks");
        assertEquals("1. [T][ ] Test Task 1\n2. [T][X] Test Task 2", loadedTasks.toString());
    }

    /**
     * Tests that quotes can be loaded from a file.
     */
    @Test
    public void testLoadQuotes_success() throws IOException {
        Files.writeString(quotesPath, "Quote 1\nQuote 2");
        Storage newStorage = new Storage(taskListPath, quotesPath);
        List<String> quotes = newStorage.getQuotes();
        assertEquals(2, quotes.size(), "Should load 2 quotes");
        assertEquals("Quote 1", quotes.get(0));
        assertEquals("Quote 2", quotes.get(1));
    }

    /**
     * Tests that an empty task list is returned when loading from a non-existent file.
     */
    @Test
    public void testLoadTasks_fileNotFound_returnsEmptyList() throws IOException {
        Path nonExistentPath = tempDir.resolve("non-existent.txt");
        Storage newStorage = new Storage(nonExistentPath, quotesPath);

        assertThrows(IOException.class, newStorage::loadTasksFromFile, "Input is not an integer");
    }
}
