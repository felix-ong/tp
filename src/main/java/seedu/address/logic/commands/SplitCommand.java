package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.time.DayOfWeek;
import java.time.format.TextStyle;
import java.util.Locale;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.PersonAvailableOnDayPredicate;

/**
 * Splits members available on particular day to different Facilities.
 */
public class SplitCommand extends Command {
    public static final String COMMAND_WORD = "split";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": splits members into locations based on availability.\n"
            + "Parameters: " + "DAY\n"
            + "DAY must be an integer from 1 to 7\n"
            + "where 1 represents Monday, 2 represents Tuesday ... and 7 represents Sunday\n"
            + "Example: " + COMMAND_WORD + " 1";
    public static final String MESSAGE_SUCCESS = "Members have been split for %1$s";

    private final int dayNumber;

    /**
     * Creates a SplitCommand object to split the members.
     *
     * @param dayNumber Day to split members for.
     */
    public SplitCommand(int dayNumber) {
        assert dayNumber >= 1 && dayNumber <= 7 : "dayNumber should be between 1 and 7";
        this.dayNumber = dayNumber;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        PersonAvailableOnDayPredicate predicate = new PersonAvailableOnDayPredicate(dayNumber);
        model.split(predicate);
        return new CommandResult(String.format(MESSAGE_SUCCESS,
                DayOfWeek.of(dayNumber).getDisplayName(TextStyle.FULL, Locale.getDefault())));
    }

    @Override
    public boolean equals(Object obj) {
        return (obj == this)
                || (obj instanceof SplitCommand
                && dayNumber == ((SplitCommand) obj).dayNumber);
    }
}
