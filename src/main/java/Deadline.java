import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    protected LocalDate deadline;
    public Deadline(String description) {
        super(description.split(" /by ")[0]);
        this.deadline = LocalDate.parse(description.split(" /by ")[1]);
    }

    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(),
                deadline.format(DateTimeFormatter.ofPattern("MMM d yyyy")));
    }

    public String toCommand() {
        return String.format("deadline %s /by %s/%b", getDescription(), deadline, isDone);
    }
}
