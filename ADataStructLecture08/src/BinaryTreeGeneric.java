
public class BinaryTreeGeneric {

	private static class Node<E extends Comparable<E>>{
		Node<E> leftChild;
		Node<E> rightChild;
		E data;
		
		public Node(E data) {
			this.data = data;
		}
	}
	
	private static class BinaryTree<E extends Comparable<E>>{
		Node<E> root;
		int size;
		
		public BinaryTree() {
			this(null);
		}		
		public BinaryTree(Node<E> root) {
			this.root = root;
		}
		
		public void insert(E data) {
			insertHelper(root, data);
		}
		private Node<E> insertHelper(Node<E> node, E data) {
			if(this.root == null) {
				this.root = new Node<>(data);
			}
			
			if(node == null) {
				node = new Node<>(data);
			}else if(data.compareTo(node.data) < 0) {	//Search Left (data - node.data ==> data is less than current node's data)
				node.leftChild = insertHelper(node.leftChild, data);
			}else if(data.compareTo(node.data) > 0) {	//Search Right
				node.rightChild = insertHelper(node.rightChild, data);
			}
				
			return node;
		}
		
		public void delete(E data) {
			deleteHelper(root, data);
		}
		private Node<E> deleteHelper(Node<E> node, E data){
			if(node == null) {
				System.out.println("Node does not exist");
				return null;
			}
			
			//Search through the tree until the Node is found
			if(data.compareTo(node.data) < 0) {	//Search Left 
				node.leftChild = deleteHelper(node.leftChild, data);
			}else if(data.compareTo(node.data) > 0) {	//Search Right
				node.rightChild = deleteHelper(node.rightChild, data);
			}else {															//If the node is found
				//If the node doesn't have children, make it null (deletes it)
				if(node.leftChild == null && node.rightChild == null) {
					node = null;
				}//If it has a right child, Find the successor (if it's left node)
				else if(node.rightChild != null) {
					node.data = Successor(node);
					node.rightChild = deleteHelper(node.rightChild, node.data);
				}//If it has a left child, Find the predecessor (if it's right node)
				else if(node.leftChild != null) {
					node.data = Predecessor(node);
					node.leftChild = deleteHelper(node.leftChild, node.data);
				}
			}
			
			return node;
		}
		private E Successor(Node<E> node){
			//System.out.println("Checked Successor");
			node = node.rightChild;
			while(node.leftChild != null) {
				node = node.leftChild;
			}
			//System.out.println(node.data);
			
			return node.data;
		}
		private E Predecessor(Node<E> node){
			//System.out.println("Checked Predecessor");
			node = node.leftChild;
			while(node.rightChild != null) {
				node = node.rightChild;
			}
			//System.out.println(node.data);
			
			return node.data;
		}
		
		public void displayTree() {
			displayTreeHelper(root);
		}
		private void displayTreeHelper(Node<E> node) {	//In Order Traversal
			if(node != null) {
				displayTreeHelper(node.leftChild);
				System.out.print(node.data + " ");
				displayTreeHelper(node.rightChild);
			}
		}
	}
	
	public static void main(String[] args) {
		BinaryTree<Integer> tree = new BinaryTree<>();
		//tree.insert(2); tree.insert(5); tree.insert(4); tree.insert(3); tree.insert(6); tree.insert(9); tree.insert(7); tree.insert(12); tree.insert(10);
		tree.insert(4); tree.insert(2); tree.insert(1); tree.insert(3); tree.insert(6); tree.insert(5); tree.insert(7); 
		tree.displayTree();
		
		System.out.println("\nRoot " + tree.root.data);
		
		int n = 4;
		System.out.println("\nReplace " + n);
		
		tree.delete(n);
		
		System.out.println("\nRoot " + tree.root.data);
		tree.displayTree();
	}
	
}
