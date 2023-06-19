 
package budgetcalculator;

import java.text.*;
import java.util.*;

public class BudgetCalculator {

    //Textcolor
    public static final String REDTEXT    = "\u001B[31m";
    public static final String BLUETEXT   = "\u001B[34m";
    public static final String RESETCOLOR = "\033[0m";
    
    
    private static int maritialStat = 0, dependentStat = 0, carOwnership = 0, age = 0, income = 0;
    static User uwu;
    
    public static void main(String[] args) {
        
        Scanner input  = new Scanner(System.in);
        
        try {  
           
            System.out.println("Hello, Welcome to BudgetCalculator!");
            
            //Age
            System.out.print("\nPlease enter your " + BLUETEXT + "Age" + RESETCOLOR + ": ");           
            if(input.hasNextInt()){
                age = input.nextInt(); 
                
                if(age == 0){                  
                    System.out.println("\n" + REDTEXT + "INVALID age!");
                    System.out.println("Ending program...");
                    System.exit(0);
                }else if(age < 18){
                    System.out.println("\n" + REDTEXT + "You are too young to be fully employed!");
                    System.exit(0);
                }else if(age > 65){
                    System.out.println("\n" + REDTEXT + "You have surpassed the retirement age!");
                    System.exit(0);
                }
            }else{
                throw new Exception("No Strings allowed!");
            }
            
            //Income
            System.out.print("Please enter your " + BLUETEXT + "Monthly Income" + RESETCOLOR + ": RM ");
            if(input.hasNextInt()){
                income = input.nextInt();
                
                if(income == 0){                  
                    System.out.println("\n" + REDTEXT + "You have no income...");
                    System.out.println("There is no need for budget calculator.");
                    System.exit(0);
                }else if(income < 800){
                    System.out.println("\n" + REDTEXT + "You are receiving far below minumum wage!");
                    System.exit(0);
                }
            }else{
                throw new Exception("No Strings allowed!");
            }
            
            //Maritial Status
            if (age < 60) {
                System.out.println("What is your " + BLUETEXT + "Maritial Status" + RESETCOLOR + "?");
                System.out.println(BLUETEXT + "[1] " + RESETCOLOR + "Single");
                System.out.println(BLUETEXT + "[2] " + RESETCOLOR + "Married");
                System.out.println(BLUETEXT + "[3] " + RESETCOLOR + "Divorced/Widowed");
                System.out.println(REDTEXT  + "[0] " + RESETCOLOR + "Exit");
                System.out.print("Answer: ");
                if (input.hasNextInt()) {
                    maritialStat = input.nextInt();

                    if (maritialStat == 0) {
                        System.exit(0);
                    } else if (maritialStat > 3) {
                        System.out.println("\n" + REDTEXT + "Please enter from the option above!");
                        System.exit(0);
                    }
                } else {
                    throw new Exception("No Strings allowed!");
                }
            } else {
                System.out.println("What is your " + BLUETEXT + "Maritial Status" + RESETCOLOR + "?");
                System.out.println(BLUETEXT + "[1] " + RESETCOLOR + "Single");
                System.out.println(BLUETEXT + "[2] " + RESETCOLOR + "Married");
                System.out.println(REDTEXT  + "[0] " + RESETCOLOR + "Exit");
                System.out.print("Answer: ");
                if (input.hasNextInt()) {
                    maritialStat = input.nextInt();

                    if (maritialStat == 0) {
                        System.exit(0);
                    } else if (maritialStat > 2) {
                        System.out.println("\n" + REDTEXT + "Please enter from the option above!");
                        System.exit(0);
                    }
                } else {
                    throw new Exception("No Strings allowed!");
                }
            }

            //Dependent Status
            if(maritialStat != 1 && age < 60){
                System.out.println("What is your " + BLUETEXT + "Dependent Status" + RESETCOLOR + "?" );
                System.out.println(BLUETEXT + "[1] " + RESETCOLOR + "No child");
                System.out.println(BLUETEXT + "[2] " + RESETCOLOR + "1 child");
                System.out.println(BLUETEXT + "[3] " + RESETCOLOR + "2 children");
                System.out.println(REDTEXT  + "[0] " + RESETCOLOR + "Exit");
                System.out.print("Answer: ");
                if (input.hasNextInt()) {
                    dependentStat = input.nextInt();

                    if (dependentStat == 0) {
                        System.exit(0);
                    } else if (dependentStat > 3) {
                        System.out.println("\n" + REDTEXT + "Please enter from the option above!");
                        System.exit(0);
                    }
                } else {
                    throw new Exception("No Strings allowed!");
                }
            }
            
            //Car Ownership Status
            if (maritialStat < 2 && age < 60) {
                System.out.println("Do you own a " + BLUETEXT + "car" + RESETCOLOR + "?");
                System.out.println(BLUETEXT + "[1] " + RESETCOLOR + "No");
                System.out.println(BLUETEXT + "[2] " + RESETCOLOR + "Yes");
                System.out.println(REDTEXT  + "[0] " + RESETCOLOR + "Exit");
                System.out.print("Answer: ");
                if (input.hasNextInt()) {
                    carOwnership = input.nextInt();

                    if (carOwnership == 0) {
                        System.exit(0);
                    } else if (carOwnership > 2) {
                        System.out.println("\n" + REDTEXT + "Please enter from the option above!");
                        System.exit(0);
                    }
                } else {
                    throw new Exception("No Strings allowed!");

                }
            }
            //System.out.println("");
            uwu = new User(age, income, maritialStat, dependentStat, carOwnership);           
            
        }catch (Exception e) {
            System.out.println("");
            e.printStackTrace();
            //System.out.println(REDTEXT + "WARNING: " + RESETCOLOR +"Please enter" + REDTEXT + " Integers " + RESETCOLOR + "only!");
            System.exit(0);
        }
        
        System.out.println(uwu.toString());
    }
    
}
