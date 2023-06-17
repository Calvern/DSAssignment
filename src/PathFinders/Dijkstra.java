/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package PathFinders;

import Graph.Edge;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.PriorityQueue;
import java.util.Scanner;
import threekingdoms.GraphProvider;

/**
 *
 * @author user
 */
public class Dijkstra {

    public static void DijkstraPathFinder() throws InterruptedException {
        HashMap<Integer, ArrayList<Edge>> adjList = GraphProvider.getadjList();
        Scanner sc = new Scanner(System.in);
        int dst = -1;
        while (true) {
            try {
                System.out.println("You are now trying to find the best path to enemy base camp while considering the distances between each node. Our base camp is 1.");
                System.out.print("Enter the base camp for the enemy base camp (2-10): ");

                dst = sc.nextInt();
                if (dst < 2 || dst > 10) {
                    throw new IllegalArgumentException();
                }
                System.out.println("");
                break;
            } catch (IllegalArgumentException | InputMismatchException e) {
                System.out.println("Invalid Input!!. Please enter again.\n");
                sc.nextLine();
            }
        }

        // Initialize all distances as INFIINITY from source
        HashMap<Integer, Integer> distances = new HashMap<>();
        for (Integer nodes : adjList.keySet()) {
            distances.put(nodes, Integer.MAX_VALUE);
        }
        //Distance from the source is always 0
        distances.put(1, 0);

        // Initialize -1 for prevNodes 
        HashMap<Integer, Integer> prevNodes = new HashMap<>();
        for (Integer nodes : adjList.keySet()) {
            prevNodes.put(nodes, -1);
        }

        //Creating array for determining whether best distance for the node has been found
        boolean[] visited = new boolean[adjList.size() + 1];

        //Create a priority queue of nodes arranged by their distance from the source node, which is node 1
        PriorityQueue<Integer> queue = new PriorityQueue<>((v1, v2) -> distances.get(v1).compareTo(distances.get(v2)));
        queue.add(1);

        // Process nodes in the queue until all nodes are updated with shortest distance or the dest is reached
        while (!queue.isEmpty()) {
            int current = queue.poll();
            visited[current] = true;
            if (current == dst) {
                break;
            }

            // Update the dist value of the adjacent nodes for the currentNode
            for (Edge edge : adjList.get(current)) {
                int neighbor = edge.getDest();
                if (!visited[neighbor]) {
                    int distanceFromCurrent = distances.get(current) + edge.getWeight();
                    if (distanceFromCurrent < distances.get(neighbor)) {
                        distances.put(neighbor, distanceFromCurrent);
                        prevNodes.put(neighbor, current);
                        queue.remove(neighbor);
                        queue.add(neighbor);
                    }
                }
            }
        }

        // Construct the path from the source to the dest vertex using the prevNode map
        ArrayList<Integer> path = new ArrayList<>();
        int current = dst;
        while (current != -1) {
            path.add(current);
            current = prevNodes.get(current);
        }
        Collections.reverse(path);


        System.out.println("Finding the shortest path to enemy base camp...\n");
        Thread.sleep(1000);
        System.out.print("Shortest Path: ");
        printPath(path);
        System.out.printf("Total Distance Travelled: (%dkm)\n\n", distances.get(dst));
    }

    private static void printPath(ArrayList<Integer> path) {
        String str = "";
        for (int i = 0; i < path.size(); i++) {
            str += path.get(i);
            str += (i == path.size() - 1) ? "" : "->";

        }

        System.out.println(str);
    }
}
