
public class SearchTreesNotes {

	/*
	 *		1) Binary Search Trees
	 *		2) AVL Tree 
	 * 		3) Red-Black Tree		(self study: implement properties that AVL tree does)
	 */
	
	/**		Binary Search Tree
	 * 			* A binary tree storing keys (key-value entries) at its internal nodes and satisfying following property:
	 * 				- Let u, v and w be three nodes such that u is left subtree of v and w is right subtree of v.
	 * 						
	 * 									key(u) <= key(v) <= key(w)
	 * 
	 * 				- External nodes don't store items (to the left; lesser values, to the right; greater values)
	 * 											[6]
	 * 									 	/ 		 \
	 * 									[2]				[9]
	 * 								   /  \		  	   /    \
	 * 								[1]		[4]		 [8]	[]
	 * 								/ \     /   \    /  \	
	 * 							[]     []  []   []  []  []
	 * 											
	 * 				
	 * 				And inorder traversal visits keys in increasing order
	 * 
	 * 			Tree Search (recursive)						//Will be inOrder traversal (left->root->right) ascending order
	 * 				//TreeSearch(k, v)
	 * 				if(T.isExternal(v))						//If the tree at node v has no children (external: true if left and right == null of root node)
	 * 					return v;		//Did not find
	 * 				if (k < key)
	 * 					return TreeSearch(k, left(v))
	 * 				else if(k == key(v))
	 * 					return v
	 * 				else 						// k > key(v)
	 * 					return TreeSearch(k, right(v))
	 * 			
	 * 				Search for 4: Look for something smaller (go left); look for something greater (go right)	
	 * 
	 * 		Insertion: insert(5)
	 * 									5<6		[6]
	 * 									 	/ 		 \
	 * 									[2]				[9]
	 * 								   /  \	 5>2  	   /    \
	 * 								[1]		[4]		 [8]	[]
	 * 								/ \     /   \	 /  \	
	 * 							[]     []  []   []  []  []			//Insert 5 on 4's right child
	 * 
	 * 
	 * 									5<6		[6]
	 * 									 	/ 		 \
	 * 									[2]				[9]
	 * 								   /  \	 5>2  	   /    \
	 * 								[1]		[4]		 [8]	[]
	 * 								/ \     /   \5>4 /  \	
	 * 							[]     []  []   [5]  []  []
	 * 											/  \
	 * 										   []   []
	 * 
	 * 		* NOTE: If you remove a node that has null child nodes with no elements; won't be a problem (ex: delete 1)		
	 * 				HOWEVER: If remove node that has children, will affect the order to the tree (ex: delete 2)
	 * 
	 * 		Deletion: delete 4		
	 * 					* First search for 4 (left-->right-->found 4)
	 * 					* Replace 4 with successor: 5 
	 * 											[6]
	 * 									 	/ 		 \
	 * 									[2]				[9]
	 * 								   /  \	 	  	   /    \
	 * 								[1]		[4]		 [8]	[]
	 * 								/ \     /   \	 /  \	
	 * 							[]     []  []   [5]  []  []
	 * 											/  \
	 * 										   []   []
	 * 
	 * 											[6]
	 * 									 	/ 		 \
	 * 									[2]				[9]
	 * 								   /  \	 	  	   /    \
	 * 								[1]		[5]		 [8]	[]
	 * 								/ \     /   \	 /  \	
	 * 							[]     []  []   []  []  []
	 * 											
	 * 		Deletion: delete 3 (IDEA: replace the node with another node that is at the bottom of the tree)
	 * 					* Start with 1 (nothing smaller than 1)
	 * 					* First go left to see if the element is smaller than 3 (2<3, could replace 3 with 2, or could keep going right until there 
	 * 						is a left element)
	 * 					* Must check for the last node that was put into the tree, 5 was the last node since it's at the depth of the tree
	 * 					* Ascending approach:
	 * 											[1]
	 * 									 	 		 \
	 * 													[3]
	 * 								     	 	  	   /    \
	 * 												 [2]	 [8]
	 * 									 			/  \	/   \	
	 * 											   []  []  [6]	 [9]
	 * 													  /  \  /  \
	 * 													[5]  [] []  []
	 * 													/ \
	 * 												  []   []
	 * 											[1]
	 * 									 	 		 \							
	 * 													[5]						//Replace 3 with 5 since it was the last
	 * 								     	 	  	   /    \						node put into the tree (downheap)
	 * 												 [2]	 [8]
	 * 									 			/  \	/   \	
	 * 											   []  []  [6]	 [9]
	 * 													  /  \  /  \
	 * 													[]  [] []  []
	 * 
	 * 			* Must replace the element with node at the bottom of the tree (reach depth)	
	 * 
	 * 		Deletion NOTE:
	 * 			* Can either go left or go right until reaching the bottom of the tree
	 * 
	 * 		Performance:
	 * 			* Consider a size-n binary tree of height h
	 * 			* The space used is O(n)
	 * 			* search, insertion, deletion take O(h) time
	 * 			* The height h is:
	 * 				- O(n) in worst case
	 * 				- O(nlog(n)) is best case (balanced)
	 * 
	 * 			*Binary tree is best solution if				Worst case since checking each node will 
	 * 		everything branches out (see this below)		be n times (checking the entire height)	
	 * 				 		[]										  []
	 *					  /	   \									 / \
	 *					[]		[]									[]	[]
	 *				  /	 \  	/ \										/ \
	 *				[]	  []   []	[]								  []	[]
	 *																	   /  \
	 *																	 [] 	[]
	 *
	 *		Reconstruct the tree (change the root)
	 *					[1]
	 *						\
	 *						[2]							//This is a poor structure since searching will be O(n)
	 *							\
	 *							[3]
	 *								\								[3]
	 *								[9]								/ \					* This is improvement, but not
	 *									\						  [1]  [2]				good enough; the best approach is AVL tree
	 *									[10]											for reconstruction
	 */
	
	/*		AVL Tree		(internal: children-sibling relationship)
	 * 			* A balanced binary search tree such that for every internal node v of T, the heights of children 
	 * 				of v can differ by at most 1
	 * 			* We went from root-parent relationship to child-sibling relationship
	 * 										   [44]
	 * 									 	/ 		 \
	 * 								   [17]				[78]
	 * 								   /  \	 	  	   /    \
	 * 								[]	  [32]		 [50]	 [88]
	 * 								     /  \	 	 /  \	  /  \
	 * 									[]	 []	  [48]	[62] []	  []
	 * 											  /  \   /  \
	 * 											[]   [] []  []
	 * 			* If we insert element 63; difference is 1 (63-62), so there isn't much to do to restructure	
	 * 			* If insert element 66; 66-62 is 4, will lead to O(n) due to the size
	 * 
	 * 		Heights of the tree and subtree (44 will have height 4 because it has 3 subtrees + itself)
	 * 											4
	 * 										   [44]
	 * 									 2	/ 		 \	  3
	 * 								   [17]				[78]
	 * 								   /  \	1 	  	  2 /    \ 1
	 * 								[]	  [32]		 [50]	 [88]
	 * 								     /  \	 	1 /  \ 1  /  \
	 * 									[]	 []	  [48]	[62] []	  []
	 * 											  /  \   /  \
	 * 											[]   [] []  []
	 * 
	 * 		Height of AVL Tree
	 * 			* Height of AVL tree storing n keys is O(log(n))
	 * 			Proof by Induction: Let us bound n(h): minimum number of internal nodes of an AVL tree of height h
	 * 					* n(1) = 1 and n(2) = 2
	 * 					* For n > 2, and AVL tree of height h contains root node, one AVL subtree of height n - 1 and
	 * 						another of height n - 2
	 * 					* That is, n(h) = 1 + n(h - 1) + n(h - 2)
	 * 					* Knowing that n(h - 1) > n(h - 2), we get n(h) > 2n(h - 2)
	 * 					* n(h) > 2n(h - 2) > 4n(h - 4) > 8n(h - 8) > ... > (2^i * n(h - 2^i))
	 * 					* Solving base case results: n(h) > 2^(h/(2-1))
	 * 					* Taking Logarithms: log(n(h)) > h/2-1		will result in h < 2log(n(h)) + 2
	 * 					* Thus height of AVL tree is O(log(n))			
	 * 
	 */ 
	/** 		Insertion NOTE: The BEST Data Structure to use for Insertion Trinode Restructuring is Linked Lists
	 * 			* Insertion is as in a binary tree
	 * 											3
	 * 											[7]
	 * 									2 	/ 		 \	1
	 * 								   [4]				[8]
	 * 								 1 /  \	1 	  	 0 /    \ 0
	 * 								[3]	  [5]		 []	 	 []
	 * 							0 /   \0  0/  \ 0
	 * 							[] 	  [] []   []
	 * 
	 * 			* Insert 2
	 * 											4
	 * 								z->			[7]
	 * 									3 	/ 		 \	1
	 * 							y->	   [4]				[8]				**Problem between [4] and [8], they're unbalanced
	 * 								 1 /  \	1 	  	 0 /    \ 0				because instead of 1 and 2 subnodes (from right to left), it's now 1 and 3
	 * 						X->		[3]	  [5]		 []	 	 []			* Need the subnodes to be evenly spaced out (have a difference of at most 1)
	 * 							1 /   \0  0/  \ 0
	 * 							[2]	  [] []   []
	 * 						  0	/ \ 0
	 * 						  []   []
	 * 				
	 * 				Search: Starting at the inserted node, traverse toward the root until an imbalance is discovered
	 * 				Repair (trinode restructuring): 3 nodes x, y, and z are distinguished (look at tree)
	 * 					z = parent of high sibling
	 * 					y = the high sibling
	 * 					x = the high child of the high sibling
	 * 
	 * 
	 * 			Insertion: Trinode Restructuring: Case 1: x <= y <= z
	 * 							
	 * 							h	
	 * 							[z]
	 * 					h-1	   /	\
	 * 						[y]		[T3] h-3
	 * 				h-2    /	\
	 * 					[x]		[T2] h-3
	 * 					/  \
	 * 				  [T0]  [T1]
	 * 
	 * 				Will be restructured to
	 * 							h-1
	 * 							[y]
	 * 					h-2	   /   \	h-2
	 * 						[x]		 [z]
	 * 						/ \     /   \
	 * 					 [T0] [T1] [T2] [T3] 
	 * 								h-3	 h-3
	 * 
	 * 			Insertion: Trinode Restructuring: Case 2: z <= y <= x
	 * 							
	 * 							h	
	 * 							[z]
	 * 							/	\   h-1	   
	 * 						  [T0]	[y]
	 * 						h-3		/	\	h-2   
	 * 							  [T1]	[x]
	 * 							h-3		/ \
	 * 								  [T2]	[T3]
	 * 
	 * 							h-1
	 * 							[y]
	 * 					h-2	   /   \	h-2
	 * 						[x]		 [z]
	 * 						/ \     /   \
	 * 					 [T0] [T1] [T2] [T3] 
	 * 					h-3	   h-3
	 * 
	 * 			Insertion: Trinode Restructuring: Case 3: y <= x <= z
	 * 							
	 * 							h	
	 * 							[z]
	 * 					   h-1 /   \
	 * 						[y]		[T3] h-3
	 * 						/  \ h-2   
	 * 				h-3	 [T0]	[x]
	 * 							/ \
	 * 						[T1]  [T2]
	 * 
	 * 							h-1
	 * 							[x]
	 * 					h-2	   /   \	h-2
	 * 						[y]		 [z]
	 * 						/ \     /   \
	 * 					 [T0] [T1] [T2] [T3] 
	 * 					h-3	   			 h-3
	 * 
	 * 			Insertion: Trinode Restructuring: Case 4: z <= x <= y
	 * 							
	 * 							h	
	 * 							[z]
	 * 					   	   /   \	h-1	   
	 * 					h-3	[T0]	[y]
	 * 							h-2 /  \
	 * 							[x]    [T3] h-3
	 * 							/ \
	 * 						 [T1] [T2] 
	 * 
	 * 							h-1
	 * 							[x]
	 * 					h-2	   /   \	h-2
	 * 						[y]		 [z]
	 * 						/ \     /   \
	 * 					 [T0] [T1] [T2] [T3] 
	 * 					h-3	   			 h-3
	 * 
	 * 	Insertion: Trinode Restructuring - The Whole Tree
	 * 				* Do we have to repeat this process further up the tree? NO***
	 * 					- Tree was balanced before insertion
	 * 					- Insertion raised height of subtree by 1
	 * 					- Re-balancing lowered height of subtree by 1
	 * 					- Thus whole tree is balanced
	 * 
	 * 	Deletion: delete 8
	 * 											 3
	 * 										 z->[7]
	 * 									2 	/ 		 \	1
	 * 								y->[4]				[8]
	 * 								 1 /  \	1 	  	 0 /    \ 0
	 * 							x->[3]	  [5]		 []	 	 []
	 * 							0 /   \0  0/  \ 0
	 * 							[] 	  [] []   []
	 * 
	 * 											 3
	 * 										 z->[7]
	 * 									2 	/ 		 \	0
	 * 								y->[4]				[]			* There'll be a problem because right and left (0 and 2 subtrees)
	 * 								 1 /  \	1							are unbalanced (differ by factor 2)
	 * 							x->[3]	  [5]
	 * 							0 /   \0  0/  \ 0
	 * 							[] 	  [] []   []
	 * 	
	 * 		Search: 
	 * 			* Let w be node actually removed (node matching the key if it has a leaf child, otherwise the node following 
	 * 				in an in-order traversal)
	 * 			* Starting at w, traverse toward the root until an imbalance is discovered
	 * 
	 * 		Repair (trinode restructuring): 3 nodes x, y, and z are distinguished (look at tree)
	 * 			z = parent of high sibling
	 * 			y = the high sibling
	 * 			x = the high child of the high sibling
	 * 
	 * 	Deletion: Trinode Restructuring
	 * 
	 * 							h	
	 * 							[z]
	 * 					h-1	   /	\
	 * 						[y]		[T3] h-3
	 * 				h-2    /	\
	 * 					[x]		[T2] h-3
	 * 					/  \
	 * 				  [T0]  [T1]
	 * 
	 * 				Will be restructured to
	 * 							h-1
	 * 							[y]
	 * 					h-2	   /   \	h-2
	 * 						[x]		 [z]
	 * 						/ \     /   \
	 * 					 [T0] [T1] [T2] [T3] 
	 * 									 h-3
	 * 
	 * 		Do we have to repeat this process further up the tree? YES***
	 * 			* Unfortunately, trinode restructuring may reduce height of subtree, causing another imbalance further up the tree
	 */
	
	/*	AVL Tree Performance
	 * 		Suppose AVL tree storing n items
	 * 			* Data structure uses O(n) space
	 * 			* Single restructuring takes O(1) time
	 * 				- Using a linked-structure binary tree
	 * 			* Searching takes O(log(n)) time
	 * 				- Height of tree is O(log(n)), no restructures needed 
	 * 			* Insertion takes O(log(n)) because it searches upwards on the branches
	 * 				- Initial find is O(log(n))
	 * 				- Restructuring up the tree, maintaining heights, takes O(1) time
	 * 			* Removal takes O(log(n)) time
	 * 				- Initial find is O(log(n))
	 * 				- Restructuring up the tree, maintaining heights, takes O(1) time
	 */
	
	/**	Red Black Tree (same as AVL Tree)
	 * 		Rotations: 
	 * 			==>Right-Rotation; <==left-rotation (rotate x and y)
	 * 			
	 * 				[y]						[x]
	 * 			   /   \				   /    \
	 * 			[x]		[T2]			[T0]	[y]
	 *  	    /  \							/  \
	 * 		[T0]	[T1]					[T1]	[T2]
	 * 
	 * 	NOTE: The right image was the left image rotated
	 * 
	 * 		* Right-Rotate: Old-root becomes right child of the new root
	 * 		* Left-Rotate: Old-root becomes left child of the new root
	 * 
	 * 	Left-Rotation (Right-Rotate pseudocode is symmetrical)
	 * 
	 * 		//LeftRotate(Node x)
	 * 		y = x.right									//Node y will be the new root of the rotated subtree
	 * 
	 * 		//Build linked between x and T1
	 * 		x.right = y.left							//Move node y's left child to node x's right child 
	 * 
	 * 		if(y.left is not null)						//If node y has a left child, update its pointer	
	 * 			y.left.parent = x			
	 * 
	 * 		//Build links between y and x.parent		//Update Pointers:
	 * 		y.parent = x.parent								//y inherits x's parent
	 * 		if(x is root)
	 * 			root = y									//x's parent will be y
	 * 		else if x is the left child
	 * 			x.parent.left = y
	 * 		else
	 * 			x.parent.right = y
	 * 
	 * 		y.left = x
	 * 		x.parent = y
	 * 
	 * 	
	 * Right-Rotation
	 * 		//RightRotate(Node y)
	 * 		x = y.left
	 * 
	 * 		//Build linked between x and T1
	 * 		y.left = x.right
	 * 		if(x.right is not null)
	 * 			x.right.parent = y
	 * 
	 * 		//Build links between y and x.parent
	 * 		x.parent = y.parent
	 * 		if(y is root)
	 * 			root = x
	 * 		else if y is the right child
	 * 			y.parent.right = x
	 * 		else
	 * 			y.parent.left = x
	 * 
	 * 		x.right = y
	 * 		y.parent = x
	 * 
	 * Red-Black Tree
	 * 		1. Every node has color; black or red
	 * 		2. Root and dummy leaves are colored black
	 * 		3. No red node is a parent of another red node
	 * 		4. Each root --> leaf path in the tree has the same number of black nodes (Black heights also count)
	 * 
	 * 										   [13]						Black: 13, 1, 11, 15, 25
	 * 									 	/ 		 \
	 * 								   [8]				[17]
	 * 								   /  \	 	  	   /    \
	 * 							   [1]	  [11]		 [15]	 [25]
	 * 							  /  \    /  \	 	 /  \	  /  \
	 * 							 []  [] []	 []	  []	[]  [22]  [27]
	 * 								/ \   				    /  \   /  \
	 * 							  []   [] 			       []  [] []  []
	 * 
	 * 
	 * 		NOTE: Must Solve the AVL tree cases: height, insert (count), Delete
	 * 		
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
