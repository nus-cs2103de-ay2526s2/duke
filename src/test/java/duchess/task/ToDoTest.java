package duchess.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

/**
 * Tests for the ToDo class.
 */
public class ToDoTest {
    /**
     * Tests that a todo object is created correctly.
     */
    @Test
    public void testToDoCreation() {
        ToDo todo = new ToDo("Test ToDo");
        assertEquals("Test ToDo", todo.getName());
        assertFalse(todo.isComplete());
    }

    /**
     * Tests that a todo object with completion status is created correctly.
     */
    @Test
    public void testToDoWithStatusCreation() {
        ToDo todo = new ToDo("Test ToDo", true);
        assertEquals("Test ToDo", todo.getName());
        assertTrue(todo.isComplete());
    }

    /**
     * Tests the string representation of the todo.
     */
    @Test
    public void testToString() {
        ToDo todo = new ToDo("Test ToDo");
        assertEquals("[T][ ] Test ToDo", todo.toString());
        todo.markAsComplete();
        assertEquals("[T][X] Test ToDo", todo.toString());
    }

    /**
     * Tests the save string representation of the todo.
     */
    @Test
    public void testToSaveString() {
        ToDo todo = new ToDo("Test ToDo");
        assertEquals("T | 0 | Test ToDo", todo.toSaveString());
        todo.markAsComplete();
        assertEquals("T | 1 | Test ToDo", todo.toSaveString());
    }
}
