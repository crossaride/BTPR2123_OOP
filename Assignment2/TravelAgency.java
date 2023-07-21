
package travelagencysystem;

import java.util.*;
import java.sql.*;

public class TravelAgency {
    
    private String country, state, season, tourType, ticket;
    private int id, id2, duration, tourState, price;
    private ArrayList<String> priceList;
    
    private DatabaseConnection newConnection;
    private Connection connectDB;
    
    /**
     * tourState:
     * 1: Joined Tour
     * 2: Private Tour
     * 3: Both 1 + 2
     * 4: Honeymoon Tour
     * 5: Both 2 + 4
     */
    
    protected TravelAgency(){
        //default constructor
    }
    
    protected TravelAgency(String tourType, String country, String state, String season){
        
        this.tourType = tourType;
        this.country  = country;
        this.state    = state;
        this.season   = season;
    }
    
    protected TravelAgency(String tourType, String country, String state, int tourState, 
                           int duration, String ticket ,int price, String season){
        
        this.tourType  = tourType;
        this.country   = country;
        this.state     = state;
        this.tourState = tourState;
        this.duration  = duration;
        this.ticket    = ticket;
        this.price     = price;
        this.season    = season;      
    }
    
    protected TravelAgency(int id, int id2, String tourType, String country, String state, int tourState, 
                           int duration, String ticket ,int price, String season){
        
        this.id        = id;
        this.id2       = id2;
        this.tourType  = tourType;
        this.country   = country;
        this.state     = state;
        this.tourState = tourState;
        this.duration  = duration;
        this.ticket    = ticket;
        this.price     = price;
        this.season    = season;      
    }
    
    //------------------get----------------------------//
    
    protected int getID(){ 
        return id;
    }
    
    protected int getID2(){ 
        return id2;
    }
    
    protected String getCountry(){
        return country;
    }
    
    protected String getState(){
        return state;
    }
    
    protected int getDuration(){
        return duration;
    }
     
    protected int getTourState(){ 
        return tourState;
    }
    
    protected String getTourType(){ //Oversea or Domestic
        return tourType;
    }
    
    protected String getSeason(){
        return season;
    }
    
    protected String getTicketType(){
        return ticket;
    }
    
    protected int getPrice(){
        return price;
    }
      
    
    //------------------set----------------------------// 
    
    protected void setID(int newID){
        id = newID;
    }
    
    protected void setID2(int newID2){
        id2 = newID2;
    }
    
    protected void setCountry(String newCountry){
       country    = newCountry;
    }
    
    protected void setState(String newState){
        state     = newState;
    }
    
    protected void setDuration(int newDuration){
        duration  = newDuration;
    }
     
    protected void setTourState(int newTourState){
        tourState = newTourState;
    }
    
    protected void setTourType(String newTourType){
        tourType  = newTourType;
    }  
    
    protected void setSeason(String newSeason){
        season    = newSeason;
    }
    
    protected void setTicketType(String newTicket){
        ticket    = newTicket;
    }
    
     protected void setPrice(int newPrice){
        price     = newPrice;
    }
         
    
    //-------------------------------------------------//
    
     private void connectDatabase(){
        newConnection = new DatabaseConnection();
        connectDB = newConnection.getConnection();
    }
    
    protected void add(){
        boolean temp = checkRepeat2();
        connectDatabase();
        
        String sql  = "INSERT INTO `oop`.`agency` (country, state, type, tours, duration) VALUES ('" + 
                       country + "','" + state + "','" + tourType + "','" +tourState + "','" + duration +"');";
        //System.out.println(sql);
         
         
        String sql2 = "INSERT INTO `oop`.`priceoption` (type, ticket, country, state, season, price) VALUES ('" + 
                       tourType + "','" + ticket + "','" + country + "','" + state + "','" + season + "','" + price +"');";
        //System.out.println(sql2);
        
        try {
            if (temp == true) {
                Statement stmt = connectDB.createStatement();
                stmt.executeUpdate(sql);
              
            } else {

                Statement stmt = connectDB.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);

                stmt.addBatch(sql);
                stmt.addBatch(sql2);
                connectDB.setAutoCommit(false);

                ResultSet result = stmt.executeQuery("SELECT * FROM oop.agency;");
                result.last();
                stmt.executeBatch();
                connectDB.commit();

                stmt.close();
                result.close();
                connectDB.close();

                System.out.println("Insert Successful!");
            }
        } catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }
    }
    
    protected void update(){
        connectDatabase();
        
        String sql  = 
                      "UPDATE oop.agency SET country = '" + country + "', state = '" + state + "', type = '" + tourType + 
                      "', tours = '" + tourState + "', duration = '" + duration + "' WHERE id = " + id + ";";
        System.out.println(sql);
         
         
        String sql2 = 
                      "UPDATE oop.priceoption SET type = '" + tourType + "', country = '" + country + "', state = '" + 
                       state + "', ticket = '" + ticket + "', season = '" + season + "', price = '" + price + 
                      "' WHERE id = " + id2 + ";";
        System.out.println(sql2);

        try {
            Statement stmt = connectDB.createStatement();
            stmt.executeUpdate(sql);
            stmt.close();
            
            stmt = connectDB.createStatement();
            stmt.executeUpdate(sql2);
        
        } catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        } 
    }
    
    
    protected void delete(){
        connectDatabase();
    
        String sql  = "DELETE FROM oop.agency WHERE country ='" + country + "' && state ='" + state + "';";
        String sql2 = "DELETE FROM oop.priceoption WHERE country ='" + country + "' && state ='" + state + "';";
        
        try{          
            Statement stmt = connectDB.createStatement();
            stmt.executeUpdate(sql);
            stmt.close();
            
            stmt = connectDB.createStatement();
            stmt.executeUpdate(sql2);
            
            System.out.println("Data has been deleted.");
     
        }catch(Exception e){
            e.printStackTrace();
        }   
    }
    
    public String checkTours(){
        connectDatabase();

        String sql = "SELECT tours FROM oop.agency WHERE country ='" + country + "' && state ='" + state + "';";
        //System.out.println(sql);

        try {
            Statement stmt = connectDB.createStatement();
            ResultSet result = stmt.executeQuery(sql);

            while (result.next()) {
                setTourState(Integer.parseInt(result.getString(1)));
            }
            
            stmt.close();
            result.close();
            connectDB.close();

        } catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }

        String temp = "NOT FOUND";
        
        if(getTourState() == 1){
            temp = "Joined Tour only";
        }else if(getTourState() == 2){
            temp = "Private Tour only";
        }else if(getTourState() == 3){
            temp = "Joined Tour & Private Tour";
        }else if(getTourState() == 4){
            temp = "Honeymoon Tour only";
        }else if(getTourState() == 5){
            temp = "Private Tour & Honeymoon Tour";
        }
        
        return temp;
    }
    
    public String checkPrice(){
        
       connectDatabase();
       
       String sql = "SELECT price FROM oop.priceoption WHERE type ='" + tourType + "' && season ='" + season + "';";
       //System.out.println(sql);
     
       priceList  = new ArrayList<>();
       
       try{
           Statement stmt = connectDB.createStatement();
           ResultSet result = stmt.executeQuery(sql);
           
           while (result.next()) {
               priceList.add(result.getString(1));
           }      
           //System.out.println(priceList);
           stmt.close();
           result.close();
           connectDB.close();
           
       }catch(Exception e){
           e.printStackTrace();
           e.getCause();
       }
        
        return "\nAdult: RM" + priceList.get(0) +
               "\nChild With Extra Bed: RM" + priceList.get(1) + 
               "\nChild with no Extra Bed: RM" + priceList.get(2) + 
               "\nInfant: RM" + priceList.get(3); 
    }
    
    public int checkDuration(){
        
        connectDatabase();

        String sql = "SELECT duration FROM oop.agency WHERE country ='" + country + "' && state ='" + state + "';";
        //System.out.println(sql);

        try {
            Statement stmt = connectDB.createStatement();
            ResultSet result = stmt.executeQuery(sql);

            while (result.next()) {
                setDuration(Integer.parseInt(result.getString(1)));
            }
            
            stmt.close();
            result.close();
            connectDB.close();

        } catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }
        
        return getDuration();
    }
    
    
    public boolean checkRepeat(){
        
        boolean temp = false;
        
        connectDatabase();

        String sql = "SELECT count(1) FROM oop.priceoption WHERE type = '" + tourType + 
                     "' && country = '" + country + "' && state ='" + state + "' && ticket = '" +
                     ticket + "' && season = '" + season + "';";       
        //System.out.println(sql);

        try {
            Statement stmt = connectDB.createStatement();
            ResultSet result = stmt.executeQuery(sql);
            
            while (result.next()) {
                if (result.getInt(1) == 1) {
                    temp = true;
                    System.out.println("Result found!");
                }
            }
            
            stmt.close();
            result.close();
            connectDB.close();

        } catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }
        
        
        return temp;
    }
    
    public boolean checkRepeat2(){
        
        boolean temp = false;
        
        connectDatabase();

        String sql = "SELECT count(1) FROM oop.agency WHERE type = '" + tourType + 
                     "' && country = '" + country + "' && state ='" + state + "';";       
        //System.out.println(sql);

        try {
            Statement stmt = connectDB.createStatement();
            ResultSet result = stmt.executeQuery(sql);
            
            while (result.next()) {
                if (result.getInt(1) == 1) {
                    temp = true;
                    System.out.println("Result found!");
                }
            }
            
            stmt.close();
            result.close();
            connectDB.close();

        } catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }
               
        return temp;
    }
    
    @Override
    public String toString(){ //8 things
        return("\nCountry: " + getCountry() +
               "\nState: " + getState() +
               "\nAvailable Tours: " + checkTours() +             
               "\nDuration: " + checkDuration() + " days" +
               "\nSeason: " + getSeason() +
               "\n\n--- Price List ---" +
               "\n" + checkPrice()
               );
    }
}

