
package OOP_Test;

import java.util.*;

public class TestClass {
    
    private static CarManufacturer cm;
    private static ElectricCar ec;
    private static Customer cus;
    private static ChargingStation cs;
    
    private static int price, y, z; 
    private static String name, temp2;
    private static ArrayList<String> temp, ownedCar;
    
    //MAIN
    public static void main(String[] args) {
        
       Scanner input  = new Scanner(System.in);

        try {
                       
            System.out.println("Hello, Welcome to Electric Vehicle (EV) Car Management System!");
            System.out.println("How can we help you?");
            System.out.println("[1] Purchase a car");
            System.out.println("[2] Exit");
            
            cm = new CarManufacturer();
            
         
            if (input.hasNextInt()) {
                System.out.println(cm.toString() +"\n"); //Display Manufacturer + models
                if (input.nextInt() == 1) {
                    
                    System.out.println("Please select a manufacturer:");
                    System.out.println("[1] " + cm.getTeslaManufacturer());
                    System.out.println("[2] " + cm.getGWMManufacturer());
                } else {
                    System.exit(0);
                }
            } else {
                throw new Exception("No Strings allowed!");
            }           
            
            
            if(input.hasNextInt()){
                y = input.nextInt();
            }else{
                throw new Exception("No Strings allowed!");
            }
            
            ec = new ElectricCar();
            System.out.println("Please select a car model:"); //with price
            
            if (y == 1) {
                z = 3;
                System.out.println("[1] " + cm.getTeslaModelS() + " RM " + ec.getSmodel());
                System.out.println("[2] " + cm.getTeslaModelX() + " RM " + ec.getXmodel());
                
            } else if (y == 2) {
                z = 4;
                System.out.println("[1] " + cm.getGWMmodels() + " RM " + ec.getCmodel());
            }
            
            if(input.hasNextInt()){
                y = input.nextInt();
            }else{
                throw new Exception("No Strings allowed!");
            }
        
            if (z == 3) {
                if (y == 1) {
                    //tesla   
                    ec = new ElectricCar(cm.getTeslaManufacturer(), cm.getTeslaModelS());
                    ec.checkPrice();
                    price = ec.getPrice();
                    temp = new ArrayList<>();
                    temp.add(cm.getTeslaManufacturer());
                    temp.add(cm.getTeslaModelS());
                    ownedCar = new ArrayList<>();
                    ownedCar.add(cm.getTeslaModelS());
                } else {
                    ec = new ElectricCar(cm.getTeslaManufacturer(), cm.getTeslaModelX());
                    ec.checkPrice();
                    price = ec.getPrice();
                    temp = new ArrayList<>();
                    temp.add(cm.getTeslaManufacturer());
                    temp.add(cm.getTeslaModelX());
                    ownedCar = new ArrayList<>();
                    ownedCar.add(cm.getTeslaModelX());
                }
            } else if (z == 4) {
                //ora
                ec = new ElectricCar(cm.getGWMManufacturer(), cm.getGWMmodels());
                ec.checkPrice();
                temp = new ArrayList<>();
                temp.add(cm.getGWMManufacturer());
                temp.add(cm.getGWMmodels());
                ownedCar = new ArrayList<>();
                ownedCar.add(cm.getGWMmodels());
            }
                       
            System.out.println("Please enter your name: ");
            name = input.next();

            
            cus = new Customer(temp.get(0),temp.get(1),price,name,ownedCar);
            
            System.out.println("Purchase succeed!");
            System.out.println("What else can we help you?");
            System.out.println("[1] View/Update charging station for electric cars"); //select car -> get current station + list
            System.out.println("[2] Exit");
            
            if(input.nextInt() == 1){
                System.out.println("Which car would you update?");
                System.out.println("[1]" + cus.getownedCars());             
            
            }else if(input.nextInt() == 2){
                System.exit(0);
            }
            ec.setDefault();
            System.out.println("\nCurrent Charging Station location: " + ec.getChargeStation());            
            System.out.println("Please select a charging station: ");
            
            cs = new ChargingStation();
            System.out.println("[1] " + cs.getSenai());
            System.out.println("[2] " + cs.getKotaTinggi());
            
            if(input.hasNextInt()){
                if(input.nextInt() == 1){
                    temp2 = cs.getSenai();
                }else{
                    temp2 = cs.getKotaTinggi();
                }               
            }else{
                throw new Exception("No Strings allowed!");
            }
            
            System.out.println("Update successfully!");
            System.out.println("Your details are as below:\n");

            if(z == 3){
                Tesla t = new Tesla(ec.getManufacturer(),ec.getCarModel(),ec.getPrice());
                ec.setChargeStation(temp2);
                System.out.println(cus.toString() + t.toString());
            }else if(z == 4){
                Ora o = new Ora(ec.getManufacturer(),ec.getCarModel(),ec.getPrice());
                ec.setChargeStation(temp2);
                System.out.println(cus.toString() + o.toString());
            }
            
        } catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }

    
        
    }
}
