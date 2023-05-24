/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package PathFinders;

import Graph.Edge;
import Graph.Graph;
import java.util.ArrayList;
import threekingdoms.Abilities;
import threekingdoms.Teams;

/**
 *
 * @author user
 */
public class HamiltonianCycle {
    private static int targetPathSize;
    private static double foodHarvested;

    private static boolean isValidNode(int nodeWithoutFood) {
        return (nodeWithoutFood == -1 || (nodeWithoutFood > 1 && nodeWithoutFood <= 10));
    }

    private static boolean isValidAbility(Abilities ab) {
        return (ab == Abilities.POLITIC || ab == Abilities.INTELLIGENCE);
    }

    public static void FoodHarvester(Graph graph, int nodeWithoutFood, Teams team, Abilities ab) {
        if (!isValidNode(nodeWithoutFood)) {
            System.out.println("Invalid Node");
            return;
        }
        if (!isValidAbility(ab)) {
            System.out.println("Only Politics and Intelligence Abilities are allowed to go harvesting!!!!");
            return;
        }
        boolean[] visited = new boolean[graph.getSize() + 1];
        ArrayList<Integer> path = new ArrayList<>();
        ArrayList<String> paths = new ArrayList<>();
        targetPathSize = (nodeWithoutFood == -1) ? graph.getSize() : graph.getSize() - 1;
        path.add(1);
        visited[1] = true;
        paths = findHamCycles(graph, visited, path, 1, paths, nodeWithoutFood, team, ab);
        if (paths.isEmpty()) {
            targetPathSize = graph.getSize();
            paths = findHamCycles(graph, visited, path, 1, paths, -1, team, ab);
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

    private static ArrayList<String> findHamCycles(Graph graph, boolean[] visited, ArrayList<Integer> path, int pos, ArrayList<String> paths, int nodeWithoutFood, Teams team, Abilities ab) {
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
            foodHarvested = targetPathSize * calculateHarvestedNodes(team, ab);
            return paths;
        }
        for (int i = 2; i <= graph.getSize(); i++) {
            if (isSafe(graph, visited, i, nodeWithoutFood, path)) {
                path.add(i);
                visited[i] = true;
                paths = findHamCycles(graph, visited, path, pos + 1, paths, nodeWithoutFood, team, ab);
                path.remove(path.size() - 1);
                visited[i] = false;
            }
        }
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
