/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package threekingdoms;

import Graph.Edge;
import Graph.Graph;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author user
 */
public class GraphProvider {

    private static HashMap<Integer, ArrayList<Edge>> adjList = new HashMap<>();
    private static Graph graph_cur;

    public static void setGraph(Graph graph) {
        graph_cur = graph;
        GraphProvider.adjList=graph_cur.getAdjList();
    }

    public static Graph getGraph() {
        return graph_cur;
    }

    public static void setadjList(HashMap<Integer, ArrayList<Edge>> adjList) {
        GraphProvider.adjList = adjList;
    }

    public static HashMap<Integer, ArrayList<Edge>> getadjList() {
        return adjList;
    }
}
