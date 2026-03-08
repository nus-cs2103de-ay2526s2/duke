package dill.quote;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Represents the list of motivational quotes stored by Dill.
 */
public class QuoteList {
    private static final List<String> DEFAULT_QUOTES = List.of(
            "Mastery is a journey, not a destination.",
            "Every bug you fix makes you a better programmer.",
            "The code you write today is the legacy you leave tomorrow.",
            "Confusion is part of programming; embrace it.",
            "Refactoring is a sign of growth.",
            "Think twice, code once.",
            "The expert in anything was once a beginner.",
            "Believe you can and you are halfway there",
            "A comfort zone is a beautiful place, but nothing ever grows there."
    );

    private List<String> quotes;
    private Random random;

    /**
     * Creates an instance of QuoteList and initializes an empty quote list.
     */
    public QuoteList() {
        quotes = new ArrayList<>();
    }

    /**
     * Creates an instance of QuoteList with the specified list of quotes.
     *
     * @param quotes The list of motivational quotes.
     */
    public QuoteList(List<String> quotes) {
        this.quotes = quotes;
        this.random = new Random();
    }

    /**
     * Returns a random quote from the list, or a default quote if the list is empty.
     *
     * @return A quote string
     */
    public String getRandomQuote() {
        if (quotes.isEmpty()) {
            return "My quote stash is empty, but your potential is overflowing.";
        }
        int randIndex = random.nextInt(quotes.size()); // generate random index
        return quotes.get(randIndex);
    }

    /**
     * Returns the predefined default list of quotes
     *
     * @return A list of default quotes.
     */
    public static List<String> getDefaultQuotes() {
        return DEFAULT_QUOTES;
    }
}
