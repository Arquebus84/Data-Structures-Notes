import java.util.*;
public class GraphTest {

	public static void main(String[] args) {
		/**
		 * (A)----->(B)
		 * 	|		 ^
		 * 	|		 |
		 * 	v		 |
		 * (C)<---->(D)
		 */
		
		// Vertices are Nodes
		// Edges are links
		
		Graph graph = new Graph();
		graph.addNode('A'); graph.addNode('B'); graph.addNode('C'); graph.addNode('D');
		graph.addEdge(0, 1);	// A to B (A index = 0, B index = 1)
		graph.addEdge(0, 2); 	// A to C
		graph.addEdge(3, 1);	// D to B
		graph.addEdge(2, 3);	// C to D
		graph.addEdge(3, 2);	// D to C
		
		graph.printGraph();
		//Prints:
		/**
		  	A->B->C->		// A is connected to B and C
			B->
			C->D->
			D->B->C->
		 */
	}

	//Created using Linked Lists
	private static class Node<E>{
		char data;
		
		public Node(char data) {
			this.data = data;
		}
	}
	
	private static class Graph{
		// Array list that contains a linked list of nodes (these linked lists will point to each other on the graph)
		ArrayList<LinkedList<Node>> list;
		
		public Graph() {
			list = new ArrayList<>();
		}
		
		public void addNode(char data) {
			LinkedList<Node> newest = new LinkedList();
			newest.add(new Node(data));
			list.add(newest);
		}
		public void addEdge(int src, int dest) {
			LinkedList<Node> newest = list.get(src);
			Node destNode = list.get(dest).get(0);		// 0 is head of the linked list
			newest.add(destNode);
		}
		public boolean checkEdge(int src, int dest) {
			LinkedList<Node> newest = list.get(src);
			Node destNode = list.get(dest).get(0);		// 0 is head of the linked list
			
			for(Node node : newest) {		// Iterate all nodes in the current list (newest)
				if(node == destNode) {		// Check if the addresses are the same
					return true;
				}
			}
			return false;				// If we escape the loop (didn't find the node), return false
		}
		public void printGraph() {

			for(LinkedList<Node> currentList : list) {
				for(Node node : currentList) {
					System.out.print(node.data + "->");
				}
				System.out.println();
			}
			
		}
	}
}
