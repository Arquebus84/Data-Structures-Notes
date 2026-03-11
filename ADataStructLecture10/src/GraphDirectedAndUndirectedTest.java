import java.util.*;

public class GraphDirectedAndUndirectedTest {
	/**
	 * 		Directed				0
	 *  							(A)---____
	 * 							   /   \	  \
	 * 							 1v    2 v     v 3
	 * 							 (B)--->(C)-->(D)
	 * 							   \    ^ \   ^
	 * 								v /	   v /
	 * 							  4 (E)	   (F) 5
	 * 
	 * 		Undirected				 0
	 * 								(A)
	 * 							   /   \	 
	 * 							 1/      \ 2    3
	 * 							 (B)----(C)---(D)
	 * 							   \    / \   
	 * 							   4\ /	   \ 5
	 * 								(E)	   (F)
	 */

	private static class DirectedGraph<E> {
		
		ArrayList<LinkedList<E>> graph;
		
		public DirectedGraph(int size) {
			graph = new ArrayList<>();
			for(int i = 0; i < size; i++) {
				graph.add(new LinkedList<>());
			}
		}
		public int size() {
			return graph.size();
		}
		
		public void addAdjacent(E b, int a) {
			graph.get(a).add(b);
		}
		
		public void displayGraph() {
			for(LinkedList<E> list : graph) {
				for(E e : list) {
					System.out.print(e + "->");
				}
				System.out.println();
			}
		}
	}
	
	/**Use a Map to create an undirected graph*/
	private static class UndirectedGraph<V, E> {
		
		Map<V, LinkedList<V>> adjacencyList;
		Map<V, E> subMap;
		
		public UndirectedGraph() {
			adjacencyList = new HashMap<>();
			subMap = new HashMap<>();
		}
		
		/**		public void addVertex(V vertex)		*/
		public void addVertex(V vertex, E value) {
			if(!adjacencyList.containsKey(vertex)) {
				adjacencyList.put(vertex, new LinkedList<>());
				subMap.put(vertex, value);
			}else {
				System.out.println("Vertex " + vertex + " already exists");
			}
		}
		public void addEdge(V vertex1, V vertex2) {
			if(adjacencyList.containsKey(vertex1) && adjacencyList.containsKey(vertex2)) {
				adjacencyList.get(vertex1).add(vertex2);
				adjacencyList.get(vertex2).add(vertex1);
			}
		}
		
		// Fix Later
		public void addAdjacent(V vertex, E index) {				//Vertex at index b (converted to hashcode)
			//System.out.println(b.hashCode());
			
			/**Check if the vertex already exists in the graph*/
//			LinkedList<V> targetList = new LinkedList<>();			//Linked list with element for reference
//			targetList.add(vertex);
//			
//			//If it's the first index of the arraylist and the first linkedlist is empty
//			if(index.hashCode() == 0 && adjacencyList.get(index.hashCode()).isEmpty()) {
//				adjacencyList.get(index.hashCode()).add(vertex);
//				System.out.println(vertex + " List is empty");
//			}else if(index.hashCode() == 0 && !adjacencyList.get(index.hashCode()).isEmpty()) {		//When connecting to the first list
//				//adjacencyList.get(index.hashCode()).add(adjacencyList.get(0).get(0));
//				System.out.println(vertex + " List is Empty");
//				//Find the linked list that contains the vertex 
//				//System.out.println(adjacencyList.get(adjacencyList.indexOf(targetList) + 1) );
//				
//				adjacencyList.get(++currentIndex).addFirst(vertex);	//When moving onto the next list in the graph, change the current index of the arrayList
//				
//			}else if(adjacencyList.get(index.hashCode()).isEmpty()){
//				System.out.println("Add to List");
//				adjacencyList.get(index.hashCode()).addLast(vertex);
//			}
			//System.out.println(currentIndex);
			
			/**If yes, connect it to an adjacent vertex*/
			/**Otherwise, just add it to the graph*/
//			if(!adjacencyList.get(index.hashCode()).contains(vertex) && adjacencyList.get(adjacencyList.indexOf(vertex)) == null) {
//				//adjacencyList.get(b.hashCode()).add(a);
//				System.out.println(vertex + " is not in adj list");
//			}else {
//				System.out.println(adjacencyList.get(index.hashCode()) + " is in adj list");
////				adjacencyList.get(adjacencyList.indexOf(a)).add(a);
//				//adjacencyList.get(adjacencyList.indexOf(a)).add(a);
//			}
		}
		
		public void displayGraph() {
			//System.out.println(adjacencyList.toString());
			adjacencyList.forEach((V vertex, LinkedList<V> list) ->{
				System.out.println(vertex + "->" + list.toString());
			});
		}
	}
	
	public static void main(String[] args) {
		//Directed
		System.out.println("Directed: ");
		DirectedGraph<Character> Dgraph = new DirectedGraph<>(6);
		Dgraph.addAdjacent('A', 0); Dgraph.addAdjacent('B', 0); Dgraph.addAdjacent('C', 0);
		Dgraph.addAdjacent('B', 1); Dgraph.addAdjacent('C', 1); Dgraph.addAdjacent('E', 1);
		Dgraph.addAdjacent('C', 2); Dgraph.addAdjacent('D', 2); Dgraph.addAdjacent('F', 2);
		Dgraph.addAdjacent('D', 3);
		Dgraph.addAdjacent('E', 4); Dgraph.addAdjacent('C', 4);
		Dgraph.addAdjacent('F', 5); Dgraph.addAdjacent('D', 5);
		Dgraph.displayGraph();
			
		//Undirected
		System.out.println("Undirected: ");
		UndirectedGraph<Character, Integer> Ugraph = new UndirectedGraph<>();
		Ugraph.addVertex('A', 0); Ugraph.addVertex('B', 1); Ugraph.addVertex('A', 2);
		Ugraph.addVertex('C', 2); Ugraph.addVertex('D', 3); Ugraph.addVertex('E', 4); Ugraph.addVertex('F', 5);
		
		Ugraph.addEdge('A', 'B'); Ugraph.addEdge('A', 'C');
		Ugraph.addEdge('B', 'C'); Ugraph.addEdge('B', 'E');
		Ugraph.addEdge('C', 'D'); Ugraph.addEdge('C', 'E'); Ugraph.addEdge('C', 'F');
		//Ugraph.addEdge('E', 'C');
		
//		graph_u.addAdjacent('A', 0); graph_u.addAdjacent('A', 1); graph_u.addAdjacent('A', 2); 
//		graph_u.addAdjacent('B', 0); //graph_u.addAdjacent('B', 1);
//		graph_u.addAdjacent('C', 0);
		//graph_u.addAdjacent('D', 2); graph_u.addAdjacent('D', 2);*/
		
		//Map<Character, Integer> submap = new HashMap<>();
//		for(int i = 0; i < graph.adjacencyList.size(); i++) {
//			submap.put((Character)graph.adjacencyList.keySet().toArray()[i], i);
//		}
		//System.out.println(graph.submap.toString());
		
		Ugraph.displayGraph();
	}

}
