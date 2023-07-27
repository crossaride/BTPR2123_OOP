
package OOP_Test;

import java.util.ArrayList;

public class Customer extends ElectricCar{
    
    private String name;
    private ArrayList<String> ownedCars;
    
    
    public Customer(){
        //default constructor
        super();
    }
    
    public Customer(String name, ArrayList<String> ownedCars){
        this.name = name;
        this.ownedCars = ownedCars;
    }
    
    public Customer(String manufacturer, String carModel, int price, String name, ArrayList<String> ownedCars){
        super(manufacturer,carModel,price);
        this.name = name;
        this.ownedCars = ownedCars;
    }
    
    //------------------get----------------------------//
    
    protected String getName(){
        return name;
    }
    
    protected String getownedCars(){
        return ownedCars.get(0);
    }
    
    //------------------set----------------------------//
    
    protected void setName(String newName){
        name = newName;
    }
    
    protected void setownedCars(String newCar){
        ownedCars.add(newCar);
    }
    
    //-------------------------------------------------//
    
    @Override
     public String toString(){
         return( "\nCustomer name: " + getName() +
                 "\nOwned cars: " + getownedCars() 
                );
     }
}
