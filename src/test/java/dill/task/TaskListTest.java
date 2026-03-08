package dill.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import java.lang.reflect.Field;
import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class TaskListTest {
    private TaskList taskList;

    // Test if a task is added successfully into the list.
    @Test
    void addTaskTest() throws NoSuchFieldException, IllegalAccessException {
        taskList = new TaskList();
        Task mockTask = Mockito.mock(Task.class); // Create mock Task to isolate TaskList
        taskList.addTask(mockTask);

        Field tasksField = TaskList.class.getDeclaredField("tasks");
        tasksField.setAccessible(true); // Set internal list as accessible
        @SuppressWarnings("unchecked")
        List<Task> internalTaskList = (List<Task>) tasksField.get(taskList); // Get internal list

        assertEquals(1, internalTaskList.size());
        assertEquals(mockTask, internalTaskList.get(0));
    }

    // Test if a task is successfully deleted from the list.
    @Test
    void deleteTaskTest() throws NoSuchFieldException, IllegalAccessException {
        taskList = new TaskList();
        Task mockTask = Mockito.mock(Task.class); // Create mock Task to isolate TaskList

        Field tasksField = TaskList.class.getDeclaredField("tasks");
        tasksField.setAccessible(true); // Set internal list as accessible
        @SuppressWarnings("unchecked")
        List<Task> internalTaskList = (List<Task>) tasksField.get(taskList); // Get internal list

        internalTaskList.add(mockTask); // add mock Task to internal list
        Task removed = taskList.deleteTask(0);

        assertEquals(0, internalTaskList.size());
        assertEquals(mockTask, removed);
    }

    // Test if a task is marked successfully
    @Test
    void markTaskTest() throws NoSuchFieldException, IllegalAccessException {
        taskList = new TaskList();

        Task task1 = new ToDo("Study");

        Field tasksField = TaskList.class.getDeclaredField("tasks");
        tasksField.setAccessible(true); // Set internal list as accessible
        @SuppressWarnings("unchecked")
        List<Task> internalTaskList = (List<Task>) tasksField.get(taskList); // Get internal list
        internalTaskList.add(task1); // Manually add task into the list

        assertFalse(internalTaskList.get(0).getIsDone()); // Check default is undone

        taskList.markTask(0);

        assertTrue(internalTaskList.get(0).getIsDone()); // Verify task marked as done
    }

    // Test if a task is unmarked successfully
    @Test
    void unmarkTaskTest() throws NoSuchFieldException, IllegalAccessException {
        taskList = new TaskList();

        Task task1 = new ToDo("Study");
        task1.setIsDone(true); // Mark task as done initially

        Field tasksField = TaskList.class.getDeclaredField("tasks");
        tasksField.setAccessible(true); // Set internal list as accessible
        @SuppressWarnings("unchecked")
        List<Task> internalTaskList = (List<Task>) tasksField.get(taskList); // Get internal list
        internalTaskList.add(task1); // Manually add task into the list

        assertTrue(internalTaskList.get(0).getIsDone()); // Verify default is marked as done

        taskList.unmarkTask(0);

        assertFalse(internalTaskList.get(0).getIsDone()); // Verify task is now unmarked
    }

    @Test
    void filterByNameTest() throws NoSuchFieldException, IllegalAccessException {
        taskList = new TaskList();

        // Create mock tasks to isolate TaskList
        Task mockTask1 = Mockito.mock(Task.class);
        Task mockTask2 = Mockito.mock(Task.class);
        Task mockTask3 = Mockito.mock(Task.class);
        Task mockTask4 = Mockito.mock(Task.class);

        when(mockTask1.getTaskName()).thenReturn("Study");
        when(mockTask2.getTaskName()).thenReturn("Assignment 1");
        when(mockTask3.getTaskName()).thenReturn("Test");
        when(mockTask4.getTaskName()).thenReturn("Assignment 2");

        // Manually add tasks into the task list
        Field tasksField = TaskList.class.getDeclaredField("tasks");
        tasksField.setAccessible(true); // Set internal list as accessible
        @SuppressWarnings("unchecked")
        List<Task> internalTaskList = (List<Task>) tasksField.get(taskList); // Get internal list
        internalTaskList.add(mockTask1);
        internalTaskList.add(mockTask2);
        internalTaskList.add(mockTask3);
        internalTaskList.add(mockTask4);

        // Case 1
        List<TaskList.MatchingTask> matchingTasks = taskList.filterByName("Study");
        assertEquals(1, matchingTasks.size());
        assertEquals("Study", matchingTasks.get(0).getTask().getTaskName());
        assertEquals(0, matchingTasks.get(0).getIndex()); // Check index

        // Case 2
        List<TaskList.MatchingTask> matchingTasks2 = taskList.filterByName("Assignment");
        assertEquals(2, matchingTasks2.size());
        assertEquals("Assignment 1", matchingTasks2.get(0).getTask().getTaskName());
        assertEquals("Assignment 2", matchingTasks2.get(1).getTask().getTaskName());
        assertEquals(1, matchingTasks2.get(0).getIndex()); // Check index
        assertEquals(3, matchingTasks2.get(1).getIndex());

        // Case 3 (no matches)
        List<TaskList.MatchingTask> matchingTasks3 = taskList.filterByName("Nothing");
        assertEquals(0, matchingTasks3.size());
    }

    @Test
    void filterByDateTest() throws NoSuchFieldException, IllegalAccessException {
        taskList = new TaskList();

        // Create mock tasks to isolate TaskList
        Task mockTask1 = Mockito.mock(Task.class);
        Task mockTask2 = Mockito.mock(Task.class);
        Task mockTask3 = Mockito.mock(Task.class);
        Task mockTask4 = Mockito.mock(Task.class);

        // Mock the isOccurringOn method
        when(mockTask1.isOccurringOn(LocalDate.parse("2026-03-14"))).thenReturn(true);
        when(mockTask2.isOccurringOn(LocalDate.parse("2026-03-14"))).thenReturn(false);
        when(mockTask3.isOccurringOn(LocalDate.parse("2026-03-14"))).thenReturn(true);
        when(mockTask4.isOccurringOn(LocalDate.parse("2026-03-14"))).thenReturn(false);

        // Manually add tasks into the task list
        Field tasksField = TaskList.class.getDeclaredField("tasks");
        tasksField.setAccessible(true); // Set internal list as accessible
        @SuppressWarnings("unchecked")
        List<Task> internalTaskList = (List<Task>) tasksField.get(taskList); // Get internal list
        internalTaskList.add(mockTask1);
        internalTaskList.add(mockTask2);
        internalTaskList.add(mockTask3);
        internalTaskList.add(mockTask4);

        List<TaskList.MatchingTask> matchingTasks = taskList.filterByDate(LocalDate.parse("2026-03-14"));
        assertEquals(2, matchingTasks.size());
        assertEquals(0, matchingTasks.get(0).getIndex()); // Check index
        assertEquals(2, matchingTasks.get(1).getIndex());
    }
}
