/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package threekingdoms;

import Graph.Graph;
import javafx.scene.paint.Color;
import java.util.ArrayList;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 *
 * @author user
 */
public class Main extends Application {

    public static void main(String[] args) {
        ArrayList<Warriors> warriors = JsonReader.getWarriorsList(JsonReader.readJSONFile("/Users/user/NetBeansProjects/ThreeKingdoms/src/Data/Warriors.json"));

        WarriorsCamp.organizeCamp(warriors);
        Graph graph = JsonReader.getGraph(JsonReader.readJSONFile("/Users/user/NetBeansProjects/ThreeKingdoms/src/Data/Map.json"));
        ArrayList<ArrayList<Warriors>> warrior = TeamFormer.getTeam(Teams.S_TEAM, Abilities.POLITIC);
        System.out.println(warrior);
        launch(args);
    }

    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/Scenes/LoginPage.fxml"));

        Scene scene = new Scene(root);
        Image icon = new Image("/Assets/GameIcon.jpeg");
        stage.getIcons().add(icon);
        stage.setResizable(false);
       
        stage.setScene(scene);
        stage.setTitle("Three Kingdoms");
        stage.show();
    }
}
