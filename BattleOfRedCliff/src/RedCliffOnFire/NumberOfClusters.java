package RedCliffOnFire;

public class NumberOfClusters {
    private int rows;
    private int columns;
    
    public int numClusters(char[][] grid){
        if(grid == null || grid.length == 0){
            return 0;
        }
        
        rows = grid.length;
        columns = grid[0].length;
        int numClusters = 0;
        
        //iterating from the very first cell
        for(int i=0;i<rows;i++){
            for(int j=0;j<columns;j++){
                if(grid[i][j] == '1'){
                    numClusters++;          //mark it as a battleship
                    dfs(grid, i, j);        //will go up, down, left, right and diagonal
                }
            }
        }
        
        return numClusters;
    }
    
    private void dfs(char[][] grid, int i, int j){
        //handling out of bounds conditions and visited/non relevant cells
        if(i<0 || i>= rows || j<0 || j>= columns || grid[i][j] != '1'){
            return;
        }
        
        grid[i][j] = '2';   //marks : the cell is now visited.
        
        //8 diff directions to go
        int[] dx = {0, 0, -1, 1, 1, 1, -1, -1};
        int[] dy = {1, -1, 0, 0, 1, -1, -1, 1};
        
        for(int k=0;k<dx.length;k++){
            int newI = i + dx[k];
            int newJ = j + dy[k];
            dfs(grid, newI, newJ);
        }
    }
}
