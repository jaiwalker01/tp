package seedu.address.model.employee.predicatechecker;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.PersonBuilder;

public class DepartmentContainsKeywordsPredicateTest {

    @Test
    public void equals() {
        List<String> firstPredicateKeywordList = Collections.singletonList("engineering");
        List<String> secondPredicateKeywordList = Arrays.asList("engineering", "finance");

        DepartmentContainsKeywordsPredicate firstPredicate =
                new DepartmentContainsKeywordsPredicate(firstPredicateKeywordList);
        DepartmentContainsKeywordsPredicate secondPredicate =
                new DepartmentContainsKeywordsPredicate(secondPredicateKeywordList);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        DepartmentContainsKeywordsPredicate firstPredicateCopy =
                new DepartmentContainsKeywordsPredicate(firstPredicateKeywordList);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different predicate -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_departmentContainsKeywords_returnsTrue() {
        // One keyword
        DepartmentContainsKeywordsPredicate predicate = new DepartmentContainsKeywordsPredicate(
                Collections.singletonList("engineering"));
        assertTrue(predicate.test(new PersonBuilder().withDepartment("Engineering").build()));

        // Multiple keywords
        predicate = new DepartmentContainsKeywordsPredicate(Arrays.asList("finance", "engine"));
        assertTrue(predicate.test(new PersonBuilder().withDepartment("Engineering").build()));

        // Only one matching keyword
        predicate = new DepartmentContainsKeywordsPredicate(Arrays.asList("hr", "engine"));
        assertTrue(predicate.test(new PersonBuilder().withDepartment("Engineering").build()));

        // Mixed-case keywords
        predicate = new DepartmentContainsKeywordsPredicate(Arrays.asList("ENgine", "FINANCE"));
        assertTrue(predicate.test(new PersonBuilder().withDepartment("Engineering").build()));
    }

    @Test
    public void test_departmentDoesNotContainKeywords_returnsFalse() {
        // Zero keywords
        DepartmentContainsKeywordsPredicate predicate =
                new DepartmentContainsKeywordsPredicate(Collections.emptyList());
        assertFalse(predicate.test(new PersonBuilder().withDepartment("Engineering").build()));

        // Non-matching keyword
        predicate = new DepartmentContainsKeywordsPredicate(Arrays.asList("finance"));
        assertFalse(predicate.test(new PersonBuilder().withDepartment("Engineering").build()));

        // Keywords match name and email, but do not match department
        predicate = new DepartmentContainsKeywordsPredicate(Arrays.asList("Alice", "email.com"));
        assertFalse(predicate.test(new PersonBuilder().withName("Alice")
                .withEmail("alice@email.com")
                .withDepartment("Engineering")
                .build()));
    }

    @Test
    public void toStringMethod() {
        List<String> keywords = List.of("keyword1", "keyword2");
        DepartmentContainsKeywordsPredicate predicate = new DepartmentContainsKeywordsPredicate(keywords);

        String expected = DepartmentContainsKeywordsPredicate.class.getCanonicalName()
                + "{keywords=" + keywords + "}";
        assertEquals(expected, predicate.toString());
    }
}
