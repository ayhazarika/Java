/*
Note: All of my external methods are located below, which is a large portion of my code
 */
package Main;

//Importing all required modules
import java.util.Scanner;
import java.io.File;
import java.io.PrintWriter;
import java.io.FileWriter;
import java.util.*;
import java.lang.*;
import java.io.FileNotFoundException;
import java.io.IOException;

public class externalMethods {
    
    //EXTERNAL METHOD #1 - USED TO LOGIN TO DATABASE
    public static int login(String password, String loginID){
        int attempts = 0;
        int maxAttempts = 4;
        int binary = 0;

        String userLogin = "";
        String userPass = "";

        Scanner input = new Scanner(System.in);

        System.out.println("You will be required to enter the database’s LoginID and Password.");
        System.out.println("You have 5 attempts, after which you will be locked out of the database.\n");

        System.out.println("LoginID: ");
        userLogin = input.next();
        input.nextLine();

        System.out.println("\nPassword: ");
        userPass = input.next();
        input.nextLine();

        while((!(userLogin.equals(loginID)) || !(userPass.equals(password)))  &&  attempts < maxAttempts){
            System.out.println("\nIncorrect LoginID or Password Entered");
            System.out.println("Try Again\n");

            attempts++;
            
            System.out.println("LoginID: ");
            userLogin = input.next();
            input.nextLine();

            System.out.println("\nPassword: ");
            userPass = input.next();
            input.nextLine();
        }

        if(userLogin.equals(loginID) && userPass.equals(password)){
            binary = 1;
        }
        else if(userLogin != loginID || userPass != password){
            binary = 0;
        }
        else{
            
        }
        
        return binary;
    }
    
    //METHOD #2 - USED WHEN USER WANTS TO ADD NEW PATIENT TO DATABASE
    public static String[] newPatient(String[][] data, int rows){
        String[] newPatient = new String[9];
        
        Scanner patient = new Scanner(System.in);
        
        System.out.println("\nLet's Enter a new patient into the Guelph Public Hospital Medical Records Database!");
        System.out.println("The patient will be required to enter 9 pieces of personal data:");
        System.out.println("Medical ID, First Name, Last Name, Date of Birth, Gender, Medical Priority, Appointment Date, Appointment Time, Name of Doctor");
        System.out.println("Ensure Patient has ALL THIS DATA, or else entries will not be saved to the records database.");
        
        
        
        //validity loop to check if entered id is equals
        int repeat = 1;
        
        do{
            System.out.println("\nEnter the Patient's Medical ID: ");
            String ID = patient.next();
            newPatient[0] = ID;
            patient.nextLine();
            
            repeat = 0;

            for(int i = 0; i < rows; i ++){
                if(ID.equals(data[i][0])){
                    repeat = 1;
                    break;
                }
                else{
                    repeat = 2;
                }
            }
            
            if(repeat == 1){
                System.out.println("\nInvalid value entered");
                System.out.println("The entered Medical ID already belongs to another patient.");
                System.out.println("Try Again.\n");
            }
            else{

            }    
        }while(repeat != 2);
        
        
        System.out.println("\nEnter the Patient's First Name: ");
        String firstName = patient.next();
        newPatient[2] = firstName;
        patient.nextLine();
        
        System.out.println("\nEnter the Patient's Last Name: ");
        String lastName = patient.next();
        newPatient[1] = lastName;
        patient.nextLine();
        
        //Date Of Birth
        System.out.println("\nLet's Enter the Patient's DOB");
        System.out.println("Enter the Patient’s DOB YEAR (4 Digit Value):");
        String year = patient.next();
        patient.nextLine();
        System.out.println("\nEnter the Patient’s DOB MONTH (2 Digit Value): ");
        String month= patient.next();
        patient.nextLine();
        System.out.println("\nEnter the Patient’s DOB DAY (2 Digit Value): ");
        String day= patient.next();
        patient.nextLine();

        String DOB = (day + "/" + month + "/" + year);
        newPatient[3] = DOB;

        System.out.println("\nEnter Patient's Gender (Male, Female or Other): ");
        String gender = patient.next();
        newPatient[4] = gender;
        patient.nextLine();
        
        System.out.println("\nEnter Patient's Priority Level (This is determined by patient's previous doctor)");
        System.out.println("1 for Urgent, 2 for Important, 3 for Casual");
        String priority = patient.next();
        newPatient[5] = priority;
        patient.nextLine();
        
        //Appointment Date
        System.out.println("\nLet’s set an appointment date for this new patient");
        System.out.println("Enter Patient’s MONTH for Appointment (2 Digit Value): ");
        String monthApp = patient.next();
        patient.nextLine();
        System.out.println("\nEnter Patient’s DAY for Appointment (2 Digit Value): ");
        String dayApp = patient.next();
        patient.nextLine();
        
        String appointmentDate = ("2021-" + monthApp + "-" + dayApp);
        newPatient[6] = appointmentDate;

        //Appointment Time
        System.out.println("\nLet’s set an appointment time for this new patient");
        System.out.println("Enter Patient’s HOURS for Appointment (2 Digit Value, 24 Hour Clock): ");
        String hours = patient.next();
        patient.nextLine();
        System.out.println("\nEnter Patient’s MINUTES for Appointment (2 Digit Value, 24 Hour Clock): ");
        String minutes = patient.next();
        patient.nextLine();

        String appointmentTime = (hours + ":" + minutes + ":00");
        newPatient[7] = appointmentTime;

        //Asking for Doctor
        String doctor;
        
        System.out.println("\nWhich doctor would the Patient Like?");
        System.out.println("1) Dr.Diana-Prince  2) Dr.Bruce-Wayne  3) Dr.Clark-Kent  4) Dr.Will-Smith");
        System.out.println("Enter Number corresponding to doctor the patient would like: ");
        String num = patient.next();
        
        patient.nextLine();
            
        if(num.equals("1")){
            doctor = "Dr.Diana-Prince";
            newPatient[8] = doctor;
        }    
        else if(num.equals("2")){
            doctor = "Dr.Bruce-Wayne";
            newPatient[8] = doctor;    
        }
        else if(num.equals("3")){
            doctor = "Dr.Clark-Kent";
            newPatient[8] = doctor;
        }    
        else if(num.equals("4")){
            doctor = "Dr.Will-Smith";
            newPatient[8] = doctor;
        }    
        else{
            System.out.println("\nAn invalid value has been entered.");
            doctor = "Dr.Will-Smith";
            newPatient[8] = doctor;
            System.out.println("Your default doctor will be set to " + doctor);
            System.out.println("You may run the program again after the new patient has been entered into the database to change their doctor");
        }
        return newPatient;
    }
    
    //METHOD #3 - USED WHEN USER WANTS TO ADD NEW PATIENT TO DATABASE
    public static int findRows(String fileDirectory) throws FileNotFoundException{
        int numLines = 0;
        
        File file = new File(fileDirectory);
        Scanner readLines = new Scanner(file);
        
        while(readLines.hasNextLine()){
            readLines.nextLine();
            numLines++;
        }
        
        return numLines;
    }
    
    //METHOD #4 - READING ALL DATA INTO 2D ARRAY
    public static String[][] readData(String[][] data, String fileDirectory) throws FileNotFoundException{
        
        File readFile = new File(fileDirectory);
        Scanner read = new Scanner(readFile);
        
        for(int row = 0; row < data.length; row++){
            for(int cols = 0; cols < data[row].length; cols++){
                data[row][cols] = read.next();
            }
        }
        return data;
    }
        
    
    //METHOD #5 - LOCATES INDEX OF PATIENT'S DATA
    public static int searchData(String identifier, String[][] data, int searchIndex){
        int rows = data.length;
        int location = 0;
        
        for(int i = 0; i < rows; i++){
            String arrayValue = data[i][searchIndex];
            
            if(arrayValue.equalsIgnoreCase(identifier)){
                location = i;
                break;
            }
            else{
                location = 500;
            }
        }
        return location;
    }
    
    //METHOD #6 - ASKING USER HOW TO IDENTIFY PATIENT USING NESTED METHODS
    public static int findPatient(String[][] data){
        String nameOrID = "";
        String patientID = "";
        String firstName = "";
        String lastName = "";
        int patientIndex = 0;
        
        Scanner input = new Scanner(System.in);
               
        do{
            System.out.println("\nEnter 1 to identify patient by their Medical ID, Enter 2 to identify patient by Name: ");
            nameOrID = input.next();
            input.nextLine(); 
            
            if(nameOrID.equals("1")){
                System.out.println("\nEnter Patient's Medical ID: ");
                patientID = input.next();
                input.nextLine();
                
                int searchIndex = 0;
                
                patientIndex = externalMethods.searchData(patientID, data, searchIndex);
            }
            else if(nameOrID.equals("2")){
                System.out.println("\nEnter Patient's First Name: ");
                firstName = input.next();
                input.nextLine();
                
                System.out.println("\nEnter Patient's Last Name: ");
                lastName = input.next();
                input.nextLine();
                
                int searchIndex = 2;
                
                patientIndex = externalMethods.searchData(firstName, data, searchIndex);
            }
            else{
                System.out.println("\nInvalid Value Entered. Try Again");
                nameOrID = "0";
            }
            
        }while(!(nameOrID.equals("1")) && !(nameOrID.equals("2")));
    
        return patientIndex;
    }
    
    //METHOD #7 - CHECKING IF IDENTIFIED PATIENT IS CORRECT
    public static String checkIfCorrectPatient(String[] patientData){
        String patientOrNo = "";
        String lastName = patientData[1];
        String firstName = patientData[2];  
        String ID = patientData[0];
        
        Scanner enter = new Scanner(System.in);
        
        System.out.println("\nPatient has been identified.\n");
        System.out.println("Name: " + firstName + " " + lastName);
        System.out.println("Medical ID: " + ID + "\n");
        
        System.out.println("Is this the correct patient? (Enter 1 for Yes, 2 for No): ");
        patientOrNo = enter.next();
        enter.nextLine();
        
        while(!(patientOrNo.equals("1")) && !(patientOrNo.equals("2"))){
            System.out.println("Invalid value entered.");
            
            System.out.println("Is this the correct patient? (Enter 1 for Yes, 2 for No): ");
            patientOrNo = enter.next();
            enter.nextLine();
        }
        
        return patientOrNo;
    }
    
    //METHOD #8 - ASKING FOR USER'S CHOICE
    public static String patientChoice(){
        String choice = "";
        
        Scanner input = new Scanner(System.in);
        
        System.out.println("\nAsk the patient which one of these options they would like to do.\n");
        System.out.println("Option 1: View and Print Next Appointment Slip");
        System.out.println("Option 2: Generate Priority List");
        System.out.println("Option 3: Update Patient Records");
        System.out.println("Option 4: Change Current Doctor\n");
        
        System.out.println("Enter number corresponding to the option the patient chose: ");
        choice = input.next();
        input.nextLine();
        
        return choice;
    }    
    
    //EXTERNAL METHOD #9 - PRINT OUT APPOINTMENT SLIP
    public static void printSlip(String[] patientData){
        System.out.println("-----------------------------------------------------");
	System.out.println("Patient: " + patientData[2] + " " + patientData[1]);
	System.out.println("Next Appointment Date: " + patientData[6]);
	System.out. println("Time: " + patientData[7] + " (24 hour clock)");
	System.out.println("This appointment will be attended by " + patientData[8]);
	System.out.println("-----------------------------------------------------");
    }
    
    //EXTERNAL METHOD #10 - GENERATING PRIORITY LIST OF ALL PATIENT'S, DISPLAYING PATIENT'S SPECIFIC PATIENT
    public static void generatePriority(String[][] data, int patientIndex, int rows, String firstName, String lastName, String medicalID){
        String patientPriority = data[patientIndex][5];
        String[] priority1 = new String[rows];
        String[] priority2 = new String[rows];
        String[] priority3 = new String[rows];

        for(int i = 0; i < rows; i++){
            if(data[i][5].equals("1")){
                priority1[i] =  data[i][1];
            }
            else if(data[i][5].equals("2")){
                priority2[i] =  data[i][1];
            }
            else if(data[i][5].equals("3")){
                priority3[i] = data[i][1];
            }	
        }

        //Printing out all patients based on priority
        System.out.println("\nPriority 1 Patients (Urgent): ");
        for(int a = 0; a < priority1.length; a++){
            if(priority1[a] != null){
                System.out.print(priority1[a] + ", ");
            }
            
        }

        System.out.println("\n\nPriority 2 Patients (Important): ");
        for(int b = 0; b < priority2.length; b++){
            if(priority2[b] != null){
                System.out.print(priority2[b] + ", ");
            }
        }

        System.out.println("\n\nPriority 3 Patients: ");
        for(int c = 0; c < priority3.length; c++){
            if(priority3[c] != null){
                System.out.print(priority3[c] + ", ");
            }
        }

        //Printing out current patient’s priority
        System.out.println("\n\n" + firstName + " " + lastName + " (Medical ID: " + medicalID + ")" + " Priority Level is " + patientPriority);
    }
    
    //EXTERNAL METHOD #11 - CHANGING PATIENT'S DOCTOR
    public static String changeDoctor(String[][] data, int patientIndex){
        String choice = ""; 
        String newDoctor = "";
        
        Scanner enter = new Scanner(System.in);
        
        System.out.println("\nThis patient's current doctor is: " + data[patientIndex][8]);
        System.out.println("Enter Number corresponding to whichever NEW doctor the patient selects: ");
        System.out.println("1) Dr.Diana-Prince  2) Dr.Bruce-Wayne  3) Dr.Clark-Kent  4) Dr.Will-Smith");
        System.out.println("Enter number here: ");
        choice = enter.next();
        enter.nextLine();
        
        if(choice.equals("1")){
            System.out.println("\nThe patient's doctor will be changed from: ");
            System.out.println(data[patientIndex][8] + " to " + "Dr.Diana-Prince");
            newDoctor = "Dr.Diana-Prince";
        }
        else if(choice.equals("2")){
            System.out.println("\nThe patient's doctor will be changed from: ");
            System.out.println(data[patientIndex][8] + " to " + "Dr.Bruce-Wayne");
            newDoctor = "Dr.Bruce-Wayne";
        }
        else if(choice.equals("3")){
            System.out.println("\nThe patient's doctor will be changed from: ");
            System.out.println(data[patientIndex][8] + " to " + "Dr.Clark-Kent");
            newDoctor = "Dr.Clark-Kent";
        }
        else if(choice.equals("4")){
            System.out.println("\nThe patient's doctor will be changed from: ");
            System.out.println(data[patientIndex][8] + " to " + "Dr.Will-Smith");
            newDoctor = "Dr.Will-Smith";
        }
        else{
            System.out.println("\nAn invalid value has been entered, that corresponds to no doctor.");
            System.out.println("The patient's doctor will not change, and will remain as their previous doctor");
            newDoctor = data[patientIndex][8];
        }
        
        return newDoctor;
    }
    
    //EXTERNAL METHOD #12 - UPDATING PATIENT'S RECORDS
    public static String[][] updateRecords(String[][] data, int patientIndex){
        String choice = "";
        String firstName = "";
        String lastName = "";
        String gender = "";
        String appMonth = "";
        String appDay = "";
        String appHour = "";
        String appMins = "";
        
        Scanner input = new Scanner(System.in); 
        
        System.out.println("\n\nWhat Data would the Patient like to change? ");
        System.out.println("1) First Name");
        System.out.println("2) Last Name");
        System.out.println("3) Gender");
        System.out.println("4) Next Appointment Date and Time");
        System.out.println("Enter the corresponding value to the choice the patient wants: ");
        choice = input.next();
        input.nextLine();
        
        //validity loop
        while(!(choice.equals("1")) && !(choice.equals("2")) && !(choice.equals("3")) && !(choice.equals("4"))){
            System.out.println("\nInvalid Value Entered.");
            System.out.println("1) First Name");
            System.out.println("2) Last Name");
            System.out.println("3) Gender");
            System.out.println("4) Next Appointment Date and Time");
            System.out.println("Enter the corresponding value to the choice the patient wants: ");
            choice = input.next();
            input.nextLine();
        }
        
        if(choice.equals("1")){
            System.out.println("\nEnter the Patient's NEW First Name: ");
            firstName = input.next();
            input.nextLine();
            
            data[patientIndex][2] = firstName;
        }
        else if(choice.equals("2")){
            System.out.println("\nEnter the Patient's NEW Last Name: ");
            lastName = input.next();
            input.nextLine();
            
            data[patientIndex][1] = lastName;
        }
        else if(choice.equals("3")){
            System.out.println("\nEnter the Patient's NEW Gender (Male, Female or Other): ");
            gender = input.next();
            input.nextLine();
            
            data[patientIndex][4] = gender;
        }
        else if(choice.equals("4")){
            System.out.println("\nEnter the Patient's next appointment MONTH (2 digit value): ");
            appMonth = input.next();
            input.nextLine();	

            System.out.println("\nEnter the Patient’s Next Appointment DAY (2 digit value): ");
            appDay = input.next();
            input.nextLine();

            System.out.print("\nEnter the Patient’s Next Appointment HOUR (2 digit value): ");
            appHour = input.next();
            input.nextLine();    

            System.out.println("\nEnter the Patient’s Next Appointments MINUTES (2 digit value): ");
            appMins = input.next();
            input.nextLine();

            //Putting appointment time and dates into two strings
            String date = ("2021-" + appMonth + "-" + appDay);
            String time = (appHour + ":" + appMins + ":00");

            data[patientIndex][6] = date;
            data[patientIndex][7] = time;
        }
        return data;
    }

    //FINAL EXTERNAL METHOD #13 - WRITE ALL UPDATED DATA TO THE FILE
    public static void writeToCSVFile(String[][] data, String fileDirectory) throws FileNotFoundException{
        int cols = data[0].length;
        int rows = data.length;
        char delim = ' ';
        
        File writeFile = new File(fileDirectory);
        PrintWriter outputFile = new PrintWriter(writeFile);
        
        for(int i = 0; i < rows; i++){
            String line = "";
            
            for(int j = 0; j < cols; j++){
                line += data[i][j] + delim;
            }
            
            //trimming the last delim.
            line = line.substring(0,(line.length() - 1));
        
            outputFile.print(line);
            
            //entering new line at end of row
            if(i < rows-1){
                outputFile.print("\n");
            }
            
        }
        
        outputFile.close();
    }
    
    //FINAL EXTERNAL METHOD #14 - WRITE NEW PATIENT'S DATA TO THE FILE
    public static void writeNewPatientCSVFile(String[] newPatient, String fileDirectory) throws FileNotFoundException, IOException{
        File writeFile = new File(fileDirectory);
        FileWriter writeNew = new FileWriter(writeFile, true);
        
        writeNew.write("\n");
        
        for(int i = 0; i < 9; i++){
            writeNew.write(newPatient[i] + " "); 
        }
        
        writeNew.close();
    }
} 