package dill.storage;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.lang.reflect.Field;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import org.mockito.Mockito;

import dill.task.Deadline;
import dill.task.Event;
import dill.task.Task;
import dill.task.TaskList;
import dill.task.ToDo;

public class StorageTest {
    @TempDir
    Path tempDir;

    private Storage storage;

    // Test saving tasks to a valid file.
    @Test
    void saveTasksValidFileTest() throws IOException, NoSuchFieldException, IllegalAccessException {
        Path tempFile = tempDir.resolve("saveTasksTest.txt");
        storage = new Storage(tempFile.toString(), "");

        TaskList mockTaskList = Mockito.mock(TaskList.class);
        Task mockTask1 = Mockito.mock(Task.class);
        Task mockTask2 = Mockito.mock(Task.class);

        when(mockTaskList.getSize()).thenReturn(2);
        when(mockTaskList.getTask(0)).thenReturn(mockTask1);
        when(mockTaskList.getTask(1)).thenReturn(mockTask2);

        // For encodeTask helper method
        when(mockTask1.getTaskType()).thenReturn("T");
        when(mockTask1.getTaskName()).thenReturn("read");
        when(mockTask1.getIsDone()).thenReturn(true);
        when(mockTask1.getDates()).thenReturn(new String[0]);

        when(mockTask2.getTaskType()).thenReturn("D");
        when(mockTask2.getTaskName()).thenReturn("assignment");
        when(mockTask2.getIsDone()).thenReturn(false);
        when(mockTask2.getDates()).thenReturn(new String[]{"2026-03-14"});

        // Set isTaskWritable field in storage to true using reflection
        Field isTaskWritableField = Storage.class.getDeclaredField("isTaskWritable");
        isTaskWritableField.setAccessible(true); // Set isTaskWritable as accessible
        isTaskWritableField.set(storage, true); // Set isTaskWritable as true

        // Read and verify storage file content
        assertDoesNotThrow(() -> storage.saveTasks(mockTaskList));
        List<String> lines = java.nio.file.Files.readAllLines(tempFile);
        assertEquals(2, lines.size());
        assertEquals("T | 1 | read", lines.get(0));
        assertEquals("D | 0 | assignment | 2026-03-14", lines.get(1));
    }

    // Test saving tasks to an invalid file.
    @Test
    void saveTasksInvalidFileTest() {
        storage = new Storage("./nonexistentDir/test.txt", "");
        TaskList mockTaskList = Mockito.mock(TaskList.class);
        assertDoesNotThrow(() -> storage.saveTasks(mockTaskList));
    }

    // Test creation of a new storage file when one does not exist.
    @Test
    void loadTasksCreateNewFileTest() {
        Path tempFile = tempDir.resolve("tasks.txt");
        storage = new Storage(tempFile.toString(), "");

        // Checks that temp file does not exist.
        assertFalse(Files.exists(tempFile));

        List<Task> tasks = assertDoesNotThrow(() -> storage.loadTasks());

        // Checks that task list is initialized with an empty arraylist.
        assertTrue(tasks.isEmpty());

        // Checks that temp file now exists.
        assertTrue(Files.exists(tempFile));
    }

    // Test loading tasks from an existing valid file with content
    @Test
    void loadTasksExistingFileTest() throws IOException {
        Path taskPath = tempDir.resolve("tasks.txt");

        // Create a temp storage file with some saved tasks.
        String content = "T | 1 | read book\n"
                + "D | 0 | study | 2026-03-14\n"
                + "E | 0 | meeting | 2026-03-14 | 2026-05-18";
        java.nio.file.Files.writeString(taskPath, content);

        // Load tasks from storage.
        Storage storage = new Storage(taskPath.toString(), "");
        List<Task> tasks = assertDoesNotThrow(() -> storage.loadTasks());

        // Verify if task list is correct.
        assertEquals(3, tasks.size());
        assertEquals(ToDo.class, tasks.get(0).getClass());
        assertEquals(Deadline.class, tasks.get(1).getClass());
        assertEquals(Event.class, tasks.get(2).getClass());
    }
}
