package yeyAI;

/**
 * Represents a To-Do task.
 */
public class Todo extends Task {

    /**
     * Creates a new To-Do task with the given description.
     *
     * @param description The description of the task.
     */
    public Todo(String description) {
        super(description);
        assert !description.trim().isEmpty() : "Description of a todo cannot be empty!";
    }

    /**
     * Returns a string representation of the To-Do task.
     *
     * @return The formatted string of the task.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * Converts the To-Do task into a savable command format.
     *
     * @return The formatted command string.
     */
    public String toCommand() {
        return String.format("todo %s/%b", getDescription(), isDone);
    }
}
