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
        System.out.println("\nPhysical Examination (Enter details for each system):");
        String[] systems = {"Cardiovascular", "Respiratory", "Gastrointestinal", "Neurological", "Musculoskeletal", "Integumentary(skin)", "Endocrine", "Lymphatic", "Urinary", "Reproductive", "Immunological", "Miscellanaeous"};
        
        for (int i = 0; i < systems.length; i++) {
            System.out.print(systems[i] + " findings: ");
            String findings = scanner.nextLine();
            if (!findings.isEmpty()) {
                exam.addPhysicalExamination(systems[i], findings, i);
            }
        }
        
        //Differential diagnoses
        System.out.print("\nNumber of differential diagnoses: ");
        int diffCount = scanner.nextInt();
        scanner.nextLine();
        
        for (int i = 0; i < diffCount && i < 5; i++) {
            System.out.print("Differential diagnosis " + (i + 1) + ": ");
            exam.addDifferentialDiagnosis(scanner.nextLine(), i);
        }
        
        System.out.print("Final diagnosis: ");
        exam.setFinalDiagnosis(scanner.nextLine());
        
        System.out.println("\nExamination details added successfully!");  
    }
    
    private static void addUpdateLog() {
        System.out.println("\n---------------Add Update Log----------------");
        
        System.out.print("Enter patient ID number: ");
        String idNumber = scanner.nextLine();
        
        PatientRecord record = findPatientByID(idNumber);
        if (record == null) {
            System.out.println("Patient not found!");
            return;
        }
        
        System.out.print("Enter staff name responsible: ");
        String staff = scanner.nextLine();
        
        System.out.println("Select procedure type:");
        System.out.println("1. Amniotic Fluid Fern Test");
        System.out.println("2. Arterial Line Placement");
        System.out.println("3. Arterial puncture");
        System.out.println("4. Arthrocentesis (Diagnostic and/or Therapeutic)");
        System.out.println("5. Central Venous Catheterization");
        System.out.println("6. Chest Tube Placement (Closed Thoracostomy, Tube Thoracostomy)");
        System.out.println("7. Cricothyroidotomy (Needle and/or Surgical)");
        System.out.println("8. Doppler Pressures");
        System.out.println("9. Electrocardiogram (ECG)");
        System.out.println("10. Endotracheal Intubation and/or other Airway Management");
        System.out.println("11. Fever Work-Up");
        System.out.println("12. Gastrointestinal intubation");
        System.out.println("13. Heelstick and/or Fingerstick (Capillary Blood Sampling)");
        System.out.println("14. Injection Procedures");
        System.out.println("15. IV Procedures");
        System.out.println("16. Lumbar Puncture");
        System.out.println("17. Orthostatic Blood Pressure Measurement");
        System.out.println("18. Pelvic Examination");
        System.out.println("19. Pericardiocentesis");
        System.out.println("20. Peripheral Insertion of Central Catheter (PICC)");
        System.out.println("21. Peritoneal (Abdominal) Paracentesis");
        System.out.println("22. Pulmonary Artery Catheterization");
        System.out.println("23. Pulsus Paradoxus Measurement");
        System.out.println("24. Skin Biopsy and other Dermatological Procedures");
        System.out.println("25. Thoracentesis");
        System.out.println("26. Ultrasound Diagnostics/Procedures");
        System.out.println("27. Urinary Tract Procedures");
        System.out.println("28. Venipuncture");
        System.out.println("29. Venous Access: Intraosseus (IO) Infusion");
        System.out.println("30. Other/Miscellaneous");
        
        int procChoice = scanner.nextInt();
        scanner.nextLine();
        
        String procedureType = "";
        
        switch (procChoice) {
            case 1: procedureType = "Amniotic Fluid Fern Test"; break;
            case 2: procedureType = "Arterial Line Placement"; break;
            case 3: procedureType = "Arterial Puncture"; break;
            case 4: procedureType = "Arthrocentesis (Diagnostic and/or Therapeutic)"; break;
            case 5: procedureType = "Central Venous Catheterization"; break;
            case 6: procedureType = "Chest Tube Placement (Closed Thoracostomy, Tube Thoracostomy)"; break;
            case 7: procedureType = "Cricothyroidotomy (Needle and/or Surgical)"; break;
            case 8: procedureType = "Doppler Pressures"; break;
            case 9: procedureType = "Electrocardiogram (ECG)"; break;
            case 10: procedureType = "Endotracheal Intubation and/or other Airway Management"; break;
            case 11: procedureType = "Fever Work-Up"; break;
            case 12: procedureType = "Gastrointestinal Intubation"; break;
            case 13: procedureType = "Heelstick and/or Fingerstick (Capillary Blood Sampling)"; break;
            case 14: procedureType = "Injection Procedures"; break;
            case 15: procedureType = "IV Procedures"; break;
            case 16: procedureType = "Lumbar Puncture"; break;
            case 17: procedureType = "Orthostatic Blood Pressure Measurement"; break;
            case 18: procedureType = "Pelvic Examination"; break;
            case 19: procedureType = "Pericardiocentesis"; break;
            case 20: procedureType = "Peripheral Insertion of Central Catheter (PICC)"; break;
            case 21: procedureType = "Peritoneal (Abdominal) Paracentesis"; break;
            case 22: procedureType = "Pulmonary Artery Catheterization"; break;
            case 23: procedureType = "Pulsus Paradoxus Measurement"; break;
            case 24: procedureType = "Skin Biopsy and other Dermatological Procedures"; break;
            case 25: procedureType = "Thoracentesis"; break;
            case 26: procedureType = "Ultrasound Diagnostics/Procedures"; break;
            case 27: procedureType = "Urinary Tract Procedures"; break;
            case 28: procedureType = "Venipuncture"; break;
            case 29: procedureType = "Venous Access: Intraosseus (IO) Infusion"; break;
            case 30: procedureType = "Other/Miscellaneous"; break;
            default: procedureType = "Other/Miscellaneous"; break;
           }
        
        System.out.println("Enter details: ");
        String details = scanner.nextLine();
        
        UpdateLog log = new UpdateLog(staff, procedureType, details);
        record.addUpdateLog(log);
        
        System.out.println("Update log added successfully!");
    }
    
    
}
