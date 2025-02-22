package yeyAI;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

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
    public Event(String description) throws YeyException {
        super(description.split(" /from ")[0]);

        String[] parts = description.split(" /from ");
        if (parts.length != 2 || !parts[1].contains(" /to ")) {
            throw new YeyException("Invalid event format! Use: event DESCRIPTION /from YYYY-MM-DD /to YYYY-MM-DD");
        }

        String[] dateParts = parts[1].split(" /to ");
        try {
            this.start = LocalDate.parse(dateParts[0].trim());
            this.end = LocalDate.parse(dateParts[1].trim());
        } catch (DateTimeParseException e) {
            throw new YeyException("Invalid date format! Use YYYY-MM-DD.");
        }

        if (this.end.isBefore(this.start)) {
            throw new YeyException("Invalid event dates! End date cannot be before start date.");
        }
    }

    /**
     * Changes the start date of the event.
     *
     * @param date The new start date of the event.
     */
    public void setStart(String date) throws YeyException {
        try {
            LocalDate from = LocalDate.parse(date.trim());
            if (this.end.isBefore(from)) {
                throw new YeyException("Invalid event dates! Start date cannot be after end date.");
            } else {
                this.start = from;
            }
        } catch (DateTimeParseException e) {
            throw new YeyException("Invalid date format! Use YYYY-MM-DD.");
        }
    }

    /**
     * Changes the end date of the event.
     *
     * @param date The new
     */
    public void setEnd(String date) throws YeyException {
        try {
            LocalDate to = LocalDate.parse(date.trim());
            if (this.start.isAfter(to)) {
                throw new YeyException("Invalid event dates! End date cannot be before start date.");
            }
            this.end = LocalDate.parse(date.trim());
        } catch (DateTimeParseException e) {
            throw new YeyException("Invalid date format! Use YYYY-MM-DD.");
        }
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
