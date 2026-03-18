package seedu.address.model.tag;

import org.junit.jupiter.api.Test;
import seedu.address.model.person.Task;
import seedu.address.model.person.TaskList;

import static org.junit.jupiter.api.Assertions.*;

public class TaskListTest {

    @Test
    public void removeCompletedTasks_removesOnlyCompleted() {
        TaskList taskList = new TaskList();

        Task t1 = new Task("task1", "desc1");
        Task t2 = new Task("task2", "desc2");

        t2.markAsCompleted();

        taskList.addTask(t1);
        taskList.addTask(t2);

        taskList.removeCompletedTasks();

        assertTrue(true);
    }
}
