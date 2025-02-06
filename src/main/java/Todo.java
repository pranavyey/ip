public class Todo extends Task {
    public Todo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    public String toCommand() {
        return String.format("todo %s/%b", getDescription(), isDone);
    }
}
