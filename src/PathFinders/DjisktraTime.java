/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package PathFinders;

import Graph.Edge;
import Graph.EdgeType;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.PriorityQueue;
import threekingdoms.ArmyTypes;
import threekingdoms.WarriorsCamp;

/**
 *
 * @author user
 */
public class DjisktraTime {

    private static final double CAVALRY_SPEED = 2.0;
    private static final double ARCHER_SPEED = 1.0;
    private static final double INFANTRY_SPEED = 1.0;

    public static void ShortestTimeCalculator(HashMap<Integer, ArrayList<Edge>> adjList,  int destination, String generalName) {
        if (destination<= 1 || destination > 10) {
            System.out.println("Invalid Node");
        }
        ArmyTypes generalType=WarriorsCamp.getGeneral(generalName).getArmy_type();
        
         // Initialize all distances as INFIINITY from source
        HashMap<Integer, Double> times = new HashMap<>();
        for (Integer node : adjList.keySet()) {
            times.put(node, Double.POSITIVE_INFINITY);
        }
        
        //Time is always 0 to travel to base camp(Node 1)
        times.put(1, 0.0);

        // Initialize -1 for prevNodes 
        HashMap<Integer, Integer> prevNode = new HashMap<>();
        for (Integer node : adjList.keySet()) {
            prevNode.put(node, -1);
        }

        // Creating an array to determine whether the best time for the node has been found
        boolean[] visited = new boolean[adjList.size() + 1];

        //Create a priority queue of nodes arranged by their travelling time from the source node, which is node 1
        PriorityQueue<Integer> queue = new PriorityQueue<>((v1, v2) -> Double.compare(times.get(v1), times.get(v2)));
        queue.add(1);

        // Process nodes in the queue until all nodes are updated with shortest time or the dest is reached
        while (!queue.isEmpty()) {
            int current = queue.poll();
            visited[current] = true;

            if (current == destination) {
                break;
            }

            // Update the travelling time value of the adjacent nodes for the currentNode
            for (Edge edge : adjList.get(current)) {
                int neighbor = edge.getDest();

                if (!visited[neighbor]) {
                    double edgeSpeed = getEdgeSpeed(edge.getEdgeType(), generalType);
                    double time = edge.getWeight() / edgeSpeed;

                    double timeFromCurrent = times.get(current) + time;

                    if (timeFromCurrent < times.get(neighbor)) {
                        times.put(neighbor, timeFromCurrent);
                        prevNode.put(neighbor, current);
                        queue.remove(neighbor);
                        queue.add(neighbor);
                    }
                }
            }
        }

       // Construct the path from the source to the dest vertex using the prevNode map
        ArrayList<Integer> path = new ArrayList<>();
        int current = destination;

        while (current != -1) {
            path.add(current);
            current = prevNode.get(current);
        }
        Collections.reverse(path);
        
        
        double totalTime = calculateTotalTime(adjList, path, generalType);
        printPath(path);
        System.out.printf("Total Time Travelled: (%.2f hours)\n", totalTime);
    }

    private static double getEdgeSpeed(EdgeType edgeType, ArmyTypes generalType) {

        switch (edgeType) {
            case FOREST:
                return (generalType==ArmyTypes.CAVALRY) ? CAVALRY_SPEED * 0.8
                        : (generalType==ArmyTypes.ARCHER) ? ARCHER_SPEED * 1.0
                        : INFANTRY_SPEED * 2.5;
            case SWAMP:
                return (generalType==ArmyTypes.CAVALRY) ? CAVALRY_SPEED * 0.3
                        : (generalType==ArmyTypes.ARCHER) ? ARCHER_SPEED * 2.5
                        : INFANTRY_SPEED * 1.0;
            case PLANKROAD:
                return (generalType==ArmyTypes.CAVALRY) ? CAVALRY_SPEED * 0.5
                        : (generalType==ArmyTypes.ARCHER) ? ARCHER_SPEED * 0.5
                        : INFANTRY_SPEED * 0.5;
            case FLATROAD:
                return (generalType==ArmyTypes.CAVALRY) ? CAVALRY_SPEED * 3.0
                        : (generalType==ArmyTypes.ARCHER) ? ARCHER_SPEED * 2.0
                        : INFANTRY_SPEED * 2.0;
            default:
                return -1;
        }
    }

    private static double calculateTotalTime(HashMap<Integer, ArrayList<Edge>> adjList, ArrayList<Integer> path, ArmyTypes generalType) {
        double totalTime = 0.0;
        for (int i = 1; i < path.size(); i++) {
            int sourceVertex = path.get(i - 1);
            int destVertex = path.get(i);

            for (Edge edge : adjList.get(sourceVertex)) {
                if (edge.getDest() == destVertex) {
                    double edgeSpeed = getEdgeSpeed(edge.getEdgeType(), generalType);
                    totalTime += edge.getWeight() / edgeSpeed;
                    break;
                }
            }
        }

        return totalTime;
    }

    private static void printPath(ArrayList<Integer> path) {
        String str = "";
        int total = 0;
        for (int i = 0; i < path.size(); i++) {
            str += path.get(i);
            str += (i == path.size() - 1) ? "" : "->";

        }

        System.out.println(str);
    }
}
