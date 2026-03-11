import java.util.*;

public class BinaryTreeLinkedList {
	/**
	 * Height						
	 * 									null
	 * 									 |
	 * 			0						[A]
	 *								   / | \		
	 *								 /   |   \		
	 *			1				  [B]   [C]   [D]			* Will have traversal of Log(n) because divide by 2
	 *							 / \	 / \	\	
	 *					        /  \	 /	 \	  \
	 *			2			  [E] [F]   [G]  [H]  null
	 *						 /	 / | \	 |    |
	 *						/   /  |  \	null  null					
	 *			3		null  [I] [J] [K]	
	 * 							|  |	|
	 * 						null  null null
	 */

	/*
	 *			null
	 *			 |
	 *			[3]
	 *			/ \
	 *   	  [2]  [7]
	 *   			/
	 *   		 [5]
	 *          /
	 *         [4] 
	 * 
	 */
	public static void main(String[] args) {
		BinaryTree binTree = new BinaryTree(3);
		binTree.insert(2); binTree.insert(7); binTree.insert(5); binTree.insert(4);
		binTree.display();
		
		System.out.println("\n" + binTree.getNode(5).parent.data);
	}

	private static class Node{
		int data;
		Node parent;			//Parent will be treated as previous node
		Node leftChild;
		Node rightChild;
		
		public Node(int data) {
			this.data = data;
		}
	}
	
	private static class BinaryTree{
		//Get parent nodes and child nodes
		Node root;
		int size;
		
		public BinaryTree(int data) {
			root = new Node(data);
			root.parent = null;
			this.size = 1;
		}
		public int Size() {return size;}
		
		public void insert(int data) {
			insertHelper(data, root);
		}
		private Node insertHelper(int data, Node node) {
			// Change this insert helper to assign the parent for each node
			Node current = node;
			//Node prev;
			
			if(node == null) {
				node = new Node(data);
			}else if(data < node.data) {
				node.leftChild = insertHelper(data, node.leftChild);
				node.leftChild.parent = current;
			}else {
				node.rightChild = insertHelper(data, node.rightChild);
				node.rightChild.parent = current;
			}
			
			return node;
		}
		
		public Node getNode(int data) {
			return getNodeHelper(data, root);
		}
		private Node getNodeHelper(int data, Node node) {
			if(node.data == data) {
				return node;
			}else if(data < node.data) {
				return getNodeHelper(data, node.leftChild);
			}else if(data > node.data){
				return getNodeHelper(data, node.rightChild);
			}else {
				return null;
			}
		}
		
		public void display() {
			displayHelper(root);
		}
		private void displayHelper(Node node) {		//InOrder traversal
			if(node != null) {
				displayHelper(node.leftChild);
				System.out.print(node.data + " ");
				displayHelper(node.rightChild);
			}
		}
	}
}
