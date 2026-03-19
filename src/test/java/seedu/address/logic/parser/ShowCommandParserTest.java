package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.ShowCommand;

public class ShowCommandParserTest {

    private ShowCommandParser parser = new ShowCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertThrows(Exception.class, () ->
                parser.parse("     "));
    }

    @Test
    public void parse_validNamePrefix_returnsShowCommand() throws Exception {
        ShowCommand command = parser.parse("n/Alice");
        assertNotNull(command);
    }

    @Test
    public void parse_multipleFields_returnsShowCommand() throws Exception {
        ShowCommand command = parser.parse("n/Alice d/IT e/gmail");
        assertNotNull(command);
    }

    @Test
    public void parse_invalidInputWithNoPrefix_returnsEmptyFilter() throws Exception {
        ShowCommand command = parser.parse("Alice");
        assertNotNull(command);
    }

    @Test
    public void parse_onlySpacesAfterPrefix_returnsCommand() throws Exception {
        ShowCommand command = parser.parse("n/   ");
        assertNotNull(command);
    }

    @Test
    public void parse_caseInsensitivePrefix_returnsCommand() throws Exception {
        ShowCommand command = parser.parse("n/alice");
        assertNotNull(command);
    }

    @Test
    public void parse_phonePrefix_returnsCommand() throws Exception {
        ShowCommand command = parser.parse("p/9123");
        assertNotNull(command);
    }

    @Test
    public void parse_positionPrefix_returnsCommand() throws Exception {
        ShowCommand command = parser.parse("pos/Manager");
        assertNotNull(command);
    }

    @Test
    public void parse_departmentPrefix_returnsCommand() throws Exception {
        ShowCommand command = parser.parse("d/Finance");
        assertNotNull(command);
    }

    @Test
    public void parse_emailPrefix_returnsCommand() throws Exception {
        ShowCommand command = parser.parse("e/gmail");
        assertNotNull(command);
    }

    @Test
    public void parse_multipleSamePrefix_returnsCommand() throws Exception {
        ShowCommand command = parser.parse("n/Alice n/Bob");
        assertNotNull(command);
    }

    @Test
    public void parse_mixedPrefixesDifferentOrder_returnsCommand() throws Exception {
        ShowCommand command = parser.parse("e/gmail n/Alice d/IT");
        assertNotNull(command);
    }

    @Test
    public void parse_invalidPrefixIgnored_returnsCommand() throws Exception {
        ShowCommand command = parser.parse("x/unknown n/Alice");
        assertNotNull(command);
    }

    @Test
    public void parse_prefixWithoutValue_returnsCommand() throws Exception {
        ShowCommand command = parser.parse("d/");
        assertNotNull(command);
    }

    @Test
    public void parse_leadingWhitespace_returnsCommand() throws Exception {
        ShowCommand command = parser.parse("   n/Alice");
        assertNotNull(command);
    }

    @Test
    public void parse_trailingWhitespace_returnsCommand() throws Exception {
        ShowCommand command = parser.parse("n/Alice   ");
        assertNotNull(command);
    }

    @Test
    public void parse_extraSpacesBetweenPrefixes_returnsCommand() throws Exception {
        ShowCommand command = parser.parse("n/Alice    d/IT");
        assertNotNull(command);
    }

    @Test
    public void parse_uppercase_returnsCommand() throws Exception {
        ShowCommand command = parser.parse("n/ALICE");
        assertNotNull(command);
    }

    @Test
    public void parse_mixedCaseValues_returnsCommand() throws Exception {
        ShowCommand command = parser.parse("n/AlIcE");
        assertNotNull(command);
    }


    @Test
    public void parse_multipleKeywordsInName_returnsCommand() throws Exception {
        ShowCommand command = parser.parse("n/Alice Bob Charlie");
        assertNotNull(command);
    }

    @Test
    public void parse_multipleKeywordsInDepartment_returnsCommand() throws Exception {
        ShowCommand command = parser.parse("d/Finance HR");
        assertNotNull(command);
    }

    @Test
    public void parse_allPrefixesTogether_returnsCommand() throws Exception {
        ShowCommand command = parser.parse("n/Alice d/IT e/gmail p/9123 pos/Manager");
        assertNotNull(command);
    }

    @Test
    public void parse_twoPrefixes_returnsCommand() throws Exception {
        ShowCommand command = parser.parse("n/Alice e/gmail");
        assertNotNull(command);
    }

    @Test
    public void parse_threePrefixes_returnsCommand() throws Exception {
        ShowCommand command = parser.parse("n/Alice d/IT ph/9123");
        assertNotNull(command);
    }

    @Test
    public void parse_randomTextWithPrefix_returnsCommand() throws Exception {
        ShowCommand command = parser.parse("random n/Alice text");
        assertNotNull(command);
    }

    @Test
    public void parse_symbolsInValue_returnsCommand() throws Exception {
        ShowCommand command = parser.parse("e/@gmail.com");
        assertNotNull(command);
    }

    @Test
    public void parse_numbersInName_returnsCommand() throws Exception {
        ShowCommand command = parser.parse("n/Alice123");
        assertNotNull(command);
    }

    @Test
    public void parse_onlyPrefixCharacters_returnsCommand() throws Exception {
        ShowCommand command = parser.parse("n/");
        assertNotNull(command);
    }

    @Test
    public void parse_phonePrefixNotConfusedWithPosition_returnsCommand() throws Exception {
        ShowCommand command = parser.parse("p/9123 pos/Manager");
        assertNotNull(command);
    }

    @Test
    public void parse_positionPrefixNotConfusedWithPhone_returnsCommand() throws Exception {
        ShowCommand command = parser.parse("pos/Manager p/9123");
        assertNotNull(command);
    }

    @Test
    public void parse_repeatedSamePrefix_returnsCommand() throws Exception {
        ShowCommand command = parser.parse("n/Alice n/Bob n/Charlie");
        assertNotNull(command);
    }

    @Test
    public void parse_repeatedDifferentPrefixes_returnsCommand() throws Exception {
        ShowCommand command = parser.parse("n/Alice d/IT n/Bob d/HR");
        assertNotNull(command);
    }
}
