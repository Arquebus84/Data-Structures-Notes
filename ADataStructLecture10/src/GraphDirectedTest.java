import java.util.*;

public class GraphDirectedTest {	
	/*
	 * 								(A)---____
	 * 							   /   \	  \
	 * 							  v      v     v
	 * 							 (B)--->(C)-->(D)
	 * 							   \    ^ \   ^
	 * 								v /	   v /
	 * 								(E)	   (F)
	 */
	private static class Vertex {
		char data;
		public Vertex(char data) {this.data = data;}
	}
	
	private static class Graph {
		
		ArrayList<LinkedList<Vertex>> list;
		
		public Graph() {
			list = new ArrayList<>();
		}
		public void addVertex(char data) {
			LinkedList<Vertex> newest = new LinkedList<>();
			newest.add(new Vertex(data));
			list.add(newest);
		}
		public void addEdge(int src, int dest) {
			LinkedList<Vertex> newest = list.get(src);
			Vertex newVertex = list.get(dest).get(0);
			newest.add(newVertex);
		}
		public boolean checkEdge(int src, int dest) {
			LinkedList<Vertex> newest = list.get(src);
			Vertex destNode = list.get(dest).get(0);
			for(Vertex current : newest) {
				if(current == destNode) {
					return true;
				}
			}
			return false;
		}
		
		public void printGraph() {
			for(LinkedList<Vertex> currentList : list) {
				for(Vertex v : currentList) {
					System.out.print(v.data + "->");
				}
				System.out.println();
			}
		}
	}
	
	public static void main(String[] args) {
		Graph graph = new Graph();
		graph.addVertex('A'); graph.addVertex('B'); graph.addVertex('C'); graph.addVertex('D'); graph.addVertex('E'); graph.addVertex('F');
		graph.addEdge(0, 1); graph.addEdge(0, 2); graph.addEdge(0, 3); 
		graph.addEdge(1, 2); graph.addEdge(1, 4);
		graph.addEdge(2, 3); graph.addEdge(2, 5);
		graph.addEdge(4, 2);
		graph.addEdge(5, 3);		
		graph.printGraph();
	}
}
