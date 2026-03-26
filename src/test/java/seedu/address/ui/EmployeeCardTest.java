package seedu.address.ui;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class EmployeeCardTest {

    @Test
    public void formatPhone_validPhone_returnsFormattedString() {
        assertEquals("📞  98765432", EmployeeCard.formatPhone("98765432"));
    }

    @Test
    public void formatEmail_validEmail_returnsFormattedString() {
        assertEquals("📧  john@example.com", EmployeeCard.formatEmail("john@example.com"));
    }

    @Test
    public void formatDepartment_validDepartment_returnsFormattedString() {
        assertEquals("🏢  Engineering", EmployeeCard.formatDepartment("Engineering"));
    }

    @Test
    public void formatPosition_validPosition_returnsFormattedString() {
        assertEquals("💼  Manager", EmployeeCard.formatPosition("Manager"));
    }

    @Test
    public void formatPhone_anotherValidPhone_returnsFormattedString() {
        assertEquals("📞  91234567", EmployeeCard.formatPhone("91234567"));
    }

    @Test
    public void formatEmail_anotherValidEmail_returnsFormattedString() {
        assertEquals("📧  alice@company.com", EmployeeCard.formatEmail("alice@company.com"));
    }

    @Test
    public void formatDepartment_anotherValidDepartment_returnsFormattedString() {
        assertEquals("🏢  Finance", EmployeeCard.formatDepartment("Finance"));
    }

    @Test
    public void formatPosition_anotherValidPosition_returnsFormattedString() {
        assertEquals("💼  Analyst", EmployeeCard.formatPosition("Analyst"));
    }

    @Test
    public void formatPhone_zeroStartingPhone_returnsFormattedString() {
        assertEquals("📞  01234567", EmployeeCard.formatPhone("01234567"));
    }

    @Test
    public void formatEmail_schoolEmail_returnsFormattedString() {
        assertEquals("📧  john.doe@u.nus.edu", EmployeeCard.formatEmail("john.doe@u.nus.edu"));
    }

    @Test
    public void formatDepartment_singleWordDepartment_returnsFormattedString() {
        assertEquals("🏢  HR", EmployeeCard.formatDepartment("HR"));
    }

    @Test
    public void formatPosition_multiWordPosition_returnsFormattedString() {
        assertEquals("💼  Senior Software Engineer", EmployeeCard.formatPosition("Senior Software Engineer"));
    }

}
