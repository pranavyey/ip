package yeyAI;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents a deadline task with a specific due date.
 */
public class Deadline extends Task {
    protected LocalDate deadline;

    /**
     * Constructs a Deadline task with a description and a due date.
     *
     * @param description The task description containing "/by" followed by the due date.
     */
    public Deadline(String description) throws YeyException {
        super(description.split(" /by ")[0]);

        String[] parts = description.split(" /by ");
        if (parts.length != 2) {
            throw new YeyException("Invalid deadline format! Use: deadline DESCRIPTION /by YYYY-MM-DD");
        }

        try {
            this.deadline = LocalDate.parse(parts[1].trim());
        } catch (DateTimeParseException e) {
            throw new YeyException("Invalid date format! Use YYYY-MM-DD.");
        }
    }

    /**
     * Changes the due date of a given Deadline task
     *
     * @param date The new due date of the Deadline task
     */
    public void setDeadline(String date) throws YeyException {
        try {
            this.deadline = LocalDate.parse(date.trim());
        } catch (DateTimeParseException e) {
            throw new YeyException("Invalid date format! Use YYYY-MM-DD.");
        }
    }

    /**
     * Returns a formatted string representation of the deadline.
     *
     * @return The deadline in the format "[D][status] description (by: due date)".
     */
    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(),
                deadline.format(DateTimeFormatter.ofPattern("MMM d yyyy")));
    }

    /**
     * Converts the deadline into a savable command format.
     *
     * @return The formatted command string for file storage.
     */
    public String toCommand() {
        return String.format("deadline %s /by %s/%b", getDescription(), deadline, isDone);
    }
}
