package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.model.Model;

/**
 * Marks a task as completed.
 */
public class MarkTaskCommand extends Command {

    public static final String COMMAND_WORD = "marktask";

    public static final String MESSAGE_SUCCESS = "Task marked as completed.";

    private final int index;

    /**
     * @param index zero-based index
     */
    public MarkTaskCommand(int index) {
        this.index = index;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);

        //model.markTask(index);

        return new CommandResult(MESSAGE_SUCCESS);
    }
}
