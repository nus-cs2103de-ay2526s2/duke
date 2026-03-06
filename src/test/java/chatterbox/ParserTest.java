package chatterbox;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for Parser.
 */
public class ParserTest {

    @Test
    public void parse_todoEmpty_throws() {
        assertThrows(ChatterBotException.class, () -> Parser.parse("todo"));
        assertThrows(ChatterBotException.class, () -> Parser.parse("todo   "));
    }

    @Test
    public void parse_deadlineMissingBy_throws() {
        assertThrows(ChatterBotException.class,
                () -> Parser.parse("deadline return book"));
    }

    @Test
    public void parse_deadlineValidDate_ok() {
        assertDoesNotThrow(() ->
                Parser.parse("deadline return book /by 2019-12-02"));
        assertDoesNotThrow(() ->
                Parser.parse("deadline return book /by 2019-12-02 1800"));
    }

    @Test
    public void parse_eventMissingParts_throws() {
        assertThrows(ChatterBotException.class,
                () -> Parser.parse("event project meeting /from 2019-12-02"));
        assertThrows(ChatterBotException.class,
                () -> Parser.parse("event project meeting /to 2019-12-02"));
    }

    @Test
    public void parse_eventValidDate_ok() {
        assertDoesNotThrow(() ->
                Parser.parse("event meeting /from 2019-12-02 1200 /to 2019-12-02 1400"));
    }

    @Test
    public void parse_unknown_throws() {
        assertThrows(ChatterBotException.class,
                () -> Parser.parse("asdfghjkl"));
    }
}