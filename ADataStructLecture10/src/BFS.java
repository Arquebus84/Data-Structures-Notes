/** Breadth-First-Search */
import java.util.*;		// Using Array and Linked Lists, and PriorityQueue
public class BFS {
	// Implement Queue to keep track of every vertex visited
	// Vertices are Nodes
	// Edges are links
	/**
	 * 		BFS (G, s)
	 * 			for each u in v
	 * 				u.color = GRAY 		//Initialize the vertex
	 * 			s.color = RED			//Starting vertex (A) will be red
	 * 			Q.enqueue(s)			//the first element will be in the queue
	 * 			while Q is not empty
	 * 				u = Q.dequeue()		//Create new vertex (node) that is the first element in the queue
	 * 				for each v in u.Adj
	 * 					if(v.color == GRAY)
	 * 						v.color = RED
	 * 						Q.enqueue(v)
	 * 				u.color = black
	 */
	
	/**NOTE: Search for all the adjacent nodes in the current node*/
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
//		int[] bfs_array = {2, 4, 1, 6, 5, 8};		
//		PriorityQueue<Integer> queueBFS = new PriorityQueue<>();
//		for(int i : bfs_array) {
//			queueBFS.add(i); 
//		}
//		System.out.println(queueBFS.toString());
		
		int size = 6;
		ArrayList<LinkedList<Integer>> graph = new ArrayList<>(size);
		for(int i = 0; i < size; i++) {
			graph.add(new LinkedList<>());
		}
		addEdgeAdj(graph, 0, 1); addEdgeAdj(graph, 0, 2); addEdgeAdj(graph, 0, 3); 
		addEdgeAdj(graph, 1, 2); addEdgeAdj(graph, 1, 4);
		addEdgeAdj(graph, 2, 3); addEdgeAdj(graph, 2, 5);
		addEdgeAdj(graph, 4, 2);
		addEdgeAdj(graph, 5, 3);
		printGraph(graph);
		//System.out.println("2 has degree " + adjacents(graph, 2));
		
		System.out.println("\nQueue:");
		BFS(graph, 0);
	}
	
	public static void addEdgeAdj(ArrayList<LinkedList<Integer>> graph, int i, int j) {
		graph.get(i).add(j);
		graph.get(j).add(i);
	}
	public static void printGraph(ArrayList<LinkedList<Integer>> graph) {
		for(int i = 0; i < graph.size(); i++) {
			System.out.print(i + "->");
			for(Integer n : graph.get(i)) {
				System.out.print(n + "->");
			}
			System.out.println();
		}
	}
	public static int adjacents(ArrayList<LinkedList<Integer>> graph, int src) {
		return graph.get(src).size();
	}
	
	public static void BFS(ArrayList<LinkedList<Integer>> graph, int start) {
		Queue<Integer> queueBFS = new LinkedList<>();
		boolean[] visited = new boolean[graph.size()];
		
//		for(int i = 0; i < graph.size(); i++) {
//			visited[i] = false;
//		}
		visited[start] = true;
		queueBFS.add(start);
		
		while(!queueBFS.isEmpty()) {
			int vertex = queueBFS.peek();
			System.out.println(vertex);
			for(Integer adj : graph.get(vertex)) {
				if(!visited[adj]) {
					queueBFS.add(adj);
					visited[adj] = true;
					//System.out.println(adj);
				}
			}
			queueBFS.poll();
		}
	}
	
}
