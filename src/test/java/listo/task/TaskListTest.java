package listo.task;

import listo.exception.ListoException;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class TaskListTest {
    @Test
    public void addTask_increasesSize() {
        // Verifies that adding a new task correctly increments the list size count.
        TaskList tasks = new TaskList();
        tasks.addTask(new Todo("read"));
        assertEquals(1, tasks.getSize());
    }

    @Test
    public void deleteTask_decreasesSize() throws ListoException {
        // Verifies that deleting a task correctly decrements the list size count.
        TaskList tasks = new TaskList();
        tasks.addTask(new Todo("read"));
        tasks.deleteTask(0); // Delete the first task
        assertEquals(0, tasks.getSize());
    }

    @Test
    public void containsDuplicate_duplicateTask_returnsTrue() {
        TaskList taskList = new TaskList();
        Task t1 = new Todo("read book");
        taskList.addTask(t1);

        Task t2 = new Todo("read book"); // Same description
        assertTrue(taskList.containsDuplicate(t2)); // Should be true
    }

    @Test
    public void containsDuplicate_differentTask_returnsFalse() {
        TaskList taskList = new TaskList();
        Task t1 = new Todo("read book");
        taskList.addTask(t1);

        Task t2 = new Todo("write code"); // Different description
        assertFalse(taskList.containsDuplicate(t2)); // Should be false
    }
}