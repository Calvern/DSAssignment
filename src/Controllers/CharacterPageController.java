/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package Controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
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
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import threekingdoms.Abilities;
import threekingdoms.Warriors;
import threekingdoms.WarriorsCamp;
import threekingdoms.WarriorsSorter;

/**
 * FXML Controller class
 *
 * @author user
 */
public class CharacterPageController implements Initializable {

    @FXML
    private ScrollPane ScrollList;

    @FXML
    private AnchorPane WarList;

    @FXML
    private VBox characterContainer;

    @FXML
    private Button toHierachyButton;
    @FXML
    private ChoiceBox<Abilities> AttributeSorter;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        AttributeSorter.getItems().addAll(Abilities.values());
        AttributeSorter.setValue(Abilities.STRENGTH);
        AttributeSorter.setOnAction(event -> {
            initiateCharacterList();
        });
        initiateCharacterList();
    }

    public void initiateCharacterList() {
        Abilities selectedOption = AttributeSorter.getValue();
        characterContainer.getChildren().clear();
        int containerheight = WarriorsCamp.getGenerals().size() * 100 + 30;
        WarList.setPrefHeight(containerheight);
        characterContainer.setPrefHeight(containerheight);
        Node[] nodes = new Node[WarriorsCamp.getGenerals().size()];
        Warriors[] warrior = WarriorsSorter.getSortedGenerals(selectedOption);
        for (int i = 0; i < nodes.length; i++) {
            try {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("/Scenes/CharacterCards.fxml"));
                nodes[i] = loader.load();
                CharacterCardsController cardsController = loader.getController();
                final String name = warrior[i].getName();
                cardsController.setContent(name);
                //final int index=i;
                nodes[i].setOnMousePressed(event -> {
                    switchToDetailsScene(name, event);
                });
                characterContainer.getChildren().add(nodes[i]);

            } catch (IOException ex) {
                Logger.getLogger(CharacterPageController.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }

    private void switchToDetailsScene(String name, MouseEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Scenes/WarriorsDetails.fxml"));
            Parent root = loader.load();
            WarriorsDetailsController controller = loader.getController();
            controller.setPage(name);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(CharacterPageController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void toHierachy(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource(("/Scenes/Hierachy.fxml")));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
