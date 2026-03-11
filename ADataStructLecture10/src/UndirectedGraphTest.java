import java.util.*;
public class UndirectedGraphTest {

	/*
	 * 								(0)---___
	 * 							   /   \	 \
	 * 							  /     \     \
	 * 							 (1)----(2)---(3)
	 * 							   \    / \   /
	 * 								\  /   \ /
	 * 								(4)	   (5)
	 */
	public static void main(String[] args) {
		ArrayList<LinkedList<Integer>> adj = new ArrayList<>(6);
		for(int i = 0; i < 6; i++) {
			adj.add(new LinkedList<>());
		}
		addEdgeAdj(adj, 0, 1); addEdgeAdj(adj, 0, 2); addEdgeAdj(adj, 0, 3);
		addEdgeAdj(adj, 1, 2); addEdgeAdj(adj, 1, 4);
		addEdgeAdj(adj, 2, 3); addEdgeAdj(adj, 2, 5);
		addEdgeAdj(adj, 4, 2); 
		addEdgeAdj(adj, 5, 3);
		
		printGraph(adj);
		
		System.out.println("degree of 2: " + degree(adj, 2));
	}
	public static void addEdgeAdj(ArrayList<LinkedList<Integer>> adj, int i, int j) {
		adj.get(i).add(j);
		adj.get(j).add(i);
	}
	public static int degree(ArrayList<LinkedList<Integer>> adj, int src) {
		return adj.get(src).size();	
	}
	public static void printGraph(ArrayList<LinkedList<Integer>> adj) {
		for(int i = 0; i < adj.size(); i++) {
			System.out.print(i + "->");
			for(int c : adj.get(i)) {
				System.out.print(c + "->");
			}
			System.out.println();
		}
	}

}
