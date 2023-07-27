
package OOP_Test;


public class Ora extends ElectricCar{
    
    private boolean hasPanaromicSunroof;
    private boolean hasWirelessCharging;
    
    public Ora(){
        super();
    }
    
    public Ora(String manufacturer, String carModel, int price){
        super(manufacturer, carModel, price);
        checkMode();
    }
    
    public Ora(String manufacturer, String carModel, int price, boolean hasPanaromicSunroof, boolean hasWirelessCharging){
        super(manufacturer, carModel, price);
        this.hasPanaromicSunroof = hasPanaromicSunroof;
        this.hasWirelessCharging = hasWirelessCharging;
    }
    
    //------------------get----------------------------//
    
    protected boolean getPanaromicSunroof(){
        return hasPanaromicSunroof;
    }
    
    protected boolean getWirelessCharging(){
        return hasWirelessCharging;
    }
    
    //------------------set----------------------------//
    
    protected void setPanaromicSunroof(boolean newPanaromicSunroof){
        hasPanaromicSunroof = newPanaromicSunroof;
    }
    
    protected void setWirelessCharging(boolean netWirelessCharging){
        hasWirelessCharging = netWirelessCharging;
    }
    
    //-------------------------------------------------//
    
    public void checkMode(){
        if (super.getCarModel() == super.getGWMmodels()) {
            setPanaromicSunroof(true);
            setWirelessCharging(true);
        } else {
            setPanaromicSunroof(false);
            setWirelessCharging(false);
        }
    }   
    
    @Override
     public String toString(){
         return(super.toString() +
                "\nPanaromic Sunroof: " + getPanaromicSunroof() +
                "\nWireless Charging: " + getWirelessCharging()
                );
     }
    
}
