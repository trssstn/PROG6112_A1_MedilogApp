package medilog;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;


public class PatientRecordTest {
    
    private PatientRecord record;
    private Patient patient;
    
    @BeforeEach
    void setUp() {
        patient = new Patient("Test", "Patient", "TEST123", 25, "01/01/1999", "Test Address");
        record = new PatientRecord(patient);
    }
    
    @Test
    @DisplayName("Test PatientRecord Initialisation")
    void testInitialisation() {
        assertNotNull(record);
        assertNotNull(record.getPatient());
        assertNotNull(record.getExamination());
        assertNotNull(record.getTreatmentPlan());
        assertNotNull(record.getAdmissionStatus());
        assertEquals(0, record.getUpdateLogs().length, "Should have no logs initially");
    }
    
    @Test
    @DisplayName("Test Adding Update Logs")
    void testAddUpdateLogs() {
        UpdateLog log1 = new UpdateLog("Staff1", "Procedure1", "Details1");
        UpdateLog log2 = new UpdateLog("Staff2", "Procedure2", "Details2");
        
        record.addUpdateLog(log1);
        record.addUpdateLog(log2);
        
        UpdateLog[] logs = record.getUpdateLogs();
        assertEquals(2, logs.length);
        assertEquals("Staff1", logs[0].getResponsibleStaff());
        assertEquals("Staff2", logs[1].getResponsibleStaff());
    }
    
    @Test
    @DisplayName("Test Update Log Array Limit")
    void testUpdateLogLimit() {
        //Try to add more than 100 logs
        for (int i = 0; i < 105; i++) {
            record.addUpdateLog(new UpdateLog("Staff " + i, "Proc", "Details"));
        }
        
        assertEquals(100, record.getUpdateLogs().length, "Should not exceed 100 logs");
    }
    
    @Test
    @DisplayName("Test Report Generation")
    void testGenerateReport() {
        //Setting up some test data
        record.getExamination().setPresentingComplaint("Test Complaint");
        record.getExamination().setFinalDiagnosis("Test Diagnosis");
        record.getTreatmentPlan().setDepartment("Test Department");
        record.getAdmissionStatus().admitPatient("Ward X", "Dept Y", "999");
        record.addUpdateLog(new UpdateLog("Test Staff", "Test Procedure", "Test Details"));
        
        String report = record.generateReport();
        
        assertNotNull(report, "Report should not be null");
        assertTrue(report.contains("Test Patient"), "Report should contain patient name");
        assertTrue(report.contains("TEST123"), "Report should contain patient ID");
        assertTrue(report.contains("Test Complaint"), "Report should contain complaint");
        assertTrue(report.contains("Test Diagnosis"), "Report should contain diagnosis");
        assertTrue(report.contains("Ward X"), "Report should contain ward");
        assertTrue(report.contains("Test Staff"), "Report should contain staff from log");
    }
    
    @Test
    @DisplayName("Test Report with Null Fields")
    void testReportWithNullFields() {
        //Generate report without setting any additional data
        String report = record.generateReport();
        
        assertNotNull(report, "Report should handle null fields without errors");
        assertTrue(report.length() > 0, "Report should have content");
        assertFalse(report.contains("null"), "Report should not display 'null' strings");
    }
}
