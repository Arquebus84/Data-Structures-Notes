
import java.util.*;

public class SearchUndirectedGraph {
	/**
	 * 			(A)---___
	 * 		   /   \	 \
	 * 		  /     \     \
	 * 		 (B)----(C)---(D)
	 * 		   \    / \   /
	 * 			\  /   \ /
	 * 			(E)	   (F)
	 */
	
	public static void addVertex(Map<Character, LinkedList<Character>> graph, Character vertex) {
		if(!graph.containsKey(vertex)) {
			graph.put(vertex, new LinkedList<>());
		}else {
			System.out.println("Vertex " + vertex + " already exists");
		}
	}
	public static void addEdge(Map<Character, LinkedList<Character>> graph, Character vertex1, Character vertex2) {
		if(graph.containsKey(vertex1) && graph.containsKey(vertex2)) {
			graph.get(vertex1).add(vertex2);
			graph.get(vertex2).add(vertex1);
		}
	}
	
	public static void displayGraph(Map<Character, LinkedList<Character>> graph) {
		graph.forEach((Character c, LinkedList<Character> list)->{
			System.out.println(c + "->" + list.toString());
		});
	}
	public static void main(String[] args) {
		Map<Character, LinkedList<Character>> graph = new HashMap<>();
		addVertex(graph, 'A'); addVertex(graph, 'B'); addVertex(graph, 'C'); addVertex(graph, 'D'); addVertex(graph, 'E'); addVertex(graph, 'F');
		addEdge(graph, 'A', 'B'); addEdge(graph, 'A', 'C'); addEdge(graph, 'A', 'D');
		addEdge(graph, 'B', 'C'); addEdge(graph, 'B', 'E');
		addEdge(graph, 'C', 'D'); addEdge(graph, 'C', 'E'); addEdge(graph, 'C', 'F');
		addEdge(graph, 'D', 'F');
		displayGraph(graph);
		
		Map<Character, Integer> charMap = new HashMap<>();
		for(int i = 0; i < graph.keySet().toArray().length; i++) {
			charMap.put((Character)graph.keySet().toArray()[i], i);
		}
		//System.out.println(charMap.toString());
		
		//BFS(graph, 'A', charMap);
		DFS(graph, charMap);
	}
	
	public static void DFS(Map<Character, LinkedList<Character>> graph, Map<Character, Integer> charMap) {
		Stack<Character> stack = new Stack<>();
		boolean[] visited = new boolean[graph.size()];
		
		for(int i = 0; i < graph.size(); i++) {
			if(visited[i] == false) {
				DFS_Visit(graph, charMap, visited, stack, i);
			}
		}
		
	}
	public static void DFS_Visit(Map<Character, LinkedList<Character>> graph, Map<Character, Integer> charMap, boolean[] visited, Stack<Character> stack, int currIndex) {
		visited[currIndex] = true;
		stack.push((Character)charMap.keySet().toArray()[currIndex]);
		System.out.println(stack.peek());
		for(Character adj : graph.get(charMap.keySet().toArray()[currIndex])) {
			if(visited[charMap.get(adj)] == false) {
				DFS_Visit(graph, charMap, visited, stack, charMap.get(adj));
			}
		}
		
	}
	
	public static void BFS(Map<Character, LinkedList<Character>> graph, Character start, Map<Character, Integer> charMap) {
		Queue<Character> queue = new LinkedList<>();
		boolean[] visited = new boolean[graph.size()];
		
		int startValue = charMap.get(start);
		
		queue.add(start);
		visited[startValue] = true;
		while(!queue.isEmpty()) {
			Character u = queue.peek();
			
			System.out.println(u);
			for(Character adj : graph.get(u)) {
				if(visited[charMap.get(adj)] == false) {
					visited[charMap.get(adj)] = true;
					queue.add(adj);
				}
			}
			
			queue.poll();
		}
	}
	

}
