package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Person;

/**
 * Deletes an employee identified using their name or index from the address book.
 */
public class DeleteCommand extends Command {

    public static final String COMMAND_WORD = "delete";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the employee identified by their name or index.\n"
            + "Parameters: NAME or INDEX (must consist of alphabets and optional '/', case-insensitive, leading spaces normalized)\n"
            + "Example: " + COMMAND_WORD + " John Doe";

    public static final String MESSAGE_DELETE_EMPLOYEE_SUCCESS = "Deleted Employee: %1$s";
    public static final String MESSAGE_INVALID_NAME = "Name must contain only alphabets and optional '/'.";
    public static final String MESSAGE_EMPLOYEE_NOT_FOUND = "Employee with name '%1$s' does not exist.";
    public static final String MESSAGE_INVALID_INDEX = "The employee index provided is invalid.";

    private final Integer targetIndex; // null if not used
    private final String targetName; // null if not used

    // Constructor for index-based deletion
    public DeleteCommand(int targetIndex) {
        this.targetIndex = targetIndex;
        this.targetName = null;
    }

    // Constructor for name-based deletion
    public DeleteCommand(String targetName) {
        this.targetIndex = null;
        this.targetName = targetName;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        Person personToDelete = null;
        List<Person> lastShownList = model.getFilteredPersonList();

        if (targetIndex != null) {
            // Index-based deletion
            if (targetIndex < 1 || targetIndex > lastShownList.size()) {
                throw new CommandException(MESSAGE_INVALID_INDEX);
            }
            personToDelete = lastShownList.get(targetIndex - 1);
        } else {
            // Name-based deletion
            requireNonNull(targetName);
            String normalizedTarget = normalizeName(targetName);
            if (!isValidName(normalizedTarget)) {
                throw new CommandException(MESSAGE_INVALID_NAME);
            }
            for (Person person : lastShownList) {
                String personName = normalizeName(person.getName().fullName);
                if (personName.equals(normalizedTarget)) {
                    personToDelete = person;
                    break;
                }
            }
            if (personToDelete == null) {
                throw new CommandException(String.format(MESSAGE_EMPLOYEE_NOT_FOUND, targetName));
            }
        }
        model.deletePerson(personToDelete);
        return new CommandResult(String.format(MESSAGE_DELETE_EMPLOYEE_SUCCESS, Messages.format(personToDelete)));
    }

    private String normalizeName(String name) {
        // Trim and collapse leading spaces to one, then lowercase
        return name.trim().replaceAll("^ +", " ").replaceAll(" +", " ").toLowerCase();
    }

    private boolean isValidName(String name) {
        // Only alphabets, spaces, and '/'
        return name.matches("[a-zA-Z /]+");
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if (!(other instanceof DeleteCommand)) {
            return false;
        }
        DeleteCommand otherDeleteCommand = (DeleteCommand) other;
        return (targetIndex == null ? otherDeleteCommand.targetIndex == null : targetIndex.equals(otherDeleteCommand.targetIndex)) &&
               (targetName == null ? otherDeleteCommand.targetName == null : targetName.equals(otherDeleteCommand.targetName));
    }

    @Override
    public String toString() {
        if (targetIndex != null) {
            return DeleteCommand.class.getCanonicalName() + "{targetIndex=" + targetIndex + "}";
        } else {
            return DeleteCommand.class.getCanonicalName() + "{targetName=" + targetName + "}";
        }
    }
}
