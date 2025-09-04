package medilog;

import java.util.Arrays;

class TreatmentPlan {
    private String[] medications;
    private String[] procedures;
    private String[] referrals;
    private String department;
    private String triageLevel;
    private int medCount, procCount, refCount;
    
    //Constructor
    public TreatmentPlan() {
        this.medications = new String[20];
        this.procedures = new String[10];
        this.referrals = new String[5];
        this.medCount = 0;
        this.procCount = 0;
        this.refCount = 0;
    }
    
    public void addMedication(String medication) {
        if (medCount < medications.length) {
            medications[medCount++] = medication;
        }
    }
    
    public void addProcedure(String procedure) {
        if (procCount < procedures.length) {
            procedures[procCount++] = procedure;
        }
    }
    
    public void addReferral(String referral) {
        if (refCount < referrals.length) {
            referrals[refCount++] = referral;
        }
    }
    
    //Getters and setters
    public String[] getMedications() { return Arrays.copyOf(medications, medCount); }
    public String[] getProcedures() { return Arrays.copyOf(procedures, procCount); }
    public String[] getReferrals() { return Arrays.copyOf(referrals, refCount); }
    
    public String getDepartment() { return department; }
    public void setDepartment(String department) { this.department = department;  }
    
    public String getTriageLevel() { return triageLevel; }
    public void setTriageLevel(String triageLevel) { this.triageLevel = triageLevel; }
}
