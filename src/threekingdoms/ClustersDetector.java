/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package threekingdoms;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 *
 * @author user
 */
public class ClustersDetector {
    public static void main(String[] args) {
        RedCliffOnFire();
    }
    public static void RedCliffOnFire() {
        Scanner sc = new Scanner(System.in);
        StringBuilder sb = new StringBuilder();
        while (true) {
            try {
                System.out.println("Please enter position of battleships in the 2D matrix form, where 1 denotes battleship and 0 denotes position without battleship.\nEnter a blank line if you have completed your input.");
                String line;
                while (sc.hasNextLine()) {
                    line = sc.nextLine();

                    if (line.isBlank()) {
                        break;
                    }
                    sb.append(line).append("\n");
                }
                String structure = sb.toString().replaceAll("[ \\t]", "");
                System.out.println(structure);
                if(structure.isBlank()){
                    throw new IllegalArgumentException();
                }
                char[][] battleships = getBattleships(structure);
                if (battleships == null) {
                    throw new IllegalArgumentException();
                    
                }
                int numCluster = numClusters(battleships);
                System.out.printf("Number of Cluster: %d clusters\n", numCluster);
                break;
            } catch (IllegalArgumentException e) {
                System.out.println("Invalid Input!! Please try again.\n");
                sb.setLength(0);
            }
        }

    }

    private static char[][] getBattleships(String input) {
        String[] rows = input.split("\n");
        char[][] battleships = new char[rows.length][];
        for (int i = 0; i < rows.length; i++) {
            battleships[i] = rows[i].toCharArray();
            for (char nodes : battleships[i]) {
                if (nodes != '1' && nodes != '0') {
                    return null;
                }
            }
        }
        return battleships;
    }

    private static int numClusters(char[][] grid) {
        if (grid == null || grid.length == 0) {
            return 0;
        }

        int numClusters = 0;
        boolean[][] visited = new boolean[grid.length][];

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                visited[i] = new boolean[grid[i].length];
            }
        }
        //iterating from the very first cell
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {;
                if (grid[i][j] == '1' && !visited[i][j]) {
                    numClusters++;          //mark it as a battleship
                    dfs(grid, i, j, visited);        //will go up, down, left, right and diagonal
                }
            }
        }

        return numClusters;
    }

    private static void dfs(char[][] grid, int i, int j, boolean[][] visited) {
        //handling out of bounds conditions and visited/non relevant cells
        if (i < 0 || i >= grid.length || j < 0 || j >= grid[i].length || grid[i][j] != '1' || visited[i][j]) {
            return;
        }

        visited[i][j] = true;   //marks : the cell is now visited.

        //8 diff directions to go
        int[] dx = {0, 0, -1, 1, 1, 1, -1, -1};
        int[] dy = {1, -1, 0, 0, 1, -1, -1, 1};

        for (int k = 0; k < dx.length; k++) {
            int newI = i + dx[k];
            int newJ = j + dy[k];
            dfs(grid, newI, newJ, visited);

        }
    }

}
