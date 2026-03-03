package duchess.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Tests for the TaskList class.
 */
public class TaskListTest {
    private TaskList tasks;

    /**
     * Sets up the test environment.
     */
    @BeforeEach
    public void setUp() {
        tasks = new TaskList();
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
    }

    /**
     * Tests that a task can be added to the list.
     */
    @Test
    public void testAddTask() {
        tasks.addTask(new ToDo("Test Task 4"));
        assertEquals(4, tasks.getSize(), "1 more task is added to the list");
    }

    /**
     * Tests that a task can be removed from the list.
     */
    @Test
    public void testRemoveTask() throws Exception {
        tasks.removeTask(1);
        assertEquals(2, tasks.getSize(), "1 task is removed from the list");
    }

    /**
     * Tests that a task can be marked as complete.
     */
    @Test
    public void testMarkTaskAsComplete() throws Exception {
        tasks.markTaskAsComplete(1);
        assertTrue(tasks.removeTask(1).isComplete(), "Removed task should be completed");
    }

    /**
     * Tests that a task can be marked as incomplete.
     */
    @Test
    public void testMarkTaskAsIncomplete() throws Exception {
        tasks.markTaskAsIncomplete(1);
        assertFalse(tasks.removeTask(1).isComplete(), "Removed task should be completed");
    }

    /**
     * Tests that outstanding tasks are returned correctly.
     */
    @Test
    public void testGetOutstandingTasks() {
        assertEquals("""
                1. [E][ ] Test Task 3 (from: Wed, 03 Jan 2001 to: Fri, 05 Jan 2001)""",
                tasks.getOutstandingTasks(LocalDate.parse("2001-01-04"))
                        .toString(),
                "Only event is outstanding");
    }
}
