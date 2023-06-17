/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package PathFinders;

import Graph.Edge;
import Graph.Graph;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;
import threekingdoms.Abilities;
import threekingdoms.GraphProvider;
import threekingdoms.TeamFormer;
import threekingdoms.Teams;
import threekingdoms.Warriors;
import threekingdoms.WarriorsCamp;

/**
 *
 * @author user
 */
public class FoodHarvestingExtra {

    private static int targetPathSize;
    private static double foodHarvested;

    private static boolean isValidNode(int nodeWithoutFood) {
        return (nodeWithoutFood == -1 || (nodeWithoutFood > 1 && nodeWithoutFood <= 10));
    }

    /*private static boolean isValidAbility(Abilities ab) {
        return (ab == Abilities.POLITIC || ab == Abilities.INTELLIGENCE);
    }*/
    public static void FoodHarvesterI() throws InterruptedException {
        Graph graph=GraphProvider.getGraph();
        foodHarvested=0;
        Scanner sc = new Scanner(System.in);
        int size = graph.getSize();
        int nodeWithoutFood = -1;
        while (true) {
            try {
                System.out.println("You will be assigning 3 Generals in Politics or Intelligence to harvest food at each node of the map (2-10), departing from Sun Wu's camp (Node 1)");
                System.out.println("These are the buffs to the food production according to the type of teams chosen:\nS team in politic = food *2\nA team in politic = food *1.5\nB team in politic = food *1.2\nC team in politic = food * 1\n\nS team in intelligence = food *1.8\nA team in intelligence = food *1.3\nB team in intelligence = food *1\nC team in intelligence = food * 0.8\n\nThe food harvested at each node will be 100 initially\n");
                System.out.print("Enter node that does not have food ( -1 if all nodes have food): ");
                nodeWithoutFood = sc.nextInt();
                if (!isValidNode(nodeWithoutFood)) {
                    throw new IllegalArgumentException();
                }
                System.out.println();
                break;
            } catch (IllegalArgumentException | InputMismatchException e) {
                System.out.println("Invalid Input!! Please enter again.\n");
                sc.nextLine();
            }
        }
        sc.nextLine();
        String warrior1, warrior2, warrior3 = null;
        while (true) {
            try {
                System.out.println("List of Generals");
                int index = 1;
                ArrayList<Warriors> generals = WarriorsCamp.getGenerals();
                for (Warriors general : generals) {
                    System.out.println((index++) + ". " + general);
                }
                System.out.println("Please choose your generals among the list: ");
                System.out.print("Warrior 1: ");
                warrior1 = sc.nextLine();
                System.out.print("Warrior 2: ");
                warrior2 = sc.nextLine();
                System.out.print("Warrior 3: ");
                warrior3 = sc.nextLine();

                if (!WarriorsCamp.hasGeneral(warrior1) || !WarriorsCamp.hasGeneral(warrior2) || !WarriorsCamp.hasGeneral(warrior3) | warrior1.equals(warrior2) || warrior1.equals(warrior3) || warrior2.equals(warrior3)) {
                    throw new IllegalArgumentException();
                }
                break;
            } catch (IllegalArgumentException | InputMismatchException e) {
                System.out.println("Invalid Input! Please enter again.\n");
            }
        }
        System.out.println();
        Abilities ab = null;
        while (true) {
            try {
                System.out.print("1.Politics\n2.Intelligence\nEnter the field of abilities: ");
                int choice = sc.nextInt();
                if (choice == 1) {
                    ab = Abilities.POLITIC;
                } else if (choice == 2) {
                    ab = Abilities.INTELLIGENCE;
                } else {
                    throw new IllegalArgumentException();
                }
                break;
            } catch (IllegalArgumentException | InputMismatchException e) {
                System.out.println("Invalid Input! Please enter again.\n");
                sc.nextLine();
            }
        }
        System.out.println();
        Teams team = TeamFormer.evaluateTeam(new String[]{warrior1, warrior2, warrior3}, ab);
        System.out.println("The team that you have chosen is " + team.toString());
        System.out.println();
        boolean[] visited = new boolean[size + 1];
        ArrayList<Integer> path = new ArrayList<>();
        ArrayList<String> paths = new ArrayList<>();
        targetPathSize = (nodeWithoutFood == -1) ? size : size - 1;
        path.add(1);
        visited[1] = true;
        System.out.println("Harvesting Food...\n");
        Thread.sleep(1000);
        paths = findHamCycles(graph, visited, path, 1, paths, nodeWithoutFood, team, ab, false);
        if (paths.isEmpty()) {
            targetPathSize = size;
            paths = findHamCycles(graph, visited, path, 1, paths, -1, team, ab, true);
            if (paths.isEmpty()) {
                System.out.println("No Paths Found!");
            } else {
                System.out.println("The Node Without Food " + nodeWithoutFood + " has to be travelled in order to travel all nodes with food");
                System.out.println("Possible paths:");
                for (String cycles : paths) {

                    System.out.println(cycles);
                }
                System.out.println("Food Harvested: " + foodHarvested);
            }
        } else {
            System.out.println("Possible paths:");
            for (String cycles : paths) {

                System.out.println(cycles);
            }
            System.out.println("Food Harvested: " + foodHarvested);
        }
    }

    private static ArrayList<String> findHamCycles(Graph graph, boolean[] visited, ArrayList<Integer> path, int pos, ArrayList<String> paths, int nodeWithoutFood, Teams team, Abilities ab, boolean secAttempt) {
        if (pos == targetPathSize) {
            for (Edge edge : graph.getAdjList().get(path.get(path.size() - 1))) {
                if (edge.getDest() == 1) {
                    path.add(1);
                    String hamipath = "";
                    for (int i = 0; i < path.size(); i++) {
                        hamipath += path.get(i);
                        hamipath += (i == path.size() - 1) ? "" : "-->";
                    }
                    paths.add(hamipath);
                    path.remove(path.size() - 1);
                }
            }
            return paths;
        }
        for (int i = 2; i <= graph.getSize(); i++) {
            if (isSafe(graph, visited, i, nodeWithoutFood, path)) {
                path.add(i);
                visited[i] = true;
                paths = findHamCycles(graph, visited, path, pos + 1, paths, nodeWithoutFood, team, ab, secAttempt);
                path.remove(path.size() - 1);
                visited[i] = false;
            }
        }
        foodHarvested = (secAttempt) ? (targetPathSize - 2) * calculateHarvestedNodes(team, ab) : (targetPathSize-1 )* calculateHarvestedNodes(team, ab);
        return paths;
    }

    private static boolean isSafe(Graph graph, boolean[] visited, int curNode, int nodeWithoutFood, ArrayList<Integer> path) {
        if (!visited[curNode] && graph.hasEdge(path.get(path.size() - 1), curNode) && curNode != nodeWithoutFood) {
            return true;
        }
        return false;

    }

    private static double calculateHarvestedNodes(Teams team, Abilities ab) {
        switch (team) {
            case S_TEAM:
                return (ab == Abilities.POLITIC) ? 100 * 2.0
                        : 100 * 1.8;
            case A_TEAM:
                return (ab == Abilities.POLITIC) ? 100 * 1.5
                        : 100 * 1.3;
            case B_TEAM:
                return (ab == Abilities.POLITIC) ? 100 * 1.2
                        : 100 * 1.0;
            case C_TEAM:
                return (ab == Abilities.POLITIC) ? 100 * 1.0
                        : 100 * 0.8;
            default:
                return 0;
        }
    }

}
