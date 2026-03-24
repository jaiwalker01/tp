package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.model.Model;

/**
 * Deletes all completed tasks.
 */
public class DeleteCompletedTasksCommand extends Command {

    public static final String COMMAND_WORD = "cleartasks";

    public static final String MESSAGE_SUCCESS = "All completed tasks deleted.";

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        //model.removeCompletedTasks();
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
