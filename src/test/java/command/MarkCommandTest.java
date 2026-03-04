/**
 * @author Your Name
 * @since 13/02/2026
 */
package command;

import static org.junit.jupiter.api.Assertions.*;

import exception.CustomException;
import model.Priority;
import model.Task;
import model.TaskList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Test 3 cases:
 * 1. Marking a task
 * 2. Attempt to mark an already marked task
 * 3. Marking invalid task index
 */
public class MarkCommandTest {

    @BeforeEach
    void setUp() {
        TaskList.clear();  // Reset static
    }

    @Test
    void execute_validIndex_marksTask() {
        Task task = new Task("Test", Priority.MEDIUM);  // Assume constructor
        TaskList.addTask(task);
        MarkCommand cmd = new MarkCommand(0);

        String result = cmd.execute();
        assertTrue(task.isDone());
        assertTrue(result.contains("Nice! I've marked this task as done"));
    }

    @Test
    void execute_alreadyDone_printsMessage() {
        Task task = new Task("Test", Priority.MEDIUM);
        task.markAsDone();
        TaskList.addTask(task);
        MarkCommand cmd = new MarkCommand(0);

        String result = cmd.execute();
        assertTrue(result.contains("Already done!"));
    }

    @Test
    void execute_invalidIndex_throwsException() throws  CustomException {
        MarkCommand cmd = new MarkCommand(99);
        assertThrows(CustomException.class, cmd::execute);
    }
}