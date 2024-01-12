/*
Name: Ayan Hazarika
Program: Medical Database Culminating Activity
Date: 1/19/2021
*/
package Main;

//Importing all required modules
import java.util.Scanner;
import java.io.File;
import java.io.PrintWriter;
import java.util.*;
import java.lang.*;
import java.io.FileNotFoundException;
import java.io.IOException;

public class MedicalDatabase {
    //METHOD TO ASK USER AT END OF PROGRAM IF THEY WANT TO RUN PROGRAM AGAIN
    public static String continueOrNot(){
        String continueOrNot = "";
        int counter = 0;
        
        Scanner input = new Scanner(System.in);
        
        do{
            //if user has entered invalid value, display this
            if(counter > 0){
                System.out.println("\nInvalid Value Entered. Enter either the number 1 or 2");
            }    
            
            System.out.println("Would you like to run this program again for another patient?");
            System.out.println("Enter 1 to end program, Enter 2 to run program again: ");
            
            continueOrNot = input.next();
            input.nextLine();
            
        }while(!(continueOrNot.equals("1")) && !(continueOrNot.equals("2")));
        
        return continueOrNot;
    }
    
    
    //METHOD TO ASK USER AT END OF PROGRAM IF THEY WANT TO RATE THE DATABASE UI
    public static void checkRating(){
        System.out.println("\nThank you for using the Guelph Public Hospital Medical Records Database!");
        System.out.println("Would you like to leave a rating for our user interface?");
        System.out.println("Enter 1 for yes, Enter 2 for no");
        
        Scanner rate = new Scanner(System.in);
        
        String rating = rate.next();
        rate.nextLine();
              
        if(rating.equals("1")){
            String finalRating = "";
            
            System.out.println("\nWould you rate your experience as Excellent, Good, Unsatisfactory or Poor?");
            System.out.println("Enter 1 for Excellent, 2 for Good, 3 for Unsatisfactory, 4 for Poor");
            finalRating = rate.next();
            rate.nextLine();
            
            //validity loop
            while(!(finalRating.equals("1")) && !(finalRating.equals("2")) && !(finalRating.equals("3")) && !(finalRating.equals("4"))){
                System.out.println("\nInvalid rating entered.");
                System.out.println("Try again!");
                
                System.out.println("Enter 1 for Excellent, 2 for Good, 3 for Unsatisfactory, 4 for Poor");
                finalRating = rate.next();
                rate.nextLine();
            }
            
            if(finalRating.equals("1") || finalRating.equals("2")){
                System.out.println("\nIt is good to know you enjoyed your experience, thank you and goodbye!");
            }
            else if(finalRating.equals("3") || finalRating.equals("4")){
                System.out.println("\nWe are sorry to hear you had a bad experience, have a great day!");
            }
        }
        else{
        System.out.println("\nThank you, goodbye!");
        }
    }
    
    //MAIN METHOD
    public static void main(String[] args) throws FileNotFoundException, IOException{
        
        //PART 1 - Operator Logs into Database ==============================================================================================
        
        //initializing and declaring variables
        String password = "password";
        String loginID = "loginID";
        int correctOrNo;
        
        //calling in external method
        correctOrNo = externalMethods.login(password, loginID);
        
        //program ends if login was unsuccessful
        if(correctOrNo == 0){  
            System.out.println("\nYou have been unsuccessful in logging into the database.");
            System.out.println("You will be transported out of the program and you wont be able to attempt to login anymore.");
            System.exit(0);
        }
        else{
            
        }
        //END OF PART 1 =======================================================================================================================
        
        
        //PART 2 - Introducing Operator to database ===========================================================================================
        System.out.println("\nHello and Welcome to the Guelph General Hospital Medical Records Database.");
        System.out.println("You will be given the option of adding a new patient, or working with an existing patient");
        System.out.println("Let's get started!");
        System.out.println("------------------------------------------------------------------------------------------------------------\n");
        
        //END OF PART 2 ========================================================================================================================
        
        String continueOrNot = "";
        
        //Loop runs as long as user continues running program
        do{
            //PART 3 - Asking if user wants to enter new patient, or work with existing patient ================
            //initializing and declaring variables
            String choice;
            Scanner input = new Scanner(System.in);
            
            //asking if new patient is being added, or if existing patient is going to be worked with
            System.out.println("Would you like to Enter a new Patient into the System, or find an existing patient?");  
            System.out.println("Enter 1 for New Patient, Enter 2 for Existing Patient.");
            
            choice = input.next();
            input.nextLine();    
            
            //END OF PART 3 =====================================================================================
            
            while(!(choice.equals("1")) && !(choice.equals("2"))){
                System.out.println("\nYou entered an invalid value.");
                System.out.println("Enter 1 for New Patient, Enter 2 for Existing Patient.\n");
                choice = input.next();
                input.nextLine();   
            }
            
            //PART 4 - Main Chunk of the program --> Add new Patient or Work with Existing Patient ==============
            //user chooses to add new patient
            if(choice.equals("1")){
                String fileDir = "C:\\Users\\ayhaz\\Desktop\\Ayan\\GCVI Projects and Homework\\Grade 10\\Computer Programming - ICS 3U\\Culminating\\src\\Main\\recordsBackup.txt";
                String[] newPatient = new String[9];
                
                //building array to read all data from file into
                int columns = 9;
                int rows = externalMethods.findRows(fileDir);
                
                //building and populating the array with all data from the Records.txt file
                String[][] data = new String[rows][columns];
                data = externalMethods.readData(data, fileDir);
                
                newPatient = externalMethods.newPatient(data, rows);
                
                //writing all new patient data to file 
                externalMethods.writeNewPatientCSVFile(newPatient, fileDir);
                
                System.out.println("New Patient's Data has been Saved, and will be entered into the database\n");
            }
            //user chooses to locate and work with existing patient
            else if(choice.equals("2")){
                //initializing and declaring
                String fileDir = "C:\\Users\\ayhaz\\Desktop\\Ayan\\GCVI Projects and Homework\\Grade 10\\Computer Programming - ICS 3U\\Culminating\\src\\Main\\recordsBackup.txt";
                
                //building array to read all data from file into
                int columns = 9;
                int rows = externalMethods.findRows(fileDir);
                
                //building and populating the array with all data from the Records.txt file
                String[][] data = new String[rows][columns];
                data = externalMethods.readData(data, fileDir);
                
                //identifying patient ============================================
                String isCorrect = "";
                int patientIndex = 0;
                String[] patientData = new String[columns];          
                
                do{
                    patientIndex = 0;
                    
                    //find patient array location to store data
                    patientIndex = externalMethods.findPatient(data);
                    if(patientIndex == 500){
                        System.out.println("\nYou entered a Name or Medical ID that does not belong to any Patient");
                        System.out.println("Try Again");
                        
                        isCorrect = "2";
                    }
                    else{
                        patientData = data[patientIndex];
                                        
                        //check if the patient is correct
                        isCorrect = externalMethods.checkIfCorrectPatient(patientData);
                    }                  
                }while(!isCorrect.equals("1"));
                
                // ===============================================================
                
                //asking what patient would like to do
                String option = "";
                option = externalMethods.patientChoice();
                
                while(!(option.equals("1")) && !(option.equals("2")) && !(option.equals("3")) && !(option.equals("4"))){
                    System.out.println("Invalid Value Entered.");
                    
                    option = "";
                    option = externalMethods.patientChoice();
                    
                }
                
                //determining what option the patient chose, and what to do
                if(option.equals("1")){
                    System.out.println();
                    externalMethods.printSlip(patientData);
                }
                else if(option.equals("2")){
                    externalMethods.generatePriority(data, patientIndex, rows, patientData[1], patientData[2], patientData[0]);
                }
                else if(option.equals("3")){
                    data = externalMethods.updateRecords(data, patientIndex);
                }  
                else{
                    data[patientIndex][8] = externalMethods.changeDoctor(data, patientIndex);
                }
                //END OF PART 4 ================================================================
                //PART 5 - WRITING ALL UPDATED RECORDS/DATA TO FILE ============================
                externalMethods.writeToCSVFile(data, fileDir);

                //END OF PART 5 ================================================================
            }

            //calling upon method to check if user wants to run program again
            System.out.println();
            continueOrNot = continueOrNot();
            
        }while(continueOrNot.equals("2") && !(continueOrNot.equals("1")));       
        
        //asking user if they want to check rating
        checkRating();
    }
}
