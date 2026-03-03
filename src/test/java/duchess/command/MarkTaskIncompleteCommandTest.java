package duchess.command;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;

import java.util.Map;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import duchess.exception.InvalidArgumentException;
import duchess.exception.MissingArgumentException;
import duchess.storage.Storage;
import duchess.task.TaskList;
import duchess.task.ToDo;

/**
 * Tests for the MarkTaskIncompleteCommand class.
 */
public class MarkTaskIncompleteCommandTest {
    private TaskList tasks;
    private Storage mockStorage;

    /**
     * Sets up the test environment.
     */
    @BeforeEach
    public void setUp() {
        tasks = new TaskList();
        tasks.addTask(new ToDo("Test Task 1", true));
        mockStorage = mock(Storage.class);
    }

    /**
     * Cleans up the test environment.
     */
    @AfterEach
    public void tearDown() {
        tasks = null;
        mockStorage = null;
    }

    /**
     * Tests that an exception is thrown when the task index is missing.
     */
    @Test
    public void testExecute_missingIndex_exceptionThrown() {
        assertThrows(MissingArgumentException.class, () ->
                new MarkTaskIncompleteCommand(
                        Map.of("/default", ""))
                        .execute(tasks, mockStorage),
                "No list index provided");
    }

    /**
     * Tests that an exception is thrown when the task index is invalid.
     */
    @Test
    public void testExecute_invalidIndex_exceptionThrown() {
        assertThrows(InvalidArgumentException.class, () ->
                new MarkTaskIncompleteCommand(
                        Map.of("/default", "-1"))
                        .execute(tasks, mockStorage),
                "Index is out of range");
    }

    /**
     * Tests that a task is successfully marked as incomplete when a valid index is provided.
     */
    @Test
    public void testExecute_validIndex_success() throws Exception {
        assertEquals("""
                Hark, I have set this labour as yet unfinishen:
                [T][ ] Test Task 1""",
                new MarkTaskIncompleteCommand(
                        Map.of("/default", "1"))
                        .execute(tasks, mockStorage),
                "Marks the only task as undone");
    }
}
