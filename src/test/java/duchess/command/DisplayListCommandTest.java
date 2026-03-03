package duchess.command;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import duchess.storage.Storage;
import duchess.task.TaskList;
import duchess.task.ToDo;

/**
 * Tests for the DisplayListCommand class.
 */
public class DisplayListCommandTest {
    private Storage mockStorage;

    /**
     * Sets up the test environment.
     */
    @BeforeEach
    public void setUp() {
        mockStorage = mock(Storage.class);
    }

    /**
     * Cleans up the test environment.
     */
    @AfterEach
    public void tearDown() {
        mockStorage = null;
    }

    /**
     * Tests that the list is empty when there are no tasks in the list.
     */
    @Test
    public void testExecute_emptyList_success() {
        assertEquals("Hark, thy scroll be bare of any note!",
                new DisplayListCommand()
                        .execute(new TaskList(), mockStorage),
                "List is empty");
    }

    /**
     * Tests that the list is displayed correctly when there are tasks in the list.
     */
    @Test
    public void testExecute_nonEmptyList_success() {
        TaskList tasks = new TaskList();
        tasks.addTask(new ToDo("Test Task"));
        tasks.addTask(new ToDo("Test Task 2"));

        assertEquals("""
                Hark, attend to the tasks upon thy scroll:
                1. [T][ ] Test Task
                2. [T][ ] Test Task 2""",
                new DisplayListCommand()
                        .execute(tasks, mockStorage),
                "2 tasks in list should be displayed");
    }
}
