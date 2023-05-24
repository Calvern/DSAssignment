/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package Controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.StackPane;

/**
 * FXML Controller class
 *
 * @author user
 */
public class NavBarController implements Initializable {
    
    @FXML
    private ToggleButton AttackButton;

    @FXML
    private ToggleButton BattleShipsButton;

    @FXML
    private ToggleButton CharacterDetailsButton;

    @FXML
    private ToggleButton CharacterSearchButton;

    @FXML
    private ToggleButton FoodHarvestButton;

    @FXML
    private ToggleButton LetterButton;

    @FXML
    private ToggleButton MainPageButton;

    @FXML
    private ToggleButton MazeButton;

    @FXML
    private ToggleButton StrawBoatButton;

    @FXML
    private StackPane pageContainer;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            Parent root=FXMLLoader.load(getClass().getResource("/Scenes/MainMenu.fxml"));
            
            pageContainer.getChildren().setAll(root);
        } catch (IOException ex) {
            Logger.getLogger(NavBarController.class.getName()).log(Level.SEVERE, null, ex);
        }
    } 
    public void toMainPage(ActionEvent event) throws IOException{
        switchScene("/Scenes/MainMenu.fxml");
    }
    public void toCharacterPage(ActionEvent event) throws IOException{
        switchScene("/Scenes/CharacterPage.fxml");
    }
    
    public void switchScene(String fxml) throws IOException{
        Parent root= FXMLLoader.load(getClass().getResource(fxml));
        pageContainer.getChildren().removeAll();
        pageContainer.getChildren().setAll(root);
    }
    
}
