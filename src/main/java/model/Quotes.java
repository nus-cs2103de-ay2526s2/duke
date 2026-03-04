package model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

/**
 * Quote bank
 */
public class Quotes {
    private static final Random random = new Random(0);
    private static final ArrayList<Quote> QUOTE_BANK = new ArrayList<>(Arrays.asList(
            new Quote("Nelson Mandela", "It always seems impossible until it’s done."),
            new Quote("Walt Disney", "The way to get started is to quit talking and begin doing."),
            new Quote("Confucius", "It does not matter how slowly you go as long as you do not stop."),
            new Quote("Henry Ford", "Whether you think you can, or you think you can’t – you’re right."),
            new Quote("Sam Levenson", "Don’t watch the clock; do what it does. Keep going."),
            new Quote("Winston Churchill", "Success is not final; failure is not fatal: it is the courage to continue that counts."),
            new Quote("Albert Einstein", "Life is like riding a bicycle. To keep your balance, you must keep moving."),
            new Quote("Theodore Roosevelt", "Believe you can and you’re halfway there."),
            new Quote("Vince Lombardi", "It’s not whether you get knocked down; it’s whether you get up."),
            new Quote("Thomas Edison", "Many of life’s failures are people who did not realize how close they were to success when they gave up."),
            new Quote("John Wooden", "Things work out best for those who make the best of how things work out."),
            new Quote("Steve Jobs", "The only way to do great work is to love what you do."),
            new Quote("Jim Rohn", "Either you run the day or the day runs you."),
            new Quote("Zig Ziglar", "You don’t have to be great to start, but you have to start to be great."),
            new Quote("Tony Robbins", "The only limit to your impact is your imagination and commitment."),
            new Quote("Brian Tracy", "Successful people are simply those with successful habits."),
            new Quote("Peter Drucker", "The best way to predict the future is to create it."),
            new Quote("Henry David Thoreau", "Success usually comes to those who are too busy to be looking for it."),
            new Quote("Bill Gates", "It’s fine to celebrate success, but it is more important to heed the lessons of failure."),
            new Quote("Sheryl Sandberg", "Done is better than perfect."),
            new Quote("J.K. Rowling", "It is impossible to live without failing at something, unless you live so cautiously that you might as well not have lived at all."),
            new Quote("Michael Jordan", "I’ve failed over and over and over again in my life. And that is why I succeed."),
            new Quote("Serena Williams", "Every woman’s success should be an inspiration to another. We’re strongest when we cheer each other on."),
            new Quote("Oprah Winfrey", "The biggest adventure you can ever take is to live the life of your dreams."),
            new Quote("Eleanor Roosevelt", "You must do the things you think you cannot do."),
            new Quote("Ralph Waldo Emerson", "What lies behind us and what lies before us are tiny matters compared to what lies within us."),
            new Quote("Mark Twain", "The secret of getting ahead is getting started."),
            new Quote("Bruce Lee", "The successful warrior is the average man, with laser-like focus."),
            new Quote("Mahatma Gandhi", "The future depends on what you do today.")
    ));

    /**
     * random quote
     * @return String of a random quote from quote bank
     */
    public static String getRandomQuote() {
        int index = random.nextInt(QUOTE_BANK.size());
        return QUOTE_BANK.get(index).toString();
    }
}