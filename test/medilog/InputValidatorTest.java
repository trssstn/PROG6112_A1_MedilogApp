package medilog;
//Much of this code was adapted or rewritten from examples from stackoverflow and quora forums

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;
import java.io.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;


public class InputValidatorTest {
    
    private final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    
    @BeforeEach
    void setUp() {
        System.setOut(new PrintStream(outputStream));
    }
    
    @AfterEach
    void tearDown() {
        System.setOut(originalOut);
    }
    
    @Test
    @DisplayName("Test Valid Integer Input")
    void testValidIntegerInput() {
        String input = "5\n";
        ByteArrayInputStream inputStream = new ByteArrayInputStream(input.getBytes());
        System.setIn(inputStream);
        
        int result = InputValidator.getValidInteger("Enter number: ", 1, 10);
        assertEquals(5, result, "Should return 5");
    }
    
    @Test
    @DisplayName("Test Integer Input with Invalid then Valid")
    void testIntegerInputWithRetry() {
        String input = "abc\n15\n5\n"; //Invalid, out of range, valid
        ByteArrayInputStream inputStream = new ByteArrayInputStream(input.getBytes());
        System.setIn(inputStream);
        
        int result = InputValidator.getValidInteger("Enter number: ", 1, 10);
        assertEquals(5, result, "Should return 5 after retries");
        
        String output = outputStream.toString();
        assertTrue(output.contains("Error"), "Should show error message");
    }
    
    @Test
    @DisplayName("Test Valid String Input")
    void testValidStringInput() {
        String input = "John Doe\n";
        ByteArrayInputStream inputStream = new ByteArrayInputStream(input.getBytes());
        System.setIn(inputStream);
        
        String result = InputValidator.getValidString("Enter name: ");
        assertEquals("John Doe", result, "Should return the input string");
    }
    
    @Test
    @DisplayName("Test Empty String Rejection")
    void testEmptyStringRejection() {
        String input = "\n \nJohn\n"; //Empty, whitespace, valid
        ByteArrayInputStream inputStream = new ByteArrayInputStream(input.getBytes());
        System.setIn(inputStream);
        
        String result = InputValidator.getValidString("Enter name: ");
        assertEquals("John", result, "Should return 'John' after rejecting empty inputs");
    }
    
    @Test
    @DisplayName("Test Optional String Input")
    void testOptionalStringInput() {
        String input = "\n"; //Empty input
        ByteArrayInputStream inputStream = new ByteArrayInputStream(input.getBytes());
        System.setIn(inputStream);
        
        String result = InputValidator.getOptionalString("Enter optional: ");
        assertEquals("", result, "Should accept empty string for optional input");
    }
    
    @Test
    @DisplayName("Test Valid Date Format")
    void testValidDateFormat() {
        String input = "15/03/2024\n";
        ByteArrayInputStream inputStream = new ByteArrayInputStream(input.getBytes());
        System.setIn(inputStream);
        
        String result = InputValidator.getValidDate("Enter date: ");
        assertEquals("15/03/2024", result, "Should accept valid date format");
    }
    
    @Test
    @DisplayName("Test Invalid Date Format Rejection")
    void testInvalidDateFormat() {
        String input = "2024-03-15\n32/13/2024\n15/03/2024\n"; // Wrong format, invalid date, valid
        ByteArrayInputStream inputStream = new ByteArrayInputStream(input.getBytes());
        System.setIn(inputStream);
        
        String result = InputValidator.getValidDate("Enter date: ");
        assertEquals("15/03/2024", result, "Should return valid date after rejecting invalid ones");
    }
    
    @Test
    @DisplayName("Test Valid ID Number")
    void testValidIDNumber() {
        String input = "123456\n";
        ByteArrayInputStream inputStream = new ByteArrayInputStream(input.getBytes());
        System.setIn(inputStream);
        
        String result = InputValidator.getValidIDNumber("Enter ID: ");
        assertEquals("123456", result, "Should accept valid ID number");
    }
    
    @ParameterizedTest
    @ValueSource(strings = {"12345", "12345678901234", "ABC123", ""})
    @DisplayName("Test Invalid ID Numbers")
    void testInvalidIDNumbers(String invalidId) {
        String input = invalidId + "\n1234567\n"; // Invalid then valid
        ByteArrayInputStream inputStream = new ByteArrayInputStream(input.getBytes());
        System.setIn(inputStream);
        
        String result = InputValidator.getValidIDNumber("Enter ID: ");
        assertEquals("1234567", result, "Should reject invalid ID and accept valid one");
    }
    
    @Test
    @DisplayName("Test Yes/No Confirmation - Yes")
    void testYesConfirmation() {
        String input = "Y\n";
        ByteArrayInputStream inputStream = new ByteArrayInputStream(input.getBytes());
        System.setIn(inputStream);
        
        boolean result = InputValidator.getYesNoConfirmation("Confirm?");
        assertTrue(result, "Should return true for 'Y'");
    }
    
    @Test
    @DisplayName("Test Yes/No Confirmation - No")
    void testNoConfirmation() {
        String input = "N\n";
        ByteArrayInputStream inputStream = new ByteArrayInputStream(input.getBytes());
        System.setIn(inputStream);
        
        boolean result = InputValidator.getYesNoConfirmation("Confirm?");
        assertFalse(result, "Should return false for 'N'");
    }
    
    @Test
    @DisplayName("Test Yes/No Confirmation with Invalid Input")
    void testYesNoWithInvalid() {
        String input = "Maybe\nOK\nyes\n"; // Invalid inputs then valid
        ByteArrayInputStream inputStream = new ByteArrayInputStream(input.getBytes());
        System.setIn(inputStream);
        
        boolean result = InputValidator.getYesNoConfirmation("Confirm?");
        assertTrue(result, "Should return true for 'yes' after rejecting invalid inputs");
    }
}
