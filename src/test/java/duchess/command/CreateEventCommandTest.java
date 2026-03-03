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
 * Tests for the CreateEventCommand class.
 */
public class CreateEventCommandTest {
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
                new CreateEventCommand(
                        Map.of("/default", ""))
                        .execute(tasks, mockStorage),
                "Event is missing name");
    }

    /**
     * Tests that an exception is thrown when the start date is missing.
     */
    @Test
    public void testExecute_missingStartDate_exceptionThrown() {
        assertThrows(MissingArgumentException.class, () ->
                new CreateEventCommand(
                        Map.of("/default", "a",
                                "/from", "",
                                "/to", "2001-01-02"))
                        .execute(tasks, mockStorage),
                "Event is missing start date");
    }

    /**
     * Tests that an exception is thrown when the end date is missing.
     */
    @Test
    public void testExecute_missingEndDate_exceptionThrown() {
        assertThrows(MissingArgumentException.class, () ->
                new CreateEventCommand(
                        Map.of("/default", "a",
                                "/from", "2001-01-02",
                                "/to", ""))
                        .execute(tasks, mockStorage),
                "Event is missing end date");
    }

    /**
     * Tests that an exception is thrown when the start date is invalid.
     */
    @Test
    public void testExecute_invalidStartDate_exceptionThrown() {
        assertThrows(InvalidArgumentException.class, () ->
                new CreateEventCommand(
                        Map.of("/default", "a",
                                "/from", "now",
                                "/to", "2001-01-02"))
                        .execute(tasks, mockStorage),
                "Start date is invalid");
    }

    /**
     * Tests that an exception is thrown when the end date is invalid.
     */
    @Test
    public void testExecute_invalidEndDate_exceptionThrown() {
        assertThrows(InvalidArgumentException.class, () ->
                new CreateEventCommand(
                        Map.of("/default", "a",
                                "/from", "2001-01-02",
                                "/to", "now"))
                        .execute(tasks, mockStorage),
                "End date is invalid");
    }

    /**
     * Tests that an exception is thrown when the end date is before the start date.
     */
    @Test
    public void testExecute_endDateBeforeStartDate_exceptionThrown() {
        assertThrows(InvalidArgumentException.class, () ->
                new CreateEventCommand(
                        Map.of("/default", "a",
                                "/from", "2001-01-03",
                                "/to", "2001-01-02"))
                        .execute(tasks, mockStorage),
                "End date is before start date");
    }

    /**
     * Tests that an event task is successfully created when valid inputs are provided.
     */
    @Test
    public void testExecute_validInputs_success() throws Exception {
        assertEquals("""
                Hark! I have appended this task:
                [E][ ] a (from: Mon, 01 Jan 2001 to: Tue, 02 Jan 2001)
                Now, thou hast 1 task(s) upon thy scroll.""",
                new CreateEventCommand(
                        Map.of("/default", "a",
                                "/from", "2001-01-01",
                                "/to", "2001-01-02"))
                        .execute(tasks, mockStorage),
                "Event task should be successfully created");
    }
}
