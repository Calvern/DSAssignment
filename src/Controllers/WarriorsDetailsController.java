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
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import threekingdoms.Warriors;
import threekingdoms.WarriorsCamp;

/**
 * FXML Controller class
 *
 * @author user
 */
public class WarriorsDetailsController {

    @FXML
    private Text WarInfo;

    @FXML
    private Text WarName;

    @FXML
    private Button backbutton;

    @FXML
    private Canvas AttributePentagon;

    public void setPage(String name) {
        Warriors warrior = WarriorsCamp.getGeneral(name);
        WarName.setText(name);
        setUpInfo(warrior);

    }

    public void setUpInfo(Warriors warrior) {
        String info = "Army Type: " + warrior.getArmy_type().toString().toLowerCase()
                + " \nDepartment: " + warrior.getRole()
                + "\nStrength: " + warrior.getStrength()
                + "\nLeadership: " + warrior.getLeadership()
                + "\nIntelligence: " + warrior.getIntelligence()
                + "\nPolitic: " + warrior.getPolitic()
                + "\nHit Point: " + warrior.getHit_point();
        WarInfo.setText(info);

    }

    public void BackToList(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Scenes/NavBar.fxml"));
            Parent root = loader.load();
            NavBarController controller = loader.getController();
            controller.toCharacterPage(event);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(WarriorsDetailsController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
