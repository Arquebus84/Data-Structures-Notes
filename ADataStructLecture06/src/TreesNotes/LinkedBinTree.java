package TreesNotes;

public class LinkedBinTree {

	public static void main(String[] args) {
		BinaryTree tree = new BinaryTree(2);
		tree.insert(4); tree.insert(1); tree.insert(9); tree.insert(5);
		tree.displayTree();
	}
	
	private static class Node{
		int data;
		Node leftChild;
		Node rightChild;
		
		public Node(int data) {
			this.data = data;
			leftChild = null;
			rightChild = null;
		}		
		public Node(int data, Node leftChild, Node rightChild) {
			this.data = data;
			this.leftChild = leftChild;
			this.rightChild = rightChild;
		}
	}
	
	private static class BinaryTree{
		Node root;
		int size;
				
		public BinaryTree() {
			this(0);
		}
		public BinaryTree(int data) {
			root = new Node(data, null, null);
			size++;
		}
		public boolean isEmpty() {return size < 1;}
		
		public void insert(int data) {
			insertHelper(root, data);
		}
		private Node insertHelper(Node root, int data) {
			if(isEmpty()) {
				root = new Node(data);
			}else if(root == null) {
				root = new Node(data);
			}else if(data > root.data) {
				root.rightChild = insertHelper(root.rightChild, data);
			}else {
				root.leftChild = insertHelper(root.leftChild, data);
			}
			size++;
			
			return root;
		}
		public void displayTree() {
			displayTreeHelper(root);
		}
		private void displayTreeHelper(Node root) {
			if(root != null) {
				displayTreeHelper(root.leftChild);
				System.out.print(root.data + " ");
				displayTreeHelper(root.rightChild);
			}
		}
	}

}
