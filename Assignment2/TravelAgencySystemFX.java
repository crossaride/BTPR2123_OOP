
package travelagencysystem;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import javafx.animation.PauseTransition;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Duration;


public class TravelAgencySystemFX extends Application {
    
    //Database
    private DatabaseConnection newConnection;
    private Connection connectDB;
    
    private Stage stage, editStage;
    private Scene mainPage, editPage;
    
    // -------------------- Main Page ------------------------
    
    Label lbTour    = new Label("Select Tour:");   //Domestic or Overseas
    Label lbCountry = new Label("Select Country:");
    Label lbState   = new Label("Select State:");
    Label lbSeason  = new Label("Select Season:");
    
    ComboBox tour    = new ComboBox();
    ComboBox country = new ComboBox();
    ComboBox state   = new ComboBox();
    ComboBox season  = new ComboBox();
    
    Button btnCheck = new Button("Check");
    //Button btnEdit  = new Button("Edit");
    
    /*
    // -------------------- Edit Page ------------------------
    
    Label lbSearch    = new Label("Search:"); 
    Label lbCountry2  = new Label("Country:"); 
    Label lbState2    = new Label("State:"); 
    Label lbTourType  = new Label("Tour Type:");  
    Label lbTourState = new Label("Available tours:");  
    Label lbDuration  = new Label("Duration(days):");      
    Label lbSeasonDiv = new Label("Season:"); 
    Label lbTicket    = new Label("Ticket Type:"); 
    Label lbPrice     = new Label("Price");
    
    TextField tfSearch   = new TextField();
    TextField tfCountry2 = new TextField();
    TextField tfState2   = new TextField();
    TextField tfTourType = new TextField(); //locked
    TextField tfPrice    = new TextField();

    ComboBox tourState  = new ComboBox();
    ComboBox duration   = new ComboBox();
    ComboBox ticketType = new ComboBox();
    
    Button btnAdd    = new Button("Add");
    Button btnUpdate = new Button("Update");
    Button btnDelete = new Button("Delete");
    Button btnBack   = new Button("Back");

    TableView tblView = new TableView();
    
    TableColumn<TravelAgency, Integer> column1 = new TableColumn<>("ID");
    TableColumn<TravelAgency, String>  column2 = new TableColumn<>("Country");
    TableColumn<TravelAgency, String>  column3 = new TableColumn<>("State");
    TableColumn<TravelAgency, String>  column4 = new TableColumn<>("Tour type");
    TableColumn<TravelAgency, Integer> column5 = new TableColumn<>("Available tours");
    TableColumn<TravelAgency, Integer> column6 = new TableColumn<>("Duration");
    TableColumn<TravelAgency, String>  column7 = new TableColumn<>("Ticket type");
    TableColumn<TravelAgency, String>  column8 = new TableColumn<>("Season");
    TableColumn<TravelAgency, Integer> column9 = new TableColumn<>("Price");

    */
    // --------------------- Pane ----------------------------
    //BorderPane borderPane = new BorderPane();
    GridPane gridPane     = new GridPane();
    
    HBox hbox  = new HBox();
    VBox vbox  = new VBox();
      

    // -------------------------------------------------------
    
    @Override
    public void start(Stage primaryStage) throws Exception{       
        
        //create tableView
        initialLock();
               
        //Action
        btnCheck.setOnAction(e->show());
        //btnEdit.setOnAction(e->editStage().show());
        
        //Scene
        mainPage = MainPage();
        //editPage = EditPage();
        
        //Stage       
        stage     = primaryStage;
        //editStage = editStage();
        
        stage.setTitle("Travel Agency System");
        stage.setResizable(false);
        stage.setScene(mainPage);
        stage.show();
    }
    
    
    private void getComboBox(String item, String table){
        
        connectDatabase();
        
        boolean overseas = (tour.getSelectionModel().getSelectedItem() == "Overseas");
        boolean domestic = (tour.getSelectionModel().getSelectedItem() == "Domestic");

        String sql  = "SELECT " + item + " FROM " + table + " WHERE type = 'overseas' GROUP BY " + item +";";
        String sql2 = "SELECT " + item + " FROM " + table + " WHERE type = 'domestic' GROUP BY " + item +";";
        //System.out.println(sql);
        try {
            if (overseas == true) {
                Statement stmt = connectDB.createStatement();
                ResultSet result = stmt.executeQuery(sql);

                while (result.next()) {
                    country.getItems().addAll(result.getString(1));
                }
                stmt.close();
                result.close();
                connectDB.close();
                
            } else if (domestic == true) {
                Statement stmt2 = connectDB.createStatement();
                ResultSet result2 = stmt2.executeQuery(sql2);

                while (result2.next()) {
                    country.getItems().addAll(result2.getString(1));
                }
                stmt2.close();
                result2.close();
                connectDB.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }
    }
    
    private void getComboBox2(){ //State
        connectDatabase();
        
        String temp = country.getSelectionModel().getSelectedItem().toString();
        //System.out.println(temp);     
        String sql = "SELECT state FROM oop.agency WHERE country = '" + temp + "' GROUP BY country;";
        //System.out.println(sql);
        
         try {                
                Statement stmt = connectDB.createStatement();
                ResultSet result = stmt.executeQuery(sql);

                while (result.next()) {   
                    state.getItems().addAll(result.getString(1));
                }
                
                stmt.close();
                result.close();
                connectDB.close();
            
        } catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }
    }

    //---------------- Stages -------------------------//
    /*
    private Stage editStage(){
        
        editStage = new Stage();
        
        editStage.setTitle("Edit Page");
        editStage.setX(800); //window position
        editStage.setY(200);
        editStage.initOwner(stage); 
        editStage.initModality(Modality.APPLICATION_MODAL); //Prevent actions to Owner stage;
        editStage.setResizable(false);
        
        editPage = EditPage();
        editStage.setScene(editPage);
        
        return editStage;
    }
    */
    
    //---------------- Scenes -------------------------//
    
    private Scene MainPage(){     

        tour.getItems().addAll("Domestic", "Overseas");
        season.getItems().addAll("Normal", "Peak");
        
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setPadding(new Insets(15,15,15,15));
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        
        gridPane.add(lbTour   , 0, 0);
        gridPane.add(lbCountry, 0, 1);
        gridPane.add(lbState  , 0, 2);
        gridPane.add(lbSeason , 0, 3);
        gridPane.add(tour     , 1, 0);
        gridPane.add(country  , 1, 1);
        gridPane.add(state    , 1, 2);
        gridPane.add(season   , 1, 3);
             
        //hbox.getChildren().addAll(btnCheck, btnEdit);
        hbox.getChildren().addAll(btnCheck);
        hbox.setAlignment(Pos.CENTER);
        hbox.setPadding(new Insets(10,10,10,10));
        hbox.setSpacing(50);
        
        vbox.getChildren().addAll(gridPane, hbox);
        vbox.setAlignment(Pos.CENTER);
        
       //Listener Events
        tour.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            
            boolean checkTour = !tour.getSelectionModel().isEmpty();              

            if (checkTour == true) {
                country.getItems().clear();
                state.getItems().clear();
                getComboBox("country","oop.agency");  
                country.setEditable(true);
                country.setDisable(false);
            }
        });
        
        country.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            
            boolean checkCountry = !country.getSelectionModel().isEmpty();        

            if (checkCountry == true) {
                state.getItems().clear();
                getComboBox2();
                state.setEditable(true);
                state.setDisable(false);
            }
        });
        
        state.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            boolean checkState = !state.getSelectionModel().isEmpty();        

            if (checkState == true) {
                season.setEditable(true);
                season.setDisable(false);
            }
        });       
        
        //Scene
        mainPage = new Scene(vbox, 500, 350);
        return mainPage;
    }
    
    /*
    private Scene EditPage(){
     
        return editPage;
    } 
    */
    
    //---------------- Switch Scene -------------------//
    /*
    public void switchScene(Scene scene){
        stage.setScene(scene);
    }
    
    public void setTransition(Scene scene) {
        PauseTransition transition = new PauseTransition(Duration.seconds(1.3)); //Transition duration

        transition.setOnFinished(e -> switchScene(scene)); //change scene
        transition.playFromStart();
    }
    
    public void setTransition2(){
        PauseTransition transition = new PauseTransition(Duration.seconds(1.5)); //Transition duration

        transition.setOnFinished(e -> editStage.close()); //change scene
        transition.playFromStart();
    }
    */
    
    //---------------- Functions ---------------------//
    
    private void connectDatabase(){
        newConnection = new DatabaseConnection();
        connectDB = newConnection.getConnection();
    }
    
    private void show(){
        
        if(checkEmpty() == true){
            String tourType = tour.getSelectionModel().getSelectedItem().toString();
            String Country = country.getSelectionModel().getSelectedItem().toString();
            String State = state.getSelectionModel().getSelectedItem().toString();
            String Season = season.getSelectionModel().getSelectedItem().toString();

            TravelAgency ta = new TravelAgency(tourType, Country, State, Season);
            alertMSG("Tour Info", ta.toString());
        }else{
            //do nothing;
        }       
    }
    
    private boolean checkEmpty(){
        
        boolean temp;
        
        boolean checkCountry = !country.getSelectionModel().isEmpty();
        boolean checkState   = !state.getSelectionModel().isEmpty();
        boolean checkSeason  = !season.getSelectionModel().isEmpty();
        
        if (checkCountry == false) {
            alertError("Null Exception", "Please select a country!");
            temp = false;
        }else if (checkState == false) {
            alertError("Null Exception", "Please select a state!");
            temp = false;
        }else if (checkSeason == false) {
            alertError("Null Exception", "Please select a season!");
            temp = false;
        }else{
            temp = true;
        }
        return temp;
    }
    
    /*
    private void add(){
        
    }
    
    private void update(){
        
    }
    
    private void delete(){
        
    }
    
    private void search(){
        
    } */
      
    
    private void initialLock(){
        
        country.setEditable(false);
        state.setEditable(false);
        season.setEditable(false);
        
        country.setDisable(true);
        state.setDisable(true);
        season.setDisable(true);
    }
    
    
      
    private void alertMSG(String title, String description) {
        Alert a = new Alert(Alert.AlertType.INFORMATION);
        a.setTitle(title);
        a.setHeaderText(null);
        a.setContentText(description);
        a.showAndWait();
    }
    
    private void alertError(String title, String description) {
        Alert a = new Alert(Alert.AlertType.ERROR);
        a.setTitle(title);
        a.setHeaderText(null);
        a.setContentText(description);
        a.showAndWait();
    }
    
    //---------------- Main --------------------------//
    
    public static void main(String[] args) {
        launch(args);
    }
    
}
