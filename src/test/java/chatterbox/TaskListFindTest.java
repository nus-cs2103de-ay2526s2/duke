package chatterbox;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskListFindTest {

    @Test
    public void findByKeyword_matchesCorrectTasks() {
        Task[] backing = new Task[10];
        TaskList list = new TaskList(backing, 0);

        list.add(new Todo("read book"));
        list.add(new Todo("return book"));
        list.add(new Todo("play games"));

        TaskList.FindResult result = list.findByKeyword("book");
        assertEquals(2, result.count);
        assertEquals("read book", result.matches[0].getDescription());
        assertEquals("return book", result.matches[1].getDescription());
    }
}