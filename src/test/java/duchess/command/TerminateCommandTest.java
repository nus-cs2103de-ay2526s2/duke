package duchess.command;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import duchess.storage.Storage;
import duchess.task.TaskList;

/**
 * Tests for the TerminateCommand class.
 */
public class TerminateCommandTest {
    private TerminateCommand command;
    private TaskList tasks;
    private Storage mockStorage;

    /**
     * Sets up the test environment.
     */
    @BeforeEach
    public void setUp() {
        command = new TerminateCommand();
        tasks = new TaskList();
        mockStorage = mock(Storage.class);
    }

    /**
     * Cleans up the test environment.
     */
    @AfterEach
    public void tearDown() {
        command = null;
        tasks = null;
        mockStorage = null;
    }

    /**
     * Tests that the command ends the program.
     */
    @Test
    public void testExecute() {
        assertTrue(command.isTerminatingCommand());
        assertEquals("Farewell! Mayhap we shall meet anon!",
                command.execute(tasks, mockStorage),
                "Ends the program");
    }
}
