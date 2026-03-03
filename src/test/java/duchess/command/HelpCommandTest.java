package duchess.command;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import duchess.storage.Storage;
import duchess.task.TaskList;

/**
 * Tests for the HelpCommand class.
 */
public class HelpCommandTest {
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
     * Tests that the help message is displayed.
     */
    @Test
    public void testExecute_displayHelp_success() {
        assertEquals("""
                Hark, attend to the commands at thy disposal:\n
                1. todo NAME
                - doth forge a task.\n
                2. deadline NAME /by DATE
                - doth set a task with a term.\n
                3. event NAME /from START_DATE /to END_DATE
                - doth frame a task 'twixt two suns.\n
                4. delete INDEX
                - doth strike down a task by its given count.\n
                5. list
                - doth show forth all tasks in thy ledger.\n
                6. cheer
                - doth speak a random word of courage.\n
                7. outstanding DATE
                - doth reveal tasks yet to be done by the set day.\n
                8. find KEYWORD
                - doth seek tasks akin to the given word.\n
                9. mark INDEX
                - doth deem a task done by its count.\n
                10. unmark INDEX
                - doth call back a task to be done.\n
                11. bye
                - doth bid farewell to this program.\n
                For deeper lore, seek the README.md.""",
                new HelpCommand().execute(tasks, mockStorage),
                "Help message should be displayed");
    }
}
