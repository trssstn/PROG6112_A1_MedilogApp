package medilog;

import java.text.SimpleDateFormat;
import java.util.Date;

class UpdateLog {
    private Date timestamp;
    private String responsibleStaff;
    private String procedureType;
    private String details;
    
    //Constructor
    public UpdateLog(String responsibleStaff, String procedureType, String details) {
        this.timestamp = new Date();
        this.responsibleStaff = responsibleStaff;
        this.procedureType = procedureType;
        this.details = details;
    }
    
    //Getters
    public Date getTimestamp() { return timestamp; }
    public String getResponsibleStaff() { return responsibleStaff; }
    public String getProcedureType() { return procedureType; }
    public String getDetails() { return details; }
    
    @Override
    public String toString() {
     SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
     return sdf.format(timestamp) + " - " + procedureType + " by " + responsibleStaff + ": " + details;
    }    
}
