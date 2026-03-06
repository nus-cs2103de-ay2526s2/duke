package listo.storage;

import listo.task.TaskList;
import listo.task.Todo;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.nio.file.Path;
import java.io.File;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class StorageTest {

    // @TempDir creates a temporary folder that gets deleted after the test.
    // This prevents cluttering your computer with junk test files.
    @TempDir
    Path tempDir;

    @Test
    public void saveAndLoad_success() throws Exception {
        // 1. Create a fake file path in the temp folder
        File tempFile = tempDir.resolve("tempList.txt").toFile();

        // 2. Create Storage, TaskList, and add a task
        Storage storage = new Storage(tempFile.getAbsolutePath());
        TaskList originalList = new TaskList();
        originalList.addTask(new Todo("test save function"));

        // 3. Save the list
        storage.save(originalList);

        // 4. Load it back into a NEW list
        try {
            java.util.ArrayList<listo.task.Task> loadedData = storage.load();
            TaskList loadedList = new TaskList(loadedData);

            // 5. Verify the loaded task matches the saved task
            assertEquals(1, loadedList.getSize());
            assertEquals("[T][ ] test save function", loadedList.getTask(0).toString());
        } catch (Exception e) {
            fail("Load should not throw exception");
        }
    }
}