
package OOP_Test;

public class Tesla extends ElectricCar{
    
    private boolean isAutoPilotEnabled;
    private boolean isFullSelfDrivingEnabled;
    private boolean isTeslaTheatreEnabled;
            
    public Tesla(){
        super();
    }   
    
    public Tesla(String manufacturer, String carModel, int price){
        super(manufacturer,carModel,price);
        checkMode();
    }

    public Tesla(String manufacturer, String carModel, int price, boolean isAutoPilotEnabled, boolean isFullSelfDrivingEnabled, boolean isTeslaTheatreEnabled){
        super(manufacturer,carModel,price);
        this.isAutoPilotEnabled       = isAutoPilotEnabled;
        this.isFullSelfDrivingEnabled = isFullSelfDrivingEnabled;
        this.isTeslaTheatreEnabled    = isTeslaTheatreEnabled;
    }
    
    //------------------get----------------------------//
    
    protected boolean getAutoPilot(){
        return isAutoPilotEnabled;
    }
    
    protected boolean getFullSelfDriving(){
        return isFullSelfDrivingEnabled;
    }
    
    protected boolean getTeslaTheatre(){
        return isTeslaTheatreEnabled;
    }
    
    
    //------------------set----------------------------//
    
    protected void setAutoPilot(boolean newAutoPilot){
        isAutoPilotEnabled = newAutoPilot;
    }
    
    protected void setFullSelfDriving(boolean newFullSelfDriving){
        isFullSelfDrivingEnabled = newFullSelfDriving;
    }
    
    protected void setTeslaTheatre(boolean newTeslaTheatre){
        isTeslaTheatreEnabled = newTeslaTheatre;
    }
    
    //-------------------------------------------------//
    
    public void checkMode(){
        if(super.getCarModel() == super.getTeslaModelS()){
           setAutoPilot(true);
           setFullSelfDriving(true);
           setTeslaTheatre(true);
            
       }else if(super.getCarModel() == super.getTeslaModelX()){
           setAutoPilot(false);
           setFullSelfDriving(false);
           setTeslaTheatre(true);
       }else{
           setAutoPilot(false);
           setFullSelfDriving(false);
           setTeslaTheatre(false);
       }
    }
    
    @Override
    public String toString(){
        return(super.toString() +
                "\nAutoPilot Mode: " + getAutoPilot() +
                "\nFull Self-Driving Mode: " + getFullSelfDriving() +
                "\nTesla Theatre: " + getTeslaTheatre() 
                );
    }
    
    
}
