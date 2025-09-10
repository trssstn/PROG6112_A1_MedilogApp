package medilog;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;


public class TreatmentPlanTest {
    
    private TreatmentPlan plan;
    
    @BeforeEach
    void setUp() {
        plan = new TreatmentPlan();
    }
    
    @Test
    @DisplayName("Test TreatmentPlan Initialisation")
    void testInitialisation() {
        assertNotNull(plan);
        assertEquals(0, plan.getMedications().length, "Should have no medications initially");
        assertEquals(0, plan.getProcedures().length, "Should have no procedures initially");
        assertEquals(0, plan.getReferrals().length, "Should have no referrals initially");
    }
    
    @Test
    @DisplayName("Test Adding Medications")
    void testAddMedications() {
        plan.addMedication("Antibiotic");
        plan.addMedication("Painkiller");
        
        String[] meds = plan.getMedications();
        assertEquals(2, meds.length);
        assertEquals("Antibiotic", meds[0]);
        assertEquals("Painkiller", meds[1]);
    }
    
    @Test
    @DisplayName("Test Medication Array Limit")
    void testMedicationLimit() {
        //Try to add more than 20 medications
        for (int i = 0; i < 25; i++) {
            plan.addMedication("Med " + i);
        }
        
        assertEquals(20, plan.getMedications().length, "Should not exceed 20 medications");
    }
    
    @Test
    @DisplayName("Test Adding Procedures")
    void testAddProcedures() {
        plan.addProcedure("Blood Test");
        plan.addProcedure("X-Ray");
        plan.addProcedure("MRI Scan");
        
        String[] procedures = plan.getProcedures();
        assertEquals(3, procedures.length);
        assertEquals("Blood Test", procedures[0]);
        assertEquals("X-Ray", procedures[1]);
        assertEquals("MRI Scan", procedures[2]);
    }
    
    @Test
    @DisplayName("Test Adding Referrals")
    void testAddReferrals() {
        plan.addReferral("Cardiology");
        plan.addReferral("Neurology");
        
        String[] referrals = plan.getReferrals();
        assertEquals(2, referrals.length);
        assertEquals("Cardiology", referrals[0]);
        assertEquals("Neurology", referrals[1]);
    }
    
    @Test
    @DisplayName("Test Department and Triage Level")
    void testDepartmentAndTriage() {
        plan.setDepartment("Emergency");
        plan.setTriageLevel("2");
        
        assertEquals("Emergency", plan.getDepartment());
        assertEquals("2", plan.getTriageLevel());
    }
}
