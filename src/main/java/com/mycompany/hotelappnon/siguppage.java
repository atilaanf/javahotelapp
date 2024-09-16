package com.mycompany.hotelappnon;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

/**
 *
 * @author atila
 */
public class siguppage {
      static final    String DB_URL= "jdbc:mysql://localhost/hotelapps";
      static final    String USER = "root"; // Your MySQL username
      static final    String PASS = ""; // Your MySQL password
    private Scene scene;
   public TextField userField,passField ;
   
    public int insertData(String user, String pass) {
    int res = 0;
    try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
         Statement stmt = conn.createStatement()) {

    // Corrected SQL insert statement with proper column-value mapping
        String sql = "INSERT INTO admin (userName, password) VALUES (?, ?)";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, user);
            pstmt.setString(2, pass);
        
            res = pstmt.executeUpdate();
        }
    } catch (SQLException e) {
        e.printStackTrace();
       
    }

    // Clear text fields after insertion
//    this.userField.setText("");
//    this.passField.setText("");
    
    
    return res;
}
    public siguppage() {

        // Creating the sign-up form
        GridPane gridPane = new GridPane();
        gridPane.setHgap(10);
        gridPane.setVgap(10);

        Label userLabel = new Label("USERNAME");
        userLabel.setStyle("-fx-font-size: 14px; -fx-text-fill: #333;");

        TextField userField = new TextField();
        userField.setPromptText("Username");
        userField.setStyle(" -fx-pref-width: 200px; -fx-padding: 10;");
       

        Label passLabel = new Label("PASSWORD");
        passLabel.setStyle("-fx-font-size: 14px; -fx-text-fill: #333;");

        PasswordField passField = new PasswordField();
        passField.setPromptText("Password");
        passField.setStyle(" -fx-pref-width: 200px; -fx-padding: 10;");

        Label repassLabel = new Label("Re-PASSWORD");
        repassLabel.setStyle("-fx-font-size: 14px; -fx-text-fill: #333;");

        PasswordField repassField = new PasswordField();
        repassField.setPromptText("Re-enter your Password");
        repassField.setStyle(" -fx-pref-width: 200px; -fx-padding: 10;");

        CheckBox rememberMe = new CheckBox("Remember Me");
        rememberMe.setStyle(" -fx-font-size: 12px; -fx-text-fill: #333;");

        Button signUpButton = new Button("Sign Up");
        signUpButton.setStyle(" -fx-background-color: #ff416c; -fx-text-fill: white; -fx-font-size: 14px; -fx-padding: 10.0; -fx-border-radius: 50; -fx-background-radius: 50; -fx-background-color: linear-gradient(to bottom right, #2E3192, #1BFFFF);");

        Hyperlink forgotPassword = new Hyperlink("Forgot Password?");

        HBox rf = new HBox(rememberMe, forgotPassword);
        rf.setSpacing(110);

        gridPane.add(userLabel, 0, 0);
        gridPane.add(userField, 1, 0);
        gridPane.add(passLabel, 0, 1);
        gridPane.add(passField, 1, 1);
        gridPane.add(repassLabel, 0, 2);
        gridPane.add(repassField, 1, 2);
        gridPane.add(rf, 0, 3, 2, 1);
        gridPane.add(signUpButton, 0, 4, 2, 1);

        VBox signUpForm = new VBox(20, gridPane);
        signUpForm.setStyle("-fx-background-color: white; -fx-padding: 40;");

        // Creating the welcome pane
        VBox welcomePane = new VBox(20);
        welcomePane.setStyle("-fx-background-color: linear-gradient(to bottom right, #2E3192, #1BFFFF); -fx-padding: 40; -fx-alignment: center; -fx-pref-width: 300; -fx-border-radius: 25px");

        Label welcomeLabel = new Label("Already Have Account?");
        welcomeLabel.setStyle("-fx-text-fill: white; -fx-font-size: 24px;");

        Button signInButton = new Button("Sign In");
         signInButton.setId("btsi");
            signUpButton.setId("btsu");


        welcomePane.getChildren().addAll(welcomeLabel, signInButton);

        // Creating the main layout
        HBox root = new HBox(signUpForm, welcomePane);
        root.setStyle("-fx-padding: 20; -fx-background-color: white;");
        HBox.setHgrow(root, Priority.ALWAYS);

        // Sign Up button event handling
        signUpButton.setOnAction(e -> {
            String username = userField.getText();
            String password = passField.getText();
            String rePassword = repassField.getText();

            if (username.isEmpty() || password.isEmpty() || rePassword.isEmpty()) {
                showAlert("Error", "All fields are required.");
            } else if (!password.equals(rePassword)) {
                showAlert("Error", "Passwords do not match.");
            } else {
               insertData(username, password);
                 showAlert("Succsesfull", "Your data has been inserted.");
            }
            
            
        });

        // Sign In button event handling
        signInButton.setOnAction(e -> App.switchToScene1());

        this.scene = new Scene(root, 750, 300);
        this.scene.getStylesheets().add(getClass().getResource("/style.css").toExternalForm());
    }

    public Scene getscene() {
        return this.scene;
    }

//    private void signUpAdmin(String username, String password) {
//      
//
//        
//
//        
//        
//        try (Connection conn = DriverManager.getConnection(url, dbUsername, dbPassword);
//             PreparedStatement stmt = conn.prepareStatement(insertQuery)) {
//
//            stmt.setString(1, username);
//            stmt.setString(2, password); // Note: Use hashing for real-world applications
//
//            int rowsInserted = stmt.executeUpdate();
//            if (rowsInserted > 0) {
//                showAlert("Success", "Admin registered successfully!");
//                 String insertQuery = "INSERT INTO admin (userName, password) VALUES (?, ?) ";
//            }
//
//        } catch (SQLException ex) {
//            ex.printStackTrace();
//            showAlert("Error", "Database error: " + ex.getMessage());
//        }
//        
//       
//    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}