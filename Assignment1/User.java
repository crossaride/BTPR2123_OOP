
package budgetcalculator;

import java.text.*;
import java.util.*;

public class User extends People{
    
    //Textcolor
    public static final String REDTEXT    = "\u001B[31m";
    public static final String BLUETEXT   = "\u001B[34m";
    public static final String RESETCOLOR = "\033[0m";
    
    //Rates
    public static final double SAVES = 0.1;
    public static final double WANTS = 0.25;
    public static final double NEEDS = 0.45;
    
    //Expenses
    private static final int SINGLE_PTU  = 1760;
    private static final int SINGLE_CO   = 2290;
    
    private static final int MARRIED     = 4110;
    private static final int MARRIED_1C  = 5360;
    private static final int MARRIED_2C  = 6100;
    
    private static final int SParent_1C  = 4200;
    private static final int SParent_2C  = 4940;
    
    private static final int OLD_SINGLE  = 2330;
    private static final int OLD_MARRIED = 3020;
       
    //variables
    private int maritialStat, dependentStat, carOwnership;
    private int st_savings, mt_savings, lt_savings, want, need;
    
    
    
    public User(){
        super();
        //default constructor
    }
    
    public User(int age, int income, int maritialStat, int dependentStat, int carOwnership){
        super(age, income);
        this.maritialStat    = maritialStat;
        this.dependentStat   = dependentStat;
        this.carOwnership    = carOwnership;
        
        budgetAllocation();
        
    }
    
    //------------------get----------------------------//
    
    public String getMarried(){
        if(maritialStat == 1){
            return ("Single");
        }else if(maritialStat == 2){
            return ("Married");
        }else{
            return ("Divorced/Widowed");
        }
    }
    
    public String getIndependent(){
        if(dependentStat == 1){
            return ("No child");
        }else if(dependentStat == 2){
            return ("1 child");
        }else if(dependentStat == 3){
            return ("2 children");
        }else{
            return ("-");
        }
    }
    
    public String getCar(){
        if(carOwnership == 1){
            return ("None");
        }else if(carOwnership == 2){
            return ("Yes");
        }else{
            return ("-");
        }
    }
    
    public int getShortTerm(){
        return st_savings;
    }
    
    public int getMediumTerm(){
        return mt_savings;
    }
    
    public int getLongTerm(){
        return lt_savings;
    }
    
    public int getWants(){
        return want;
    }
    
    public int getNeeds(){
        return need;
    }
    
    //------------------set----------------------------//
    
    public void setMarried(int newMaritialStat){
        maritialStat = newMaritialStat;
    }
    
     public void setIndependent(int newDependentStat){
        dependentStat = newDependentStat;
    }
     
     public void setCar(int newCarOwnership){
        carOwnership = newCarOwnership;
    }
     
    public void setShortTerm(int newShortTerm){
        st_savings = newShortTerm;
    }
    
    public void setMediumTerm(int newMediumTerm){
        mt_savings = newMediumTerm;
    }
    
    public void setLongTerm(int newLongTerm){
        lt_savings = newLongTerm;
    }
    
    public void setWants(int newWants){
        want = newWants;
    }
    
    public void setNeeds(int newNeeds){
        need = newNeeds;
    }
    
    
    //-------------------------------------------------//
    
    
    public void budgetAllocation(){
        
        double temp = super.getIncome()*SAVES;
        
        setShortTerm ((int)temp);
        setMediumTerm((int)temp);
        setLongTerm  ((int)temp);
        
        temp = super.getIncome()*WANTS;
        setWants((int)temp);
        
        temp = super.getIncome()*NEEDS;
        setNeeds((int)temp);    
    }
    
    
    public int calculateBudget(){      
        
        int temp = 0;

        if (super.getAge() < 60) { //young

            if (maritialStat == 1) { //single
                if (carOwnership == 1) { //no car
                    temp = SINGLE_PTU;
                } else {
                    temp = SINGLE_CO;
                }

            } else if (maritialStat == 2) {
                if (dependentStat == 1) { //no child
                    temp = MARRIED;
                } else if (dependentStat == 2) { //1 child
                    temp = MARRIED_1C;
                } else {                         //2 children 
                    temp = MARRIED_2C;
                }

            } else if (maritialStat == 3) { //divorced or widowed               
                if (dependentStat == 2) {
                    temp = SParent_1C;
                } else {
                    temp = SParent_2C;
                }
            }
        } else {                                 //old
            if (maritialStat == 1) {
                temp = OLD_SINGLE;
            } else {
                temp = OLD_MARRIED;
            }
        }
        return temp;
    }
    
    public String compareNecessities(){
        
        int temp = calculateBudget();
               
        if((want + need) > temp){
            return (REDTEXT + "You have exceeded your budget.");
        }else{
            return (BLUETEXT + "Your expenses is within your budget.");
        }
    }
    
    
    @Override
    public String toString(){
        return( super.toString() +
               "\nMarital Status: " + getMarried() +
               "\nDependent Status: " + getIndependent() +
               "\nCar Ownership Status: " + getCar() +
               "\nSuggested expenses: " + BLUETEXT + "RM " + calculateBudget() +
               "\n" +
               "\nYour budget allocation is as below: " +
               "\n" +
               "\nShort-term savings:  RM " + getShortTerm() +
               "\nMedium-term savings: RM " + getMediumTerm() +
               "\nLong-term savings:   RM " + getLongTerm() +
               "\nWants: RM " + getWants() +
               "\nNeeds: RM " + getNeeds() +
               "\n" + compareNecessities() 
               );
    }
}
