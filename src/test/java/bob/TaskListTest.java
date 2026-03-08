package bob;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Contain unit test for TaskList class
 */
public class TaskListTest {

    /**
     * Test that adding task increases size of task list
     */
    @Test
    public void testAddTask() {
        TaskList taskList = new TaskList();
        Task task = new ToDo("read book");
        taskList.addTask(task);
        assertEquals(1, taskList.size());
    }

    /**
     * Test that deleting a task at a valid index decreases size of the list
     */
    @Test
    public void testDeleteTask() {
        TaskList taskList = new TaskList();
        Task task1 = new ToDo("read book");
        Task task2 = new ToDo("return book");
        taskList.addTask(task1);
        taskList.addTask(task2);
        assertEquals(2, taskList.size());

        taskList.deleteTask(0);
        assertEquals(1, taskList.size());
    }

    /**
     * Checks that deleting a task with invalid index will throw exception
     */
    @Test
    public void deleteTaskInvalidIndexThrowsException() {
        TaskList taskList = new TaskList();
        taskList.addTask(new ToDo("only task"));

        assertThrows(IndexOutOfBoundsException.class,
                () -> taskList.deleteTask(5));
    }

}