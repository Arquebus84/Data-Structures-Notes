package GraphsTest;

import java.util.ArrayList;
import java.util.List;

public class myGraph {

	// Using Adjacency matrix
    public static void addEdgeAdjMat(int[][] mat, int i, int j) {
        mat[i][j] = 1;
        mat[j][i] = 1; // Since the graph is undirected
    }
    public static void displayMatrix(int[][] mat) {
        for (int[] row : mat) {
            for (int val : row) {
                System.out.print(val + " ");
            }
            System.out.println();
        }
    }

    // Using Adjacency list
    public static void addEdgeAdjList(List<List<Integer>> adj, int i, int j) {
        adj.get(i).add(j);
        adj.get(j).add(i); // Undirected
    }

    public static void displayAdjList(List<List<Integer>> adj) {
        for (int i = 0; i < adj.size(); i++) {
            System.out.print(i + "-> "); // Print the vertex
            for (int j : adj.get(i)) {
                System.out.print(j + " "); // Print its adjacent
            }
            System.out.println();
        }
    }


    public static void main(String[] args) {

        // a graph with 4 vertices (0,1,2,3)
        int V = 4;

        // for Adj Matrix, java so auto zero initialization
        int[][] mat = new int[V][V];
        
        // for Adj List
        List<List<Integer>> adj = new ArrayList<>(V);
        for (int i = 0; i < V; i++) {
            adj.add(new ArrayList<>());
        }

        // undirected edges between vertices (0,1), (0,2), (1,2), (2,3)
        addEdgeAdjMat(mat, 0, 1);
        addEdgeAdjMat(mat, 0, 2);
        addEdgeAdjMat(mat, 1, 2);
        addEdgeAdjMat(mat, 2, 3);

        // print adj matrix representation
        System.out.println("Adjacency Matrix Representation");
        displayMatrix(mat);
        System.out.println("");

        // undirected edges between vertices (0,1), (0,2), (1,2), (2,3)
        addEdgeAdjList(adj, 0, 1);
        addEdgeAdjList(adj, 0, 2);
        addEdgeAdjList(adj, 1, 2);
        addEdgeAdjList(adj, 2, 3);

        // print adj list representation
        System.out.println("Adjacency List Representation:");
        displayAdjList(adj);
    }
}

