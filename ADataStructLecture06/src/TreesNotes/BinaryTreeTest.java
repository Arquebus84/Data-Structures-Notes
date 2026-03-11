package TreesNotes;

public class BinaryTreeTest {

	/**
	 * 		class Binary TreeNode{
	 *			...
	 *			data
	 *			//Children
	 *			leftChild
	 *			rightChild			//Similar to Linked List, Parent and Children are Node<E>
	 *		----------------
	 *			...
	 *			//getChildren()
	 *			//setChildren()
	 *			getLeftChild
	 *			setLeftChild
	 *			getRightChild
	 *			setRightChild
	 *			...
	 *		}
	 *
	 *		class Tree{
	 *			root			// First thing you need is always a root (mandatory)
	 *			size			// Size is number of nodes in the tree			//** NOTE: Don't necessarily need size and height
	 *			height
	 *		----------------
	 *			root()
	 *			size()
	 *			isEmpty()
	 *			isInternal(node)
	 *			isExternal(node)
	 *			isRoot(node)
	 *			traversal()
	 *			...
	 *		}
	 */
	
	public static void main(String[] args) {
		BinaryTree binTree = new BinaryTree();
		/** Display using In-Order Traversal: 		leftNode-->RootNode-->rightNode	*/
		binTree.insert(new Node(4));	// root
		binTree.insert(new Node(2));	//2 < 4: left Node
		binTree.insert(new Node(1));	//1 < 4 && 1 < 2: left Node
		binTree.insert(new Node(3));	//3 < 4 && 3 > 2: right Node
		binTree.insert(new Node(6));	//6 > 4: right Node
		binTree.insert(new Node(5));	//5 > 4 && 5 < 6: left Node
		binTree.insert(new Node(7));	//7 > 4 && 7 > 6: right Node
		
		binTree.display();		//Displays from left-->root-->right
		System.out.println("Root Node: " + binTree.root.data);
		
		System.out.println("\nSearch for 0: " + binTree.search(0) + "\nSearch for 6: " + binTree.search(6));
		
		int n = 6;
		System.out.println("Deleting node " + n);
		binTree.remove(n);
		binTree.display();
		System.out.println("Root Node: " + binTree.root.data);
	}
	
	private static class Node{
		int data;
		Node leftNode;
		Node rightNode;
		
		public Node(int data) {this.data = data;}
	}
	
	/*
	 * 						[4]	
	 *					  /	   \
	 *					[2]		[6]
	 *				  /	 \  	/ \	
	 *				[1]	  [3]  [5]	[7]	
	 */
	
	/**Helper Methods are used to make Recursion Easier*/
	private static class BinaryTree{
		Node root;
		int size;
		int height;
		
		public void insert(Node node) {
			root = insertHelper(root, node);
		}
		private Node insertHelper(Node root, Node node){
			int data = node.data;
			//Check if root is null (if yes, then assign the node value to root)
			if(root == null) {
				root = node;
				return root;
			}else if(data < root.data) {	// If data is less than the root, assign it to the left side of the tree (left branch)
				root.leftNode = insertHelper(root.leftNode, node);	//After recursion, the root node will be the root node of the left branch of tree
											/**The root 4 will be root 2 on the left branch*/
			}else {							// If data >= root.data, assign to right leaf
				root.rightNode = insertHelper(root.rightNode, node);
			}
			
			return root; //Return the current root node
		}
		
		public void display() {
			displayHelper(root);
		}
		private void displayHelper(Node root) {		//Display using In-Order Traversal (left-->root-->right)
			if(root != null) {
				displayHelper(root.leftNode);		//Can Change it to Pre-Order or Post-Order by changing these 3 statements around
				System.out.print(root.data + " ");
				displayHelper(root.rightNode);
			}
		}
		
		public boolean search(int data) {
			return searchHelper(root, data);
		}
		private boolean searchHelper(Node root, int data) {
			if(root == null) {	//If tree is empty
				return false;
			}else if(root.data == data) {	//If we found the data, return true
				return true;
			}else if(root.data > data) {	//If root data is greater than current data, search left (current data is less than root)
				return searchHelper(root.leftNode, data);
			}else {
				return searchHelper(root.rightNode, data);
			}
		}
		
		/**Deleting a node will leave a gap*/
		public void remove(int data) {
			if(search(data)) {
				removeHelper(root, data);
			}else {
				System.out.println("Data could not be found");
			}
		}
		private Node removeHelper(Node root, int data) {
			if(root == null) {				//If node was already deleted or if it doesn't exist, return null
				return null;
			}else if(root.data > data) {	//data is less than root's data, search left
				root.leftNode = removeHelper(root.leftNode, data);
			}else if(root.data < data) {
				root.rightNode = removeHelper(root.rightNode, data);
			}else {	//Node is found
				if(root.leftNode == null && root.rightNode == null) {	//If node has no child, set to null, otherwise, replace the node
					root = null;
				}else if(root.rightNode != null) {	//If node does have a right child, find a successor to replace this node
					root.data = successor(root);
					root.rightNode = removeHelper(root.rightNode, root.data);	//Replace the right node if it has childNodes
					
				}else {								//If node does have left child, find a predecessor to replace this node
					root.data = predecessor(root);
					root.leftNode = removeHelper(root.leftNode, root.data);		//Replace the left node if it has childNodes
				}
			}
			return root;
		}
		
		/**If the root node has a right/left child, find that child and replace the deleted root node with it to fill up the gap*/
		private int successor(Node root) {	// find the least value below the right child of this root node
			root = root.rightNode;
			while(root.leftNode != null) {	
				root = root.leftNode;
			}
			return root.data;
		}
		private int predecessor(Node root) { // find the greatest value below the left child of this root node
			root = root.leftNode;
			while(root.rightNode != null) {
				root = root.rightNode;
			}			
			return root.data;
		}
	}

}
