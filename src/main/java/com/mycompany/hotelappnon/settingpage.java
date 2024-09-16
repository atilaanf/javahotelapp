/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.hotelappnon;

import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
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
public class settingpage {
            private Scene scene;
    
        
        public settingpage(){
            
         
         BorderPane mainlay= new BorderPane (); 
            
            
      
               Image dbs = new Image(getClass().getResourceAsStream("/dashboard.png"));
               ImageView dbView = new ImageView(dbs);
               Label db= new Label("Dashboard");
        HBox dashboard = new HBox(dbView,db);
          dbView.setFitWidth(20); 
            dbView.setPreserveRatio(true);
            dashboard.setSpacing(5);
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
      
            Image modes = new Image(getClass().getResourceAsStream("/dark-mode.png"));
           ImageView modesimage = new ImageView(modes);
           Label mode= new Label("Mode");  
           HBox Mode = new HBox(modesimage,mode);
            modesimage.setFitWidth(20); 
            modesimage.setPreserveRatio(true);
            Mode.setSpacing(5);
             Mode.getStyleClass().add("hbox");
               
                      Mode.setOnMouseClicked((MouseEvent event) -> {
              App.switchToMode();});
            
            
             Image set = new Image(getClass().getResourceAsStream("/cogwheel.png"));
           ImageView setimage = new ImageView(set);
           Label settings= new Label("Settings");  
         HBox Setting = new HBox(setimage,settings);
         setimage.setFitWidth(20); 
            setimage.setPreserveRatio(true);
               Setting.setSpacing(5);
                 Setting.getStyleClass().add("hbox");
         
           Setting.getStyleClass().add("dbon");
         
        VBox sidebar = new VBox ();
           sidebar.getChildren().addAll(
               dashboard,Hcin, Hcto,Mode,Setting
                
        );  
            sidebar.getStyleClass().add("sidebar");
           // Header
         HBox header = new HBox();
        
        Image headers = new Image(getClass().getResourceAsStream("/hotell.png"));
           ImageView headerimage = new ImageView(headers);
           
          
         headerimage.setFitWidth(20); 
            headerimage.setPreserveRatio(true);
        
        Label welcomeLabel = new Label("Welcome, Alexa!");
        Label reminderLabel = new Label("Don't forget to control the activity that exist.");
        header.getChildren().addAll(headerimage,welcomeLabel, reminderLabel);
        header.getStyleClass().add("header");
        
        
        
        // Main Content
        GridPane mainContent = new GridPane();
        mainContent.setVgap(10);
        mainContent.setHgap(10);


      
     

        // Recent Transactions Section
        VBox recentTransactions = new VBox();
        recentTransactions.getStyleClass().add("recent-transactions");
        recentTransactions.getChildren().addAll(
                new Label("Recent Transaction:"),
                new Label("Viktor Axelsen, Amalie Magelund, ...")
                
        );
        recentTransactions.setPrefSize(400,400);

     
       
        mainContent.add(recentTransactions, 0, 0);
        
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
