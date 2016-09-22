package seedu.addressbook.parser;

import seedu.addressbook.data.exception.IllegalValueException;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Parses command strings
 */
public class Parser {

    private String commandWord = "";
    private String arguments = "";

    public static final Pattern PERSON_INDEX_ARGS_FORMAT = Pattern.compile("(?<targetIndex>.+)");

    public static final Pattern KEYWORDS_ARGS_FORMAT =
            Pattern.compile("(?<keywords>\\S+(?:\\s+\\S+)*)"); // one or more keywords separated by whitespace

    public static final Pattern PERSON_DATA_ARGS_FORMAT = // '/' forward slashes are reserved for delimiter prefixes
            Pattern.compile("(?<name>[^/]+)"
                    + " (?<isPhonePrivate>p?)p/(?<phone>[^/]+)"
                    + " (?<isEmailPrivate>p?)e/(?<email>[^/]+)"
                    + " (?<isAddressPrivate>p?)a/(?<address>[^/]+)"
                    + "(?<tagArguments>(?: t/[^/]+)*)"); // variable number of tags


    /**
     * Signals that the user input could not be parsed.
     */
    public static class ParseException extends Exception {
    }

    /**
     * Used for initial separation of command word and args.
     */
    public static final Pattern BASIC_COMMAND_FORMAT = Pattern.compile("(?<commandWord>\\S+)(?<arguments>.*)");

    public Parser() {}

    public String getCommandWord() {
        return commandWord;
    }

    /**
     * Parses the given command string, throwing an error if invalid format
     */
    public void parse(String commandString) throws ParseException {
        final Matcher matcher = BASIC_COMMAND_FORMAT.matcher(commandString.trim());
        if (!matcher.matches()) {
            throw new ParseException();
        }

        commandWord = matcher.group("commandWord");
        arguments = matcher.group("arguments");
    }

    /**
     * Checks whether the private prefix of a contact detail in the add command's arguments string is present.
     */
    private static boolean isPrivatePrefixPresent(String matchedPrefix) {
        return matchedPrefix.equals("p");
    }

    /**
     * Extracts the new person's tags from the add command's tag arguments string.
     * Merges duplicate tag strings.
     */
    private static Set<String> getTagsFromArgs(String tagArguments) throws IllegalValueException {
        // no tags
        if (tagArguments.isEmpty()) {
            return Collections.emptySet();
        }
        // replace first delimiter prefix, then split
        final Collection<String> tagStrings = Arrays.asList(tagArguments.replaceFirst(" t/", "").split(" t/"));
        return new HashSet<>(tagStrings);
    }

    /**
     * Parses arguments of current command string as a single index number.
     *
     * @return the parsed index number
     * @throws ParseException if no region of the args string could be found for the index
     * @throws NumberFormatException the args string region is not a valid number
     */
    public int getArgsAsDisplayedIndex() throws ParseException, NumberFormatException {
        final Matcher matcher = PERSON_INDEX_ARGS_FORMAT.matcher(arguments.trim());
        if (!matcher.matches()) {
            throw new ParseException();
        }
        return Integer.parseInt(matcher.group("targetIndex"));
    }

    /**
     * Parses arguments of current command string as keywords
     *
     * @return array of keywords
     * @throws ParseException if invalid format
     */
    public String[] getArgsAsKeywords() throws  ParseException {
        final Matcher matcher = KEYWORDS_ARGS_FORMAT.matcher(arguments.trim());
        if (!matcher.matches()) {
            throw new ParseException();
        }

        // keywords delimited by whitespace
        return matcher.group("keywords").split("\\s+");
    }

    /**
     * Parses arguments of current command string as person data
     *
     * @return person data
     * @throws ParseException if invalid format
     */
    public PersonData getArgsAsPersonData() throws ParseException, IllegalValueException {
        final Matcher matcher = PERSON_DATA_ARGS_FORMAT.matcher(arguments.trim());
        // Validate arg string format
        if (!matcher.matches()) {
            throw new ParseException();
        }

        PersonData personData = new PersonData();
        personData.name = matcher.group("name");
        personData.phone = matcher.group("phone");
        personData.isPhonePrivate = isPrivatePrefixPresent(matcher.group("isPhonePrivate"));
        personData.email = matcher.group("email");
        personData.isEmailPrivate = isPrivatePrefixPresent(matcher.group("isEmailPrivate"));
        personData.address = matcher.group("address");
        personData.isAddressPrivate = isPrivatePrefixPresent(matcher.group("isAddressPrivate"));
        personData.tags = getTagsFromArgs(matcher.group("tagArguments"));

        return personData;
    }

    public class PersonData {
        public String name;
        public String phone;
        public boolean isPhonePrivate;
        public String email;
        public boolean isEmailPrivate;
        public String address;
        public boolean isAddressPrivate;
        public Set<String> tags;
    }
}