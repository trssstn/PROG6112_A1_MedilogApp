package medilog;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;


public class HealthCareProviderTest {
    
    private HealthcareProvider provider;
    
    @BeforeEach
    void setUp() {
        provider = new HealthcareProvider("Dr. Smith", "Johnson", "HC123456", "Doctor", "Emergency");
    }
    
    @Test
    @DisplayName("Test HealthcareProvider Constructor")
    void testConstructor() {
        assertNotNull(provider);
        assertEquals("Dr. Smith", provider.getName());
        assertEquals("Johnson", provider.getSurname());
        assertEquals("HC123456", provider.getIdNumber());
        assertEquals("Doctor", provider.getProfession());
        assertEquals("Emergency", provider.getDepartment());
    }
    
    @Test
    @DisplayName("Test Role Override")
    void testGetRole() {
        assertEquals("Doctor", provider.getRole(), "Role should return profession");
        
        HealthcareProvider nurse = new HealthcareProvider("Mary", "Jones", "HC789", "Nurse", "ICU");
        
        assertEquals("Nurse", nurse.getRole());
    }
    
    @Test
    @DisplayName("Test Department and Profession Setters")
    void testSetters() {
        provider.setProfession("Registrar");
        provider.setDepartment("Orthopaedics");
        
        assertEquals("Registrar", provider.getProfession());
        assertEquals("Orthopaedics", provider.getDepartment());
    }
}
