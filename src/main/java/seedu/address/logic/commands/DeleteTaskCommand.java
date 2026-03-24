package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;

/**
 * Deletes a task by index.
 */
public class DeleteTaskCommand extends Command {

    public static final String COMMAND_WORD = "deletetask";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the task identified by its task index.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_SUCCESS = "Task deleted.";
    public static final String MESSAGE_INVALID_INDEX = "The task index provided is invalid.";

    private final int index;

    public DeleteTaskCommand(int index) {
        this.index = index;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        try {
            model.deleteTask(index);
        } catch (RuntimeException e) {
            throw new CommandException(MESSAGE_INVALID_INDEX);
        }
        return new CommandResult(MESSAGE_SUCCESS);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof DeleteTaskCommand)) {
            return false;
        }

        DeleteTaskCommand otherCommand = (DeleteTaskCommand) other;
        return index == otherCommand.index;
    }
}
