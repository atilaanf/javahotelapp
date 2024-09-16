/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.hotelappnon;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

/**
 *
 * @author atila
 */
public class loginpage {
      static final    String DB_URL= "jdbc:mysql://localhost/hotelapps";
      static final    String USER = "root"; // Your MySQL username
      static final    String PASS = ""; // Your MySQL password
      public TextField userField,passField ;
    private Scene scene;
    
    private boolean validate(String user, String pass){
        
        try{
             Class.forName("com.mysql.cj.jdbc.Driver");

            // Establish connection
            Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);

            // Query to check username and password
            String query = "SELECT * FROM admin WHERE username = ? AND password = ?";
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setString(1, user);
            statement.setString(2, pass);

            ResultSet resultSet = statement.executeQuery();
            
            if(resultSet.next()){
                return true;
            }else {
                return false;
            }

        }catch(ClassNotFoundException| SQLException e){
            e.printStackTrace();
            return false;
        }
        
        
    }
    
    
    
    public loginpage(){
 
      
        
        
// Creating the login form
        GridPane gridPane = new GridPane();
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        
        
       
        
        Label userLabel = new Label("USERNAME");
        userLabel.setStyle("-fx-font-size: 14px;\n" +
                            "    -fx-text-fill: #333;");
        
        TextField userField = new TextField();
        userField.setPromptText("Username");
         userField.setStyle(" -fx-pref-width: 200px;\n" +
                        "    -fx-padding: 10;");

        Label passLabel = new Label("PASSWORD");
         passLabel.setStyle("-fx-font-size: 14px;\n" +
                    "    -fx-text-fill: #333;");
        
        
        PasswordField passField = new PasswordField();
        passField.setStyle(" -fx-pref-width: 200px;\n" +
                    "    -fx-padding: 10;");
        
        passField.setPromptText("Password");

        CheckBox rememberMe = new CheckBox("Remember Me");
        rememberMe.setStyle(" -fx-font-size: 12px;\n" +"-fx-text-fill: #333;");


        Button signInButton = new Button("Sign In");
//        signInButton.getStyleClass().add("sign-in-button");
        signInButton.setStyle(" -fx-background-color: #ff416c;\n" +
                                "    -fx-text-fill: white;\n" +
                                "    -fx-font-size: 14px;\n" +
                                "    -fx-padding: 10.0;"+"-fx-border-radius: 50;"+"-fx-background-radius: 50;"
                                    );
         signInButton.setId("btsi");

       
        

        Hyperlink forgotPassword = new Hyperlink("Forgot Password?");
        
        HBox rf = new HBox();
        rf.getChildren().addAll(rememberMe,forgotPassword);
        rf.setSpacing(110);

        gridPane.add(userLabel, 0, 0);
        gridPane.add(userField, 1, 0);
        gridPane.add(passLabel, 0, 1);
        gridPane.add(passField, 1, 1);
        gridPane.add(rf, 0, 2, 2, 1);
        gridPane.add(signInButton, 0, 3, 2, 1);
        

        VBox loginForm = new VBox(20, gridPane);
//        loginForm.getStyleClass().add("login-form");
           loginForm.setStyle("-fx-background-color: white;\n" +"-fx-padding: 40;");

        // Creating the welcome pane
        VBox welcomePane = new VBox(20);
       
//        welcomePane.getStyleClass().add("welcome-pane");
        welcomePane.setStyle("-fx-background-color: linear-gradient(to bottom right, #ff416c, #ff4b2b);\n" +
"    -fx-padding: 40;\n" +
"    -fx-alignment: center;\n" +
"    -fx-pref-width: 300;"+" -fx-border-radius: 25px");
        
        Label welcomeLabel = new Label("Dont Have Account?");
        welcomeLabel.setStyle("-fx-text-fill: white;\n" +
"    -fx-font-size: 24px;");
        
        Button signUpButton = new Button("Sign Up");
        signUpButton.setId("btsu");


        welcomePane.getChildren().addAll(welcomeLabel, signUpButton);

        // Creating the main layout
        HBox root = new HBox(loginForm, welcomePane);
         HBox.setHgrow(root, Priority.ALWAYS);
//        root.getStyleClass().add("root");
        root.setStyle("-fx-padding: 20;\n" +"-fx-background-color: white;");
         
        
        
        
        //event handling
        signUpButton.setOnAction(e->{
        
        App.switchToScene2();
        });
        
        
        signInButton.setOnAction(e->{
            String username = userField.getText();
            String password = passField.getText();
            
            
            if(validate(username, password)){
                App.switchToHomePage();
            }else{
                showAlert("Sign in failed", "Sign in failed");
            }
        
        });
        
        
         this.scene = new Scene(root, 750, 300);
    this.scene.getStylesheets().add(getClass().getResource("/style.css").toExternalForm());

}
    
    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
     public Scene getScene() {
        return this.scene;
        
    }
    
    
    
     
}
        
        
    
    
    
    

