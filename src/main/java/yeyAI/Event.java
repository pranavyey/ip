package yeyAI;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents an event task with a start and end date.
 */
public class Event extends Task {
    protected LocalDate start;
    protected LocalDate end;

    /**
     * Constructs an Event task with a description, start date, and end date.
     *
     * @param description The task description containing "/from" and "/to" for dates.
     */
    public Event(String description) {
        super(description.split(" /from ")[0]);
        this.start = LocalDate.parse(description.split(" /from ")[1].split(" /to ")[0]);
        this.end = LocalDate.parse(description.split(" /from ")[1].split(" /to ")[1]);
    }

    /**
     * Returns a formatted string representation of the event.
     *
     * @return The event in the format "[E][status] description (from: start to: end)".
     */
    @Override
    public String toString() {
        return String.format("[E]%s (from: %s to: %s)", super.toString(),
                start.format(DateTimeFormatter.ofPattern("MMM d yyyy")),
                end.format(DateTimeFormatter.ofPattern("MMM d yyyy")));
    }

    /**
     * Converts the event into a savable command format.
     *
     * @return The formatted command string for file storage.
     */
    public String toCommand() {
        return String.format("event %s /from %s /to %s/%b", getDescription(), this.start, this.end, isDone);
    }
}
