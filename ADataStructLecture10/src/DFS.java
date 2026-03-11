/** Depth-First-Search */
import java.util.*;	// Using ArrayList, LinkedList, and Stack
public class DFS {
	//Implement a Stack to keep track of every vertex visited
	// Vertices are Nodes
	// Edges are links
	
	//NOTE: Every discovered node will be placed in the stack, checked by a boolean array
	/**
	 * 			DFS(G)								//G is the given graph
	 * 				//Initialize vertex				
	 * 				for each vertex u in V			//Every other vertex will be undiscovered
	 * 					u.color = GRAY
	 * 				for each vertex u in V			
	 * 					if(u.color == GRAY)
	 * 						DFS_Visit(u)			//If the vertex (node) is gray, visit it
	 * 	----------------------------------------------------------------------------------------------------
	 * 			DFS_Visit(u)						//The starting vertex will be red (true for discovered)
	 * 
	 * 				u.color = RED					//Color the vertex by discovered color
	 * 				for each v in u.adjacent		//Explore the adjacent vertices of vertex v
	 * 					if (v.color == GRAY)		//If the vertex is undiscovered (false)
	 * 						DFS_Visit(v)			//For every vertex (node) next to the current vertex, recursively visit its neighbors
	 * 				u.color = BLACK					//	until there is no adjacent vertex
	 * 
	 */
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
//		int[] dfs_array = {2, 4, 0, 6, 5, 8};
		
//		Stack<Integer> stackDFS = new Stack<>();
//		for(int i : dfs_array) {
//			stackDFS.add(i); 
//		}
//		System.out.println(stackDFS.toString());
		
		int size = 6;
		ArrayList<LinkedList<Integer>> graph = new ArrayList<>(size);
		for(int i = 0; i < size; i++) {
			graph.add(new LinkedList<Integer>());
		}
		//graph.addVertex('A'); graph.addVertex('B'); graph.addVertex('C'); graph.addVertex('D'); graph.addVertex('E'); graph.addVertex('F');
		addEdgeAdj(graph, 0, 1); addEdgeAdj(graph, 0, 2); addEdgeAdj(graph, 0, 3); 
		addEdgeAdj(graph, 1, 2); addEdgeAdj(graph, 1, 4);
		addEdgeAdj(graph, 2, 3); addEdgeAdj(graph, 2, 5);
		addEdgeAdj(graph, 4, 2);
		addEdgeAdj(graph, 5, 3);
		printGraph(graph);
		
		System.out.println("\nStack:");
		DFS(graph);
	}
	
	public static void addEdgeAdj(ArrayList<LinkedList<Integer>> adj, int src, int dest) {
		adj.get(src).add(dest);
		adj.get(dest).add(src);		
	}
	public static void printGraph(ArrayList<LinkedList<Integer>> adj) {
		for(int i = 0; i < adj.size(); i++) {
			System.out.print(i + "->");
			for(Integer n : adj.get(i)) {
				System.out.print(n + "->");
			}
			System.out.println();
		}
	}

	public static void DFS(ArrayList<LinkedList<Integer>> graph) {
		boolean[] visited = new boolean[graph.size()];
		Stack<Integer> stackDFS = new Stack<>();
		
		//DFS_VisitAlt(graph, visited, stackDFS, start);	//Alternate version of the same code (more simplified)
		
		//Initialize each vertex as undiscovered (already false by default)
//		for(int b = 0; b < visited.length; b++) {
//			visited[b] = false;
//		}		
		//Search through each vertex
		for(int i = 0; i < graph.size(); i++) {
			if(visited[i] == false) {
				DFS_Visit(graph, visited, stackDFS, i);
			}
		}
	}
	private static void DFS_Visit(ArrayList<LinkedList<Integer>> graph, boolean[] visited, Stack<Integer> stackDFS, int vertex) {
		visited[vertex] = true;
		stackDFS.push(vertex);
		System.out.println(vertex + " ");
		for(Integer adj : graph.get(vertex)) {
			if(visited[adj] == false) {
				//System.out.println(adj);
				DFS_Visit(graph, visited, stackDFS, adj);
			}
		}
//		stackDFS.pop();
	}
	
	
	private static void DFS_VisitAlt(ArrayList<LinkedList<Integer>> graph, boolean[] visited, Stack<Integer> stackDFS, int vertex) {
		stackDFS.push(vertex);
		visited[vertex] = true;
		LinkedList<Integer> neighborList = graph.get(vertex);
		System.out.println(vertex);
		for(Integer adjVertex : neighborList) {
			if(!visited[adjVertex]) {
				//System.out.println(adjVertex);
				DFS_VisitAlt(graph, visited, stackDFS, adjVertex);
			}
		}
		stackDFS.pop();		//Removes the last element in the stack
		
	}
}
