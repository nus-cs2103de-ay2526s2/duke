package duchess.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import duchess.command.CreateDeadlineCommand;
import duchess.command.CreateEventCommand;
import duchess.command.CreateToDoCommand;
import duchess.command.DeleteTaskCommand;
import duchess.command.DisplayListCommand;
import duchess.command.DisplayQuoteCommand;
import duchess.command.FindOutstandingCommand;
import duchess.command.FindTaskCommand;
import duchess.command.MarkTaskCompleteCommand;
import duchess.command.MarkTaskIncompleteCommand;
import duchess.command.TerminateCommand;
import duchess.command.UnknownCommand;

/**
 * Tests for the CommandParser class.
 */
public class CommandParserTest {
    /**
     * Tests the getCommand method to create the correct command object.
     */
    @Test
    public void testGetCommand() {
        assertEquals(CreateDeadlineCommand.class, CommandParser.getCommand("deadline").getClass());
        assertEquals(CreateDeadlineCommand.class,
                CommandParser.getCommand("deadline task /by 2026-02-05").getClass());
        assertEquals(CreateEventCommand.class, CommandParser.getCommand("event").getClass());
        assertEquals(CreateToDoCommand.class, CommandParser.getCommand("todo").getClass());

        assertEquals(DeleteTaskCommand.class, CommandParser.getCommand("delete").getClass());
        assertEquals(DisplayListCommand.class, CommandParser.getCommand("list").getClass());
        assertEquals(FindOutstandingCommand.class, CommandParser.getCommand("outstanding").getClass());
        assertEquals(FindTaskCommand.class, CommandParser.getCommand("find").getClass());

        assertEquals(MarkTaskCompleteCommand.class, CommandParser.getCommand("mark").getClass());
        assertEquals(MarkTaskIncompleteCommand.class, CommandParser.getCommand("unmark").getClass());

        assertEquals(TerminateCommand.class, CommandParser.getCommand("bye").getClass());

        assertEquals(UnknownCommand.class, CommandParser.getCommand("").getClass());
        assertEquals(UnknownCommand.class, CommandParser.getCommand("hello").getClass());
        assertEquals(DisplayQuoteCommand.class, CommandParser.getCommand("cheer").getClass());
    }
}
