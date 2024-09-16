
package com.mycompany.hotelappnon;




import static com.mycompany.hotelappnon.homepage.DB_URL;
import static com.mycompany.hotelappnon.homepage.PASS;
import static com.mycompany.hotelappnon.homepage.USER;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;


public class checkoutpage {
    private Scene scene;
     ObservableList<tablecheckout> datas;
    static final    String DB_URL= "jdbc:mysql://localhost/hotelapps";
      static final    String USER = "root"; // Your MySQL username
      static final    String PASS = ""; // Your MySQL password
     
    public void selectData(){
            
          datas = FXCollections.observableArrayList();


          try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT NIK,RoomNo, GuestName, CONCAT('0', PhoneNo) AS PhoneNoWithZero FROM checkin")) {

            // Process the result set and populate data
            while (rs.next()) {
               Long NIK= rs.getLong("NIK");
                String guestName = rs.getString("GuestName");
                int roomNo = rs.getInt("RoomNo");
                Long hpNo = rs.getLong("PhoneNoWithZero");
                datas.add(new tablecheckout(NIK, guestName,roomNo,hpNo));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
      
        }
       public int deleteData(Long NIK) {
    int res = 0;
    try (
        Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
        Statement stmt = conn.createStatement()
    ) {
        res = stmt.executeUpdate("DELETE FROM checkin WHERE NIK='" +NIK+ "'");
    } catch (SQLException e) {
        e.printStackTrace();
        
    }
    return res;
}
    public checkoutpage(){
              
           BorderPane mainlay= new BorderPane (); 
            
            
               Image dbs = new Image(getClass().getResourceAsStream("/dashboard.png"));
               ImageView dbView = new ImageView(dbs);
               Label db= new Label("Dashboard");
              
        HBox dashboard = new HBox(dbView,db);
          dbView.setFitWidth(20); 
            dbView.setPreserveRatio(true);
            dashboard.setSpacing(5);
            db.getStyleClass().add("hbox");
            dashboard.setOnMouseClicked((MouseEvent event)->{
            App.switchToHomePage();
            });
            
            
          Image cin = new Image(getClass().getResourceAsStream("/calendar.png"));
           ImageView ctimage = new ImageView(cin);
           Label checkin= new Label("Checkin");  
         HBox Hcin = new HBox(ctimage,checkin);
         ctimage.setFitWidth(20); 
            ctimage.setPreserveRatio(true);
                 Hcin.setSpacing(5);
          
          Hcin.setOnMouseClicked((MouseEvent event) -> {
              App.switchToCheckInPage();
});

        
           Image cto = new Image(getClass().getResourceAsStream("/check-out.png"));
           ImageView ctoimage = new ImageView(cto);
           Label checkout= new Label("Checkout");  
         HBox Hcto = new HBox(ctoimage,checkout);
         ctoimage.setFitWidth(20); 
            ctoimage.setPreserveRatio(true);
               Hcto.setSpacing(5);
               Hcto.getStyleClass().add("hbox");
               Hcto.getStyleClass().add("dbon");
      
//            Image modes = new Image(getClass().getResourceAsStream("/dark-mode.png"));
//           ImageView modesimage = new ImageView(modes);
//           Label mode= new Label("Mode");  
//           HBox Mode = new HBox(modesimage,mode);
//            modesimage.setFitWidth(20); 
//            modesimage.setPreserveRatio(true);
//            Mode.setSpacing(5);
//             Mode.getStyleClass().add("hbox");
//              Mode.setOnMouseClicked((MouseEvent event) -> {
//              App.switchToMode();});
               
//            
//             Image set = new Image(getClass().getResourceAsStream("/cogwheel.png"));
//           ImageView setimage = new ImageView(set);
//           Label settings= new Label("Settings");  
//         HBox Setting = new HBox(setimage,settings);
//         setimage.setFitWidth(20); 
//            setimage.setPreserveRatio(true);
//               Setting.setSpacing(5);
//                 Setting.getStyleClass().add("hbox");
//                       Setting.setOnMouseClicked((MouseEvent event) -> {
//              App.switchToSetting();
//});
//      
               
         
        VBox sidebar = new VBox ();
           sidebar.getChildren().addAll(
               dashboard,Hcin, Hcto
                
        );  
            sidebar.getStyleClass().add("sidebar");
           // Header
         HBox header = new HBox();
        
        Image headers = new Image(getClass().getResourceAsStream("/hotell.png"));
           ImageView headerimage = new ImageView(headers);
           
          
         headerimage.setFitWidth(20); 
            headerimage.setPreserveRatio(true);
        
        Label welcomeLabel = new Label("Welcome!  ");
        Label reminderLabel = new Label("Don't forget to control the activity that exist.");
        header.getChildren().addAll(headerimage,welcomeLabel, reminderLabel);
        header.getStyleClass().add("header");
        
        
         selectData();
         // Create a TableView
        TableView<tablecheckout> tableView = new TableView<>();

        
         // Define table columns
                TableColumn<tablecheckout, Number> NIKCol = new TableColumn<>("NIK");
         NIKCol.setCellValueFactory(cellData -> cellData.getValue().NIKProperty());

         TableColumn<tablecheckout, String> guestNameCol = new TableColumn<>("Guest Name");
         guestNameCol.setCellValueFactory(cellData -> cellData.getValue().guestNameProperty());

         TableColumn<tablecheckout, Number> roomNoCol = new TableColumn<>("Room No");
         roomNoCol.setCellValueFactory(cellData -> cellData.getValue().roomNoProperty());

         TableColumn<tablecheckout, Number> hpNoCol = new TableColumn<>("Phone No");
         hpNoCol.setCellValueFactory(cellData -> cellData.getValue().hpNoProperty());

         tableView.getColumns().addAll(NIKCol, guestNameCol, roomNoCol, hpNoCol);

             tableView.setItems(datas); // Bind data to TableView
      
       
        
        
        //tombol Checkout
        Button ctobutton = new Button("CheckOut");
         ctobutton.setId("cobtn");
         
         ctobutton.setOnAction(e->{
         
         // Get selected item
    tablecheckout selectedItem = tableView.getSelectionModel().getSelectedItem();
    
    if (selectedItem != null) {
       
        showAlert("Checkout","Checkout!");
        
          // Get the NIK value
        Long NIKValue = selectedItem.NIKProperty().get();
        
        // Delete data using the actual NIK value
        deleteData(NIKValue);
        
        // Remove selected item from ObservableList
        datas.remove(selectedItem);
        
    }
         else
         showAlert("Checkout","Checkout Gagal!");
         });
         
         
         
        
        
        
        
        
        
        
        // Main Content
        GridPane mainContent = new GridPane();
        mainContent.setVgap(10);
        mainContent.setHgap(15);
        mainContent.add(tableView, 0, 0);
        
        mainContent.add(ctobutton, 1, 0);


   
        
         mainlay.setLeft(sidebar);
         mainlay.setCenter(mainContent);
         mainlay.setTop(header);
        
        
           this.scene = new Scene(mainlay,  800, 600);
           this.scene.getStylesheets().add(getClass().getResource("/stylehome.css").toExternalForm());
    
    
    
    
         }
                 
                 private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
     public Scene getscene(){
    
        return this.scene;
    
    }
    
    
    
}

