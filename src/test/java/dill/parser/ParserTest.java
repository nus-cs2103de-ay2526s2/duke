package dill.parser;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import dill.command.AddCommand;
import dill.command.CheerCommand;
import dill.command.CloneCommand;
import dill.command.Command;
import dill.command.DeleteCommand;
import dill.command.ExitCommand;
import dill.command.FindCommand;
import dill.command.HelpCommand;
import dill.command.ListCommand;
import dill.command.MarkCommand;
import dill.command.UnmarkCommand;
import dill.command.ViewCommand;
import dill.exception.InvalidCommandException;
import dill.task.ToDo;

public class ParserTest {

    // Test mark command with valid syntax
    @Test
    void parseMarkValid() {
        Command cmd = assertDoesNotThrow(() -> Parser.parse("mark 2"));
        assertEquals(MarkCommand.class, cmd.getClass());
    }

    // Test mark command with no task id
    @Test
    void parseMarkNoTaskId() {
        InvalidCommandException e = assertThrows(
                InvalidCommandException.class, () -> Parser.parse("mark "));
        assertEquals("Whoops! You forgot to give me a task id to mark. (e.g., mark 3)", e.getMessage());
    }

    // Test mark command with non-integer task id
    @Test
    void parseMarkNonInteger() {
        InvalidCommandException e = assertThrows(
                InvalidCommandException.class, () -> Parser.parse("mark x"));
        assertEquals("Hold on! The task id must be an integer!", e.getMessage());
    }

    // Test unmark command with valid syntax
    @Test
    void parseUnmarkValid() {
        Command cmd = assertDoesNotThrow(() -> Parser.parse("unmark 2"));
        assertEquals(UnmarkCommand.class, cmd.getClass());
    }

    // Test unmark command with no task id
    @Test
    void parseUnmarkNoTaskId() {
        InvalidCommandException e = assertThrows(
                InvalidCommandException.class, () -> Parser.parse("unmark "));
        assertEquals("Whoops! You forgot to give me a task id to unmark. (e.g., unmark 3)", e.getMessage());
    }

    // Test unmark command with non-integer task id
    @Test
    void parseUnmarkInvalidTaskId() {
        InvalidCommandException e = assertThrows(
                InvalidCommandException.class, () -> Parser.parse("unmark x"));
        assertEquals("Hold on! The task id must be an integer!", e.getMessage());
    }

    // Test bye command with valid syntax
    @Test
    void parseByeValid() {
        Command cmd = assertDoesNotThrow(() -> Parser.parse("bye"));
        assertEquals(ExitCommand.class, cmd.getClass());
    }

    // Test bye command with invalid syntax
    @Test
    void parseByeInvalid() {
        InvalidCommandException e = assertThrows(
                InvalidCommandException.class, () -> Parser.parse("bye x"));
        assertEquals("No need for long goodbyes! Just type \"bye\" and you are set.", e.getMessage());
    }

    // Test list command with valid syntax
    @Test
    void parseListValid() {
        Command cmd = assertDoesNotThrow(() -> Parser.parse("list"));
        assertEquals(ListCommand.class, cmd.getClass());
    }

    // Test list command with invalid syntax
    @Test
    void parseListInvalid() {
        InvalidCommandException e = assertThrows(
                InvalidCommandException.class, () -> Parser.parse("list x"));
        assertEquals("Oops, I do not quite understand that! Did you mean just \"list\"?", e.getMessage());
    }

    // Test help command with valid syntax
    @Test
    void parseHelpValid() {
        Command cmd = assertDoesNotThrow(() -> Parser.parse("help"));
        assertEquals(HelpCommand.class, cmd.getClass());
    }

    // Test help command with invalid syntax
    @Test
    void parseHelpInvalid() {
        InvalidCommandException e = assertThrows(
                InvalidCommandException.class, () -> Parser.parse("help x"));
        assertEquals("Oops, I do not quite understand that! Did you mean just \"help\"?", e.getMessage());
    }

    // Test cheer command with valid syntax
    @Test
    void parseCheerValid() {
        Command cmd = assertDoesNotThrow(() -> Parser.parse("cheer"));
        assertEquals(CheerCommand.class, cmd.getClass());
    }

    // Test cheer command with invalid syntax
    @Test
    void parseCheerInvalid() {
        InvalidCommandException e = assertThrows(
                InvalidCommandException.class, () -> Parser.parse("cheer x"));
        assertEquals("Oops, I do not quite understand that! Did you mean just \"cheer\"?", e.getMessage());
    }

    // Test find command with valid syntax
    @Test
    void parseFindValid() {
        Command cmd = assertDoesNotThrow(() -> Parser.parse("find assignment"));
        assertEquals(FindCommand.class, cmd.getClass());
        assertEquals("assignment", ((FindCommand) cmd).getKeyword());
    }

    // Test find command with invalid syntax (empty keyword)
    @Test
    void parseFindNoKeyword() {
        InvalidCommandException e = assertThrows(
                InvalidCommandException.class, () -> Parser.parse("find "));
        assertEquals("What are we looking for? Please give me a keyword to search!", e.getMessage());
    }

    // Test view command with valid syntax
    @Test
    void parseViewValid() {
        Command cmd = assertDoesNotThrow(() -> Parser.parse("view 2026-03-14"));
        assertEquals(ViewCommand.class, cmd.getClass());
        assertEquals(LocalDate.parse("2026-03-14"), ((ViewCommand) cmd).getDate());
    }

    // Test view command with invalid syntax (empty date)
    @Test
    void parseViewNoDate() {
        InvalidCommandException e = assertThrows(
                InvalidCommandException.class, () -> Parser.parse("view "));
        assertEquals("Which date are we looking at? Please give me a date!", e.getMessage());
    }

    // Test view command with invalid syntax (invalid date)
    @Test
    void parseViewInvalidDate() {
        InvalidCommandException e = assertThrows(
                InvalidCommandException.class, () -> Parser.parse("view x"));
        assertEquals("Uh oh, I cannot read that date format! Please use: yyyy-mm-dd.", e.getMessage());
    }

    // Test todo command with valid syntax
    @Test
    void parseToDoValid() {
        Command cmd = assertDoesNotThrow(() -> Parser.parse("todo myname"));
        assertEquals(AddCommand.class, cmd.getClass());
        assertEquals(ToDo.class, ((AddCommand) cmd).getTask().getClass());
    }

    // Test todo command with invalid syntax (empty task name)
    @Test
    void parseToDoNoTaskName() {
        InvalidCommandException e = assertThrows(
                InvalidCommandException.class, () -> Parser.parse("todo"));
        assertEquals("Wait, what are we doing? Please tell me a task name! (e.g., todo study)",
                e.getMessage());
    }

    // Test deadline command with valid syntax (gemini)
    @Test
    void parseDeadlineValid() {
        Command cmd = assertDoesNotThrow(() -> Parser.parse("deadline submit report /by 2026-12-31"));
        assertEquals(AddCommand.class, cmd.getClass());
        // checks if the task inside the command is actually a Deadline
        assertEquals(dill.task.Deadline.class, ((AddCommand) cmd).getTask().getClass());
    }

    // Test deadline command without specifying /by flag (gemini)
    @Test
    void parseDeadlineNoFlag() {
        // Missing /by
        InvalidCommandException e = assertThrows(
                InvalidCommandException.class, () -> Parser.parse("deadline submit report 2026-12-31"));
        assertEquals("Hold up! The deadline format is a little off."
                + " Please use: deadline <task-name> /by <yyyy-mm-dd>", e.getMessage());
    }

    // Test deadline command with an invalid date (gemini)
    @Test
    void parseDeadlineInvalidDate() {
        InvalidCommandException e = assertThrows(
                InvalidCommandException.class, () -> Parser.parse("deadline submit report /by tomorrow"));
        assertEquals("Uh oh, I cannot read that date format! Please use: yyyy-mm-dd.", e.getMessage());
    }

    // Test event command with valid syntax (gemini)
    @Test
    void parseEventValid() {
        Command cmd = assertDoesNotThrow(() ->
                Parser.parse("event project meeting /start 2026-01-01 /end 2026-01-02"));
        assertEquals(AddCommand.class, cmd.getClass());
        assertEquals(dill.task.Event.class, ((AddCommand) cmd).getTask().getClass());
    }

    // Test event command with missing /end flag (gemini)
    @Test
    void parseEventNoEndFlag() {
        // Missing /end
        InvalidCommandException e = assertThrows(
                InvalidCommandException.class, () -> Parser.parse("event meeting /start 2026-01-01"));
        assertEquals("Hold up! The event format is a little off."
                + " Please use: event <task-name> /start <yyyy-mm-dd> /end <yyyy-mm-dd>", e.getMessage());
    }

    // Test event command with start date after end date (gemini)
    @Test
    void parseEventTimeTravel() {
        // Start date is after end date
        InvalidCommandException e = assertThrows(
                InvalidCommandException.class, () ->
                        Parser.parse("event impossible /start 2026-12-31 /end 2026-01-01"));
        assertEquals("Wait a second... are we time travelling?"
                + " The start date cannot be after the end date!", e.getMessage());
    }

    // Test delete command with valid syntax (gemini)
    @Test
    void parseDeleteValid() {
        Command cmd = assertDoesNotThrow(() -> Parser.parse("delete 1"));
        assertEquals(DeleteCommand.class, cmd.getClass());
    }
    // Test delete command with no task id (gemini)
    @Test
    void parseDeleteNoTaskId() {
        InvalidCommandException e = assertThrows(
                InvalidCommandException.class, () -> Parser.parse("delete"));
        assertEquals("Whoops! You forgot to give me a task id to delete. (e.g., delete 3)", e.getMessage());
    }

    // Test delete command with non-integer task id (gemini)
    @Test
    void parseDeleteInvalidTaskId() {
        InvalidCommandException e = assertThrows(
                InvalidCommandException.class, () -> Parser.parse("delete one"));
        assertEquals("Hold on! The task id must be an integer!", e.getMessage());
    }

    // Test clone command with valid syntax (gemini)
    @Test
    void parseCloneValid() {
        Command cmd = assertDoesNotThrow(() -> Parser.parse("clone 5"));
        assertEquals(CloneCommand.class, cmd.getClass());
    }

    // Test clone command with no task id (gemini)
    @Test
    void parseCloneNoId() {
        InvalidCommandException e = assertThrows(
                InvalidCommandException.class, () -> Parser.parse("clone"));
        assertEquals("Which task are we cloning? Please give me a task id!", e.getMessage());
    }

    // Test unknown command (gemini)
    @Test
    void parseUnknownCommand() {
        InvalidCommandException e = assertThrows(
                InvalidCommandException.class, () -> Parser.parse("dance"));
        assertEquals("Hmmm... that command does not seem to be in my database!\n"
                + "Type \"help\" to see what I can do.", e.getMessage());
    }
}
