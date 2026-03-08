package bob;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

/**
 * Contains unit test for Task class
 */
public class TaskTest {

    /**
     * Test that mark task as done is updated with its completed status
     */
    @Test
    public void testMarkAsDone() {
        Task task = new Task("test task");
        assertFalse(task.isDone);
        task.markAsDone();
        assertTrue(task.isDone);
    }

    /**
     * Tests that the status icon reflects whether task is done
     */
    @Test
    public void testGetStatusIcon() {
        Task task = new Task("test task");
        assertEquals("[]", task.getStatusIcon());
        task.markAsDone();
        assertEquals("[X]", task.getStatusIcon());
    }
}