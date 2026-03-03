package duchess.command;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;

import java.util.Map;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import duchess.exception.MissingArgumentException;
import duchess.storage.Storage;
import duchess.task.TaskList;

/**
 * Tests for the CreateToDoCommand class.
 */
public class CreateToDoCommandTest {
    private TaskList tasks;
    private Storage mockStorage;

    /**
     * Sets up the test environment.
     */
    @BeforeEach
    public void setUp() {
        tasks = new TaskList();
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
     * Tests that an exception is thrown when the task name is missing.
     */
    @Test
    public void testExecute_missingTaskName_exceptionThrown() {
        assertThrows(MissingArgumentException.class, () ->
                new CreateToDoCommand(
                        Map.of("/default", ""))
                        .execute(tasks, mockStorage),
                "Todo is missing name");
    }

    /**
     * Tests that a todo task is successfully created when valid inputs are provided.
     */
    @Test
    public void testExecute_validInputs_success() throws Exception {
        assertEquals("""
                Hark! I have appended this task:
                [T][ ] a
                Now, thou hast 1 task(s) upon thy scroll.""",
                new CreateToDoCommand(
                        Map.of("/default", "a"))
                        .execute(tasks, mockStorage),
                "Todo task should be successfully created");
    }
}
