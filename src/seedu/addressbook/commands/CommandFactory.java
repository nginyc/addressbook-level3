package seedu.addressbook.commands;

import seedu.addressbook.data.exception.IllegalValueException;
import seedu.addressbook.parser.Parser;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import static seedu.addressbook.common.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

/**
 * Creates `Command`s corresponding to command keywords and arguments
 */
public class CommandFactory {

    private Parser parser = new Parser();

    public CommandFactory() {
    }

    public Command parseCommand(String commandString) {
        try {
            parser.parse(commandString);

            String commandWord = parser.getCommandWord();

            switch (commandWord) {

                case AddCommand.COMMAND_WORD:
                    return prepareAdd();

                case DeleteCommand.COMMAND_WORD:
                    return prepareDelete();

                case ClearCommand.COMMAND_WORD:
                    return new ClearCommand();

                case FindCommand.COMMAND_WORD:
                    return prepareFind();

                case ListCommand.COMMAND_WORD:
                    return new ListCommand();

                case ViewCommand.COMMAND_WORD:
                    return prepareView();

                case ViewAllCommand.COMMAND_WORD:
                    return prepareViewAll();

                case ExitCommand.COMMAND_WORD:
                    return new ExitCommand();

                case HelpCommand.COMMAND_WORD: // Fallthrough
                default:
                    return new HelpCommand();
            }

        } catch (Parser.ParseException exception) {
            return new IncorrectCommand(String.format(MESSAGE_INVALID_COMMAND_FORMAT, HelpCommand.MESSAGE_USAGE));
        } catch (IllegalValueException ive) {
            return new IncorrectCommand(ive.getMessage());
        }
    }

    /**
     * Parses arguments in the context of the add person command.
     *
     * @return the prepared command
     */
    private Command prepareAdd() throws Parser.ParseException, IllegalValueException {
        Parser.PersonData personData = parser.getArgsAsPersonData();
        return new AddCommand(
            personData.name,
            personData.phone,
            personData.isPhonePrivate,
            personData.email,
            personData.isEmailPrivate,
            personData.address,
            personData.isAddressPrivate,
            personData.tags
        );
    }

    /**
     * Parses arguments in the context of the delete person command.
     *
     * @return the prepared command
     */
    private Command prepareDelete() throws Parser.ParseException {
        final int targetIndex = parser.getArgsAsDisplayedIndex();
        return new DeleteCommand(targetIndex);
    }

    /**
     * Parses arguments in the context of the view command.
     *
     * @return the prepared command
     */
    private Command prepareView() throws Parser.ParseException {
        final int targetIndex = parser.getArgsAsDisplayedIndex();
        return new ViewCommand(targetIndex);
    }

    /**
     * Parses arguments in the context of the view all command.
     *
     * @return the prepared command
     */
    private Command prepareViewAll() throws Parser.ParseException {
        final int targetIndex = parser.getArgsAsDisplayedIndex();
        return new ViewAllCommand(targetIndex);
    }

    /**
     * Parses arguments in the context of the find person command.
     *
     * @return the prepared command
     */
    private Command prepareFind() throws Parser.ParseException {
        // keywords delimited by whitespace
        final String[] keywords = parser.getArgsAsKeywords();
        final Set<String> keywordSet = new HashSet<>(Arrays.asList(keywords));
        return new FindCommand(keywordSet);
    }
}