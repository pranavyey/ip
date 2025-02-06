import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    protected LocalDate start;
    protected LocalDate end;
    public Event(String description) {
        super(description.split(" /from ")[0]);
        this.start = LocalDate.parse(description.split( "/from ")[1].split(" /to ")[0]);
        this.end = LocalDate.parse(description.split( "/from ")[1].split(" /to ")[1]);
    }

    @Override
    public String toString() {
        return String.format("[E]%s (from: %s to: %s)", super.toString(),
                start.format(DateTimeFormatter.ofPattern("MMM d yyyy")),
                        end.format(DateTimeFormatter.ofPattern("MMM d yyyy")));
    }

    public String toCommand() {
        return String.format("event %s /from %s /to %s/%b", getDescription(), this.start, this.end, isDone);
    }
}
