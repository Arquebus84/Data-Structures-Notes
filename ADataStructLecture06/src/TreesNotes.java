
public class TreesNotes {
	/**
	 * 1) What's a Tree?
	 * 2) Traverse a Tree
	 * 3) Binary Tree
	 * 		- Inorder Traversal
	 * 		- Implementation of Binary Tree 
	 */
	
	/**	What's a Tree?
	 * 			* NOTE: In each branch, splitting in the tree will be O(log(n)) since dividing	
	 * 				
	 * 							[Formulas]
	 * 							/		\
	 * 					[Single-Line]	[Multi-line]
	 * 									/			\
	 * 								[Aligned at]	[first left, centered, last right]
	 * 								/	|	\
	 * 							  /		|	  \
	 * 				[Relation sign]	[Several]	[Center]	
	 *								[places ]
	 *
	 *			* In CS, a tree is abstract model of hierarchical structure
	 *			* A tree consists of nodes with a "parent-child relation"
	 *			* Applications:
	 *				- Organization charts
	 *				- File Systems
	 *				- Programming environments
	 *
	 *			Tree Terminology
	 *				* Storing characters example from A to K
	 *				* Only one node has to have no parent: there is one root!!
	 *
	 *										A is Root: Node without a parent (A is Parent; A's parent is null)
	 *			0						[A]
	 *								   / | \			* Parent-Child relationship: Seen from A to B (the arrow at A-->B)
	 *								 /   |   \				- B has parent A (B is child of A)
	 *			1				  [B]   [C]   [D]		* Internal Node: Node with at least one child
	 *							 / \	 / \				- A-->B, A-->C, and B-->F 	(B and F have child nodes)
	 *					        /  \	/	\
	 *			2			  [E] [F]  [G]  [H]			* External Node (AKA Leaf): Node without Children
	 *							 / | \						- E, I, J, K, G, H, D
	 *						    /  |  \						
	 *			3			  [I] [J]  [K]				* Ancestors of a node: Itself, parent, grandparent, grand-grandparent, etc
	 *														- A, B, E (E is child, B is parent of E, A is grandparent of E)
	 *													* Descendant of a node: Itself, child, grand child, grand-grand child, etc
	 *														- Same as Ancestor but forwards from A to E
	 *													* Size is the number of nodes in the tree
	 *													* Depth of node: The number of edges from the root of the node
	 *														- Depth is distance from the root to the node
	 *														- The root is at depth 0
	 *														- Depth at C, d(C) = 1 (1 hop to C)
	 *														- d(E) is 2
	 *													* Height of tree: Maximum depth of any node, H = 3
	 *													* SubTree: Tree consisting of a node and its descendants
	 *														- Example, C has 2 child nodes: G and H, which makes it a subtree
	 *			**Difference between tree and graph, a tree cannot have a cycle (cannot go from C->G->H->C which is a cycle)
	 *
	 *			Tree Interface
	 *				class treeNode {
	 *					data				//data stores values for the left and right nodes (data is like element in LinkedLists)
	 *					parent
	 *					children			//Similar to Linked List, Parent and Children are Node<E>
	 *				----------------
	 *					getParent()
	 *					setParent()
	 *					getChildren()
	 *					setChildren()
	 *					numChildren()
	 *				}
	 *
	 *				class Tree{
	 *					root			// First thing you need is always a root (mandatory)
	 *					size			// Size is number of nodes in the tree			//** NOTE: Don't necessarily need size and height
	 *					height			
	 *				----------------
	 *					root()
	 *					size()
	 *					isEmpty()
	 *					isInternal(node)
	 *					isExternal(node)
	 *					isRoot(node)
	 *					traversal()
	 *				}
	 */
	
	/**	Traverse a Tree (Have to use Recursive-based approach**)
	 * 			* In-Order (left-->root-->right)
	 * 					Algorithm TraverseTreeInOrder(Node root)	//Starting with root (check the left first, and then the root, then the right)
	 * 						TraverseTreeInOrder(root.left)
	 * 						System.out.println(root.data)
	 * 						TraverseTreeInOrder(root.right)
	 * 				- In-Order: from left->root->right (starting from root, go to the left node, until there are no left nodes at that branch))
	 * 					- Visit the left node first, then the root node, and then the right node
	 * 
	 * 			* Pre-Order (root-->left-->right)
	 * 					Algorithm TraverseTreePreOrder(Node root)	//Starting with root (check the root first, and then the left, then the right)
	 * 						System.out.println(root.data)
	 * 						TraverseTreeInOrder(root.left)
	 * 						TraverseTreeInOrder(root.right)
	 * 
	 * 			* Post-Order (left-->right-->root)
	 * 					Algorithm TraverseTreePostOrder(Node root)	//Starting with root (check the left first, and then the right, then the root)
	 * 						TraverseTreeInOrder(root.left)
	 * 						TraverseTreeInOrder(root.right)
	 * 						System.out.println(root.data)
	 * 
	 * 		PreOrder: root-->left-->right
	 * 					Algorithm PreOrder(v)		// v is root
	 * 						visit(v)
	 * 						for each child w of v
	 * 							preOrder(w)
	 * 
	 * 			* In preOrder traversal, a node is visited before* its descendants
	 * 			***NOTE: When you start operating on a tree, you start at the root first, not the children**
	 * 
	 * 										[	Make Money Fast		]		1 (pre-order first at root)
	 * 										/			|			\
	 * 						2		[Motivations]	[Methods]	5	[References]  9
	 * 							 /		\				/ | \
	 * 			3		[Greed]		4	[Avidity]	  /   |	  \
	 * 												[M1] [M2] [M3]
	 * 												6     7     8
	 * 
	 * 			* In this traversal, first start at root, then visit motivations, will then visit Greed node due to the order
	 * 			* NOTE: we can order the siblings (child) visit: can go to Avidity first instead of Greed after Motivations, because
	 * 					Motivations is a child node
	 * 			* NOTE: Must always start at root, not children
	 * 
	 * 		PostOrder: left-->right-->root
	 * 					Algorithm postOrder(v)		//v is root
	 * 						for each child w of v
	 * 							postOrder(W)
	 * 						visit(v)
	 * 
	 * 			* In post order traversal, a node is visited after* its descendants
	 * 			* NOTE: Still start at root**
	 * 
	 * 										[	Make Money Fast		]		9 (pre-order first at root)
	 * 										/			|			\
	 * 						3		[Motivations]	[Methods]	7	[References]  8
	 * 							 	/	\				/ | \
	 * 			1			[Greed]	  2	[Avidity]	  /   |	  \
	 * 												[M1] [M2] [M3]
	 * 												4     5     6
	 * 
	 * 		In-Order: left-->root-->right
	 * 
	 * 									[	Make Money Fast		]		4 (pre-order first at root)
	 * 										/					\	
	 * 						2		[Motivations]			[Methods]	7	
	 * 							 	/	\						/  \
	 * 			1			[Greed]	  3	[Avidity]	 		 /   	  \
	 * 														[M1] 	 [M3]
	 * 														5          6
	 */
	
	/**		Binary Tree
	 * 		
	 * 		Height						
	 * 			0						[A]
	 *								   / | \		
	 *								 /   |   \		
	 *			1				  [B]   [C]   [D]			* Will have traversal of Log(n) because divide by 2
	 *							 / \	 / \		
	 *					        /  \	/	\
	 *			2			  [E] [F]  [G]  [H]	
	 *							 / | \
	 *						    /  |  \						
	 *			3			  [I] [J]  [K]	
	 * 											
	 * 
	 * 			Binary Tree: Same as Tree, but each internal node has AT MOST two children (exactly two for proper binary trees)
	 * 			* Applications
	 * 				- Arithmetic Expressions
	 * 				- Decision Processes
	 * 				- Searching
	 * 
	 * 
	 * 			Arithmetic Expression Tree
	 * 				Binary tree associated with arithmetic expression
	 * 					- Internal nodes: operators
	 * 					- External nodes: operands
	 * 				Ex: arithmetic expression tree for expression
	 * 							(2 X (a - 1) + (3 X b))
	 * 
	 * 			0						[+]
	 *								   /   \		
	 *								 /       \		
	 *			1				  [X]        [X]			* Will have traversal of Log(n) because divide by 2
	 *							 / \		  / \		
	 *					        /  \		 /	 \
	 *			2			  [2] [-]  		[3]  [b]	
	 *							 /  \
	 *						    /    \						
	 *			3			  [a]   [1]		
	 *
	 *
	 *			Decision Tree
	 *				* Binary tree associated with boolean answers (yes/no)
	 *					- Internal Nodes: questions with yes/no answer
	 *					- External Nodes: decisions
	 *
	 *			0						[Fast Meal?]
	 *							yes	   /   			\	no
	 *								 /       		  \		
	 *			1			[Coffee?]       		 [On expense account?]
	 *					yes	 /	 	\	no 			yes	 / 			\		no
	 *					    /  		 \	 				/	 		 \
	 *			2		[Starbucks] [Chipotle]		[Gracie's]  	[Gracie's]
	 *
	 *			Proper Binary Tree: Every internal node has exactly two children (every parent has exactly 2 children)
	 *				- Let 
	 *					* n: num of nodes									
	 *					* e: num of external nodes
	 *					* i: num of internal nodes
	 *					* h: height		
	 *
	 *				- NOTE Properties of Binary Tree:
	 *					1) e = i + 1 (num of external nodes is num of internal + 1)
	 *					2) n = 2e - 1 (total number of nodes)
	 *					3) h <= i (height of tree could be less/equal to the number of internal nodes)	
	 *					4) h <= (n-1)/2
	 *					5) e <= 2^h					// Number 5 introduces the log_e (e is base)
	 *					6) h >= log_2(e)
	 *					7) h >= log_2(n + 1) - 1	// 6 and 7 will show worst time complexity b/c binary tree keeps branching out
	 *
	 *				- Types of Binary Trees
	 *						[]								  []
	 *					  /	   \							 / \
	 *					[]		[]							[]	[]
	 *				  /	 \  	/ \								/ \
	 *				[]	  []   []	[]						  []	[]
	 *															   /  \
	 *															 [] 	[]
	 *			
	 *		Binary Tree Interface
	 *				class Binary TreeNode{
	 *					data
	 *					//Children
	 *					leftChild
	 *					rightChild			//Similar to Linked List, Parent and Children are Node<E>
	 *				----------------
	 *					...
	 *					//getChildren()
	 *					//setChildren()
	 *					getLeftChild
	 *					setLeftChild
	 *					getRightChild
	 *					setRightChild
	 *					...
	 *				}
	 *
	 *				class Tree{
	 *					root			// First thing you need is always a root (mandatory)
	 *					size			// Size is number of nodes in the tree			//** NOTE: Don't necessarily need size and height
	 *					height
	 *				----------------
	 *					root()
	 *					size()
	 *					isEmpty()
	 *					insert(data)		//Inserts a new data element in the tree
	 *					isInternal(node)
	 *					isExternal(node)
	 *					isRoot(node)
	 *					traversal()
	 *					...
	 *				}
	 *
	 *		InOrder Traversal
	 *			Algorithm inOrder(v)
	 *				if left(v) != null
	 *					inOrder(left(v))
	 *				visit(v)
	 *				if right(v) != null
	 *					inOrder(right(v))
	 *
	 *			* In an inOrder traversal, a node is visited after its left subtree and before its right subtree**
	 *								 6
	 *								 [A]
	 *							2	/	\	8
	 *							[B]		  [C]		
	 *						1  /  \	4	7 /  \	9
	 *						[D]	  [E]	[H]	  [I]
	 *							  /  \
	 *							[F]  [G]
	 *							3	 5
	 *
	 *
	 *		Print and Evaluate Arithmetic Expression (similar to InOrder traversal)
	 *				printExpression(v):
	 *					if left(v) != null:
	 *						print("(");
	 *						inOrder(left(v));
	 *					print(v.element());
	 *					if right(v) != null:
	 *						inOrder(right(v));
	 *						print(")");
	 *
	 *				evalExpression(v):
	 *					if isExternal(v):
	 *						return v.element;
	 *					else
	 *						x = evalExpression(left(v));
	 *						y = evalExpression(right(v));
	 *						* = operator stored at v
	 *					return x * y;
	 *		
	 *		Implementation of Binary Tree
	 *				Array-Based Representation of Binary Trees
	 *					* Node v is stored at A[rank(v)]		// v will be root first
	 *						- rank(root) = 0
	 *						- If node is left child of parent(node)
	 *								rank(node) = 2 * rank(parent(node)) + 1
	 *						- If node is right child of parent(node)
	 *								rank(node) = 2 * rank(parent(node)) + 2
	 *
	 *								0
	 *		0						 [A]
	 *							1	/	\	2
	 *		1					[B]		  [C]		
	 *						3  /  \	4	5 /  \	6
	 *		2				[D]	  [E]	[H]	  [I]
	 *							  /  \
	 *		3					[F]  [G]				// NOTE will be 9 and 10, because 7 and 8 are null nodes
	 *							9	 10
	 *
	 *			**NOTE: Equation for traversing a binary tree
	 *				* NOTE that the root changes depending on whether a child node has child nodes
	 *					- if root.hasLeft(), then leftNode will be root
	 *
	 *				* Binary Tree is branching out from root (creates 2 branches, so must multiply by 2)
	 *					- Each branch is duplicated twice, so multiply by 2
	 *				* Use the root, multiply by 2 and then add 1 or 2 for left or right child
	 *					root * 2 + 1 for left children
	 *					root * 2 + 2 for right children			(root * 2) + 1 + ((-1)^(indx+1))
	 *				
	 *			Finding the parent node (root) of the children is:		
	 *			**Root is root / 2			(if in an array, then the Root is (root-1)/2 )
	 *			If node is stored at index k, then its children:
	 *			**Left is 2k + 1
	 *			**Right is 2k + 2
	 *			
	 *		Array Representation if Binary Trees
	 *					
	 *				 0  1  2  3  4  5  6  7  8  9 10 11
	 *				[A][B][C][D][E][H][I][ ][ ][F][G][ ]
	 *
	 *								 0
	 *		0						 [A]
	 *							1	/	\	2
	 *		1					[B]		  [C]		
	 *						3  /  \	4	5 /  \	6
	 *		2				[D]	  [E]	[H]	  [I]
	 *							  /  \
	 *		3					[F]  [G]				// NOTE will be 9 and 10, because 7 and 8 are null nodes
	 *							9	 10
	 *
	 *
	 *			Comparison If it's Linked Structure or Array Structure
	 *
	 *				* Linked Structure
	 *					- Do not waste any additional space 
	 *						* Will waste additional space b/c will require  space for left and right nodes
	 *					- Requires explicit representation of 3 links per position:
	 *						* parent, leftChild, rightChild
	 *					- Data Structure grows as needed, no wasted space
	 *
	 *				* Array
	 *					- Parent and Children are implicitly represented
	 *						* Lower memory requirements per position
	 *					- Memory requirements determined by the height of tree. If tree is parse, this is highly inefficient
	 */
	
//	private static class treeNode<E>{
//		E element;
//		treeNode<E> parent;
//		treeNode<E> children;
//		public treeNode<E> getParent(){return parent;}
//		public void setParent(treeNode<E> parent) {this.parent = parent;}
//		public treeNode<E> getChildren(){return children;}
//		public void setChildren(treeNode<E> children) {this.children = children;}
//		//public int numChildren() {return}
//	}
//	
//	private static class Tree{
//		
//	}
	
	public static void main(String[] args) {					/**Creating two types of Binary Trees: one using Linked Lists (recursive)*/
		LinkedBinaryTree binTree = new LinkedBinaryTree(4);		/**And one using array (non-recursive); fixed size*/
		binTree.insert(2); binTree.insert(8);
		
		//binTree.printTree();
		
		//Array Binary Tree (not working)
		ArrayBinaryTree arrTree = new ArrayBinaryTree(20);
		arrTree.insert(2); arrTree.insert(5); arrTree.insert(1); arrTree.insert(8); arrTree.insert(4); arrTree.insert(3); arrTree.insert(6);
		//arrTree.increaseCapacity();
		arrTree.printBinaryTree();
		
	}
	
	private static class TreeNode{
		TreeNode leftChild;
		TreeNode rightChild;
		int element;
		public TreeNode() {
			this(0);
		}		
		public TreeNode(int element) {
			this.element = element;			
		}
	}
	
	private static class LinkedBinaryTree{
		TreeNode root;
		int size;
		public LinkedBinaryTree(int element) {
			insert(element);
		}
		public void insert(int element) {
			root = insertHelper(element, root);
		}
		public TreeNode insertHelper(int element, TreeNode node) {
			if(node == null) {
				node = new TreeNode(element);
			}else if(node.element < element) {
				node.rightChild = insertHelper(element, node.rightChild);
			}else {
				node.leftChild = insertHelper(element, node.leftChild);
			}
			return node;
		}
		
		public void printTree() {
			printTreeHelper(root);
		}
		private void printTreeHelper(TreeNode node) {	//Post-Order
			if(node != null) {
				System.out.print(node.element + " ");
				printTreeHelper(node.leftChild);
				printTreeHelper(node.rightChild);				
			}
		}
	}

	private static class ArrayBinaryTree{
		int[] data;						//Parent = (childIndex - 1)/ 2
		int size;						//LeftChild = (2 * rootIndex) + 1
		int root = 0;					//RightChild = (2 * rootIndex) + 2
				
		public ArrayBinaryTree(int size) {
			this.size = size;
			data = new int[this.size];
		}
		
		public void insert(int element) {
			insertHelper(element, root);
		}
		private int insertHelper(int element, int rootIndex) {
			int left = (2 * rootIndex) + 1;
			int right = (2 * rootIndex) + 2;
			
			if(rootIndex > size) {return element;}
			
			if(data[rootIndex] == 0) {
				data[rootIndex] = element;
			}else if(element > data[rootIndex]) {
				//data[right] = element;
				insertHelper(element, right);
			}else {
				//data[left] = element;
				insertHelper(element, left);
			}
			return data[rootIndex];
		}
		
		public void increaseCapacity() {
			this.size *= 2;;
			int[] newData = new int[this.size];
			for(int n = 0; n < data.length; n++)
				newData[n] = data[n];
			data = newData;
		}
		
		public void printBinaryTree() {	//InOrder Traversal
			for(int i = 0; i < data.length; i++) {
				System.out.print(data[i] + " ");
			}
		}
	}
	
}
