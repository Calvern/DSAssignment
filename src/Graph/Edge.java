/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Graph;

/**
 *
 * @author user
 */
public class Edge  {

    private EdgeType type;
    private int weight;
    private int dest;

    public Edge(int dest, int weight) {
        this.dest = dest;
        this.weight = weight;
        this.type = null;
    }

    public Edge(int dest, int weight, EdgeType type) {
        this.dest = dest;
        this.weight = weight;
        this.type = type;
    }

    public EdgeType getEdgeType() {
        return type;
    }

    public void setType(EdgeType type) {
        this.type = type;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getDest() {
        return dest;
    }

    public void setDest(int dest) {
        this.dest = dest;
    }
}
