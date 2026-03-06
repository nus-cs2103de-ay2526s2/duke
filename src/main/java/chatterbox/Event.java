package chatterbox;

import java.time.LocalDateTime;

public class Event extends Task {
    private String from;
    private String to;
    private LocalDateTime toDateTime;
    private LocalDateTime fromDateTime;

    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
        this.fromDateTime = DateTimeUtility.tryParseUserDateTime(from);
        this.toDateTime = DateTimeUtility.tryParseUserDateTime(to);
    }


    public String getFrom() {
        if (fromDateTime != null) {
            return DateTimeUtility.formatForDisplay(fromDateTime);
        }
        return from;
    }

    public String getTo() {
        if (toDateTime != null) {
            return DateTimeUtility.formatForDisplay(toDateTime);
        }
        return to;
    }

    public LocalDateTime getFromDateTime() {
        return fromDateTime;
    }

    public LocalDateTime getToDateTime() {
        return toDateTime;
    }

    @Override
    protected String typeIcon() {
        return "E";
    }

    @Override
    public String toStorageString() {
        String storedFrom = (fromDateTime != null) ? fromDateTime.toString() : from;
        String storedTo = (toDateTime != null) ? toDateTime.toString() : to;

        return typeIcon() + " | " + (isDone ? "1" : "0") + " | " + description + " | " + storedFrom + " | " + storedTo;
    }

    @Override
    public String toString() {
        String displayFrom = (fromDateTime != null) ? DateTimeUtility.formatForDisplay(fromDateTime) : from;
        String displayTo = (toDateTime != null) ? DateTimeUtility.formatForDisplay(toDateTime) : to;

        return "[" + typeIcon() + "][" + statusIcon() + "] " + description + " (from: " + displayFrom + " to: " + displayTo + ")";
    }
}
