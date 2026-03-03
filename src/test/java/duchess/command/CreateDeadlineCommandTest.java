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

/**
 * Tests for the CreateDeadlineCommand class.
 */
public class CreateDeadlineCommandTest {
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
                new CreateDeadlineCommand(
                        Map.of("/default", "",
                                "/by", "2001-01-01"))
                        .execute(tasks, mockStorage),
                "Deadline is missing name");
    }

    /**
     * Tests that an exception is thrown when the end date is missing.
     */
    @Test
    public void testExecute_missingEndDate_exceptionThrown() {
        assertThrows(MissingArgumentException.class, () ->
                new CreateDeadlineCommand(
                        Map.of("/default", "a",
                                "/by", ""))
                        .execute(tasks, mockStorage),
                "Deadline is missing end date");
    }

    /**
     * Tests that an exception is thrown when the end date is invalid.
     */
    @Test
    public void testExecute_invalidEndDate_exceptionThrown() {
        assertThrows(InvalidArgumentException.class, () ->
                new CreateDeadlineCommand(
                        Map.of("/default", "a",
                                "/by", "now"))
                        .execute(tasks, mockStorage),
                "End date is in invalid format");
    }

    /**
     * Tests that a deadline task is successfully created when valid inputs are provided.
     */
    @Test
    public void testExecute_validInputs_success() throws Exception {
        assertEquals("""
                Hark! I have appended this task:
                [D][ ] a (by: Mon, 01 Jan 2001)
                Now, thou hast 1 task(s) upon thy scroll.""",
                new CreateDeadlineCommand(
                        Map.of("/default", "a",
                                "/by", "2001-01-01"))
                        .execute(tasks, mockStorage),
                "Deadline task should be successfully created");
    }
}
