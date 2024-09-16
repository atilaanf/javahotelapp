/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.hotelappnon;

import static com.mycompany.hotelappnon.siguppage.DB_URL;
import static com.mycompany.hotelappnon.siguppage.PASS;
import static com.mycompany.hotelappnon.siguppage.USER;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
/**
 *
 * @author atila
 */
public class checkingpage {
    private Scene scene;
   
      static final    String DB_URL= "jdbc:mysql://localhost/hotelapps";
      static final    String USER = "root"; // Your MySQL username
      static final    String PASS = ""; // Your MySQL password
      
      public int insertData(String nik, String guestName, String roomNo, String phoneNo) {
    int res = 0;
    try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
         Statement stmt = conn.createStatement()) {

    // Corrected SQL insert statement with proper column-value mapping
        String sql = "INSERT INTO checkin (NIK, GuestName, RoomNo, PhoneNo) VALUES (?, ?, ?, ?);";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, nik);
            pstmt.setString(2, guestName);
            pstmt.setString(3, roomNo);
            pstmt.setString(4, phoneNo);
        
            res = pstmt.executeUpdate();
        }
    } catch (SQLException e) {
        e.printStackTrace();
       
    }
      return res;
      }
      
      
      
      
    public checkingpage(){
              
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
          
         
          Hcin.getStyleClass().add("dbon");
        
           Image cto = new Image(getClass().getResourceAsStream("/check-out.png"));
           ImageView ctoimage = new ImageView(cto);
           Label checkout= new Label("Checkout");  
         HBox Hcto = new HBox(ctoimage,checkout);
         ctoimage.setFitWidth(20); 
            ctoimage.setPreserveRatio(true);
               Hcto.setSpacing(5);
               Hcto.getStyleClass().add("hbox");
               
                 Hcto.setOnMouseClicked((MouseEvent event)->{
            App.switchToCheckOutPage();
            });
//      
//            Image modes = new Image(getClass().getResourceAsStream("/dark-mode.png"));
//           ImageView modesimage = new ImageView(modes);
//           Label mode= new Label("Mode");  
//           HBox Mode = new HBox(modesimage,mode);
//            modesimage.setFitWidth(20); 
//            modesimage.setPreserveRatio(true);
//            Mode.setSpacing(5);
//             Mode.getStyleClass().add("hbox");
//                Mode.setOnMouseClicked((MouseEvent event) -> {
//                    
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
        
        
        
        
        // Main Content
        GridPane mainContent = new GridPane();
        mainContent.setVgap(10);
        mainContent.setHgap(10);
       

        
        Label NIKLabel = new Label ("NIK:");
        Label NameLabel = new Label ("Name:");
        Label RoomLabel = new Label ("RoomNo:");
        Label HpLabel = new Label ("HpNo:");
        TextField NIKField = new TextField ();
         TextField NameField = new TextField();
          TextField RoomField = new TextField ();
           TextField HpNoField = new TextField ();
           Button CheckInButton = new Button("Check In");
           CheckInButton.setId("cibtn");
           
           
           CheckInButton.setOnAction(e -> {
            String NIK= NIKField.getText();
            String name= NameField.getText();
            String room = RoomField.getText();
            String hp = HpNoField.getText();
            
            

            if (NIK.isEmpty() || name.isEmpty() || room.isEmpty()|| hp.isEmpty()) {
                showAlert("Error", "All fields are required.");
            } else {
               insertData(NIK, name, room, hp);
                 showAlert("Succsesfull", "Your data has been inserted.");
            }
            
            
        });
           
           mainContent.add(NIKLabel, 0, 0);
           mainContent.add(NameLabel, 0, 1);
           mainContent.add(RoomLabel, 0, 2);
           mainContent.add(HpLabel, 0, 3);
           mainContent.add(NIKField, 1, 0);
           mainContent.add(NameField, 1, 1);
           mainContent.add(RoomField, 1, 2);
           mainContent.add(HpNoField, 1, 3);
           mainContent.add(CheckInButton, 1, 4);


   
        
         mainlay.setLeft(sidebar);
         mainlay.setCenter(mainContent);
         mainlay.setTop(header);
        
        
           this.scene = new Scene(mainlay,  800, 600);
           this.scene.getStylesheets().add(getClass().getResource("/stylehome.css").toExternalForm());
    
    
    
    
    }
     public Scene getscene(){
    
        return this.scene;
    
    }
    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
    
    
}
