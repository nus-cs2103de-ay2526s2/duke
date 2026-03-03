package duchess.command;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;

import java.time.LocalDate;
import java.util.Map;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import duchess.exception.InvalidArgumentException;
import duchess.storage.Storage;
import duchess.task.Deadline;
import duchess.task.Event;
import duchess.task.TaskList;
import duchess.task.ToDo;

/**
 * Tests for the FindOutstandingCommand class.
 */
public class FindOutstandingCommandTest {
    private TaskList tasks;
    private Storage mockStorage;

    /**
     * Sets up the test environment.
     */
    @BeforeEach
    public void setUp() {
        tasks = new TaskList();
        mockStorage = mock(Storage.class);

        tasks.addTask(new ToDo("Test Task 1"));
        tasks.addTask(new Deadline("Test Task 2",
                LocalDate.parse("2001-01-01")));
        tasks.addTask(new Event("Test Task 3",
                LocalDate.parse("2001-01-03"),
                LocalDate.parse("2001-01-05")));
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
     * Tests that an exception is thrown when the date is invalid.
     */
    @Test
    public void testExecute_invalidDate_exceptionThrown() {
        assertThrows(InvalidArgumentException.class, () ->
                new FindOutstandingCommand(
                        Map.of("/default", "a"))
                        .execute(tasks, mockStorage),
                "Invalid date");
    }

    /**
     * Tests that the command filters out no tasks correctly when the date is valid.
     */
    @Test
    public void testExecute_validDateJan2nd2001_success() throws Exception {
        assertEquals("""
                Verily, no tasks remain outstanding past Tue, 02 Jan 2001!""",
                new FindOutstandingCommand(
                        Map.of("/default", "2001-01-02"))
                        .execute(tasks, mockStorage),
                "No tasks match the criteria");
    }

    /**
     * Tests that the command filters out the correct tasks when the date is valid.
     */
    @Test
    public void testExecute_validDateJan4th2001_success() throws Exception {
        assertEquals("""
                Hark, yon tasks of import that yet linger on thy scroll:
                1. [E][ ] Test Task 3 (from: Wed, 03 Jan 2001 to: Fri, 05 Jan 2001)""",
                new FindOutstandingCommand(
                        Map.of("/default", "2001-01-04"))
                        .execute(tasks, mockStorage),
                "Only event gets filtered out");
    }
}
