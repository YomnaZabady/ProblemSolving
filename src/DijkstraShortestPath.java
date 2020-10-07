import java.util.Arrays;
/*
Given a graph and a source vertex in the graph, find shortest paths from source to all vertices in the given graph.
 */
public class DijkstraShortestPath {
    public static void main (String[] args) {

        int graph[][] = new int[][] {
                { 0, 4, 0, 0, 0, 0, 0, 8, 0 },
                { 4, 0, 8, 0, 0, 0, 0, 11, 0 },
                { 0, 8, 0, 7, 0, 4, 0, 0, 2 },
                { 0, 0, 7, 0, 9, 14, 0, 0, 0 },
                { 0, 0, 0, 9, 0, 10, 0, 0, 0 },
                { 0, 0, 4, 14, 10, 0, 2, 0, 0 },
                { 0, 0, 0, 0, 0, 2, 0, 1, 6 },
                { 8, 11, 0, 0, 0, 0, 1, 0, 7 },
                { 0, 0, 2, 0, 0, 0, 6, 7, 0 } };
        DijkstraShortestPath t = new DijkstraShortestPath();
        int [] shortestDistances = t.dijkstra(graph, 0);
        System.out.println(Arrays.toString(shortestDistances));
    }
    public int[] dijkstra (int[][] graph, int node) {
        int[] distances = new int[graph.length];
        boolean[] taken = new boolean[graph.length];

        // instantiate list with all max except the source element
        distances[0] = 0;
        for (int i = 1; i < distances.length; i++) {
            distances[i] = Integer.MAX_VALUE;
        }
        //Always start with the smallest value and get the neighbouring nodes and update their distance values
        int nodeIndex = 0;
        while (nodeIndex > -1) {
            for (int i = 0; i < graph.length; i++) {
                if (graph[nodeIndex][i] != 0) {
                    int newDistance = distances[nodeIndex] + graph[nodeIndex][i];
                    if (distances[i] > newDistance) {
                        distances[i] = newDistance;
                    }
                }
            }
            taken[nodeIndex] = true;
            nodeIndex = getMinUntracedNode(distances, taken);

        }

        return distances;
    }

    public int getMinUntracedNode(int[] distances, boolean[] taken) {

        int index = -1;
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < distances.length; i++) {
            if(distances[i] != Integer.MAX_VALUE && !taken[i]) {
                int localMin = distances[i];
                if (localMin<min) {
                    min = localMin;
                    index = i;
                }
            }
        }
        return index;
    }
}
