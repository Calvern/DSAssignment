/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package Controllers;

import java.net.URL;
import java.util.Arrays;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import threekingdoms.Abilities;
import threekingdoms.Warriors;
import threekingdoms.WarriorsSorter;

/**
 * FXML Controller class
 *
 * @author user
 */
public class SearchPageController implements Initializable {
    
    @FXML
    private Button searchGeneral;
    
    @FXML
    private TextField attributeValue;
    
    @FXML
    private Button backButton;
    
    @FXML
    private ChoiceBox<Abilities> generalAttribute;
    
    @FXML
    private Button searchTeam;
    @FXML
    private Button startSearch;
    
    @FXML
    private Label generalsList;
    
    @FXML
    private Label invalidWarning;
    
    @FXML
    public void searchForGeneral(ActionEvent event) {
        Abilities selectedOption = generalAttribute.getValue();
        generalsList.setText("");
        try {
            int target = Integer.parseInt(attributeValue.getText());
            Warriors[] generals = WarriorsSorter.search(selectedOption, target);
            System.out.println(Arrays.toString(generals));
            generalsList.setVisible(true);
            generalAttribute.setVisible(false);
            attributeValue.setVisible(false);
            startSearch.setVisible(false);
            if (generals != null) {
                String str = "";
                for (int i = 0; i < generals.length; i++) {
                    str += generals[i].getName() + "\n";
                }
                generalsList.setText(str);
            } else {
                generalsList.setText("No general found");
            }
        } catch (NumberFormatException e) {
            invalidWarning.setText("Please input integer only");
            invalidWarning.setVisible(true);
        }
        
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        generalAttribute.getItems().addAll(Abilities.values());
        searchTeam.setVisible(true);
        searchGeneral.setVisible(true);
    }
    
    public void back(ActionEvent event) {
        generalAttribute.setVisible(false);
        backButton.setVisible(false);
        attributeValue.setVisible(false);
        startSearch.setVisible(false);
        generalsList.setVisible(false);
        invalidWarning.setVisible(false);
        searchGeneral.setVisible(true);
        searchTeam.setVisible(true);
    }
    
    public void searchGeneral(ActionEvent event) {
        backButton.setVisible(true);
        generalAttribute.setVisible(true);
        attributeValue.setVisible(true);
        startSearch.setVisible(true);
        searchGeneral.setVisible(false);
        searchTeam.setVisible(false);
    }
    
}
