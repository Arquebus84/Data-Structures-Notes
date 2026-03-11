package AdvancedListExamples;

public class MinHeapTest {
	
/**
 *					1) Find insertion node z (new last one): Find the position for the new node by referencing that last node
 * 						that was inserted (in the example below, [7] was the last node that was inserted)
 * 					2) Store k at z
 * 					3) Restore the heap-order property (**Upheap Property**)
 * 
 * 								[2]
 * 								/ \
 * 							[5]		[6]
 * 							/ \		/
 * 						[9]	  [7]	[]<-- new node z
 * 
 * 								[2]							// From depth to top, change the position of the new node and update 
 * 								/ \									the last Node
 * 							[5]		[6]
 * 							/ \		/
 * 						[9]	  [7]	[1]<-- new node z
 * 
 * 								[1]
 * 								/ \
 * 							[5]		[2]					//Swap 1 and 6, and then 1 and 2
 * 							/ \		/
 * 						[9]	  [7]	[6]<-- new node z
 * 
 */

	public static void main(String[] args) {
		MinHeapTree heapTree = new MinHeapTree(6);
		heapTree.insert(2); heapTree.insert(4); heapTree.insert(9); heapTree.insert(3); heapTree.insert(8); heapTree.insert(1);
		heapTree.display();
		/*
		 *			[4]
		 *			/ \
		 *		  [2]  [8]
		 *		 /  \
		 *		[1]  [3]
		 */
		
		System.out.println();
		System.out.println("Find 8: " + heapTree.search(8)); System.out.println("Find 0: " + heapTree.search(0));
		System.out.println("Root: " + heapTree.root.data);
	}

	private static class Node{
		Node rightNode;
		Node leftNode;
		int data;
		
		public Node() {
			data = 0;
			rightNode = null;
			leftNode = null;
		}
		
		public Node(int data) {
			this.data = data;
			rightNode = null;
			leftNode = null;
		}
	}
	
	private static class MinHeapTree{
		Node root;
		Node size;
		
		public MinHeapTree() {
			root = null;
			root.rightNode = null;
			root.leftNode = null;
		}
		public MinHeapTree(int root) {
			this.root = new Node(root);
			this.root.rightNode = null;
			this.root.leftNode = null;
		}
		
		public void insert(int n) {
			insertHelper(root, n);
		}
		private Node insertHelper(Node node, int data) {	//Modify insertHelper so that it works as heap
			if(node == null) {
				node = new Node(data);
			}else if(data > node.data) {
				node.rightNode = insertHelper(node.rightNode, data);
				//sort(node, node.rightNode);
			}else {				
				node.leftNode = insertHelper(node.leftNode, data);
				//sort(node, node.leftNode);
			}
			return node;
		}
		
		public void sort(Node root, Node node) {
			while(root != node) {
				if(root.data > node.data) {
					Node temp = root;
					root = node;
					node = temp;
				}
			}
//			if(root != null && node != null) {
//				if(root.data > node.data) {
//					Node temp = root;
//					root = node;
//					node = temp;
//				}
//			}
		}
		
		public int root() {
			return root.data;
		}
		
		public boolean search(int data) {
			return searchHelper(root, data);	// print T/F if node is found/not found
		}
		private boolean searchHelper(Node node, int data) {
			if(node == null) {
				return false;
			}else if(data == node.data) {
				return true;
			}else if(data > node.data) {
				return searchHelper(node.rightNode, data);
			}else{
				return searchHelper(node.leftNode, data);
			}
		}
		
//		public void remove(int data) {
//			removeHelper(root, data);
//		}
//		private Node removeHelper(Node node, int data) {
//			if(node == null) {
//				return node;
//			}else if(data > node.data) {
//				return removeHelper(node.rightNode, data);
//			}else {
//				return removeHelper(node.leftNode, data);
//			}else {	//If data is found
//				if(node.leftNode == null && node.rightNode == null) {
//					node = null;
//				}else if(node.rightNode != null) {
//					node.rightNode = removeHelper(node.rightNode, data);
//				}else {
//					node.leftNode = removeHelper(node.leftNode, data);
//				}
//			}
//			return node;
//		}
		
		public void upHeap() {
			
		}
		
		public void downHeap() {
			
		}
		
		public void display() {
			displayHelper(root);
		}
		private void displayHelper(Node node) {		//Display In-Order
			if(node != null) {
				displayHelper(node.leftNode);
				System.out.print(node.data + " ");
				displayHelper(node.rightNode);
			}
		}
	}
}
