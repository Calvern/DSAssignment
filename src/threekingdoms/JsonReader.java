/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package threekingdoms;

import Graph.Graph;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author user
 */
public class JsonReader {

    public static String readJSONFile(String path) {
        try {
            BufferedReader br = new BufferedReader(new FileReader(path));
            String content = "";
            String line = br.readLine();
            while (line != null) {
                content += line;
                line = br.readLine();
            }
            return content;
        } catch (IOException e) {
            System.out.println("Problem with file I/O");
            System.exit(0);
        }
        return null;
    }

    public static ArrayList<Warriors> getWarriorsList(String JSONContent) {
        try {
            JSONArray members = new JSONArray(JSONContent);
            ArrayList<Warriors> warriors = new ArrayList<>();

            for (Object jsonObj : members) {
                JSONObject memberinfo = (JSONObject) jsonObj;
                warriors.add(new Warriors(memberinfo.get("Name").toString(),
                        ArmyTypes.valueOf(memberinfo.get("ArmyType").toString()),
                        memberinfo.get("Role").toString(),
                        Integer.parseInt(memberinfo.get("Strength").toString()),
                        Integer.parseInt(memberinfo.get("Leadership").toString()),
                        Integer.parseInt(memberinfo.get("Intelligence").toString()),
                        Integer.parseInt(memberinfo.get("Politic").toString()),
                        Integer.parseInt(memberinfo.get("HitPoint").toString())
                ));
            }
            return warriors;
        } catch (IllegalArgumentException e) {
            System.out.println("The value of attributes should be within 0 to 100. Please check on Warriors.json file");
            System.exit(0);
        } catch (JSONException e) {
            System.out.println("Invalid data. Please check on Warriors.json file");
            System.exit(0);
        }
        return null;
    }

    public static Graph getGraph(String JSONContent) {
        try {
            JSONObject map = new JSONObject(JSONContent);
            int size = map.length();
            Graph graph = new Graph(size);
            for (int i = 0; i < size; i++) {
                Object[] edges = map.getJSONObject(Integer.toString(i + 1)).getJSONArray("edges").toList().toArray();
                Object[] weights = map.getJSONObject(Integer.toString(i + 1)).getJSONArray("weight").toList().toArray();
                Object[] typeEdge = map.getJSONObject(Integer.toString(i + 1)).getJSONArray("edgeType").toList().toArray();
                if (edges.length != weights.length || edges.length != typeEdge.length || weights.length != typeEdge.length) {
                    throw new IllegalArgumentException();
                }
                graph.setEdge(i + 1, edges, weights, typeEdge);
            }
            return graph;
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid data. Please check on Map.json file");
            System.exit(0);
        } catch (JSONException e) {
            System.out.println("Invalid data. Please check on Map.json file.");
            System.exit(0);
        }
        return null;
    }
}
