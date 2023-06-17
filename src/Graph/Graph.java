/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Graph;

import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author user
 */
public class Graph {

    private HashMap<Integer, ArrayList<Edge>> adjList;
    private int size = 0;

    public Graph() {
        adjList = new HashMap<>();
    }

    public int getSize() {
        return size;
    }

    public Graph(int size) {
        this.size = size;
        adjList = new HashMap<>();
        for (int i = 0; i < size; i++) {
            adjList.put(i + 1, new ArrayList<>());
        }
    }

    public void setEdge(int src, int dest, int weight, EdgeType type) {
        if (src > this.size || dest > this.size) {
            System.out.println("Nodes not present");
        } else {
            adjList.get(src).add(new Edge(dest, weight, type));
        }
    }

    public void setEdge(int src, Object[] dest, Object[] weight, Object[] type) {

        for (int i = 0; i < dest.length; i++) {
            EdgeType edgetype = EdgeType.valueOf(type[i].toString());
            this.setEdge(src, (int) dest[i], (int) weight[i], edgetype);
        }

    }

    public boolean hasEdge(int src, int dst) {
        for (Edge edge : adjList.get(src)) {
            if (edge.getDest() == dst) {
                return true;
            }
        }
        return false;
    }

    public HashMap<Integer, ArrayList<Edge>> getAdjList() {
        return this.adjList;
    }

    public void printGraph() {
        System.out.println("Map:");
        System.out.println("------");
        for (Integer node : adjList.keySet()) {
            System.out.println(node);
            for (Edge edge : adjList.get(node)) {
                System.out.printf(" --> %s (%dkm) [%s]\n", edge.getDest(), edge.getWeight(), edge.getEdgeType());
            }
            System.out.println();
        }
    }

}
