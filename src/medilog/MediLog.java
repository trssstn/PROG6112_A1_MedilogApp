package medilog;

import java.util.Scanner;

public class MediLog {
    private static PatientRecord[] patientRecords;
    private static int recordCount;
    
    //Static initializer with error handling
    static {
        patientRecords = new PatientRecord[5000]; //Max 5000 patient records
        recordCount = 0;
    }
    
    public static void main(String[] args) {
        
        System.out.println("Welcome to the MediLog Healthcare Management Application");
        System.out.println("=============================================================================================\n");
        
        boolean exitProgram = false;
        
        //Main menu loop
        while (!exitProgram) {
          try{
            displayMainMenu();
            
            //Validated integer input for menu choice
            int choice = InputValidator.getValidInteger("Enter your choice: ", 1, 8);
            
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
                    //Get user confirmation before exit
                    if (InputValidator.getYesNoConfirmation("Are you sure you want to exit?")) {
                        System.out.println("Thank you for using MediLog. Shutting down...");
                        exitProgram = true;
                    }
                    break;
            }
        } catch (Exception e) {
            //Global exception handler for unexpected errors (Code taken from stackoverflow)
            System.out.println("\nAn unexpected error occurred: " + e.getMessage());
            System.out.println("Please try again or contact system administrator if problem persists.\n");
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
        
        try {
        //Using validated input methods for all fields
        String name = InputValidator.getValidString("Enter patient name: ");
        String surname = InputValidator.getValidString("Enter patient surname: ");
        String idNumber = InputValidator.getValidIDNumber("Enter patient ID number: ");
        
        //Checking for duplicate patient ID
        if (findPatientByID(idNumber) != null) {
            System.out.println("A patient with this ID number already exists!");
            return;
        }
        
        int age = InputValidator.getValidInteger("Enter patient age: ", 0, 150);
        String dob = InputValidator.getValidDate("Enter date of birth (DD/MM/YYYY): ");
        String address = InputValidator.getValidString("Enter patient address: ");
        
        //Create new patient
        Patient newPatient = new Patient(name, surname, idNumber, age, dob, address);
        
        //Add current medications
        int medCount = InputValidator.getValidInteger("How many current medications? (0-25): ", 0, 20);
        
        for (int i = 0; i < medCount; i++) {
            String med = InputValidator.getValidString("Enter medication " + (i + 1) + ": ");
            newPatient.addMedication(med);
        }
        
        //Create patient record
        PatientRecord record = new PatientRecord(newPatient);
        patientRecords[recordCount++] = record;
        
        System.out.println("\nPatient registered successfully!");
        System.out.println("Patient ID: " + idNumber);
        
        } catch (Exception e) {
            System.out.println("Error during patient registration: " + e.getMessage());
        }
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
        
        //Validate patient exists before proceeding
        String idNumber = InputValidator.getValidIDNumber("Enter patient ID number: ");
        
        PatientRecord record = findPatientByID(idNumber);
        if (record == null) {
            System.out.println("Patient not found!");
            return;
        }
        
        try {
        ExaminationDetails exam = record.getExamination();
        
        //Only use validated string inputs
        exam.setPresentingComplaint(InputValidator.getValidString("Enter presenting complaint: "));
        exam.setPatientHistory(InputValidator.getValidString("Enter patient history: "));
        exam.setSignsAndSymptoms(InputValidator.getValidString("Enter signs and symptoms: "));
        
        //Physical examination
        System.out.println("\nPhysical Examination (Enter details for each system, press Enter to skip):");
        String[] systems = {"Cardiovascular", "Respiratory", "Gastrointestinal", "Neurological", "Musculoskeletal", "Integumentary(skin)", "Endocrine", "Lymphatic", "Urinary", "Reproductive", "Immunological", "Miscellanaeous"};
        
        for (int i = 0; i < systems.length; i++) {
            String findings = InputValidator.getOptionalString(systems[i] + " findings: ");
            if (!findings.isEmpty()) {
                exam.addPhysicalExamination(systems[i], findings, i);
            }
        }
        
        //Differential diagnoses
        int diffCount = InputValidator.getValidInteger("\nNumber of differential diagnoses (0-10): ", 0, 5);
        
        for (int i = 0; i < diffCount; i++) {
            String diagnosis = InputValidator.getValidString("Differential diagnosis " + (i + 1) + ": ");
            exam.addDifferentialDiagnosis(diagnosis, i);
        }
        
        exam.setFinalDiagnosis(InputValidator.getValidString("Final diagnosis: "));
        
        System.out.println("\nExamination details added successfully!");  
    } catch (Exception e) {
        System.out.println("Error adding examination details: " + e.getMessage());
        }
    }      
    
    private static void addUpdateLog() {
        System.out.println("\n---------------Add Update Log----------------");
        
        //Validate patient exists
        String idNumber = InputValidator.getValidIDNumber("Enter patient ID number: ");
        
        PatientRecord record = findPatientByID(idNumber);
        if (record == null) {
            System.out.println("Patient not found!");
            return;
        }
        
        try {
        String staff = InputValidator.getValidString("Enter staff name responsible: ");
        
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
        
        //Validate user menu choice
        int procChoice = InputValidator.getValidInteger("Enter choice: ", 1, 30);
        
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
        
        String details = InputValidator.getValidString("Enter details: ");
        
        UpdateLog log = new UpdateLog(staff, procedureType, details);
        record.addUpdateLog(log);
        
        System.out.println("Update log added successfully!");
    } catch (Exception e) {
        System.out.println("Error adding update log: " + e.getMessage());
        }
    }
    
    private static void manageTreatmentPlan() {
        System.out.println("\n---------------Manage Treatment Plan----------------");
        
        System.out.println("Enter patient ID number: ");
        String idNumber = scanner.nextLine();
        
        PatientRecord record = findPatientByID(idNumber);
        if (record == null) {
            System.out.println("Patient not found!");
            return;
        }
        
        TreatmentPlan plan = record.getTreatmentPlan();
        
        System.out.println("Enter department: ");
        plan.setDepartment(scanner.nextLine());
        
        System.out.println("Enter triage level (1-5): ");
        plan.setTriageLevel(scanner.nextLine());
        
        //Add medications
        System.out.println("Number of new medications to prescribe: ");
        int medCount = scanner.nextInt();
        scanner.nextLine();
        
        for (int i = 0; i < medCount; i++) {
            System.out.println("Medication " + (i + 1) + ": ");
            plan.addMedication(scanner.nextLine());
        }
        
        //Add procedures
        System.out.println("Number of procedures to schedule: ");
        int procCount = scanner.nextInt();
        scanner.nextLine();
        
        for (int i = 0; i < procCount; i++) {
            System.out.println("Procedure " + (i+1) + ": ");
            plan.addProcedure(scanner.nextLine());
        }
        
        //Add referrals
        System.out.println("Number of referrals: ");
        int refCount = scanner.nextInt();
        scanner.nextLine();
        
        for (int i = 0; i < refCount; i++) {
            System.out.println("Referral " + (i + 1) + ": ");
            plan.addReferral(scanner.nextLine());
        }
        
        System.out.println("\nTreatment plan updated successfully!");
    }
    
    private static void updateAdmissionStatus() {
        System.out.println("\n---------------Update Admission Status----------------");
        
        System.out.println("Enter patient ID number: ");
        String idNumber = scanner.nextLine();
        
        PatientRecord record = findPatientByID(idNumber);
        if (record == null) {
            System.out.println("Patient not found!");
            return;
        }
        
        AdmissionStatus status = record.getAdmissionStatus();
        
        System.out.println("1. Admit Patient");
        System.out.println("2. Discharge Patient");
        System.out.println("Choose option: ");
        
        int choice = scanner.nextInt();
        scanner.nextLine();
        
        if (choice == 1) {
            System.out.println("Enter ward: ");
            String ward = scanner.nextLine();
            
            System.out.println("Enter department: ");
            String dept = scanner.nextLine();
            
            System.out.println("Enter bed number: ");
            String bedNum = scanner.nextLine();
            
            status.admitPatient(ward, dept, bedNum);
            System.out.println("Patient admitted successfully!");
        } else if (choice == 2) {
            System.out.println("Enter discharge status (Recovered/Transferred/Other): ");
            String dischargeStatus = scanner.nextLine();
            
            status.dischargePatient(dischargeStatus);
            System.out.println("Patient discharged successfully!");
        }
    }
    
    private static void viewPatientReport() {
        System.out.println("\n---------------View Patient Report----------------");
        
        System.out.println("Enter patient ID number: ");
        String idNumber = scanner.nextLine();
        
        PatientRecord record = findPatientByID(idNumber);
        if (record == null) {
            System.out.println("Patient not found!");
            return;
        }
        
        System.out.println(record.generateReport());
    }
    
    private static void listAllPatients() {
        System.out.println("\n---------------All Registered Patients----------------");
        
        if (recordCount == 0) {
            System.out.println("No patients registered yet.");
            return;
        }
        
        System.out.println("\nTotal patients: " + recordCount);
        System.out.println("--------------------------------------------------------");
        
        //Loop through all patient records
        for (int i = 0; i < recordCount; i++) {
            Patient p = patientRecords[i].getPatient();
            AdmissionStatus status = patientRecords[i].getAdmissionStatus();
            
            System.out.println((i + 1) + ". " + p.getFullName());
            System.out.println("    ID: " + p.getIdNumber());
            System.out.println("    Age: " + p.getAge());
            System.out.println("    Status: " + (status.isAdmitted() ?  "ADMITTED" : "NOT ADMITTED"));
            if (status.isAdmitted()) {
                System.out.println("    Ward: " + status.getWard() + ",  Bed: " + status.getBedNumber());
                System.out.println("    Department: " + status.getDepartment());
            }
            System.out.println("--------------------------------------------------------");
        }       
    }
}
