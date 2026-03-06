package chatterbox;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests persistence behaviour of Storage (save + load).
 */
public class StoragePersistenceTest {

    private static final Path TEST_PATH = Paths.get("data", "test_duke.txt");

    @AfterEach
    public void cleanup() throws Exception {
        // Purpose: Ensure test does not pollute storage across runs.
        Files.deleteIfExists(TEST_PATH);
    }

    @Test
    public void saveThenLoad_restoresTasksCorrectly() {
        // Purpose: Ensure tasks saved to disk can be loaded back with correct content and count.
        Storage storage = new Storage(TEST_PATH);

        Task[] tasks = new Task[100];
        tasks[0] = new Todo("read book");
        tasks[1] = new Deadline("return book", "2019-12-02 1800");

        storage.save(tasks, 2);

        Task[] loaded = new Task[100];
        int count = storage.load(loaded);

        assertEquals(2, count);
        assertEquals(tasks[0].toStorageString(), loaded[0].toStorageString());
        assertEquals(tasks[1].toStorageString(), loaded[1].toStorageString());
    }

    @Test
    public void load_missingFile_returnsZeroTasks() throws Exception {
        // Purpose: Ensure loading from a missing file does not crash and returns 0 tasks.
        Files.deleteIfExists(TEST_PATH);

        Storage storage = new Storage(TEST_PATH);
        Task[] loaded = new Task[100];
        int count = storage.load(loaded);

        assertEquals(0, count);
    }
}