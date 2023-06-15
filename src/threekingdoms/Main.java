/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package threekingdoms;

import Graph.Graph;
import PathFinders.BFS;
import PathFinders.Dijkstra;
import PathFinders.DijkstraTime;
import PathFinders.HamiltonianCycle;
import PathFinders.FoodHarvestingExtra;
import java.util.ArrayList;

import java.util.InputMismatchException;
import java.util.Scanner;



/**
 *
 * @author user
 */
public class Main {

    public static void main(String[] args) throws InterruptedException {
        Scanner sc = new Scanner(System.in);
        ArrayList<Warriors> warriors = JsonReader.getWarriorsList(JsonReader.readJSONFile("/Users/user/NetBeansProjects/ThreeKingdoms/src/Data/Warriors.json"));

        WarriorsCamp.organizeCamp(warriors);
        Graph graph = JsonReader.getGraph(JsonReader.readJSONFile("/Users/user/NetBeansProjects/ThreeKingdoms/src/Data/Map.json"));
        GraphProvider.setGraph(graph);

        int choice = 0;
        while (true) {
            try {
                System.out.println("""
                                                                              Welcome Back, Emperor Sun Quan
                                               |===========================================|========================================|
                                               |============= BASIC FEATURES ==============|============= EXTRA FEATURES ===========|
                                               |===========================================|========================================|
                                               | 1. Wu Kingdom's Hierachy                  |                                        |
                                               | 2. Soldiers Arrangement and Searching     |  9. Strategic Attacking                |
                                               | 3. Borrowing Arrows with Straw Boats      | 10. Enemy Fottress Attack Simulation I |
                                               | 4. Enemy Fottress Attack Simulation       | 11. Secured Encryption with AES        |
                                               | 5. Food Harvesting                        | 12. Throwing Fireballs on Battleships  |
                                               | 6. Caesar Cipher Encryption               | 13. Food Harvesting I                  |
                                               | 7. Red Cliff on Fire                      |                                        |
                                               | 8. Cao Cao at Hua Rong Road               |   |           
                                               |===========================================|========================================|
                                   """);
                System.out.print("Select your choice, or enter 0 to exit: ");
                choice = sc.nextInt();
                if (choice == 0) {
                    break;
                }
                sc.nextLine();
                System.out.println();
                switch (choice) {
                    case 1:
                        WarriorsCamp.showHierachy();
                        break;
                    case 2:
                        WarriorsSorter.soldierArrangement();
                        break;
                    case 3:
                        StrawBoat.ArrowBorrowing();
                        break;
                    case 4:
                        BFS.fottrestAttacker(GraphProvider.getadjList());
                        break;
                    case 5:
                        HamiltonianCycle.FoodHarvester(graph);
                        break;
                    case 6:
                        CaeserCipher.start();
                        break;
                    case 7:
                        ClustersDetector.RedCliffOnFire();
                        break;
                    case 8:
                        MazeEscaping.findEscapePath();
                        break;
                    case 9:
                        Dijkstra.DijkstraPathFinder(graph.getAdjList());
                        break;
                    case 10:
                        DijkstraTime.ShortestTimeCalculator(GraphProvider.getadjList());
                        break;
                    case 11:
                    case 12:
                        RedCliffonFire.RedCliffOnFire();
                        break;
                    case 13:
                        FoodHarvestingExtra.FoodHarvesterI(GraphProvider.getGraph());
                        break;
                    default:
                        throw new IllegalArgumentException();
                }
                System.out.println("");
                System.out.println("Please ENTER to continue your journey...");
                sc.nextLine();
            } catch (IllegalArgumentException | InputMismatchException e) {
                System.out.println("Please enter only within the options given.\n");
                sc.nextLine();

            }
        }
        sc.close();
        System.out.println("\nFarewell, Emperor Sun Quan. May your reign be prosperous and your legacy endure.");

        /*for(Warriors warrior:warriors){
            System.out.println(warrior.getRole());
        }
    
        
         */
        //  launch(args);
    }

    /*  public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/Scenes/LoginPage.fxml"));

        Scene scene = new Scene(root);
        Image icon = new Image("/Assets/GameIcon.jpeg");
        stage.getIcons().add(icon);
        stage.setResizable(false);

        stage.setScene(scene);
        stage.setTitle("Three Kingdoms");
        stage.show();*/
    //}
}

/*public static void attackFottrest() {
        Scanner sc = new Scanner(System.in);
        while (true) {

            try {
                System.out.print("Enter the base camp for enemy base camp: ");
                int basecamp = sc.nextInt();
                sc.nextLine();
                if (!GraphProvider.getadjList().containsKey(basecamp)) {
                    System.out.println("Destination Node not Found");
                    continue;
                }

                BFS.BFSPathFinder(GraphProvider.getadjList(), basecamp);
                break;
            } catch (InputMismatchException e) {
                System.out.println("Please input integers only");
                sc.nextLine();
                
            }

        }

    }

    public static void foodHarvesting() {
        Scanner sc = new Scanner(System.in);
        while (true) {
            try {
                System.out.println("Enter node without food(-1 for none)");
                int node = sc.nextInt();
                sc.nextLine();
                HamiltonianCycle.FoodHarvester(GraphProvider.getGraph(), node, Teams.S_TEAM, Abilities.POLITIC);
                break;
            } catch (InputMismatchException e) {
                System.out.println("Please enter the valid nodes only");
                sc.nextLine();
            }
        }

    }*/
