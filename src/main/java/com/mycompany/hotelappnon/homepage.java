/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.hotelappnon;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
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

/**
 *
 * @author atila
 */
public class homepage {
    
        private Scene scene;
        ObservableList<dashboarddatamodel> datas;
        
        
      static final    String DB_URL= "jdbc:mysql://localhost/hotelapps";
      static final    String USER = "root"; // Your MySQL username
      static final    String PASS = ""; // Your MySQL password
   
        
     

      public void selectData(){
            
          datas = FXCollections.observableArrayList();


          try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT RoomNo, GuestName FROM checkin")) {

            // Process the result set and populate data
            while (rs.next()) {
                String roomNo = rs.getString("RoomNo");
                String guestName = rs.getString("GuestName");
                datas.add(new dashboarddatamodel(roomNo, guestName));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
      
        }
        
        public homepage(){
            
         
         BorderPane mainlay= new BorderPane (); 
            
            
      
               Image dbs = new Image(getClass().getResourceAsStream("/dashboard.png"));
               ImageView dbView = new ImageView(dbs);
               Label db= new Label("Dashboard");
        HBox dashboard = new HBox(dbView,db);
          dbView.setFitWidth(20); 
            dbView.setPreserveRatio(true);
            dashboard.setSpacing(5);
            dashboard.getStyleClass().add("dbon");
            
            
          Image cin = new Image(getClass().getResourceAsStream("/calendar.png"));
           ImageView ctimage = new ImageView(cin);
           Label checkin= new Label("Checkin");  
         HBox Hcin = new HBox(ctimage,checkin);
         ctimage.setFitWidth(20); 
            ctimage.setPreserveRatio(true);
                 Hcin.setSpacing(5);
          Hcin.getStyleClass().add("hbox");
          
          
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
               
               
                  Hcto.setOnMouseClicked((MouseEvent event)->{
            App.switchToCheckOutPage();
            });
      
//            Image modes = new Image(getClass().getResourceAsStream("/dark-mode.png"));
//           ImageView modesimage = new ImageView(modes);
//           Label mode= new Label("Mode");  
//           HBox Mode = new HBox(modesimage,mode);
//            modesimage.setFitWidth(20); 
//            modesimage.setPreserveRatio(true);
//            Mode.setSpacing(5);
//             Mode.getStyleClass().add("hbox");
//               
//             Mode.setOnMouseClicked((MouseEvent event) -> {
//              App.switchToMode();
//});
//            
//             Image set = new Image(getClass().getResourceAsStream("/cogwheel.png"));
//           ImageView setimage = new ImageView(set);
//           Label settings= new Label("Settings");  
//         HBox Setting = new HBox(setimage,settings);
//         setimage.setFitWidth(20); 
//            setimage.setPreserveRatio(true);
//               Setting.setSpacing(5);
//                 Setting.getStyleClass().add("hbox");
//          Setting.setOnMouseClicked((MouseEvent event) -> {
//              App.switchToSetting();
//});
         
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


        // Revenue Overview Section
       
        Label pendapatan = new Label("Pendapatan Bulan Ini: ");
       
        Label totalpendapatan = new Label ("Rp: 2000.0000");

     
        
         HBox revenueOverview = new HBox(pendapatan,totalpendapatan);
         revenueOverview.setId("ro");
        
     

        // Recent Transactions Section
        
        selectData();
         // Create a TableView
        TableView<dashboarddatamodel> tableView = new TableView<>();

        
         // Define table columns
  TableColumn<dashboarddatamodel, String> guestNameCol = new TableColumn<>("GuestName");
    guestNameCol.setCellValueFactory(cellData -> cellData.getValue().guestNameProperty());
        
      TableColumn<dashboarddatamodel, String> roomNoCol = new TableColumn<>("RoomNo");
    roomNoCol.setCellValueFactory(cellData -> cellData.getValue().roomNumberProperty());

    tableView.getColumns().addAll(guestNameCol,roomNoCol);
    tableView.setItems(datas); // Bind data to TableView
      
       
        
        Label recent=new Label ("On going Transaction");

     
        VBox recenttransaction= new VBox ();
        recenttransaction.getChildren().addAll(recent,tableView);
       
     
        mainContent.add(revenueOverview, 0, 0);
        
        mainContent.add(recenttransaction, 0, 1);
        
         mainlay.setLeft(sidebar);
         mainlay.setCenter(mainContent);
         mainlay.setTop(header);
        
        
           this.scene = new Scene(mainlay,  800, 600);
           this.scene.getStylesheets().add(getClass().getResource("/stylehome.css").toExternalForm());
          



}
        
     

          public Scene getScene() {
        return this.scene;
        
    }
    
    
}

