package chatterbox;
import java.time.LocalDateTime;

public class Deadline extends Task {
    private String by;
    private LocalDateTime byDateTime;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
        this.byDateTime = DateTimeUtility.tryParseUserDateTime(by);
    }

    public Deadline(String description, LocalDateTime byDateTime){
        super(description);
        this.byDateTime = byDateTime;
        this.by = byDateTime != null ? byDateTime.toString() : "";
    }

    public String getBy() {
        if (byDateTime != null){
            return DateTimeUtility.formatForDisplay(byDateTime);
        }
        return by;
    }

    public LocalDateTime getByDateTime() {
        return byDateTime;
    }

    @Override
    protected String typeIcon() {
        return "D";
    }

    @Override
    public String toStorageString(){
         String storedBy = (byDateTime != null) ? byDateTime.toString() : by;

        return typeIcon() + " | " + (isDone ? "1" : "0") + " | " + description + " | " + storedBy;
    }

    @Override
    public String toString() {
        if (byDateTime != null) {
            return "[" + typeIcon() + "][" + statusIcon() + "] "
                    + description
                    + " (by: " + DateTimeUtility.formatForDisplay(byDateTime) + ")";
        }

        return "[" + typeIcon() + "][" + statusIcon() + "] "
                + description + " (by: " + by + ")";
    }
}
