package yeyAI;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskTest {

    @Test
    public void testTodoTaskDescription() {
        Task task = new Todo("Complete CS2103");
        assertEquals("Complete CS2103", task.getDescription(), "Task name should match");
    }

    @Test
    public void testDeadlineTaskDescription() throws YeyException {
        Task task = new Deadline("Complete CS2103 /by 2025-03-02");
        assertEquals("Complete CS2103", task.getDescription(), "Task name should match");
        task.setDone();
        assertEquals("X", task.getStatusIcon(), "Task status should match");
    }
}
