package listo.parser;

import listo.task.TaskList;
import listo.ui.Ui;
import listo.exception.ListoException;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ParserTest {

    @Test
    public void parseCommand_unknownCommand_exceptionThrown() {
        try {
            // Test inputting nonsense
            Parser.parseCommand("blah", new TaskList(), new Ui());
            fail(); // The test should fail if no exception is thrown
        } catch (ListoException e) {
            assertEquals("OOPS!!! Sorry, I don't know what you mean :(", e.getMessage());
        }
    }

    @Test
    public void parseCommand_emptyTodo_exceptionThrown() {
        try {
            // Test inputting "todo" without a description
            Parser.parseCommand("todo", new TaskList(), new Ui());
            fail();
        } catch (ListoException e) {
            assertEquals("OOPS!!! You forgot the description of the todo task." +
                    "\nUsage: todo <description>", e.getMessage());
        }
    }

    @org.junit.jupiter.api.Test
    public void parseCommand_addTodo_success() {
        // 1. Setup dummy objects
        listo.task.TaskList tasks = new listo.task.TaskList();
        listo.ui.Ui ui = new listo.ui.Ui(); // Ui just prints to console, which is fine for tests

        // 2. Execute the command
        try {
            Parser.parseCommand("todo read book", tasks, ui);
        } catch (Exception e) {
            // If this fails, the test fails
            org.junit.jupiter.api.Assertions.fail("Exception should not be thrown for valid command");
        }

        // 3. Verify the task was actually added
        org.junit.jupiter.api.Assertions.assertEquals(1, tasks.getSize());
        org.junit.jupiter.api.Assertions.assertEquals("[T][ ] read book", tasks.getTask(0).toString());
    }

    @org.junit.jupiter.api.Test
    public void parseCommand_byeCommand_success() {
        listo.task.TaskList tasks = new listo.task.TaskList();
        listo.ui.Ui ui = new listo.ui.Ui();

        // Ensure "bye" doesn't crash the app
        try {
            Parser.parseCommand("bye", tasks, ui);
        } catch (Exception e) {
            org.junit.jupiter.api.Assertions.fail("Bye command should be safe");
        }
    }

    @Test
    public void parseCommand_duplicateTask_exceptionThrown() {
        TaskList tasks = new TaskList();
        Ui ui = new Ui();
        try {
            // Add the first task
            Parser.parseCommand("todo read book", tasks, ui);

            // Try to add the EXACT same task again
            Exception exception = assertThrows(ListoException.class, () -> {
                Parser.parseCommand("todo read book", tasks, ui);
            });

            // Verify the error message matches what you wrote in A-MoreErrorHandling
            assertEquals("OOPS!!! This todo task already exists in your list.", exception.getMessage());

        } catch (ListoException e) {
            fail("Setup failed: " + e.getMessage());
        }
    }

    @Test
    public void parseCommand_invalidEventDateLogic_exceptionThrown() {
        TaskList tasks = new TaskList();
        Ui ui = new Ui();

        String command = "event TimeTravel /from 1/1/2025 /to 1/1/2020";

        Exception exception = assertThrows(ListoException.class, () -> {
            Parser.parseCommand(command, tasks, ui);
        });

        assertEquals("OOPS!!! 🙈 The end date cannot be before the start date. " +
                "Time travel isn't allowed yet! ⏳", exception.getMessage());
    }
}