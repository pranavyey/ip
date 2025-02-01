public class Event extends Task{
    protected String start;
    protected String end;
    public Event(String description) {
        super(description.split(" /from ")[0]);
        this.start = description.split( "/from ")[1].split(" /to ")[0];
        this.end =  description.split( "/from ")[1].split(" /to ")[1];
    }

    @Override
    public String toString() {
        return String.format("[E]%s (from: %s to: %s)", super.toString(), this.start, this.end);
    }

}
