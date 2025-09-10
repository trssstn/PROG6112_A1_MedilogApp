package medilog;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;


public class PatientTest {
    
    private Patient patient;
    
    @BeforeEach
    void setUp() {
        patient = new Patient("John", "Doe", "1234567890", 30, "01/01/1994", "123 Main St");
    }
    
    @Test
    @DisplayName("Test Patient Constructor and Basic Getters")
    void testPatientConstructor() {
        assertNotNull(patient, "Patient should not be null");
        assertEquals("John", patient.getName(), "Name should match");
        assertEquals("Doe", patient.getSurname(), "Surname should match");
        assertEquals("1234567890", patient.getIdNumber(), "ID should match");
        assertEquals(30, patient.getAge(), "Age should match");
        assertEquals("01/01/1994", patient.getDateOfBirth(), "DOB should match");
        assertEquals("123 Main St", patient.getAddress(), "Address should match");
    }
    
    @Test
    @DisplayName("Test Full Name Generation")
    void testGetFullName() {
        assertEquals("John Doe", patient.getFullName(), "Full name should be 'John Doe'");
    }
    
    @Test
    @DisplayName("Test Role Inheritance")
    void testGetRole() {
        assertEquals("Patient", patient.getRole(), "Role should be 'Patient'");
    }
    
    @Test
    @DisplayName("Test Medication Management")
    void testMedicationManagement() {
        assertEquals(0, patient.getCurrentMedications().length, "Should have no medications initially");
        
        patient.addMedication("Disprin");
        patient.addMedication("Panado");
        
        String[] meds = patient.getCurrentMedications();
        assertEquals(2, meds.length, "Should have 2 medications");
        assertEquals("Disprin", meds[0], "First medication should be Disprin");
        assertEquals("Panado", meds[1], "Second medication should be Panado");
    }
    
    @Test
    @DisplayName("Test Medication Array Limit")
    void testMedicationArrayLimit() {
        for (int i = 0; i < 25; i++) {
            patient.addMedication("Med " + i);
        }
        
        assertEquals(20, patient.getCurrentMedications().length, "Should not exceed 20 medications");
    }
    
    @Test
    @DisplayName("Test Setters")
    void testSetters() {
        patient.setName("Jane");
        patient.setSurname("Smith");
        patient.setAge(35);
        patient.setAddress("456 Oak Ave");
        
        assertEquals("Jane", patient.getName());
        assertEquals("Smith", patient.getSurname());
        assertEquals(35, patient.getAge());
        assertEquals("456 Oak Ave", patient.getAddress());
    }
}
