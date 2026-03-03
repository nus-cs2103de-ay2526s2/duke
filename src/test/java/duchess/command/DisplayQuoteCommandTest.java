package duchess.command;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import duchess.storage.Storage;
import duchess.task.TaskList;

/**
 * Tests for the DisplayQuoteCommand class.
 */
public class DisplayQuoteCommandTest {
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
     * Tests that the command prints the default quote when the list is empty.
     */
    @Test
    public void testExecute_emptyList_success() {
        when(mockStorage.getQuotes()).thenReturn(new ArrayList<>());
        assertEquals("Hark! No sayings of yore be at hand!",
                new DisplayQuoteCommand()
                        .execute(tasks, mockStorage),
                "No quotes available");
    }

    /**
     * Tests that the command prints the quote when there is only one quote in the list.
     */
    @Test
    public void testExecute_singleQuote_success() {
        when(mockStorage.getQuotes()).thenReturn(List.of("Test Quote"));
        assertEquals("Test Quote",
                new DisplayQuoteCommand()
                        .execute(tasks, mockStorage),
                "Print the only quote available");
    }
}
