package medilog;

//Patient class extending the Person class

import java.util.Arrays;

class Patient extends Person {
    private int age;
    private String dateOfBirth;
    private String address;
    private String[] currentMedications;
    private int medicationCount;
    
    //Constructor
    public Patient(String name, String surname, String idNumber, int age, String dateOfBirth, String address) {
        super(name, surname, idNumber);
        this.age = age;
        this.dateOfBirth = dateOfBirth;
        this.address = address;
        this.currentMedications = new String[20]; //Max of 20 medications per patient
        this.medicationCount = 0;
    }
    
    //Getters and Setters
    public int getAge() { return age; };
    public void setAge(int age) { this.age = age; }
    
    public String getDateOfBirth() { return dateOfBirth; }
    public void setDateOfBirth(String dateOfBirth) { this.dateOfBirth = dateOfBirth; }
    
    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }
    
    public void addMedication(String medication) {
        if (medicationCount < currentMedications.length) {
            currentMedications[medicationCount++] = medication;
        }
    }
    
    public String[] getCurrentMedications() {
        return Arrays.copyOf(currentMedications, medicationCount);
    }
    
    @Override
    public String getRole() {
        return "Patient";
    }
}