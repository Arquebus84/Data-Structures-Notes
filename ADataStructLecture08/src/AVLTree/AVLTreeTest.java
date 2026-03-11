package AVLTree;

public class AVLTreeTest {
	/* Left-Rotate
	 * 		  null
	 * 			|
	 *			5
	 *		   / \
	 *		 3    9
	 *			 /  \ 
	 * 			7    11
	 *				  \					
	 *					12
	 */
	/*	Right-Rotate
	 * 		  null
	 * 			|
	 *			11
	 *		   / \
	 *		  7    12
	 *	    /  \ 
	 * 	   5    9
	 * 	  /					
	 *	 3
	 */

	public static void main(String[] args) {
		Tree tree = new Tree();
		tree.insert(5); tree.insert(3); tree.insert(9); tree.insert(7); tree.insert(11); tree.insert(12); 
		//tree.insert(11); tree.insert(12); tree.insert(7); tree.insert(9); tree.insert(5); tree.insert(3); 

		tree.display();
		int n = 9;
//		System.out.println("\nNode " + n + " has children: " + tree.hasChildren(n) + " \nAmount of children n = " + tree.numOfChildren(n));
//		System.out.println("\nNode " + n + " has height " + tree.height(n) + " located " + (tree.maxHeight() - tree.height(n)) + " nodes from depth");
//		System.out.println("\nMax height: " + tree.maxHeight());
		//System.out.println("\nDepth at " + n + ": " + tree.height(n));
//		System.out.println("\nParent of node " + n + ": " + tree.getNode(n).parent.data);
//		System.out.println("\nNode " + n + " has branches count: " + tree.numOfBranches(n));
		System.out.println("\nNode " + n + " is leftChild: " + tree.getNode(n).isLeftChild() + " is rightChild: " + tree.getNode(n).isRightChild());
	}
	
	private static class Node{
		int data;
		int height;
		Node leftChild;
		Node rightChild;
		Node parent;
		
		public Node(int data){
			this.data = data;
			height = 1;
			this.leftChild = null;
			this.rightChild = null;
			this.parent = null;
		}
		
		public Node getParent() {
			if(parent == null) {
				return null;
			}else {
				return parent;
			}
		}
		
		public boolean isLeftChild() {
			if(this.parent.leftChild == this)
				return true;
			else
				return false;
		}
		public boolean isRightChild() {
			if(this.parent.rightChild == this)
				return true;
			else
				return false;
		}
	}
	
	private static class Tree{
		Node root;
		Node lastNode;
		//int height;
		
		public Tree() {
			root = null;
		}
		public Tree(int data) {
			root = new Node(data);
		}
		
		public void insert(int data) {
			insertHelper(data, root);
		}
		private Node insertHelper(int data, Node node) {
			if(root == null) {
				root = new Node(data);
				root.parent = null;
			}
						
			Node current = node;
			if(node == null) {
				node = new Node(data);
				lastNode = node;				
			}else if(node.data > data) {				
				node.leftChild = insertHelper(data, node.leftChild);
				node.leftChild.parent = current;
			}else {
				node.rightChild = insertHelper(data, node.rightChild);				
				node.rightChild.parent = current;
			}
			//System.out.println("current node: " + node.data);		
//			if(node.leftChild != null && (numOfBranches(data) > 1)) {
//				System.out.println("Unbalanced, rotate left");
//				//LeftRotate(node);
//			}else if(node.rightChild != null && (numOfBranches(data) > 1)) {
//				System.out.println("Unbalanced, rotate right");
//				//RightRotate(node);
//			}else {
//				System.out.println("Balanced");
//			}
			
			return node;
		}
		public void LeftRotate(Node x) {
			Node y = x.rightChild;
			x.rightChild = null;
			x.rightChild = y.leftChild;
			
			if(y.leftChild != null) {
				y.leftChild.parent = x;
				y.leftChild = null;
			}
			// y's parent will be the same as x's parent (both have null previous addresses)
			y.parent = x.parent;
			if(x == root) {
				root = y;			//		x.parent = y;
			}else if(x == root.leftChild) {		//Check if the given node is a left child
				root.leftChild = y;
			}else {
				root.rightChild = y;
			}
			y.leftChild = x;
			x.parent = y;
		}
		
		public void RightRotate(Node Y) {
			//System.out.println("Done");
		}
		
		public boolean hasChildren(int data) {
			return hasChildrenHelper(root, data);
		}
		public boolean hasChildrenHelper(Node node, int data) {
			if(node == null) {
				return false;
			}
			
			if(node.data == data) {
				if(node.leftChild != null || node.rightChild != null) {
					return true;
				}
				return false;
			}else if(node.data > data) {
				return hasChildrenHelper(node.leftChild, data);
			}else {
				return hasChildrenHelper(node.rightChild, data);
			}
		}

		public Node getNode(int data) {
			return getNodeHelper(root, data);
		}
		private Node getNodeHelper(Node root, int data) {
			if(root.data == data) {
				return root;
			}else if(root.data > data) {
				return getNodeHelper(root.leftChild, data);
			}else {
				return getNodeHelper(root.rightChild, data);
			}
		}
		
		public int numOfChildren(int data) {
			if(hasChildren(data)) {
				if(getNodeHelper(root, data).leftChild != null && getNodeHelper(root, data).rightChild != null) {
					return 2;
				}else if(getNodeHelper(root, data).rightChild != null && getNodeHelper(root, data).leftChild == null) {
					return 1;
				}else {
					return 1;
				}
			}else {
				return 0;
			}
		}
		
		public int height(int data) {
			return heightHelper(data, root, 0);
		}
		private int heightHelper(int data, Node node, int height) {
			if(node == null) {
				return 0;
			}
			if(data == node.data) {
				return 0;
			}else if(data < node.data) {
				return 1 + heightHelper(data, node.leftChild, height);
			}else {
				return 1 + heightHelper(data, node.rightChild, height);
			}
		}
		
		public int maxHeight() {
			return height(lastNode.data);
		}
//		public int numOfBranches(int data) {
//			Node node = getNode(data);
//			return numOfBranchesHelper(node, 0);
//		}
//		
//		private int numOfBranchesHelper(Node node, int count) {
////			int temp = 0;
//			if(node != null) {
//				numOfBranchesHelper(node.leftChild, count++);
//				numOfBranchesHelper(node.rightChild, count++);
//			}
//			return count;
//		}
		
		public void display() {
			displayHelper(root);
		}
		private void displayHelper(Node node) {		//InOrder traversal
			if(node != null) {								
				displayHelper(node.leftChild);
				displayHelper(node.rightChild);
				System.out.print(node.data + " ");
			}
		}
	}

	
	private static class AVLTree extends Tree{
		
		public AVLTree() {
			super();
		}
		
		public void insert(int data) {
			super.insert(data);
		}
	}
}
