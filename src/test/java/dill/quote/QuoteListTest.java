package dill.quote;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

public class QuoteListTest {
    private QuoteList quoteList;
    private final List<String> quotes = new ArrayList<>(List.of("q1", "q2", "q3", "q4"));

    // Test getting a random quote when quote list is initialized with an empty list
    @Test
    void getRandomQuoteEmptyTest() {
        quoteList = new QuoteList();
        String quote = quoteList.getRandomQuote();
        assertEquals("My quote stash is empty, but your potential is overflowing.", quote);
    }

    // Test getting a random quote when quote list is initialized with a non-empty list
    @Test
    void getRandomQuoteNonEmptyTest() {
        quoteList = new QuoteList(quotes);
        String randomQuote1 = quoteList.getRandomQuote();
        String randomQuote2 = quoteList.getRandomQuote();
        assertTrue(quotes.contains(randomQuote1));
        assertTrue(quotes.contains(randomQuote2));
    }
}
