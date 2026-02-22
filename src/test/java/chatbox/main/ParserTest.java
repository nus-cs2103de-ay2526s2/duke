package chatbox.main;

import chatbox.main.commands.AddCommand;
import chatbox.main.commands.Command;
import chatbox.main.commands.ExitCommand;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

public class ParserTest {

    // Positive Test Case: Verifies that "bye" returns an ExitCommand
    @Test
    public void parse_byeCommand_success() throws Exception {
        Command c = Parser.parse("bye");
        assertTrue(c instanceof ExitCommand);
    }

    // Positive Test Case: Verifies that "todo read" returns an AddCommand
    @Test
    public void parse_todoCommand_success() throws Exception {
        Command c = Parser.parse("todo read book");
        assertTrue(c instanceof AddCommand);
    }

    // Negative Test Case: Verifies that empty todo throws an exception
    @Test
    public void parse_emptyTodo_exceptionThrown() {
        try {
            Parser.parse("todo");
            fail(); // The test should fail if the line above didn't throw an error
        } catch (ChatBoxException e) {
            assertEquals("The description of a todo cannot be empty.", e.getMessage());
        }
    }
}