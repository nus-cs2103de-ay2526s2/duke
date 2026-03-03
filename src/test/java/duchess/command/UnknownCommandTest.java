package duchess.command;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import duchess.storage.Storage;
import duchess.task.TaskList;

/**
 * Tests for the UnknownCommand class.
 */
public class UnknownCommandTest {
    private UnknownCommand command;
    private TaskList tasks;
    private Storage mockStorage;

    /**
     * Sets up the test environment.
     */
    @BeforeEach
    public void setUp() {
        command = new UnknownCommand();
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
     * Tests that the command prints an error message.
     */
    @Test
    public void testExecute() {
        assertEquals("Hark, I know not what that doth signify.",
                command.execute(tasks, mockStorage),
                "Prints an error message");
    }
}
