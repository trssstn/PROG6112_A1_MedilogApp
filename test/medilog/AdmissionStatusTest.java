package medilog;

import java.util.Date;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;


public class AdmissionStatusTest {
    
    private AdmissionStatus status;
    
    @BeforeEach
    void setUp() {
        status = new AdmissionStatus();
    }
    
    @Test
    @DisplayName("Test Initial Status")
    void testInitialStatus() {
        assertFalse(status.isAdmitted(), "Patient should not be admitted initially");
        assertNull(status.getWard());
        assertNull(status.getDepartment());
        assertNull(status.getBedNumber());
        assertNull(status.getAdmissionDate());
        assertNull(status.getDischargeDate());
    }
    
    @Test
    @DisplayName("Test Patient Admission")
    void testAdmitPatient() {
        status.admitPatient("Ward A", "Cardiology", "101");
        
        assertTrue(status.isAdmitted(), "Patient should be admitted");
        assertEquals("Ward A", status.getWard());
        assertEquals("Cardiology", status.getDepartment());
        assertEquals("101", status.getBedNumber());
        assertNotNull(status.getAdmissionDate(), "Admission date should be set");
    }
    
    @Test
    @DisplayName("Test Patient Discharge")
    void testDischargePatient() {
        //First admit the patient
        status.admitPatient("Ward B", "Psychiatry", "202");
        assertTrue(status.isAdmitted());
        
        //Then discharge the patient
        status.dischargePatient("Recovered");
        
        assertFalse(status.isAdmitted(), "Patient should not be admitted after discharge");
        assertNotNull(status.getDischargeDate(), "Discharge date should be set");
        assertEquals("Recovered", status.getDischargeStatus());
    }
    
    @Test
    @DisplayName("Test Multiple Admissions")
    void testMultipleAdmissions() {
        //First admission
        status.admitPatient("Ward A", "Dept1", "101");
        Date firstAdmission = status.getAdmissionDate();
        
        //Discharge patient
        status.dischargePatient("Transferred");
        
        //Second admission
        status.admitPatient("Ward B", "Dept2", "202");
        
        assertTrue(status.isAdmitted());
        assertEquals("Ward B", status.getWard());
        assertEquals("202", status.getBedNumber());
        assertNotEquals(firstAdmission, status.getAdmissionDate(), "Should have a new admission date");
    }
}
