/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package PathFinders;

import Graph.Edge;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

/**
 *
 * @author user
 */
public class BFS {

    public static void fottrestAttacker(HashMap<Integer, ArrayList<Edge>> adjList) {
        
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.print("Enter the base camp for the enemy base camp: ");
            try {
                int dst = sc.nextInt();
                if (!adjList.containsKey(dst)) {
                    throw new IllegalArgumentException();
                }
                System.out.println();
                ArrayList<ArrayList<Integer>> allPaths = BFS(adjList, dst);
                if (allPaths.isEmpty()) {
                    System.out.println("No Paths are found from node 1 to base camp");
                    break;
                }
                ArrayList<ArrayList<Integer>> bestPaths = BFS.findBestPath(allPaths);
                System.out.println("All possible paths:");
                BFS.printPaths(allPaths);
                System.out.println("\nBest Paths:");
                BFS.printPaths(bestPaths);
                break;
            } catch (IllegalArgumentException | InputMismatchException e) {
                System.out.println("Invalid Input!! Please enter again. \n");
                sc.nextLine();
            }

        }
    }

    //Implementing BFS as basic feature, assuming all paths are the same without considering their respective weight(distance)
    private static ArrayList<ArrayList<Integer>> BFS(HashMap<Integer, ArrayList<Edge>> adjList, int dst) {
        Queue<ArrayList<Integer>> queue = new LinkedList<>();
        ArrayList<ArrayList<Integer>> result = new ArrayList<>();
        boolean[] visited = new boolean[adjList.size() + 1];
        queue.add(new ArrayList<>(Arrays.asList(1)));
        while (!queue.isEmpty()) {
            ArrayList<Integer> path = queue.poll();
            int lastNode = path.get(path.size() - 1);
            visited[lastNode] = true;
            if (lastNode == dst) {
                result.add(path);
            } else {
                ArrayList<Integer> neighbors = new ArrayList<>();
                for (Edge adjacent : adjList.get(lastNode)) {
                    neighbors.add(adjacent.getDest());
                }
                for (Integer neighbor : neighbors) {
                    if (!visited[neighbor]) {
                        ArrayList<Integer> list = new ArrayList<>(path);
                        list.add(neighbor);
                        queue.add(list);
                    }
                }
            }
        }
        return result;
    }

    private static ArrayList<ArrayList<Integer>> findBestPath(ArrayList<ArrayList<Integer>> allPaths) {
        ArrayList<ArrayList<Integer>> bestPaths = new ArrayList<>();
        int minDistance = Integer.MAX_VALUE;
        for (ArrayList<Integer> path : allPaths) {
            int distance = path.size() - 1;
            if (distance < minDistance) {
                bestPaths.clear();
                bestPaths.add(path);
                minDistance = distance;
            } else if (distance == minDistance) {
                bestPaths.add(path);
            }
        }
        return bestPaths;
    }

    private static void printPath(ArrayList<Integer> path) {
        String str = "";
        for (int i = 0; i < path.size(); i++) {
            str += path.get(i);
            str += (i == path.size() - 1) ? "" : "->";
        }
        System.out.println(str);
    }

    private static void printPaths(ArrayList<ArrayList<Integer>> paths) {
        for (ArrayList<Integer> path : paths) {
            printPath(path);
        }
    }

}
