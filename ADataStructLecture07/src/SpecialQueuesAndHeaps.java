
public class SpecialQueuesAndHeaps {

	/**
	 * 		Stacks
	 * 			* Stack ADT stores arbitray objects
	 * 			* Insertions and deletions follow LIFO scheme
	 * 
	 * 		public interface Stack<E>{
	 * 			int size();
	 * 			boolean isEmpty();
	 * 			void push();
	 * 			E top();
	 * 			E pop();
	 * 		}
	 * 		// Could use java.util.Stack push and pop
	 * 
	 * 		public class ArrayStack<E> implements Stack<E>{
	 * 			private E[] data;
	 * 			private int top = -1;				//AKA t
	 * 			//Index of top element in stack
	 * 			
	 * 			public int size()
	 * 			public boolean isEmpty()
	 * 			public void push(E e)				//Pushes the front-most element (Last-In First-Out)
	 * 			public E pop()
 	 * 		}
 	 * 
 	 * 			Bottom (0),	Top is last index
 	 */ 
 	/** Queues
 	 * 		Array-Based Queue	(FIFO)
 	 * 
 	 * 			public class ArrayQueue<E> implements Queue<E>{
	 * 				/**Generic array used for storage of queue elements/
	 * 				private E[] data;
	 * 
	 * 				/**Index of the top element of the queue in the array/
	 * 				private int front = 0;		//AKA f 
	 * 
	 * 				/**Current number of elements in the queue/
	 * 				private int size = 0;		//AKA sz
	 * 				
	 * 				public int size()
	 * 				public boolean isEmpty()
	 * 				public void enqueue(E e) throws IllegalStateException
	 * 				public E first()
	 * 				public E dequeue()					//Pushes the first-most element out (First-In First-Out)
	 * 	
	 * 			}
	 * 
	 * 				(f)									(r)
	 * 				0 	1	2	3	4	5	6	7	8	9
	 * 				[]	[]	[]	[]	[]	[]	[]	[]	[]	[]
	 * 		
	 * 		/**Enqueue for ***inserting new elements at the top of the Queue
	 * 			Algorithm enqueue(o):			//Object o is of generic type
	 * 				if size() = N - 1 then:
	 * 					throw new IllegalStateException()
	 * 				else:
	 * 					r = (f + sz) mod N		//modulus function to keep the size of the array from 0 to N
	 * 					Q[r] = o				//r is being calculated with modulus of N
	 * 					sz = sz + 1;			//Increment size
	 * 
	 * 		
	 * 		/**Dequeue for ***deleting first element of the Queue
	 * 			Algorithm dequeue():
	 * 				if isEmpty() then:
	 * 					return null
	 * 				else:
	 * 					o = Q[f]				//The object will be at the front of the generic array (at the start)
	 * 					f = (f + 1) mod N		//Change the front to (f + 1) % N  (N is size of the Queue)
	 * 					sz = sz - 1;			//Decrease size by one
	 * 					return o;				//Return the removed first object
	 */
	
	/**		Improvements on the Stack and Queues: Sorting Stacks and Queues
	 * 
	 * 	Priority Queue 		(mixture of properties of queues and properties of sorting)
	 * 				* Elements inside the queue are sorted***
	 * 
	 * 			Priority Queue stores collection of entries**
	 * 				Each entry is a pair(key, value)
	 * 				Allow for efficient insertion and removal based on keys
	 * 
	 * 			**Using the keys to access the elements (dequeue at a specific key or specific value)
	 * 			class MinPriorityQueue{
	 * 				a collection of entries
	 * 			---------------------------
	 * 				size();
	 * 				isEmpty();
	 * 				insert(K key, V value);
	 * 				min();
	 * 				removeMin();
	 * 			}
	 * 			
	 * 			class Entry{
	 * 				key value
	 * 			-----------------
	 * 				getKey();
	 * 				getValue();
	 * 			}
	 * 
	 * 			Additional Properties
	 * 				* Keys can be arbitrary objects on which
	 * 
	 * 				Comparative Property: either X<=Y or Y>=X (compare the values of the attributes of objects Simple!!!)
	 * 				Antisymmetry Property: either X<=Y and Y<=X -->  X==Y
	 * 				Transitive Property: either X<=Y and Y<=Z --> X<=Z 
	 * 
	 * 				* A Comparator encapsulates the action of comparing two objects according to a given total order of relations
	 */
	
	/*			Comparator example: Lexicographic
	 * 				* NOTE: This example utilizes Point2D class which represents 2D coordinates in x-y Cartesian
	 * 		
	 * 				public class Lexicographic implements Comparator{
	 * 					int xa, ya, xb, yb;
	 * 					public int compare(Object a, Object b){
	 * 						xa = ((Point2D) a).getX();
	 * 	 					ya = ((Point2D) a).getY();
	 * 	 					xb = ((Point2D) b).getX();
	 * 	 					yb = ((Point2D) b).getY();
	 * 
	 * 						if(xa != xb)
	 * 							return (xb > xa);
	 * 						else
	 * 							return (yb >= ya);
	 * 					}
	 * 				}
	 */
	
	/**			Sequence-Based Priority Queue (2 approaches) **Remove min value
	 * 
	 * 			* Controlling where you are inserting the elements (similar to ArrayList)
	 * 			* Sort the Queue by comparing two elements**
	 * 			* After Priority Queue has been sorted, before, finding the min took O(n) time since it iterates through the entire queue
	 * 				after the queue is sorted, the min can be found in o(1) time
	 * 				
	 * 				1) Implemented with an Unsorted List	[4]->[5]->[2]->[3]->[1]
	 * 					* removeMin removes 1 and returns 1 in the list
	 * 					***Min priority queue: ascending order: to order the list, find the minimum first, and then sort 
	 * 
	 * 					- insert(K key, V value) takes O(1) time since we can insert the item at the beginning or end of sequence
	 * 					- removeMin and min take O(n) time since have to traverse the entire sequence to find smallest key
	 * 
	 * 
	 * 				2) Implement with a sorted list 		[1]->[2]->[3]->[4]->[5]
	 * 					
	 * 					- insert(K key, V value) takes O(n) time since we can find the place where to insert the item
	 * 					- removeMin and min take O(1) time since smallest key is at beginning
	 * 						**Smallest element will be at the front part of the Queue (first)
	 * 
	 * 
	 * 			Application Priority Queue Sorting
	 * 				Use priority queue to sort a list of comparable elements
	 * 							insert()	and 	removeMin()
	 * 				
	 * 			***IDEA: Grabbing every element from S and inserting them into list P*** (similar to Stack and Queue)
	 * 
	 * 				Algorithm PQ-Sort(S, C)
	 * 					Input: list S, comparator C for the elements of S
	 * 					Output: list S sorted in increasing order according to C
	 * 
	 * 					P <- priority queue with comparator C (store priority queue in P)
	 * 					while !S.isEmpty()					//O(n)
	 * 						e <- S.remove(S.first())
	 * 						P.insert(e, 0)				//NOTE: insert(key, value)
	 * 					while !P.isEmpty()					//O(n^2)
	 * 						e <- P.remove().getKey()
	 * 						S.addLast(e)
	 * 
	 * 				* Running time of this sorting method depends on the priority queue implementation
	 * 				* Using arrays will be O(n) for the first while loop and O(n^2) time for second while loop (will
	 * 						be shifting between S and P data structures)
	 * 				* Will be O(n^2) as worst time complexity
	 * 
	 * 				Dictionaries in Python: using key and value (will be O(1) since can access any element anywhere)
	 * 					- Using key value approach: can be O(1) time
	 * 
	 * 			Taking every element from S and inserting them into list P
	 * 
	 * 		Example:		S	[1][9][0]
	 * 
	 *  					P	[1][9][0]	***P has properties of priority queue
	 *  							**min/removeMin() will result in sorted S
	 *  								|
	 *  								V
	 *  						S	[0][1][9]
	 *  
	 *  					* Sorted a list using priority queue in O(n)
	 *  
	 *  			*If using Linked Lists, we are using Pointer Shuffling
	 */
	
	/**		Heap
	 * 			Heaps are trees (not binary trees because parent has higher/lower value than children: Min Heap and Max Heap)
	 * 				Goal:
	 * 					* O(log_2(n)) insertion
	 * 					* O(log_2(n)) removal			
	 * 
	 * 			Remember that O(log(n)) is almost as good as O(1)
	 * 
	 * 				There are min heaps and max heaps
	 * 				We will assume min heaps
	 * 
	 * 			
	 * 	A min Heap
	 * 		- Binary tree storing keys at its nodes and satisfying the following properties:
	 * 				* Heap-Order: For every internal node v other than the root
	 * 						key(v) <= key(children(v))		* The parent node must have value that's smaller than child node
	 * 
	 * 				**In Max Heap, the order will be: 
	 * 						key(v) >= key(children(v)), so the root of each subtree and tree will be replaced if the newest node has a greater value
	 * 				
	 * 				* (Almost) Complete Binary Tree: Let h be height of heap
	 * 						* Accessing the first element will be O(1)
	 * 						* Removing the first element (root), then will have to replace it with successor, this 
	 * 							will take O(log(n)) time
	 * 						* for i = 0, ..., h-1, there are 2^i nodes of depth i
	 * 						* at depth h-1
	 * 							- The internal nodes are to left of the external nodes
	 * 							- Only rightmost internal node may have single child
	 * 											[2]
	 * 											/ \
	 * 										[5]		[6]
	 * 										/ \
	 * 									[9]		[7]
	 * 											 ^The last node of a heap is the rightmost node of depth h
	 * 				* Last node always right, since it's traversal from left to right
	 * 
	 * 	Height of a Heap
	 * 			Theorem
	 * 				A heap storing n keys has height O(log(n))
	 * 				
	 * 			Let h be height of a heap storing n keys; since there are 2^i keys at depth i = 0, ..., h-1
	 * 				and at least one key at depth h, we have n >= 1 + 2 + 4 +... + 2^h-1 + 1
	 * 			Thus n >= 2^h, therefore h <= log_2(n)
	 * 
	 * 				depth		keys
	 * 				 0			1				 		 []
	 * 										     		/  \
	 * 				 1			2			 		 []	       []
	 * 												/  \	  /  \
	 * 				h-1			2^h-1	  		  []    []	[]	  []
	 * 											 /
	 * 				 h			1	  		   []
	 * 	
	 * 	Insertion into a Heap
	 * 			Insert algorithm has 3 steps:
	 * 					1) Find insertion node z (new last one): Find the position for the new node by referencing that last node
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
	 * 						[9]	  [7]	[1]<-- last node z
	 * 
	 * 								[1]
	 * 								/ \
	 * 							[5]		[2]					//Swap 1 and 6, and then 1 and 2
	 * 							/ \		/
	 * 						[9]	  [7]	[6]<-- last node z
	 * 
	 * 			The time complexity of UpHeap insertion will be O(log(n))
	 * 
	 * 							
	 * NOTE: Learn how to insert an element at a certain spot and print the element
	 * 
	 * 	Updating the Last Node
	 * 			The insertion node can be found by traversing a path of O(log(n)) nodes
	 * 				* NOTE: Will have the information of the last node inserted: find the left branch first, if there are no left branches,
	 * 					then search for right branches
	 * 
	 * 								  []
	 * 							   /     \
	 * 							[]		   []
	 * 							/ \		   / \
	 * 						[]	  []	  [] []
	 * 					   / \	 /  \    /
	 * 					 []	 [] []	[l]	[i]<-- new node Z
	 * 
	 * 
	 * 			* Using the last node that was inserted (the one labeled l), create a new node at the position:
	 * 					 i is the node that we will insert now 
	 * 
	 * 
	 * 	Removal from a Heap (remove 2)
	 * 		The removeMin algorithm has 3 steps:
	 * 			1) Replace the root key with the key of the last node w
	 * 			2) Remove w
	 * 			3) Restore the heap-order property (**DownHeap property**)
	 * 								[2]							//remove 2
	 * 								/ \
	 * 							[5]		[6]
	 * 							/ \		
	 * 						[9]	  [7]<-- last node w
	 * 
	 * 								[7]
	 * 								/ \
	 * 							[5]		[6]
	 * 							/ \	
	 * 						[9]	  []
	 * 						 ^last node z
	 * 
	 * 								[5]						//Swap 5 and 7 since this is minHeap
	 * 								/ \
	 * 							[7]		[6]
	 * 							/ \	
	 * 						[9]	  []
	 * 						 ^last node z
	 * 
	 * 			DownHeap runs in O(log(n)) time
	 * 		
	 * 
	 * 
	 * 		Heap: Array-based Heap Implementation
	 * 				Recall: Array-Based representation of Binary Trees
	 * 
	 * 						 0  1  2  3  4  5  6  7  8  9 10 11
	 *						[A][B][C][D][E][H][I][ ][ ][F][G][ ]
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
	 *		Array-Based Heap Implementation
	 *			* Heap is 'almost' a complete binary tree storing keys and its nodes in a satisfying Heap-order
	 *								 0
	 *		0						 [A]
	 *							1	/	\	2
	 *		1					[B]		  [C]		
	 *						3  /  \	4	5 /  \	6
	 *		2				[D]	  [E]	[H]	  [I]
	 *					    /  \
	 *		3			  [F]  [G]	<-The Last Node is the right-most node in the array
	 *					  7		8		
	 */
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
