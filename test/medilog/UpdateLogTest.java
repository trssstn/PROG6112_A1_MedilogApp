package medilog;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;


public class UpdateLogTest {
    
    @Test
    @DisplayName("Test UpdateLog Creation and ToString")
    void testUpdateLogCreation() {
        UpdateLog log = new UpdateLog("Nurse Johnson", "Medication Administration", "Administered 500mg Paracetamol");
        
        assertNotNull(log);
        assertNotNull(log.getTimestamp(), "Timestamp should be set");
        assertEquals("Nurse Johnson", log.getResponsibleStaff());
        assertEquals("Medication Administration", log.getProcedureType());
        assertEquals("Administered 500mg Paracetamol", log.getDetails());
        
        String logString = log.toString();
        assertTrue(logString.contains("Nurse Johnson"), "String should contain staff name");
        assertTrue(logString.contains("Medication Administration"), "String should contain procedure");
    }
    
    @Test
    @DisplayName("Test UpdateLog Timestamp")
    void testTimestamp() throws InterruptedException {
        UpdateLog log1 = new UpdateLog("Staff1", "Procedure1", "Details1");
        Thread.sleep(10); //Small delay added to ensure different timestamps
        UpdateLog log2 = new UpdateLog("Staff2", "Procedure2", "Details2");
        
        assertNotEquals(log1.getTimestamp().getTime(), log2.getTimestamp().getTime(), "Different logs should have different timestamps");
    }
}
