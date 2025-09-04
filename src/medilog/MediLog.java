package medilog;

import java.util.Scanner;

public class MediLog {
    private static PatientRecord[] patientRecords;
    private static int recordCount;
    private static Scanner scanner;
    
    static {
        patientRecords = new PatientRecord[5000]; //Max 5000 patient records
        recordCount = 0;
        scanner = new Scanner(System.in);
    }
    
    public static void main(String[] args) {
        
        System.out.println("Welcome to the MediLog Healthcare Management Application");
        System.out.println("=============================================================================================\n");
        
        //Main menu loop
        while (true) {
            displayMainMenu();
            int choice = scanner.nextInt();
            scanner.nextLine(); //Clears buffer
            
            switch (choice) {
                case 1:
                    registerNewPatient();
                    break;
                case 2:
                    addExaminationDetails();
                    break;
                case 3:
                    addUpdateLog();
                    break;
                case 4:
                    manageTreatmentPlan();
                    break;
                case 5:
                    updateAdmissionStatus();
                    break;
                case 6:
                    viewPatientReport();
                    break;
                case 7:
                    listAllPatients();
                    break;
                case 8:
                    System.out.println("Thank you for using MediLog!");
                    System.out.println("Shutting down...");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice, please try again.");
            }
        }
    }
    
    private static void displayMainMenu() {
        System.out.println("\n======================MAIN MENU======================");
        System.out.println("1. Register New Patient");
        System.out.println("2. Add Examination Details");
        System.out.println("3. Add Update Log");
        System.out.println("4. Manage Treatment Plan");
        System.out.println("5. Update Admission Status");
        System.out.println("6. View Patient Report");
        System.out.println("7. List All Patients");
        System.out.println("8. Exit");
        System.out.println("Enter your choice: ");
    }
    
    private static void registerNewPatient() {
        System.out.println("\n----------------Register New Patient-----------------");
        
        System.out.print("Enter patient name: ");
        String name = scanner.nextLine();
        
        System.out.print("Enter patient surname: ");
        String surname = scanner.nextLine();
        
        System.out.print("Enter patient ID number: ");
        String idNumber = scanner.nextLine();
        
        System.out.print("Enter patient age: ");
        int age = scanner.nextInt();
        scanner.nextLine();
        
        System.out.print("Enter date of birth (DD/MM/YYYY): ");
        String dob = scanner.nextLine();
        
        System.out.print("Enter patient address: ");
        String address = scanner.nextLine();
        
        //Create new patient
        Patient newPatient = new Patient(name, surname, idNumber, age, dob, address);
        
        //Add current medications
        System.out.print("Amount of current medications: ");
        int medCount = scanner.nextInt();
        scanner.nextLine();
        
        for (int i = 0; i < medCount; i++) {
            System.out.print("Enter medication #" + (i + 1) + " : ");
            String med = scanner.nextLine();
            newPatient.addMedication(med);
        }
        
        //Create patient record
        PatientRecord record = new PatientRecord(newPatient);
        patientRecords[recordCount++] = record;
        
        System.out.println("\nPatient registered successfully!");
        System.out.println("Patient ID: " + idNumber);
    }
    
    private static PatientRecord findPatientByID(String idNumber) {
        for (int i = 0; i < recordCount; i++) {
            if (patientRecords[i].getPatient().getIdNumber().equals(idNumber)) {
                return patientRecords[i];
            }
        }
        return null;
    }
    
    private static void addExaminationDetails() {
        System.out.println("\n--------------Add Examination Details---------------");
        
        System.out.print("Enter patient ID number: ");
        String idNumber = scanner.nextLine();
        
        PatientRecord record = findPatientByID(idNumber);
        if (record == null) {
            System.out.println("Patient not found!");
            return;
        }
        
        ExaminationDetails exam = record.getExamination();
        
        System.out.print("Enter presenting complaint: ");
        exam.setPresentingComplaint(scanner.nextLine());
        
        System.out.print("Enter patient history: ");
        exam.setPatientHistory(scanner.nextLine());
        
        System.out.print("Enter signs and symptoms: ");
        exam.setSignsAndSymptoms(scanner.nextLine());
        
        //Physical examination
        
    }
}
