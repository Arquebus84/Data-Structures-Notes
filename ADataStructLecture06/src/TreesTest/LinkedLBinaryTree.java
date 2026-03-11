package TreesTest;

public class LinkedLBinaryTree {  
    
	/**NOTE: This Code DOES Use Recursion*/
	
	/**
	 * 						  [2]
	 * 						/    \
	 * 					 [4]      [3]
	 * 					/   \     /  
	 * 				  [8]   [0]  [6]
	 * 
	 * 
	 */
	
	public static void main(String[] args) {
		LLBinaryTree LLBinTree = new LLBinaryTree();
		// 2 is the root data
		LLBinTree.insert(2); LLBinTree.insert(4); LLBinTree.insert(3); LLBinTree.insert(8); LLBinTree.insert(0); LLBinTree.insert(6);  
		System.out.println(LLBinTree.search(4));
		//System.out.println("Root " + LLBinTree.getRoot());
		
		System.out.println("\nIn-Order:");
		LLBinTree.inOrder();
		System.out.println("\nPre-Order:");
		LLBinTree.preOrder();
		System.out.println("\nPost-Order:");
		LLBinTree.postOrder();
	}
	
	private static class LLBTNode {

		LLBTNode left, right;
		int data;

		/* Constructor */
		public LLBTNode() {			//				[0]
			left = null;			//			   /  \
			right = null;			//		 [null]	   [null]
			data = 0;
		}
		/* Constructor */
		public LLBTNode(int n) {
			left = null;
			right = null;
			data = n;
		}/**									Never using the setLeft and setRight functions
		/* Function to set left node
		public void setLeft(LLBTNode n) {
			left = n;
		}										/**In the BinaryTreeTest.java file:
		// Function to set right node 			// the int data = n.data
		public void setRight(LLBTNode n) {			// Same as node.right = insertHelper(node.right, node.data)
			right = n;
		}
		*/
		/* Function to get left node */
		public LLBTNode getLeft() {
			return left;
		}
		/* Function to get right node */
		public LLBTNode getRight() {
			return right;
		}
		/* Function to set data to node */
		public void setData(int d) {
			data = d;
		}
		/* Function to get data from node */
		public int getData() {
			return data;
		}
	}

	/* Class LLBinaryTree */
	private static class LLBinaryTree {
		private LLBTNode root;

		/* Constructor */
		public LLBinaryTree() {
			root = null;
		}

		/* Function to check if tree is empty */
		public boolean isEmpty() {
			return root == null;
		}

		public int getRoot() {
			return root.data;
		}
		
		/* Functions to insert data */
		public void insert(int data) {
			root = insert(root, data);
		}

		/* Function to insert data recursively */			//insert Helper Method
		private LLBTNode insert(LLBTNode node, int data) {
			if (node == null) {
				node = new LLBTNode(data);						// If the root node is null, make it the first element
//			else {
//				if (node.getRight() == null) 					// If right child of node is null, insert the data onto that child
//					node.right = insert(node.right, data);
//				else
//					node.left = insert(node.left, data);		// Otherwise, insert data on the kleft child
//			}
			}else if(node.data < data) {
				node.left = insert(node.left, data);
			}else {
				node.right = insert(node.right, data);
			}
			return node;
		}

		/* Function to count number of nodes */
		public int countNodes() {
			return countNodes(root);
		}

		/* Function to count number of nodes recursively */
		private int countNodes(LLBTNode r) {
			if (r == null)
				return 0;
			else {
				int l = 1;
				l += countNodes(r.getLeft());
				l += countNodes(r.getRight());
				return l;
			}
		}

		/* Function to search for an element */
		public boolean search(int val) {
			return search(root, val);
		}

		/* Function to search for an element recursively */
		private boolean search(LLBTNode node, int val) {			//Think of as Binary Search
			if (node.getData() == val)				// if node's data matches the search value return true
				return true;
			if (node.getLeft() != null)				// If left child isn't null, then keep searching for it's children until reaching that node
				if (search(node.getLeft(), val))
					return true;
			if (node.getRight() != null)			// Same procedure for right	
				if (search(node.getRight(), val))
					return true;
			return false;
		}

		/* Function for inorder traversal */		//LeftNode-->RootNode-->RightNode
		public void inOrder() {
			inOrder(root);
		}

		private void inOrder(LLBTNode r) {	//inOrder helper method
			if (r != null) {
				inOrder(r.getLeft());
				System.out.print(r.getData() + " ");		//Visit left first, then root, then right
				inOrder(r.getRight());
			}
		}

		/* Function for preorder traversal */		//RootNode-->LeftNode-->RightNode
		public void preOrder() {
			preOrder(root);
		}

		//Modified preOrder to ensure that every new node is displayed
		private void preOrder(LLBTNode r) {	//preOrder helper method
			if (r != null) {
				System.out.print(r.getData() + " ");		//Visit the root node first and then the descendants
				preOrder(r.getLeft());
				preOrder(r.getRight());
			}
		}

		/* Function for postorder traversal */		//LeftNode-->RightNode-->RootNode
		public void postOrder() {
			postOrder(root);
		}

		private void postOrder(LLBTNode r) {	//postOrder helper method
			if (r != null) {
				postOrder(r.getLeft());
				postOrder(r.getRight());
				System.out.print(r.getData() + " ");		//Visit descendants (left then right) first, and finally the root
			}
		}
		
	}
}


