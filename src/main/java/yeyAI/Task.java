package yeyAI;

/**
 * Represents a general task in the task manager.
 * This is an abstract class to be extended by specific task types (e.g., Todo, Deadline, Event).
 */
public abstract class Task {
    protected final String description;
    protected boolean isDone;

    /**
     * Constructs a new Task with a given description.
     * The task is initially marked as not done.
     *
     * @param description The description of the task.
     */
    public Task(String description) {
        assert description != null : "Task description cannot be null!";
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns the status icon of the task.
     *
     * @return "X" if the task is done, otherwise " ".
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    /**
     * Gets the description of the task.
     *
     * @return The task description.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Marks the task as done.
     */
    public void setDone() {
        isDone = true;
    }

    /**
     * Marks the task as not done.
     */
    public void setUndone() { isDone = false; }

    /**
     * Converts a task string back into a Task object.
     *
     * @param taskLine The formatted task string from file storage.
     * @return The corresponding Task object.
     * @throws YeyException If the format is invalid.
     */
    public static Task fromString(String taskLine) throws YeyException {
        String[] parts = taskLine.split("/");
        if (parts.length < 2) {
            throw new YeyException("Invalid task format in file.");
        }

        int booleanIndex = parts.length - 1;
        boolean isDone;
        if (parts[booleanIndex].equals("true")) {
            isDone = true;
        } else if (parts[booleanIndex].equals("false")) {
            isDone = false;
        } else {
            throw new YeyException("Invalid task completion status in file.");
        }

        if (taskLine.startsWith("todo")) {
            Task t = new Todo(parts[0].substring(5)); // Remove "todo "
            if (isDone) t.setDone();
            return t;
        } else if (taskLine.startsWith("deadline")) {
            if (parts.length < 3) {
                throw new YeyException("Invalid deadline format in file.");
            }
            parts[0] = parts[0].substring(9).trim();
            parts[1] = parts[1].substring(3).trim();
            Task t = new Deadline(parts[0] + " /by " + parts[1]);
            if (isDone) t.setDone();
            return t;
        } else if (taskLine.startsWith("event")) {
            if (parts.length < 4) {
                throw new YeyException("Invalid event format in file.");
            }
            parts[0] = parts[0].substring(6).trim();
            parts[1] = parts[1].substring(5).trim();
            parts[2] = parts[2].substring(3).trim();
            Task t = new Event(parts[0] + " /from " + parts[1] + " /to " + parts[2]);
            if (isDone) t.setDone();
            return t;
        } else {
            throw new YeyException("Unknown task type in file.");
        }
    }

    /**
     * Returns a string representation of the task.
     *
     * @return The formatted string representation.
     */
    @Override
    public String toString() {
        return String.format("[%s] %s", this.getStatusIcon(), this.getDescription());
    }

    /**
     * Converts the task into a savable command format.
     *
     * @return The formatted command string.
     */
    public abstract String toCommand();
}
