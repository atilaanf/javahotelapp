package com.mycompany.hotelappnon;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;



public class App extends Application {

      private static Stage primaryStage;
    
   @Override
    public void start(Stage primaryStage) {
        App.primaryStage = primaryStage;
        primaryStage.setTitle("Hotel App");
        switchToScene1();
        primaryStage.show();
         Image titleImage = new Image(getClass().getResourceAsStream("/logo.jpg"));
        primaryStage.getIcons().add(titleImage);
     
    }
    
    //switch to login page
    public static void switchToScene1() {
        loginpage scene1 = new loginpage();
        Scene scene = scene1.getScene();
        primaryStage.setScene(scene);
    }
    
     public static void switchToScene2() {
        siguppage scene2 = new siguppage();
        Scene scene = scene2.getscene();
        primaryStage.setScene(scene);
    }
     
    public static void switchToHomePage(){
        homepage Scene3= new homepage();
        Scene scene = Scene3.getScene();
        primaryStage.setScene(scene);

        
    
    }
     public static void switchToCheckInPage(){
        checkingpage Scene4= new checkingpage();
        Scene scene = Scene4.getscene();
        primaryStage.setScene(scene);

        
    
    }
       public static void switchToCheckOutPage(){
        checkoutpage Scene5= new checkoutpage();
        Scene scene = Scene5.getscene();
        primaryStage.setScene(scene);

        
    
    }
       public static void switchToMode(){
        modepage Scene6= new modepage();
        Scene scene = Scene6.getScene();
        primaryStage.setScene(scene);

        
    
    }
       public static void switchToSetting(){
        settingpage Scene7= new settingpage();
        Scene scene = Scene7.getScene();
        primaryStage.setScene(scene);

        
    
    }
     
     
     

   

    public static void main(String[] args) {
        launch(args);
    }
}
