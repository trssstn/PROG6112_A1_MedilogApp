package medilog;

//Base person class that allows for inheritance
public abstract class Person {
    private String name;
    private String surname;
    private String idNumber;
    
    //Constructor
    public Person(String name, String surname, String idNumber) {
        this.name = name;
        this.surname = surname;
        this.idNumber = idNumber;
    }
    
    //Getters and setters that allow for encapsulation
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    
    public String getSurname() { return surname; }
    public void setSurname(String surname) { this.surname = surname; }
    
    public String getIdNumber() { return idNumber; }
    public void setIdNumber(String idNumber) {this.idNumber = idNumber; }
    
    public String getFullName() {
        return name + " " + surname;
    }
    
    //Abstract method to be implemented by other classes
    public abstract String getRole();
}
