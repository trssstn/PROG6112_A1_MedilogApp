package medilog;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

//InputValidator class for centralised input validation
class InputValidator {
    private static Scanner scanner = new Scanner(System.in);
    
    /**
     * Validates and gets integer input from user
     * @param prompt The message to display to user
     * @param min Minimum acceptable value
     * @param max Maximum acceptable value
     * @return Valid integer within specified range
     */
    
    public static int getValidInteger(String prompt, int min, int max) {
        int value = -1;
        boolean isValid = false;
        
        while (!isValid) {
            System.out.println(prompt);
            try {
                String input = scanner.nextLine();
                value = Integer.parseInt(input);
                
                if (value >= min && value <= max) {
                    isValid = true;
                } else {
                    System.out.println("Please enter a number between " + min + " and " + max);
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid number.");
            }
        }
        return value;
    }
    
    /**
     * Validates and gets non-empty string input
     * @param prompt The message to display to user
     * @return Non-empty string
     */
    
    public static String getValidString(String prompt) {
        String input = "";
        boolean isValid = false;
        
        while (!isValid) {
            System.out.println(prompt);
            input = scanner.nextLine().trim();
            
            if (!input.isEmpty()) {
                isValid = true;
            } else {
                System.out.println("Field cannot be empty. Please enter a value.");
            }
        }
        return input;
    }
    
    /**
     * Gets optional string input (ie can be empty)
     * @param prompt The message to display to user
     * @return String input (may be empty)
     */
    
    public static String getOptionalString(String prompt) {
        System.out.println(prompt);
        return scanner.nextLine().trim();
    }
    
    /**
     * Validates date format (DD/MM/YYYY)
     * @param prompt The message to display to user
     * @return Valid date string
     */
    
    public static String getValidDate(String prompt) {
        String date = "";
        boolean isValid = false;
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        dateFormat.setLenient(false); //Allows for strict date parsing
        
        while (!isValid) {
            System.out.println(prompt);
            date = scanner.nextLine().trim();
            
            if (date.isEmpty()) {
                System.out.println("Date cannot be empty.");
                continue;
            }
            
            try {
                Date parsedDate = dateFormat.parse(date);
                isValid = true;
            } catch (ParseException e) {
                System.out.println("Invalid date format. Please use DD/MM/YYYY format.");
            }
        }
        return date;
    }
    
    /**
     * Validates ID number (must be numeric and of specific length)
     * @param prompt The message to display to user
     * @return Valid ID number
     */
    
    public static String getValidIDNumber(String prompt) {
        String id = "";
        boolean isValid = false;
        
        while (!isValid) {
            System.out.println(prompt);
            id = scanner.nextLine().trim();
            
            //Check if ID is not empty and contains only digits
            if (id.isEmpty()) {
                System.out.println("ID number cannot be empty.");
            } else if (!id.matches("\\d+")) {
                System.out.println("ID number must contain only digits.");
            } else if (id.length() != 13) {
                System.out.println("ID number must be 13 digits!");
            } else {
                isValid = true;
            }
        }
        return id;
    }
    
    /**
     * Gets yes/no confirmation from user
     * @param prompt The message to display to user
     * @return true for yes, false for no
     */
    
    public static boolean getYesNoConfirmation(String prompt) {
        String response = "";
        boolean isValid = false;
        
        while (!isValid) {
            System.out.println(prompt + " (Y/N): ");
            response = scanner.nextLine().trim().toUpperCase();
            
            if (response.equals("Y") || response.equals("YES")) {
                return true;
            } else if (response.equals("N") || response.equals("NO")) {
                return false;
            } else {
                System.out.println("Please enter Y for Yes or N for No.");
            }
        }
        return false;
    }
}
