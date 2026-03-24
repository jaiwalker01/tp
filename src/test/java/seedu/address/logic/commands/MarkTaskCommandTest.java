package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import org.junit.jupiter.api.Test;

import seedu.address.model.ModelManager;
import seedu.address.model.employee.Employee;
import seedu.address.model.employee.Task;
import seedu.address.testutil.PersonBuilder;

public class MarkTaskCommandTest {

    @Test
    public void execute_markTask_noCrash() {
        ModelManager model = new ModelManager();
        Employee person = new PersonBuilder().build();

        Task task = new Task("Sample Task", "Description", 0);
        model.addTaskOverall(task, person);

        MarkTaskCommand cmd = new MarkTaskCommand(0);

        assertDoesNotThrow(() -> cmd.execute(model));
    }
}
