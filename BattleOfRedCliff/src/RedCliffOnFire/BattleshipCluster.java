package RedCliffOnFire;

public class BattleshipCluster {
    public static void main(String[] args) {
        char[][] grid = {
            {'1', '1', '0', '0', '1', '0', '0', '1', '1', '1'},
            {'1', '0', '0', '0', '1', '0', '0', '0', '1', '0'}, 
            {'1', '0', '1', '1', '1', '0', '1', '0', '1', '0'},
            {'1', '0', '0', '0', '0', '0', '1', '0', '0', '0'},
            {'1', '0', '1', '1', '1', '1', '1', '1', '1', '1'},
            {'0', '0', '0', '0', '0', '0', '0', '0', '0', '0'},
            {'1', '1', '1', '1', '0', '1', '1', '0', '1', '0'},
            {'1', '0', '0', '0', '0', '0', '0', '0', '1', '0'},
            {'1', '0', '0', '0', '1', '0', '1', '1', '1', '1'},
            {'1', '0', '0', '0', '1', '0', '0', '0', '0', '0'}};
        
        NumberOfClusters clusters = new NumberOfClusters();
        int numClusters = clusters.numClusters(grid);
        
        System.out.println("Number of clusters: "+numClusters);
    }
}
