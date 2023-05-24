/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package Controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.text.Text;


/**
 * FXML Controller class
 *
 * @author user
 */
public class CharacterCardsController  {
  @FXML
    private Button warButton;

    @FXML
    private Text warName;

    public void setContent(String name){
        this.warName.setText(name);
    }
}
