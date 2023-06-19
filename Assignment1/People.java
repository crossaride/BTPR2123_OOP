
package budgetcalculator;


public class People {
    
    private int age, income;
    
    
    public People(){
        //default constructor
    }
    
    public People(int age, int income){
        this.age    = age;
        this.income = income;
    }
    
    //------------------get----------------------------//
    
     public int getAge(){
        return age;
    }
    
    public int getIncome(){
        return income;
    }
    
    //------------------set----------------------------//
    
     public void setAge(int newAge){
        age = newAge;
    }
    
    public void setIncome(int newIncome){
        income = newIncome;
    }
    
   //-------------------------------------------------//
    
    @Override
    public String toString(){
        return("\nAge: " + getAge() +
               "\nIncome: RM " + getIncome()
               );
    }
}



