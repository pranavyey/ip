package yeyAI;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents a deadline task with a specific due date.
 */
public class Deadline extends Task {
    protected final LocalDate deadline;

    /**
     * Constructs a Deadline task with a description and a due date.
     *
     * @param description The task description containing "/by" followed by the due date.
     */
    public Deadline(String description) {
        super(description.split(" /by ")[0]);
        this.deadline = LocalDate.parse(description.split(" /by ")[1]);
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
