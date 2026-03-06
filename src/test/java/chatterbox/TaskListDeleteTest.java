package chatterbox;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests deletion behavior in TaskList (1-based indexing).
 */
public class TaskListDeleteTest {

    @Test
    public void delete_validIndex_removesCorrectTask() throws ChatterBotException {
        Task[] arr = new Task[100];
        arr[0] = new Todo("t1");
        arr[1] = new Todo("t2");
        TaskList list = new TaskList(arr, 2);

        Task removed = list.delete(1);

        assertEquals("[T][ ] t1", removed.toString());
        assertEquals(1, list.size());
        assertEquals("[T][ ] t2", list.get(1).toString());
    }

    @Test
    public void delete_invalidIndex_throwsException() {
        Task[] arr = new Task[100];
        arr[0] = new Todo("t1");
        TaskList list = new TaskList(arr, 1);

        assertThrows(ChatterBotException.class, () -> list.delete(2));
        assertThrows(ChatterBotException.class, () -> list.delete(0));
        assertThrows(ChatterBotException.class, () -> list.delete(-1));
    }
}