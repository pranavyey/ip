public abstract class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    public String getDescription() {
        return description;
    }

    public void setDone() {
        isDone = true;
    }

    public void setUndone() { isDone = false; }

    public static Task fromString(String taskLine) throws YeyException {
        String[] parts = taskLine.split("/", 2);
        if (parts.length < 2) {
            throw new YeyException("Invalid task format in file.");
        }

        boolean isDone = parts[1].equals("true");

        if (taskLine.startsWith("todo")) {
            Task t = new Todo(parts[0].substring(5)); // Remove "todo "
            if (isDone) t.setDone();
            return t;
        } else if (taskLine.startsWith("deadline")) {
            String[] deadlineParts = parts[0].substring(9).split(" /by ");
            if (deadlineParts.length < 2) {
                throw new YeyException("Invalid deadline format in file.");
            }
            Task t = new Deadline(deadlineParts[0] + " /by " + deadlineParts[1]);
            if (isDone) t.setDone();
            return t;
        } else if (taskLine.startsWith("event")) {
            String[] eventParts = parts[0].substring(6).split(" /from | /to ");
            if (eventParts.length < 3) {
                throw new YeyException("Invalid event format in file.");
            }
            Task t = new Event(eventParts[0] + " /from " + eventParts[1] + " /to " + eventParts[2]);
            if (isDone) t.setDone();
            return t;
        } else {
            throw new YeyException("Unknown task type in file.");
        }
    }

    @Override
    public String toString() {
        return String.format("[%s] %s", this.getStatusIcon(), this.getDescription());
    }

    public abstract String toCommand();
}