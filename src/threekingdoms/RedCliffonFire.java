/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package threekingdoms;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;

/**
 *
 * @author user
 */
public class RedCliffonFire {

    public static void main(String[] args) {
        RedCliffOnFire();
    }

    public static void RedCliffOnFire() {
        Scanner sc = new Scanner(System.in);
        StringBuilder sb = new StringBuilder();
        while (true) {
            try {
                System.out.println("Please enter position of battleships in the 2D matrix form, where 1 denotes battleship and 0 denotes position without battleship");
                String line;
                while (sc.hasNextLine()) {
                    line = sc.nextLine();

                    if (line.isEmpty()) {
                        break;
                    }
                    sb.append(line).append("\n");
                }
                String structure = sb.toString().replaceAll(" ", "");
                char[][] battleships = getBattleships(structure);
                if (battleships == null) {
                    throw new IllegalArgumentException();

                }
                int numCluster = numClusters(battleships);
                System.out.printf("Number of Cluster: %d clusters\n", numCluster);
                List<int[]> optimumCoordinates = findOptimumCoordinateForClusters(battleships);
                for (int i = 0; i < optimumCoordinates.size(); i++) {
                    int[] coordinate = optimumCoordinates.get(i);
                    System.out.printf("Optimum coordinate for Cluster %d: (%d, %d)\n", i + 1, coordinate[0], coordinate[1]);
                }
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

    private static List<int[]> findOptimumCoordinateForClusters(char[][] patients) {
        List<int[]> optimumCoordinates = new ArrayList<>();
        int numRows = patients.length;
        boolean[][] visited = new boolean[numRows][];
        int[][] clusterIndices = new int[numRows][];
        for (int i = 0; i < numRows; i++) {
            int numCols = patients[i].length;
            clusterIndices[i] = new int[numCols];
            visited[i] = new boolean[numCols];
        }
        int currentClusterIndex = 1;

        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < patients[i].length; j++) {
                if (patients[i][j] == '1' && clusterIndices[i][j] == 0) {
                    assignClusterIndex(patients, clusterIndices, i, j, currentClusterIndex);
                    currentClusterIndex++;
                }
            }
        }

        // Find optimum coordinate for each cluster using BFS
        for (int clusterIndex = 1; clusterIndex < currentClusterIndex; clusterIndex++) {
            int optimumX = -1;
            int optimumY = -1;
            int minSpreads = Integer.MAX_VALUE;

            for (int i = 0; i < numRows; i++) {
                int numCols = patients[i].length;
                for (int j = 0; j < numCols; j++) {
                    if (clusterIndices[i][j] == clusterIndex) {
                        int spreads = bfs(patients, i, j, numRows, visited);
                        if (spreads < minSpreads) {
                            minSpreads = spreads;
                            optimumX = i;
                            optimumY = j;
                        }
                    }
                }
            }

            if (optimumX != -1 && optimumY != -1) {
                optimumCoordinates.add(new int[]{optimumX, optimumY});
            }
        }

        return optimumCoordinates;
    }

    private static void assignClusterIndex(char[][] patients, int[][] clusterIndices, int i, int j, int clusterIndex) {
        int rows = patients.length;
        // int cols = patients[i].length;

        if (i < 0 || i >= rows || j < 0 || j >= patients[i].length || patients[i][j] != '1' || clusterIndices[i][j] != 0) {
            return;
        }

        clusterIndices[i][j] = clusterIndex;

        assignClusterIndex(patients, clusterIndices, i - 1, j, clusterIndex); // Up
        assignClusterIndex(patients, clusterIndices, i + 1, j, clusterIndex); // Down
        assignClusterIndex(patients, clusterIndices, i, j - 1, clusterIndex); // Left
        assignClusterIndex(patients, clusterIndices, i, j + 1, clusterIndex); // Right
        assignClusterIndex(patients, clusterIndices, i - 1, j - 1, clusterIndex); // Diagonal: Up-Left
        assignClusterIndex(patients, clusterIndices, i - 1, j + 1, clusterIndex); // Diagonal: Up-Right
        assignClusterIndex(patients, clusterIndices, i + 1, j - 1, clusterIndex); // Diagonal: Down-Left
        assignClusterIndex(patients, clusterIndices, i + 1, j + 1, clusterIndex); // Diagonal: Down-Right
    }

    private static int bfs(char[][] matrix, int startX, int startY, int rows, boolean[][] visited) {
        int spreads = 0;
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{startX, startY});

        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int[] cell = queue.poll();
                int row = cell[0];
                int col = cell[1];

                if (row >= 0 && row < rows && col >= 0 && col < matrix[row].length && matrix[row][col] == '1' && !visited[row][col]) {
                    visited[row][col] = true; // Infect the patient

                    // Enqueue neighboring cells (including diagonals)
                    for (int r = -1; r <= 1; r++) {
                        for (int c = -1; c <= 1; c++) {
                            if (r != 0 || c != 0) {
                                int newRow = row + r;
                                int newCol = col + c;
                                if (newRow >= 0 && newRow < rows && newCol >= 0 && newCol < matrix[newRow].length) {
                                    queue.offer(new int[]{newRow, newCol});
                                }
                            }
                        }
                    }
                }
            }
            if (!queue.isEmpty()) {
                spreads++;
            }
        }
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                visited[i][j] = false;
            }
        }
        return spreads;
    }

}
