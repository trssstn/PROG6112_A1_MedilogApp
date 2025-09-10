package medilog;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;


public class MediLogIntegrationTest {
    
    @Test
    @DisplayName("Test Complete Patient Flow")
    void testCompletePatientFlow() {
        // Create a patient
        Patient patient = new Patient("Integration", "Test", "INT123456", 40, "15/06/1984", "Integration Street");
        patient.addMedication("Test Med 1");
        patient.addMedication("Test Med 2");
        
        // Create patient record
        PatientRecord record = new PatientRecord(patient);
        
        // Add examination details
        ExaminationDetails exam = record.getExamination();
        exam.setPresentingComplaint("Integration Test Complaint");
        exam.setPatientHistory("Integration Test History");
        exam.setFinalDiagnosis("Integration Test Diagnosis");
        
        // Add treatment plan
        TreatmentPlan plan = record.getTreatmentPlan();
        plan.setDepartment("Test Department");
        plan.setTriageLevel("3");
        plan.addMedication("New Test Med");
        plan.addProcedure("Test Procedure");
        
        // Admit patient
        AdmissionStatus status = record.getAdmissionStatus();
        status.admitPatient("Test Ward", "Test Dept", "T101");
        
        // Add update logs
        record.addUpdateLog(new UpdateLog("Test Nurse", "Medication", "Given medication"));
        record.addUpdateLog(new UpdateLog("Test Doctor", "Examination", "Examined patient"));
        
        // Generate report
        String report = record.generateReport();
        
        // Verify report contains all information
        assertNotNull(report);
        assertTrue(report.contains("Integration Test"));
        assertTrue(report.contains("INT123456"));
        assertTrue(report.contains("Test Med 1"));
        assertTrue(report.contains("Integration Test Complaint"));
        assertTrue(report.contains("Integration Test Diagnosis"));
        assertTrue(report.contains("Test Department"));
        assertTrue(report.contains("Test Ward"));
        assertTrue(report.contains("Test Nurse"));
        assertTrue(report.contains("ADMITTED"));
        
        // Discharge patient
        status.dischargePatient("Recovered");
        
        // Generate new report
        String dischargeReport = record.generateReport();
        assertTrue(dischargeReport.contains("NOT ADMITTED"));
        assertTrue(dischargeReport.contains("Recovered"));
    }
    
    @Test
    @DisplayName("Test Multiple Patients Management")
    void testMultiplePatientsManagement() {
        // Create multiple patient records
        PatientRecord[] records = new PatientRecord[5];
        
        for (int i = 0; i < 5; i++) {
            Patient p = new Patient("Patient" + i, "Test", "ID00" + i, 20 + i, "01/01/2000", "Address " + i);
            records[i] = new PatientRecord(p);
        }
        
        // Verify all records are unique
        for (int i = 0; i < 5; i++) {
            for (int j = i + 1; j < 5; j++) {
                assertNotEquals(records[i].getPatient().getIdNumber(), records[j].getPatient().getIdNumber(), "Patient IDs should be unique");
            }
        }
        
        // Admit some patients
        records[0].getAdmissionStatus().admitPatient("Ward A", "Dept 1", "101");
        records[2].getAdmissionStatus().admitPatient("Ward B", "Dept 2", "201");
        records[4].getAdmissionStatus().admitPatient("Ward C", "Dept 3", "301");
        
        // Count admitted patients
        int admittedCount = 0;
        for (PatientRecord record : records) {
            if (record.getAdmissionStatus().isAdmitted()) {
                admittedCount++;
            }
        }
        
        assertEquals(3, admittedCount, "Should have 3 admitted patients");
    }
}
