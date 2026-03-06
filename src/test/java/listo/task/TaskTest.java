package listo.task;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskTest {
    @Test
    public void testMarkAsDone() {
        // Verifies that the getStatusIcon() method returns "X" when a task is marked done.
        Task t = new Todo("test task");
        t.markAsDone();
        assertEquals("X", t.getStatusIcon());
    }

    @Test
    public void testMarkAsUndone() {
        // Verifies that un-marking a task reverts the status icon to a space " ".
        Task t = new Todo("test task");
        t.markAsDone();   // Mark it first
        t.markAsNotDone(); // Then unmark it
        assertEquals(" ", t.getStatusIcon());
    }
}