/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package Controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.animation.FadeTransition;
import javafx.animation.Interpolator;
import javafx.animation.ScaleTransition;
import javafx.animation.SequentialTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author user
 */
public class LoginPageController {

    @FXML
    Label WelcomeLabel;
    @FXML
    Label invalidPassword;
    @FXML
    PasswordField passwordField;
    
    @FXML
    Button LoginButton;

    private final String password = "1";

    public void LogIn(ActionEvent event) throws IOException {
        String text = passwordField.getText();
        if (!text.equals(password)) {
            invalidPassword.setText("ERRORRR!!");

        } else {
            Parent root = FXMLLoader.load(getClass().getResource("/Scenes/MainPage.fxml"));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            ScaleTransition transitionToMain = new ScaleTransition(Duration.seconds(2.5));
            transitionToMain.setFromX(1.0);
            transitionToMain.setFromY(1.0);
            transitionToMain.setToX(3.0);
            transitionToMain.setToY(3.0);
            transitionToMain.setInterpolator(Interpolator.EASE_BOTH);
            
            FadeTransition fadeOutTransition=new FadeTransition(Duration.seconds(0.2));
            fadeOutTransition.setFromValue(1.0);
            fadeOutTransition.setToValue(0.0);
            WelcomeLabel.setOpacity(0.0);
            passwordField.setOpacity(0.0);
            LoginButton.setOpacity(0.0);
            invalidPassword.setOpacity(0.0);
            SequentialTransition transition = new SequentialTransition(transitionToMain,fadeOutTransition);
            transition.setOnFinished(e-> {
                stage.setScene(scene);
            });
           transition.setNode(stage.getScene().getRoot());
           transition.play();

        }

    }

}
