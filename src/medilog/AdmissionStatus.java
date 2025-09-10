package medilog;

import java.util.Date;

public class AdmissionStatus {
    private boolean isAdmitted;
    private String ward;
    private String department;
    private String bedNumber;
    private Date admissionDate;
    private Date dischargeDate;
    private String dischargeStatus;
    
    public AdmissionStatus() {
        this.isAdmitted = false;
    }
    
    public void admitPatient(String ward, String department, String bedNumber) {
        this.isAdmitted = true;
        this.ward = ward;
        this.department = department;
        this.bedNumber = bedNumber;
        this.admissionDate = new Date();
    }
    
    public void dischargePatient(String status) {
        this.isAdmitted = false;
        this.dischargeDate = new Date();
        this.dischargeStatus = status;
    }
    
    //Getters
    public boolean isAdmitted() { return isAdmitted; }
    public String getWard() { return ward; }
    public String getDepartment() { return department; }
    public String getBedNumber() { return bedNumber; }
    public Date getAdmissionDate() { return admissionDate; }
    public Date getDischargeDate() { return dischargeDate; }
    public String getDischargeStatus() { return dischargeStatus; }
}
