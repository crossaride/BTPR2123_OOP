
package OOP_Test;

import java.util.*;


public class ElectricCar extends CarManufacturer{
    
    private static final int SMODEL = 237000;
    private static final int XMODEL = 489000;
    private static final int CMODEL = 169800;
    
    private int price;
    
    private static ChargingStation chargingstation = new ChargingStation();
    
    public ElectricCar(){
        //default constructor
        super();
    }
    
    public ElectricCar(String manufacturer, String carModel){
        super(manufacturer,carModel);
    }
    
    public ElectricCar(String manufacturer, String carModel, int price){
        super(manufacturer,carModel);
        this.price = price;   
        setDefault();
        checkPrice();
    }
       
    
    //------------------get----------------------------//
    
    protected int getPrice(){
        return price;
    }
    
    protected String getChargeStation(){
        return chargingstation.getLocation();
    }
    
    protected int getSmodel(){
        return SMODEL;
    }
    
    protected int getXmodel(){
        return XMODEL;
    }
    
    protected int getCmodel(){
        return CMODEL;
    }
    
    //------------------set----------------------------//
    
    protected void setPrice(int newPrice){
        price = newPrice;
    }
    
    protected void setChargeStation(String newChargeStation){
        
        chargingstation.setLocation(newChargeStation);
    }
    
    //-------------------------------------------------//
    
    protected void checkPrice(){
       if(super.getCarModel() == super.getTeslaModelS()){
           price = SMODEL;
       }else if(super.getCarModel() == super.getTeslaModelX()){
           price = XMODEL;
       }else if(super.getCarModel() == super.getGWMmodels()){
           price = CMODEL;
       }else{
           price = 0;
       }
    }
    
    protected void setDefault(){
        
        if(chargingstation.getLocation() == null){
          chargingstation.setLocation(chargingstation.getSkudai());  
        }
        //chargingstation.setLocation(chargingstation.getSkudai());
        
        if(super.getCarModel() == super.getTeslaModelS()){
           chargingstation.setCapacity(103);          
       }else if(super.getCarModel() == super.getTeslaModelX()){
           chargingstation.setCapacity(100);
       }else if(super.getCarModel() == super.getGWMmodels()){
           chargingstation.setCapacity(107);
       }else{
           chargingstation.setCapacity(0);
       }
    }
    
    
    @Override
     public String toString(){

         return("\nCar Manufacturer: " + super.getManufacturer() +
                "\nCar Model: " + super.getCarModel() +
                "\nPrice: RM " + getPrice() + 
                chargingstation.toString()
                );
     }
}
