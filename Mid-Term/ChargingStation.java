
package OOP_Test;


public class ChargingStation{
    
    private static final String SENAI       = "Senai";
    private static final String SKUDAI      = "Skudai";
    private static final String KOTA_TINGGI = "Kota Tinggi";
    
    private String location;
    private double capacity;
    
    public ChargingStation(){
         //default constructor
    }
    
    public ChargingStation(String location, double capacity){
        this.location = location;
        this.capacity = capacity;
    }
    
    //------------------get----------------------------//
    
    protected String getLocation(){
        return location;
    }
    
    protected double getCapacity(){
        return capacity;
    }
    
    protected String getSenai(){
        return SENAI;
    }
    
    protected String getSkudai(){
        return SKUDAI;
    }
    
    protected String getKotaTinggi(){
        return KOTA_TINGGI;
    }
    
    
    //------------------set----------------------------//
    
    protected void setLocation(String newLocation){
        location = newLocation;
    }
    
     protected void setCapacity(double newCapacity){
        capacity = newCapacity;
    }    
    
    //-------------------------------------------------//
        
     
     @Override
     public String toString(){
         return("\nCharging station Location: " + getLocation() +
                "\nBattery Capacity: " + getCapacity() + "kWh"
                );
     }
}
