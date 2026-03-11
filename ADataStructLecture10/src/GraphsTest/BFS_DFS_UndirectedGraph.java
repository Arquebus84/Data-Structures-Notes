package GraphsTest;

import java.util.*;

public class BFS_DFS_UndirectedGraph {

	private static class Vertex<V>{
		V data;
		Vertex<V> parent;
		int distance;
		public Vertex(V data) {
			this.data = data;
			this.parent = null;
			this.distance=0;
		}
	}
	
	private static class Graph<V> {
		HashMap<Vertex<V>, LinkedList<Vertex<V>>> adjacencyList;
		HashMap<Vertex<V>, Integer> vertexList;
		HashMap<Integer, Vertex<V>> indexList;
		int size;
		
		public Graph() {
			this.adjacencyList = new HashMap<>();
			this.vertexList = new HashMap<>();
			this.indexList = new HashMap<>();
		}
		
		public void addVertex(Vertex<V> vertex) {
			if(!adjacencyList.containsKey(vertex)) {
				adjacencyList.put(vertex, new LinkedList<>());
				vertexList.put(vertex, size++);
				indexList.put(size, vertex);
			}else {
				System.out.println("Vertex already exists");
			}
		}

		public void addEdge(Vertex<V> vertex1, Vertex<V> vertex2) {
			if(adjacencyList.containsKey(vertex1) && adjacencyList.containsKey(vertex2)) {
				adjacencyList.get(vertex1).add(vertex2);
				adjacencyList.get(vertex2).add(vertex1);
			}else {
				System.out.println("At least one vertex is missing");
			}
		}
		
		public void displayAdjList() {
			adjacencyList.forEach((Vertex<V> vertex, LinkedList<Vertex<V>> list)->{
				System.out.print(vertex.data + ":->");
				for(Vertex<V> adj : list) {
					System.out.print(adj.data + "->");
				}
				System.out.println();
			});
		}
		
		public void BFS(Vertex<V> start) {
			Queue<Vertex<V>> queue = new LinkedList<>();
			boolean[] visited = new boolean[adjacencyList.size()];
			
			queue.add(start);
			visited[vertexList.get(start)]=true;
			
			while(!queue.isEmpty()) {
				Vertex<V> v = queue.peek();
				System.out.println(v.data + " ");
				for(Vertex<V> adj : adjacencyList.get(v)) {
					if(visited[vertexList.get(adj)]==false) {
						visited[vertexList.get(adj)]=true;
						queue.add(adj);
					}
				}
				queue.poll();
			}
		}
		
		public void DFS(Vertex<V> start) {
			Stack<Vertex<V>> stack = new Stack<>();
			boolean[] visit = new boolean[adjacencyList.size()];
			stack.push(start);
			
			for(Vertex<V> u : adjacencyList.get(start)){
				if(visit[vertexList.get(u)]==false)
					DFS_visit(u, stack, visit);
			}
		}
		private void DFS_visit(Vertex<V> u, Stack<Vertex<V>> stack, boolean[] visit) {
			visit[vertexList.get(u)] = true;
			stack.push(u);
			System.out.println(u.data);
			for(Vertex<V> adj : adjacencyList.get(u)) {
				if(visit[vertexList.get(adj)]==false) {
					DFS_visit(adj, stack, visit);
				}
			}
			stack.pop();
		}
	}
	
	public static void main(String[] args) {
		/**
		 * 			A
		 * 		   / \  
		 *        B   C--D
		 *         \ / \
		 *          E   F
		 * 
		 */
		
		Vertex<Character> v1 = new Vertex<>('A'), v2=new Vertex<>('B'), v3=new Vertex<>('C'),
				v4=new Vertex<>('D'), v5=new Vertex<>('E'), v6=new Vertex<>('F');
		
		Graph<Character> graph = new Graph<>();
		graph.addVertex(v1); graph.addVertex(v2); graph.addVertex(v3); graph.addVertex(v4); graph.addVertex(v5); graph.addVertex(v6);
		graph.addEdge(v1, v2); graph.addEdge(v1, v3); graph.addEdge(v2, v5); graph.addEdge(v3, v4); graph.addEdge(v3, v5); graph.addEdge(v3, v6);
		
		//graph.displayAdjList();
		//graph.BFS(v1);
		graph.DFS(v1);
	}

}
