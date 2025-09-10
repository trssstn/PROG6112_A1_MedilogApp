package medilog;

import java.text.SimpleDateFormat;
import java.util.Arrays;


public class PatientRecord {
    private Patient patient;
    private ExaminationDetails examination;
    private UpdateLog[] updateLogs;
    private int logCount;
    private TreatmentPlan treatmentPlan;
    private AdmissionStatus admissionStatus;
    
    public PatientRecord(Patient patient) {
        this.patient = patient;
        this.examination = new ExaminationDetails();
        this.updateLogs = new UpdateLog[100]; //Max 100 update logs
        this.logCount = 0;
        this.treatmentPlan = new TreatmentPlan();
        this.admissionStatus = new AdmissionStatus();
    }
    
    public void addUpdateLog(UpdateLog log) {
        if (logCount < updateLogs.length) {
            updateLogs[logCount++] = log;
        }
    }
    
    //Getters
    public Patient getPatient() { return patient; }
    public ExaminationDetails getExamination() { return examination; }
    public UpdateLog[] getUpdateLogs() { return Arrays.copyOf(updateLogs, logCount); }
    public TreatmentPlan getTreatmentPlan() { return treatmentPlan; }
    public AdmissionStatus getAdmissionStatus() { return admissionStatus; }
    
    //Complete patient report generation
    public String generateReport() {
        StringBuilder report = new StringBuilder();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        
        report.append("\n=====================================================================");
        report.append("                       PATIENT MEDICAL REPORT                          ");
        report.append("=====================================================================\n\n");
        
        //Patient information
        report.append("PATIENT INFORMATION\n");
        report.append("---------------------------------------------------------------------\n");
        report.append("Name : ").append(patient.getFullName()).append("\n");
        report.append("ID Number: ").append(patient.getIdNumber()).append("\n");
        report.append("Age: ").append(patient.getAge()).append("\n");
        report.append("Date of Birth: ").append(patient.getDateOfBirth()).append("\n");
        report.append("Address: ").append(patient.getAddress()).append("\n");
        
        report.append("\nCurrent Medications:\n");
        String[] meds = patient.getCurrentMedications();
        for (int i = 0; i < meds.length; i++) {
            if (meds[i] != null) {
                report.append("  - ").append(meds[i]).append("\n");
            }
        }
        
        //Examination Details
        report.append("\nEXAMINATION DETAILS\n");
        report.append("---------------------------------------------------------------------\n");
        report.append("Date: ").append(sdf.format(examination.getExaminationDate())).append("\n");
        
        //Null checks for examination details to prevent NullPointerException errors
        if (examination.getPresentingComplaint() != null) {
        report.append("Presenting Complaint: ").append(examination.getPresentingComplaint()).append("\n");
        }
        if (examination.getPatientHistory() != null) {
        report.append("Patient History: ").append(examination.getPatientHistory()).append("\n");
        }
        if (examination.getSignsAndSymptoms() != null) {
        report.append("Signs and Symptoms: ").append(examination.getSignsAndSymptoms()).append("\n");
        }
        
        report.append("\nPhysical Examination:\n");
        for (String exam : examination.getPhysicalExamination()) {
            if (exam != null) {
                report.append("  - ").append(exam).append("\n");
            }
        }
        
        report.append("\nDifferential Diagnoses:\n");
        for (String diag : examination.getDifferentialDiagnoses()) {
            if (diag != null) {
                report.append("  - ").append(diag).append("\n");
            }
        }
        
        if (examination.getFinalDiagnosis() != null) {
        report.append("Final Diagnosis: ").append(examination.getFinalDiagnosis()).append("\n");
        }
        
        //Treatment Plan
        report.append("\nTREATMENT PLAN\n");
        report.append("---------------------------------------------------------------------\n");
        
        if (treatmentPlan.getDepartment() != null) {
        report.append("Department: ").append(treatmentPlan.getDepartment()).append("\n");
        }
        if (treatmentPlan.getTriageLevel() != null) {
        report.append("Triage Level: ").append(treatmentPlan.getTriageLevel()).append("\n");
        }
        
        report.append("\nMedications Prescribed:\n");
        for (String med : treatmentPlan.getMedications()) {
            if (med != null) {
                report.append("  - ").append(med).append("\n");
            }
        }
        
        report.append("\nProcedures Scheduled:\n");
        for (String proc : treatmentPlan.getProcedures()) {
            if (proc != null) {
                report.append("  - ").append(proc).append("\n");
            }
        }
        
        report.append("\nReferrals:\n");
        for (String ref : treatmentPlan.getReferrals()) {
            if (ref != null) {
                report.append("  - ").append(ref).append("\n");
            }
        }
        
        //Admission Status
        report.append("\nADMISSION STATUS\n");
        report.append("---------------------------------------------------------------------\n");
        if (admissionStatus.isAdmitted()) {
            report.append("Status: ADMITTED\n");
            report.append("Ward: ").append(admissionStatus.getWard()).append("\n");
            report.append("Department: ").append(admissionStatus.getDepartment()).append("\n");
            report.append("Bed Number: ").append(admissionStatus.getBedNumber()).append("\n");
            report.append("Admission Date: ").append(sdf.format(admissionStatus.getAdmissionDate())).append("\n");
        } else {
            report.append("Status: NOT ADMITTED/DISCHARGED\n");
            if (admissionStatus.getDischargeDate() != null) {
                report.append("Discharge Date: ").append(sdf.format(admissionStatus.getDischargeDate())).append("\n");
                report.append("Discharge Status: ").append(admissionStatus.getDischargeStatus()).append("\n");
            }
        }
        
        //Update Logs 
        report.append("\nUPDATE LOGS\n");
        report.append("---------------------------------------------------------------------\n");
        UpdateLog[] logs = getUpdateLogs();
        for (int i = 0; i < logs.length; i++) {
            report.append(logs[i].toString()).append("\n");
        }
        
        report.append("\n=====================================================================\n");
        
        return report.toString();
    }
}
