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
    
    
}
