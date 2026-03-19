package seedu.address.model.employee.predicatechecker;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.PersonBuilder;

public class PositionContainsKeywordsPredicateTest {

    @Test
    public void equals() {
        List<String> firstPredicateKeywordList = Collections.singletonList("engineer");
        List<String> secondPredicateKeywordList = Arrays.asList("engineer", "manager");

        PositionContainsKeywordsPredicate firstPredicate =
                new PositionContainsKeywordsPredicate(firstPredicateKeywordList);
        PositionContainsKeywordsPredicate secondPredicate =
                new PositionContainsKeywordsPredicate(secondPredicateKeywordList);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        PositionContainsKeywordsPredicate firstPredicateCopy =
                new PositionContainsKeywordsPredicate(firstPredicateKeywordList);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different predicate -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_positionContainsKeywords_returnsTrue() {
        // One keyword
        PositionContainsKeywordsPredicate predicate =
                new PositionContainsKeywordsPredicate(Collections.singletonList("engineer"));
        assertTrue(predicate.test(new PersonBuilder().withPosition("Software Engineer").build()));

        // Multiple keywords
        predicate = new PositionContainsKeywordsPredicate(Arrays.asList("manager", "software"));
        assertTrue(predicate.test(new PersonBuilder().withPosition("Software Engineer").build()));

        // Only one matching keyword
        predicate = new PositionContainsKeywordsPredicate(Arrays.asList("analyst", "engineer"));
        assertTrue(predicate.test(new PersonBuilder().withPosition("Software Engineer").build()));

        // Mixed-case keywords
        predicate = new PositionContainsKeywordsPredicate(Arrays.asList("SOFTWARE", "ENgineer"));
        assertTrue(predicate.test(new PersonBuilder().withPosition("Software Engineer").build()));
    }

    @Test
    public void test_positionDoesNotContainKeywords_returnsFalse() {
        // Zero keywords
        PositionContainsKeywordsPredicate predicate =
                new PositionContainsKeywordsPredicate(Collections.emptyList());
        assertFalse(predicate.test(new PersonBuilder().withPosition("Software Engineer").build()));

        // Non-matching keyword
        predicate = new PositionContainsKeywordsPredicate(Arrays.asList("manager"));
        assertFalse(predicate.test(new PersonBuilder().withPosition("Software Engineer").build()));

        // Keywords match name and department, but do not match position
        predicate = new PositionContainsKeywordsPredicate(Arrays.asList("Alice", "Engineering"));
        assertFalse(predicate.test(new PersonBuilder().withName("Alice")
                .withDepartment("Engineering")
                .withPosition("Software Engineer")
                .build()));
    }

    @Test
    public void toStringMethod() {
        List<String> keywords = List.of("keyword1", "keyword2");
        PositionContainsKeywordsPredicate predicate = new PositionContainsKeywordsPredicate(keywords);

        String expected = PositionContainsKeywordsPredicate.class.getCanonicalName() + "{keywords=" + keywords + "}";
        assertEquals(expected, predicate.toString());
    }
}
