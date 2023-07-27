
package OOP_Test;

import java.util.*;

public class CarManufacturer {
    
    private static final String TESLA = "Tesla";
    private static final String GWM   = "Great Wall Motors";
    
    private static final String TESLA_MODEL_S = "Tesla Model S";
    private static final String TESLA_MODEL_X = "Tesla Model X";
    private static final String GWMCars       = "Ora Good Cat";
    
    private String manufacturer;
    private String carModel;
    
    private String[] manufacturers = {TESLA, GWM};
    private String[] carModels = {TESLA_MODEL_S, TESLA_MODEL_X, GWMCars};
      
    public CarManufacturer(){
         //default constructor
    }
    
    public CarManufacturer(String manufacturer, String carModel){
         this.manufacturer = manufacturer;
         this.carModel    = carModel;
    }
    
    public CarManufacturer(String[] manufacturers, String[] carModels){
         this.manufacturers = manufacturers;
         this.carModels    = carModels;
    }
    
    //------------------get----------------------------//
    
    protected String getManufacturer(){
        return manufacturer;
    }
    
    protected String getCarModel(){
        return carModel;
    }
    
    protected String[] getManufacturers(){
        return manufacturers;
    }
    
    protected String[] getCarModels(){
        return carModels;
    }
    
    protected String getTeslaManufacturer(){
        return TESLA;
    }
    
    protected String getGWMManufacturer(){
        return GWM;
    }
    
    protected String getTeslaModelS(){
        return (TESLA_MODEL_S);
    }
    
    protected String getTeslaModelX(){
        return (TESLA_MODEL_X);
    }
        
    protected String getTeslaModels(){
        return (TESLA_MODEL_S + ", " + TESLA_MODEL_X);
    }
    
    protected String getGWMmodels(){
        return GWMCars;
    }
    

    //-------------------------------------------------//
     
     @Override
     public String toString(){ 
         return("\nCar Manufacturer: " + getTeslaManufacturer() +
                 
                "\nAvailable models: " + getTeslaModels() +
                "\n" +
                "\nCar Manufacturer: " + getGWMManufacturer() +
                "\nAvailable models: " + getGWMmodels()
                );
     }
}
