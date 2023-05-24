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
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import threekingdoms.Warriors;
import threekingdoms.WarriorsCamp;

/**
 * FXML Controller class
 *
 * @author user
 */
public class HierachyController implements Initializable {

    @FXML
    private Label ChiefManagementNode;

    @FXML
    private Label ChiefMilitaryNode;

    @FXML
    private Label EmperorNode;

    @FXML
    private VBox ManagementDepartment;

    @FXML
    private VBox MilitaryDepartment;
    @FXML
    private Button backButton;

    @FXML
    void BackToList(ActionEvent event) {
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

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        

        EmperorNode.setText("Emperor\n"+WarriorsCamp.getEmperor().getName());
        ChiefManagementNode.setText("Chief Of Management\n"+WarriorsCamp.getChiefManagement().getName());
        ChiefMilitaryNode.setText("Chief Of Military\n"+WarriorsCamp.getChiefMilitary().getName());
        
        for (Warriors warrior : WarriorsCamp.getGenerals()) {
            Label label = new Label();
            label.setStyle("-fx-font-size: 20;");
            if (warrior.getRole().equals("Department of Management")) {
                label.setText(warrior.getName() + "\n");

                ManagementDepartment.getChildren().add(label);
            } else {
                label.setText(warrior.getName() + "\n");
                MilitaryDepartment.getChildren().add(label);
            }
        }

    }

}
