/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package threekingdoms;

import Graph.Edge;
import java.util.HashMap;

/**
 *
 * @author user
 */
public class GraphProvider {

    public static HashMap<Integer, Edge> adjList = new HashMap<>();

    public static void setadjList(HashMap<Integer,Edge>adjList) {
        GraphProvider.adjList=adjList;
    }
}
