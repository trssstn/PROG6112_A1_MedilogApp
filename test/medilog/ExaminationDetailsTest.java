package medilog;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

public class ExaminationDetailsTest {
    
    private ExaminationDetails examination;
    
    @BeforeEach
    void setUp() {
        examination = new ExaminationDetails();
    }
    
    @Test
    @DisplayName("Test Examination Initialisation")
    void testInitialisation() {
        assertNotNull(examination);
        assertNotNull(examination.getExaminationDate(), "Examination date should be set");
        assertNotNull(examination.getPhysicalExamination(), "Physical exam array should exist");
        assertNotNull(examination.getDifferentialDiagnoses(), "Differential diagnoses array should exist");
    }
    
    @Test
    @DisplayName("Test Setting Patient History and Complaints")
    void testPatientHistoryAndComplaints() {
        examination.setPatientHistory("Previous surgery in 2020");
        examination.setPresentingComplaint("Severe headache");
        examination.setSignsAndSymptoms("Fever, nausea");
        
        assertEquals("Previous surgery in 2020", examination.getPatientHistory());
        assertEquals("Severe headache", examination.getPresentingComplaint());
        assertEquals("Fever, nausea", examination.getSignsAndSymptoms());
    }
    
    @Test
    @DisplayName("Test Physical Examination Array")
    void testPhysicalExamination() {
        examination.addPhysicalExamination("Cardiovascular", "Normal heart rate", 0);
        examination.addPhysicalExamination("Respiratory", "Clear lungs", 1);
        
        String[] exams = examination.getPhysicalExamination();
        assertEquals("Cardiovascular: Normal heart rate", exams[0]);
        assertEquals("Respiratory: Clear lungs", exams[1]);
        assertNull(exams[2], "Unset indices should be null");
    }
    
    @Test
    @DisplayName("Test Physical Examination Boundary Conditions")
    void testPhysicalExaminationBoundaries() {
        //Test negative index
        examination.addPhysicalExamination("Test", "Data", -1);
        assertNull(examination.getPhysicalExamination()[0], "Should not add with negative index");
        
        //Test out of bounds index
        examination.addPhysicalExamination("Test", "Data", 15);
        String[] exams = examination.getPhysicalExamination();
        assertEquals(10, exams.length, "Array should maintain size of 10");
    }
    
    @Test
    @DisplayName("Test Differential Diagnoses")
    void testDifferentialDiagnoses() {
        examination.addDifferentialDiagnosis("Migraine", 0);
        examination.addDifferentialDiagnosis("Tension headache", 1);
        examination.setFinalDiagnosis("Migraine with aura");
        
        String[] diagnoses = examination.getDifferentialDiagnoses();
        assertEquals("Migraine", diagnoses[0]);
        assertEquals("Tension headache", diagnoses[1]);
        assertEquals("Migraine with aura", examination.getFinalDiagnosis());
    }
}
