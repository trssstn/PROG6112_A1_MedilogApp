package medilog;

import java.util.Date;

class ExaminationDetails {
    private String patientHistory;
    private String presentingComplaint;
    private String signsAndSymptoms;
    private String[] physicalExamination;
    private String[] differentialDiagnoses;
    private String finalDiagnosis;
    private Date examinationDate;
    
    //Constructor
    public ExaminationDetails() {
        this.physicalExamination = new String[10]; //Different body systems for examination
        this.differentialDiagnoses = new String[5]; //Other possible diagnoses
        this.examinationDate = new Date();
    }
    
    //Getters and Setters
    public String getPatientHistory() { return patientHistory; }
    public void setPatientHistory(String patientHistory) {
        this.patientHistory = patientHistory;
    }
    
    public String getPresentingComplaint() { return presentingComplaint; }
    public void setPresentingComplaint(String presentingComplaint) {
        this.presentingComplaint = presentingComplaint;
    }
    
    public String getSignsAndSymptoms() { return signsAndSymptoms; }
    public void setSignsAndSymptoms(String signsAndSymptoms) {
        this.signsAndSymptoms = signsAndSymptoms;
    }
    
    public void addPhysicalExamination(String bodySystem, String findings, int index) {
        if (index >= 0 && index < physicalExamination.length) {
            physicalExamination[index] = bodySystem + ": " + findings;
        }
    }
    
    public String[] getPhysicalExamination() { return physicalExamination; }
    
    public void addDifferentialDiagnosis(String diagnosis, int index) {
        if (index >= 0 && index < differentialDiagnoses.length) {
            differentialDiagnoses[index] = diagnosis;
        }
    }
    
    public String[] getDifferentialDiagnoses() { return differentialDiagnoses; }
    
    public String getFinalDiagnosis() { return finalDiagnosis; }
    public void setFinalDiagnosis(String finalDiagnosis) {
        this.finalDiagnosis = finalDiagnosis;
    }
    
    public Date getExaminationDate() { return examinationDate; }
}
