package chatbox.main;

import chatbox.main.tasks.ToDo;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ToDoTest {

    // Positive Test Case: Checks if toString() formats the task correctly
    @Test
    public void toString_newTodo_correctFormat() {
        ToDo todo = new ToDo("read book");
        // We expect the [T] tag and [ ] empty box for a new task
        assertEquals("[T][ ] read book", todo.toString());
    }

    // Positive Test Case: Checks if markAsDone() updates the icon
    @Test
    public void markAsDone_updatesIcon() {
        ToDo todo = new ToDo("read book");
        todo.markAsDone();
        // We expect the box to now have an X: [X]
        assertEquals("[T][X] read book", todo.toString());
    }
}