/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package threekingdoms;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author user
 */
public class MazeEscaping {

    /**
     * 0: obstacle or a blocked cell. 1: open path or a passable cell. 2:
     * starting point of Cao Cao. 3: exit point of the maze.
     *
     */
    private static final int[] ROW_OFFSETS = {-1, 1, 0, 0};  // Up, down, left, right
    private static final int[] COL_OFFSETS = {0, 0, -1, 1};

    public static void main(String[] args) {
        findEscapePath();
    }

    public static void findEscapePath() {
        char[][] maze = getMaze();
        int[] startLocation = findStart(maze);
        boolean[][] visited = new boolean[maze.length][maze[0].length];
        dfs(startLocation[0], startLocation[1], visited, maze);
    }

    private static int[] findStart(char[][] maze) {
        for (int i = 0; i < maze.length; i++) {
            for (int j = 0; j < maze[i].length; j++) {
                if (maze[i][j] == '2') {
                    return new int[]{i, j};
                }
            }
        }
        System.out.println("Starting point not found");
        return null;
    }

    private static void dfs(int row, int col, boolean[][] visited, char[][] maze) {
        if (maze[row][col] == '3') {
            
            printMaze(maze);
            return;
        }
        visited[row][col] = true;
        if (maze[row][col] != '2') {
            maze[row][col] = '.';
        }
        for (int i = 0; i < 4; i++) {
            int newRow = row + ROW_OFFSETS[i];
            int newCol = col + COL_OFFSETS[i];
            if (isPath(newRow, newCol, visited, maze)) {
                dfs(newRow, newCol, visited, maze);
            }

        }
        visited[row][col] = false;
        maze[row][col] = '0';
    }

    private static boolean isPath(int row, int col, boolean[][] visited, char[][] maze) {
        if (row < 0 || row >= maze.length || col < 0 || col >= maze[row].length || visited[row][col] || maze[row][col] == '1') {
            return false;
        }
        return true;
    }

    private static char[][] getMaze() {
        int[][] MAZE = {
            {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
            {2, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 1},
            {1, 0, 1, 1, 1, 0, 1, 0, 1, 0, 1, 1, 1},
            {1, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 1},
            {1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1},
            {1, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 1},
            {1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 0, 1},
            {1, 0, 1, 0, 0, 0, 0, 0, 1, 0, 0, 0, 1},
            {1, 0, 1, 0, 1, 0, 1, 1, 1, 1, 1, 1, 1},
            {1, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 3},
            {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1}
        };
        char[][] maze = new char[MAZE.length][MAZE[0].length];
        for (int i = 0; i < MAZE.length; i++) {
            for (int j = 0; j < MAZE[i].length; j++) {
                maze[i][j] = Character.forDigit(MAZE[i][j], 10);
            }

        }
        return maze;
    }

    private static void printMaze(char[][] maze) {
        for (char[] row : maze) {
            for (char column : row) {
                System.out.print(column+" ");
            }
            System.out.println();
        }
    }
}
