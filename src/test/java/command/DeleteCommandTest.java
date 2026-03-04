/**
 * @author Your Name
 * @since 13/02/2026
 */
package command;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import exception.CustomException;
import model.Priority;
import model.Task;
import model.TaskList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Tests 2 test cases:
 * 1. Deleting an existing task
 * 2. Deleting invalid task index
 */
public class DeleteCommandTest {
    // Similar @BeforeEach
    @BeforeEach
    void setUp() {
        TaskList.clear(); // Resets the static list before every test
    }
    
    @Test
    void execute_validIndex_removesTask() {
        Task task1 = new Task("Keep", Priority.MEDIUM);
        Task task2 = new Task("Delete", Priority.MEDIUM);
        TaskList.addTask(task1);
        TaskList.addTask(task2);

        DeleteCommand cmd = new DeleteCommand(1);
        cmd.execute();

        assertEquals(1, TaskList.getSize());
        assertEquals(task1, TaskList.getTask(0));
    }

    @Test
    void execute_invalidIndex_throws() {
        assertThrows(CustomException.class, () -> new DeleteCommand(99).execute());
    }
}