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
import duchess.task.ToDo;

/**
 * Tests for the FindTaskCommand class.
 */
public class FindTaskCommandTest {
    private TaskList tasks;
    private Storage mockStorage;

    /**
     * Sets up the test environment.
     */
    @BeforeEach
    public void setUp() {
        tasks = new TaskList();
        mockStorage = mock(Storage.class);
        tasks.addTask(new ToDo("Findable task"));
        tasks.addTask(new ToDo("Another task"));
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
     * Tests that an exception is thrown when the keyword is missing.
     */
    @Test
    public void testExecute_missingKeyword_exceptionThrown() {
        assertThrows(MissingArgumentException.class, () ->
                new FindTaskCommand(
                        Map.of("/default", ""))
                        .execute(tasks, mockStorage),
                "No keyword provided");
    }

    /**
     * Tests that the command returns the correct tasks when a match is found.
     */
    @Test
    public void testExecute_matchFound_success() throws Exception {
        String response = new FindTaskCommand(
                Map.of("/default", "findable"))
                .execute(tasks, mockStorage);
        assertEquals("""
                Hark, the tasks that doth align within thy roster:
                1. [T][ ] Findable task""",
                response,
                "Should find the findable task");
    }

    /**
     * Tests that the command returns a "not found" message when no match is found.
     */
    @Test
    public void testExecute_noMatchFound_success() throws Exception {
        String response = new FindTaskCommand(
                Map.of("/default", "nonexistent"))
                .execute(tasks, mockStorage);
        assertEquals("Hark! No tasks of such sort art found within!",
                response,
                "Should not find any matching task");
    }
}
