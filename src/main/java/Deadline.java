public class Deadline extends Task{
    protected String deadline;
    public Deadline(String description) {
        super(description.split(" /by ")[0]);
        this.deadline = description.split(" /by ")[1];
    }

    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(), deadline);
    }

    public String toCommand() {
        return String.format("deadline %s /by %s/%b", getDescription(), deadline, isDone);
    }
}
