package command;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import model.Priority;
import model.Task;
import model.TaskList;

import static org.junit.jupiter.api.Assertions.assertEquals;
/**
 * Tests AddTaskCommand by looking for the task in TaskList
 */
public class AddCommandTest {
    @BeforeEach
    public void setUp() {
        TaskList.clear(); // Reset static list before EACH test
    }
    @Test
    public void execute_addsTaskToTasklList() {
        Task task = new Task("Walk", Priority.MEDIUM);
        AddTaskCommand cmd = new AddTaskCommand(task);

        // Execute command
        cmd.execute();

        // Checks size
        assertEquals(1, TaskList.getSize());

        // checks if the task correct
        assertEquals(task.toString(), TaskList.getTask(0).toString());
    }
}