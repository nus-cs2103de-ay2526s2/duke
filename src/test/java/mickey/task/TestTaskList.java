package mickey.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Test class for TaskList
 */
public class TestTaskList {
    private TaskList tasks;
    private Todo task1;
    private Todo task2;

    @BeforeEach
    public void setUp() {
        // initialize before each test
        tasks = new TaskList();
        task1 = new Todo("read book");
        task2 = new Todo("return book");
    }

    @Test
    public void testAddSingleTask() {
        tasks.addTask(task1);

        // check size
        assertEquals(1, tasks.size());
        // check if task is at index 0
        assertEquals(task1, tasks.getTask(0));
    }

    @Test
    public void testAddMultipleTasks() {
        tasks.addTask(task1);
        tasks.addTask(task2);

        assertEquals(2, tasks.size());
        assertEquals(task1, tasks.getTask(0));
        assertEquals(task2, tasks.getTask(1));
    }

    @Test
    public void testDeleteTask() {
        // add 2 tasks
        tasks.addTask(task1);
        tasks.addTask(task2);

        // delete first task
        Task deletedTask = tasks.deleteTask(0);

        assertEquals(task1, deletedTask);
        assertEquals(1, tasks.size());
        // task2 should now be at index 0
        assertEquals(task2, tasks.getTask(0));
    }

    @Test
    public void testMarkTask() {
        tasks.addTask(task1);
        tasks.markTask(0);

        // task should be marked as complete
        assertTrue(tasks.getTask(0).isComplete());
    }

    @Test
    public void testUnmarkTask() {
        tasks.addTask(task1);

        // first mark it
        tasks.markTask(0);
        // then unmark it
        tasks.unmarkTask(0);

        // should be incomplete now
        assertFalse(tasks.getTask(0).isComplete());
    }

    @Test
    public void testIsEmptyWhenEmpty() {
        // new list should be empty
        assertTrue(tasks.isEmpty());
    }

    @Test
    public void testIsEmptyWhenNotEmpty() {
        tasks.addTask(task1);
        // list with tasks should not be empty
        assertFalse(tasks.isEmpty());
    }
}
