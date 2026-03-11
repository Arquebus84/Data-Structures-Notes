package DijkstrasAlgTest;

import java.util.*;

public class DijkstraAlgTest {
		
	/*		Example: distance is randomized
	 *  				0
	 * 					(a)_
	 * 					|	\_ 7
	 * 					|	  \_
	 * 					|		\_  ∞   8		∞
	 * 				   	|5		  (b)----------(c)
	 * 					| 	9 ____/ \_ 7     __/
	 * 				   	|	_/		  \_   _/   5
	 * 					| _/	15		\ /
	 * 				  ∞(d)--------------(e)∞
	 * 					 \__          __/ \_
	 * 						\__	  8__/		\__9
	 * 						6  \  /            \
	 * 							(f)------------(g)
	 * 							∞		11		∞
	 */
	
	/***	Modify BFS
	 * 
	 * 		BFS(Graph g, int start){
	 * 			For each (v in V)			//initialize each vertex
	 * 				v.d = ∞					//Shortest distance between start vertex and the next vertex
	 * 				v.p = null				//Parent of vertex v on shortest path from s to v
	 * 				v.color = GREY			
	 * 
	 * 			s.d = 0;
	 * 			Queue.enQ(s)
	 * 			while(!Queue.isEmpty()){
	 * 				u = Queue.deQ();
	 * 				for each v in u.adj
	 * 					if(v.color == GREY)
	 * 						v.color = RED;
	 * 						v.d = u.d + 1;		//Randomize distance by adding math.random() go for number between 0-9 
	 * 						v.p = u;
	 * 						Queue.enQ(v);
	 * 			}
	 */
	/* 		Dijkstra's Algorithm Pseudocode (three methods)
	 * 
	 * 		initialize(G, s)			// d is the current distance/weight number of the vertex
	 * 			
	 * 			for each v in V			// V is the list of all vertices, v is any vertex node
	 * 				v.d = infinite
	 * 				d.p = null;				
	 * 				s.d = 0
	 * 		...
	 *
	 * 		relax(u, v, w)			//Updating method
	 * 			if (v.d > u.d + w(u,v))	// If becomes greater than weight of edge
	 * 				v.d = u.d + w(u,v)
	 * 				v.p = u
	 *
	 * 		...initialize(G, s)
	 * 			S = 0
	 * 			Q = V
	 * 			while Q is not empty
	 * 				u = Extract_Min(Q)
	 * 				S = S union u
	 * 				for each v in u.adj
	 * 					relax(u, v, w)			//Call the relax function
	 * 		
	 */
	
	private static class Vertex<V>{
		V data;
		Vertex<V> parent;	//Will be updated in Dijkstra's Alg
		int distance;	
		
		public Vertex(V data) {
			this.parent = null;
			this.data = data;
			this.distance = 0;
		}
	}
	
	private static class UndirectedGraph<V>{
		public Map<Vertex<V>, LinkedList<Vertex<V>>> adjacencyList;
		public Map<Vertex<V>, Integer> vertexList;	//Submap stores the generics in a dictionary that can be used to find their index
															// Submap is used for Queuing
		
		public Map<LinkedList<Vertex<V>>, Integer> weightMap;	// Submap used for weights between vertices
		public Map<LinkedList<Vertex<V>>, Integer> distanceMap;	//Submap used for distances between vertices + weights
		
		private int size = 0;
		
		public UndirectedGraph() {
			adjacencyList = new HashMap<>();
			vertexList = new HashMap<>();
			weightMap = new HashMap<>();
			distanceMap = new HashMap<>();		//Will be updated in Dijkstra's Alg
		}
		public void addVertex(Vertex<V> vertex) {
			if(!adjacencyList.containsKey(vertex)) {
				adjacencyList.put(vertex, new LinkedList<>());
				vertexList.put(vertex, size++);
			}else {
				System.out.println("Vertex " + vertex + " already exists in graph");
			}
		}
		public void addAdjacent(Vertex<V> vertex1, Vertex<V> vertex2, int weight) {
			if(adjacencyList.containsKey(vertex1) && adjacencyList.containsKey(vertex2)) {
				adjacencyList.get(vertex1).add(vertex2);
				adjacencyList.get(vertex2).add(vertex1);
			
				LinkedList<Vertex<V>> temp1 = new LinkedList<>();
				temp1.add(vertex1); temp1.add(vertex2);
				weightMap.put(temp1, weight);
			}
		}
		public void displayGraph() {
			adjacencyList.forEach((Vertex<V> v, LinkedList<Vertex<V>> list)->{
				System.out.print(v.data + "->");
				for(Vertex<V> adj : list) {
					System.out.print(adj.data + ", ");
				}
				System.out.println();
			});
		}
		
		//	Find weight between the vertices: Always checking the fixed order of the adjacents
		public int getWeight(Vertex<V> vertex1, Vertex<V> vertex2) {
			LinkedList<Vertex<V>> temp = new LinkedList<>();
			temp.add(vertex1); temp.add(vertex2);
			if (weightMap.containsKey(temp)) {
				return weightMap.get(temp);
			}
			return 0;	//If the weightmap doesn't contain these vertices in this sequence, then they could be fliped (return 0)
		}
		
		public void weightList() {
			weightMap.forEach((LinkedList<Vertex<V>> list, Integer n)->{
				for(Vertex<V> v : list) {
					System.out.print(v.data + ",");
				}				
				System.out.print("->" + n + "\n");
			});
		}
	
		public int getDistance(Vertex<V> vertex1, Vertex<V> vertex2) {
			LinkedList<Vertex<V>> temp = new LinkedList<>();
			temp.add(vertex1); temp.add(vertex2);
			if(distanceMap.containsKey(temp)) {
				return distanceMap.get(temp);
			}else {
				return 0;					//Return 0 works the same as getWeight()
			}
		}
				
		public void Dijkstra(Vertex<V> start) {
			Queue<Vertex<V>> queue = new LinkedList<>();
			boolean[] visited = new boolean[adjacencyList.size()];
			
			queue.add(start);
			visited[vertexList.get(start)] = true;
			
			while(!queue.isEmpty()) {
				Vertex<V> u = queue.peek();
				
				System.out.println(u.data + " Distance: " + u.distance + " parent: " + ((u.parent == null)? u.parent : u.parent.data));
				for(Vertex<V> adj : adjacencyList.get(u)) {
					if(visited[vertexList.get(adj)] == false) {
						visited[vertexList.get(adj)] = true;
						//Calculate the distance between two vertices with weights
						relax(u, adj);
						queue.add(adj);
					}
				}
				queue.poll();
			}
		}
		private void relax(Vertex<V> u, Vertex<V> adj) {
			//adj.distance = u.distance + 1;
			if(adj.distance < u.distance + ((getWeight(adj, u) == 0)? getWeight(u, adj) : getWeight(adj, u))){
				adj.distance += u.distance + ((getWeight(adj, u) == 0)? getWeight(u, adj) : getWeight(adj, u));
				adj.parent = u;
			}
		}
	}

	public static void main(String[] args) {
		//Create an Undirected graph using a map: this will allow for dynamic vertices
		Vertex<Character> v1=new Vertex<>('a'), v2=new Vertex<>('b'), v3=new Vertex<>('c'),
				v4=new Vertex<>('d'),v5=new Vertex<>('e'),v6=new Vertex<>('f'),v7=new Vertex<>('g');
		UndirectedGraph<Character> graph = new UndirectedGraph<>();
		
		graph.addVertex(v1); graph.addVertex(v2); graph.addVertex(v3); graph.addVertex(v4); graph.addVertex(v5); graph.addVertex(v6); graph.addVertex(v7);
		
		graph.addAdjacent(v1, v2, 7); graph.addAdjacent(v1, v4, 5);
		graph.addAdjacent(v2, v3, 8); graph.addAdjacent(v2, v4, 9); graph.addAdjacent(v2, v5, 7);
		graph.addAdjacent(v3, v5, 5);
		graph.addAdjacent(v4, v5, 15); graph.addAdjacent(v4, v6, 6);
		graph.addAdjacent(v5, v6, 8); graph.addAdjacent(v5, v7, 9);
		graph.addAdjacent(v6, v7, 11);
		//graph.displayGraph();
		//graph.weightList();
		//graph.Dijkstra(v1);
		
		
	}

}
