package medilog;

//Healthcare Provider class extending the Person class
public class HealthcareProvider extends Person {
    private String profession; //ie Doctor, Nurse, Physio, etc.
    private String department; //ie Dermatology, Neurology, Gynaecology, etc.
    
    public HealthcareProvider(String name, String surname, String idNumber, String profession, String department) {
        super(name, surname, idNumber);
        this.profession = profession;
        this.department = department;
    }
    
    //Getters and setters for healthcare provider class
    public String getProfession() { return profession; }
    public void setProfession(String profession) {this.profession = profession; }
    
    public String getDepartment() { return department; }
    public void setDepartment(String department) { this.department = department; }
    
    @Override
    public String getRole() {
        return profession;
    }
}
