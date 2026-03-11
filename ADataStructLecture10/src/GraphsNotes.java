
public class GraphsNotes {
	/**
	 * 1) Graph
	 * 		Represent and Implementation of Graphs
	 * 2) Depth First Search (DFS)
	 * 3) Breadth First Search (BFS)
	 * 4) Shortest Path
	 * 		Dijstra's Algorithm
	 * 5) Minimum Spanning Tree
	 * 		Prim's Algorithm
	 * 		Kruskal's Algorithm
	 */
	
	/**	Graph	(Recall that nodes in linked lists have an address)
	 * 		* graph G is a pair (V, E) where
	 * 			- V is set of nodes, called Vertices*
	 * 			- E is collection of pairs of vertices, called Edges*
	 * 	Note that vertices and edges are positions and store elements
	 * 		* Ex: In class, graph of students, each student is a vertices at an edge, each edge connects to the next student
	 * 
	 * 		* Directed Edge: ordered pair of vertices(u, v)
	 * 		* Undirected edge: unordered pair of vertices(u, v)
	 * 		* Directed graph (Digraph): All edges are directed (one vertex is specifically directed to another vertex/vertices)
	 * 		* Undirected graph: All edges are undirected			** Can have both
	 * 
	 * 				[S]
	 * 			  /		\
	 * 			 v       v			* Neither a directed or undirected graph (from Statesboro to Atlanta or Statesboro to Pooler)
	 *   		[P]-----[A]
	 *   
	 *   			[S]
	 * 			  /		\
	 * 			 /       \			* Cycle graph
	 *   		[P]-----[A]
	 *    
	 * 		
	 * 		EndPoints of an edge: (a) and (b) are endpoints of e_1
	 * 		Edges incident on a vertex: e_1 and e_3 are incident on (a)
	 * 		Adjacent vertices: (a) and (b) are adjacent
	 * 		Degree of vertex: (d) has degree 4				
	 * 		Parallel edges: e_2 and e_13 are parallel edges
	 * 		Self-Loop: e_12 is a self-loop
	 * 		
	 * 								(a)____        
	 * 								 |	   \ e_1		e_13	 _ e_12
	 * 								 |		\ 	   ____________	/ \
	 * 								 |		 \__  /		e_2	   \\ /
	 * 							e_3	 | 		  __(b)-------------(c)
	 * 								 | 		 /	   \_		  _/
	 * 								 |	 e_4/	  e_5\_ 	_/ e_6
	 * 								 |_____/  e_7	   \   /
	 * 								(d)-----------------(e)
	 * 								  \_____	  e_9 __/ \__
	 * 									e_8	\___   __/ 		 \__ e_10
	 * 											\ /		e_11	\
	 * 											(f)------------(g)
	 * 
	 * 
	 * 	Concepts: Paths and Cycles
	 * 
	 * 								(a)____ 
	 * 								 |	   \ e_1
	 * 								 |		\ 	 
	 * 								 |		 \__ 
	 * 							e_3	 | 		  __(b)-------------(c)
	 * 								 | 		 /	   \_		  _/
	 * 								 |	 e_4/	  e_5\_ 	_/ e_6
	 * 								 |_____/  e_7	   \   /
	 * 								(d)-----------------(e)
	 * 								  \_____	  e_9 __/ \__
	 * 									e_8	\___   __/ 		 \__ e_10
	 * 											\ /		e_11	\
	 * 											(f)------------(g)
	 * 
	 * 		Path: a sequence of alternating vertices and edges, begins with vertex, ends with a vertex
	 * 				a-b-c-e-b-c		**Note the repetition, optimize by not repeating
	 * 								
	 * 								(a)____ 
	 * 								 	   \ e_1
	 * 								 		\ 	 
	 * 								 		 \__ 
	 * 								  		  __(b)-------------(c)
	 * 								 		 /	   \_		
	 * 								 	 e_4/	  e_5\_ 	
	 * 								  _____/  e_7	   \   
	 * 								(d)-----------------(e)
	 * 
	 * 		Simple Path: Path such that all it's vertices and edges are distinct**
	 * 				a-b-c
	 * 								(a)____ 
	 * 									   \ e_1
	 * 										\ 	 
	 * 										 \__ 
	 * 										  	(b)-------------(c)
	 * 
	 * 		Cycle: circular sequence of alternating vertices and edges (avoid cycles; find simple cycles and then move onto more complex cycles)
	 * 				a-b-e-c-b-d-a
	 * 								(a)____ 
	 * 								 |	   \ e_1
	 * 								 |		\ 	 
	 * 								 |		 \__ 
	 * 							e_3	 | 		  __(b)-------------(c)
	 * 								 | 		 /	   \_		  _/
	 * 								 |	 e_4/	  e_5\_ 	_/ e_6
	 * 								 |_____/  		   \   /
	 * 								(d)					(e)
	 * 
	 * 		Simple Cycle: Cycle such that all its vertices and edges are distinct
	 * 				a-b-d-a
	 * 								(a)____ 
	 * 								 |	   \ e_1
	 * 								 |		\ 	 
	 * 								 |		 \__ 
	 * 							e_3	 | 		  __(b)
	 * 								 | 		 /	 
	 * 								 |	 e_4/	 
	 * 								 |_____/ 
	 * 								(d)
	 * 		
	 *	Concepts: Subgraphs and Trees
	 *	 
	 * 		Subgraph S = (E_s, E_s): V_s subset of V, and E_s subset of E	
	 * 
	 * 					
	 * 								(a)____ 
	 * 								 |	   \ e_1		subgraph({a, b, d}, {e_1, e_3, e_4})
	 * 								 |		\ 	 
	 * 								 |		 \__ 
	 * 							e_3	 | 		  __(b)
	 * 								 | 		 /
	 * 								 |	 e_4/
	 * 								 |_____/
	 * 								(d)
	 * 
	 * 		Spanning Subgraph S = (V_s, E_s): V_s = V, and E_s subset of E
	 * 
	 * 								(a)____ 
	 * 								 	   \ e_1		Spanning subgraph({a, b, c, d, e, f, g}, {red edges})
	 * 								 		\ 	 						//The red edges are the only visible ones here
	 * 										 \__ 			
	 * 								 		 	(b)-------------(c)
	 * 								  		 	   \_		
	 * 								 	 		  e_5\_ 	
	 * 								  		  e_7	   \   
	 * 								(d)-----------------(e)
	 * 								  			  e_9 __/ 
	 * 											   __/ 		 
	 * 											  /		e_11	
	 * 											(f)------------(g)
	 * 
	 * 
	 * 		Connected component: a maximal connected subgraph of G
	 * 								(a)____ 
	 * 								 |	   \ e_1
	 * 								 |		\ 	 
	 * 								 |		 \__ 
	 * 							e_3	 | 		  __(b)-------------(c)			//All Connected
	 * 								 | 		 /	   \_		  _/
	 * 								 |	 e_4/	  e_5\_ 	_/ e_6
	 * 								 |_____/  e_7	   \   /
	 * 								(d)-----------------(e)
	 * 								  \_____	  e_9 __/ \__
	 * 									e_8	\___   __/ 		 \__ e_10
	 * 											\ /		e_11	\
	 * 											(f)------------(g)
	 * 
	 * 								(a)____ 
	 * 								 |	   \ e_1
	 * 								 |		\ 	 
	 * 								 |		 \__ 
	 * 							e_3	 | 		  __(b)-------------(c)	   (h)		//Not Connected Component
	 * 								 | 		 /	   \_		  _/		|
	 * 								 |	 e_4/	  e_5\_ 	_/ e_6		|
	 * 								 |_____/  e_7	   \   /			|
	 * 								(d)-----------------(e)				|
	 * 								  \_____	  e_9 __/ \__			|
	 * 									e_8	\___   __/ 		 \__ e_10	|
	 * 											\ /		e_11	\		|
	 * 											(f)------------(g)	   (i)
	 * 
	 * 		Tree: a connected, acyclic, undirected graph****
	 * 								(a)____ 
	 * 								 |	   \ e_1
	 * 								 |		\ 	 
	 * 								 |		 \__ 
	 * 							e_3	 | 		  __(b)-------------(c)
	 * 								 | 		 /	   \_		  _/
	 * 								 |	 e_4/	  e_5\_ 	_/ e_6
	 * 								 |_____/  e_7	   \   /
	 * 								(d)-----------------(e)
	 * 								  \_____	  e_9 __/ \__
	 * 									e_8	\___   __/ 		 \__ e_10
	 * 											\ /		e_11	\
	 * 											(f)------------(g)
	 * 
	 * 								(a)____ 
	 * 								 	   \ e_1		Tree({a, b, c, d, e, f, g}, {red edges})
	 * 								 		\ 	 						//The red edges are the only visible ones here
	 * 										 \__ 			
	 * 								 		 	(b)-------------(c)
	 * 								  		 	   \_		
	 * 								 	 		  e_5\_ 	
	 * 								  		  e_7	   \   
	 * 								(d)-----------------(e)
	 * 								  			  e_9 __/ 
	 * 											   __/ 		 
	 * 											  /		e_11	
	 * 											(f)------------(g)
	 * 
	 * 				* Tree can have branch removed
	 * 								(a)____ 
	 * 								 	   \ e_1		Tree({a, b, c, d, e, f}, {red edges})
	 * 								 		\ 	 						//The red edges are the only visible ones here
	 * 										 \__ 			
	 * 								 		 	(b)-------------(c)
	 * 								  		 	   \_		
	 * 								 	 		  e_5\_ 	
	 * 								  		  e_7	   \   
	 * 								(d)-----------------(e)
	 * 								  			  e_9 __/ 
	 * 											   __/ 		 
	 * 											  /			
	 * 											(f)				(g)
	 *  
	 * 		Forest: A set of trees (not necessarily connected)
	 * 								(a)____ 
	 * 								 	   \ e_1		Forest({a, b, c, d, e, f, g}, {red edges})
	 * 								 		\ 	 						//The red edges are the only visible ones here
	 * 										 \__ 			
	 * 								 		 	(b)-------------(c)
	 * 								  		 	   \_		
	 * 								 	 		  e_5\_ 	
	 * 								  		  		   \   
	 * 												  (e)
	 * 								  			   
	 * 											   		 
	 * 											  		e_11	
	 * 											(f)------------(g)
	 * 
	 * 		Spanning Tree: A spanning subgraph that is a tree (every outer vertex is connected by a branch)
	 * 								(a)____ 
	 * 								 	   \ e_1
	 * 								 		\ 	 
	 * 								 		 \__ 
	 * 								  		  	(b)-------------(c)
	 * 								  		 	   \_	
	 * 								 	 		  e_5\_ 	
	 * 								  		  e_7	   \  
	 * 								(d)-----------------(e)
	 * 								  			  e_9 __/ 
	 * 											   __/ 		 
	 * 											  /		e_11	
	 * 											(f)------------(g)
	 * 				
	 * 
	 * Properties:
	 * 		property 1:
	 * 			sum of vertices deg(v) = 2 * abs(E)
	 * 				∑_v = deg(v) = 2|E|
	 * 
	 * 			proof: each edge is counted twice
	 * 
	 * 		property 2:
	 * 			In an undirected graph with no self-loops and no multiple edges
	 * 			|E| <= |V|(|V| - 1)/2			**Maximum value of the number of edges (connected with every vertex)
	 * 		
	 * 			proof: each vertex has degree at most (|V| - 1)
	 * 			
	 * 			Bound for a digraph is |E| <= |V|(|V| - 1)
	 */
	/*		Graph Interface
	 * 			public interface Graph<V, E>{
	 * 				numVertices();
	 * 				numEgdes();
	 * 
	 * 				outDegree(Vertex<V> v);
	 * 				inDegree(Vertex<V> v);
	 * 
	 * 				getEdge(Vertex<V> u, Vertex<V> v);
	 * 				endVertices(Edge<E> e);
	 * 
	 * 				insertVertex(Vertex<V> u);
	 * 				insertEdge(Edge<E> e);
	 * 
	 * 				removeVertex(Vertex<V> v);
	 * 				removeEdge(Edge<E> e);
	 * 			}
	 * 
	 */
	
	/**		Graph (represent and implement graphs) a type of data structure
	 * 			Representing Graphs
	 * 				* Edge list
	 * 				* Adjacency List
	 * 				* Adjacency Matrix
	 * 
	 * 					NOTE: The graph here is undirected, if it was directed, then the **Adjacency List** will make sense (one direction
	 * 									will be similar to a singly linked list; aka, one direction)
	 * 												e_1
	 * 										   (1)---------(2)__ e_3
	 * 											|		 _/	|	\___
	 * 	 										|	e_5_/	|		\
	 * 	 									e_2	|	 _/		|e_4	(3)
	 * 	 										|  _/		|	 ___/
	 * 	 										| /	  e_7	| __/ e_6
	 * 	 									   (5)---------(4)
	 * 
	 * 			Edge List:
	 * 				Similar to Linked List, relies on both edges and vertices
	 * 
	 * 				* Vertex Object
	 * 					-element
	 * 				* Edge Object
	 * 					-element
	 * 					-origin vertex object
	 * 					-destination vertex object
	 * 
	 * 				[1]---->[2]---->[3]---->[4]---->[5]---->[X]
	 * 				 | \	/\_\____ \_		  \		/ \
	 * 				 |	\ /    		\__\_______\___/___\______________________
	 * 				 |	/\____      / \  \      \		   \		  \		   \
	 * 				 | /	  \    /   \  \		 \			\		   \		\
	 * 				[e_1]---->[e_2]---->[e_3]---->[e_4]---->[e_5]---->[e_6]---->[e_7]---->[X] 
	 * 
	 * 			Adjacency List (Take out the Edges and focus on vertices only)
	 * 				[1]---->[V_2]---->[V_5]---->[X]
	 * 				[2]---->[V_1]---->[V_5]---->[V_3]---->[V_4]---->[X]
	 * 				[3]---->[V_2]---->[V_4]---->[X]
	 * 				[4]---->[V_2]---->[V_5]---->[V_3]---->[X]
	 * 				[5]---->[V_4]---->[V_1]---->[V_2]---->[X]
	 * 
	 * 
	 * 			Adjacency Matrix: Problem with this is memory space (matrix involving vertices (5 vertices from the graph))
	 * 				1: There's connection and edge
	 * 				0: There's no connection and no edge
	 * 
	 * 			    Adjacent Nodes ---->
	 * 					[ ][1][2][3][4][5]
	 * 	Current Nodes	[1][0][1][0][0][1]
	 * 			|		[2][1][0][1][1][1]
	 * 			|		[3][0][1][0][1][0]
	 * 			V		[4][0][1][1][0][1]
	 * 					[5][1][1][0][1][0]
	 * 
	 */
	/**		Performance
	 * 			* n vertices, m edges
	 * 			* no parallel edges
	 * 			* no self-loops Edge
	 * 
	 * 
	 * 	Space and time complexity required for each of these techniques to represent graphs is shown below
	 * 
	 * 							Edge List	|	Adjacency List		|	Adjacency Matrix
	 * 		Space		  		 n+m		|		n+m				|		n^2
	 * 		areAdjacent(v,w)	  m			|	min(deg(v), deg(w))	|		1
	 * 		insertVertex(v)  	  1			|		1				|		n^2
	 * 		insertEdge(v,w) 	  1			|		1				|		1
	 * 		removeVertex(v)		  m			|		n+m				|		n^2
	 * 		removeEdge(e)   	  1			|	deg(v) + deg(w)		|		1
	 * 
	 * 
	 */

		// Depth First Search and Breadth First Search in undirected graphs
	
	/**	Depth First Search (start at depth of the tree: go by column) Breadth First Search (start at the rows)
	 * 
	 * 		*Depth first, because starting from the top, we have to reach the vertex at the very depth of the graph
	 * 
	 * 			Idea:
	 * 				* Continue searching "deeper" into the graph, until we get "stuck" (all the way to the depth)
	 * 				* If we get stuck, "backtrack" to the first "available" vertex	(go the the next available vertex that is still unexplored)
	 * 
	 * 			Used to help solve graph problems:
	 * 				-Nodes that are reachable from a specific node v
	 * 				-Detection of cycles
	 * 				-Extraction of strongly connected components
	 * 				-Topological sort
	 * 
	 * 			Keep track of progress by coloring vertices:
	 * 				-Gray: undiscovered vertices (undiscovered nodes)
	 * 				-Red: discovered, but not finished (still exploring it)
	 * 				-Black: finished (found everything reachable from it); pop the vertex makes it black
	 * 
	 * 								(A)----_____
	 * 							   / | \		\______
	 * 							 (B) |  (D)		  _____(E)
	 * 							   \ |  /	_____/
	 * 								(C)-----
	 * 
	 * 			***We can begin anywhere in the depth first search, at the node (vertex) that we start, we
	 * 				will color it red, because we are in middle of exploring it
	 * 			**Always prioritize the explored and unexplored nodes
	 * 			** From A to B, you're changing the starting point to B
	 * 
	 * 			**The blue edges are unexplored edges that lead to explored vertex
	 * 			** Adjacent vertices of A are B, C, D, and E
	 * 			** Adjacent vertices of B are A and C
	 * 
	 * 		Stack Representation:
	 * 			* Insert into the stack only when it's explored but NOT a dead end yet
	 * 			* pop it when it is explored completely (circles back to the root vertex A)
	 * 				NOTE: popping a vertex turns it black
	 * 							Explore using push() method
	 * 					[ ]	
	 * 					[ ]	
	 * 					[ ]	
	 * 					[ ]			*Push A first (A is red)
	 *  				[ ]	
	 * 					Stack
	 * 
	 * 					[ ]
	 * 					[D]	*D will lead back to A, so it's a dead end
	 * 					[C]
	 * 					[B]
	 *  				[A]	
	 *  
	 *  				[ ]
	 * 					[ ]	*D is poped out of the stack, and we retrace back to A 
	 * 					[C]
	 * 					[B]
	 *  				[A]	
	 *  
	 *  				[ ] * E is pushed into the stack (becomes red in the graph representation)
	 * 					[E]	
	 * 					[C]
	 * 					[B]
	 *  				[A]	
	 *  
	 *  				[ ]	* The link from E to A becomes blue (explored edge that leads back to discovered vertex)
	 * 					[E]	
	 * 					[C]
	 * 					[B]
	 *  				[A]	
	 *  
	 *  				[  ]	* pop everything out in order: first it was D, now pop everything else:
	 * 					[XE]			pop D, pop E, pop C, pop B, pop A	(pops the front of the array)
	 * 					[XC]
	 * 					[XB]
	 *  				[XA]	
	 * 
	 * 		DFS Algorithm:
	 * 				-Gray: undiscovered vertices (undiscovered nodes)
	 * 				-Red: discovered, but not finished (still exploring it)
	 * 				-Black: finished (found everything reachable from it)
	 * 
	 * 			//NOTE: Depth First Search: must reach the depth until there is no adjacent node (checks the very last node put in the tree)
	 * 						DFS(G)								//G is the given graph
	 * 							//Initialize vertex				//The starting vertex must be red
	 * 							for each vertex u in V
	 * 								u.color = GRAY
	 * 							for each vertex u in V
	 * 								if(u.color == GRAY)
	 * 									DFS-Visit(u)			//If the vertex (node) is gray, visit it
	 * 				----------------------------------------------------------------------------------------------------
	 * 						DFS-Visit(u)
	 * 
	 * 							u.color = RED					//Color the vertex by discovered color
	 * 							for each v in u.adjacent		//Explore the adjacent vertices of vertex v
	 * 								if (v.color == GRAY)
	 * 									DFS-Visit(v)			//For every vertex (node) next to the current vertex, recursively visit its neighbors
	 * 							u.color = BLACK					//	until there is no adjacent vertex
	 * 	
	 * 		* Runtime will be O(n + m)
	 * 		* DFS-Visit(u) visits all the vertices and edges in the connected component of u
	 * 		* The discovery edges labeled by DFS-Visit(u) form a spanning tree of the connected component of u
	 */ 
	 /* 		DFS Application 1: Path Finding (Path Finding uses Depth First Search)
	 * 		 	* The DFS pattern can be used to find a path between two given vertices u and z, if one exists
	 * 
	 * 				DFS-Path(u, z, stack)
	 * 
	 * 					u.color = RED
	 * 					push u onto stack
	 * 					if(u == z)
	 * 						return true
	 * 					for each v in u.Adj   //Explore edge (u, v)
	 * 						if (v.color == GRAY)
	 * 							if (DFS-PATH(v, z, stack))
	 * 								return true
	 * 					u.color = BLACK
	 * 					pop u from stack
	 * 					return false
	 * 
	 * 		DFS Application 2: Cycle Finding
	 * 			* DFS pattern can be used to determine whether a graph is a cyclic 
	 * 				
	 * 				DFS-Cycle(u)
	 * 					u.color = RED
	 * 					for each v in u.Adj  // explore edge (u,v)
	 * 						if(v.color == RED)	//detect black edge
	 * 							return true
	 * 						else if (v.color == GRAY)
	 * 							if(DFS-Cycle(u))
	 * 								return true
	 * 					u.color = BLACK
	 * 					return false
	 * 						
	 * 
	 * 		Cost of DFS: When backtracking, it's not giving you a new unexplored vertex 
	 * 			example, from D to C, you have not discovered anything new
	 */
	
	/**		Breadth First Search  (improvement over DFS because it explores vertices that are not in the queue)
	 * 		Main Idea: 
	 * 			* Search graph "as wide as possible"
	 * 		
	 * 		Solves problems:
	 * 			* A BFS traversal of a graph G
	 * 			* Visits all the vertices and edges of G
	 * 			* Determines whether G is connected
	 * 			* Computes the connected components of G
	 * 			* Computes a spanning forest of G
	 * 			* Cycle detection
	 * 			* Find and report a path with the minimum number of edges between two given vertices
	 * 
	 * 		BFS
	 * 		 Keep track of progress by coloring vertices
	 * 			-Gray: undiscovered vertices (undiscovered nodes)
	 * 			-Red: discovered, but not finished (still exploring it)
	 * 			-Black: finished (found everything reachable from it)`
	 * 
	 * 								(A)---____
	 * 							   /   \	  \
	 * 							 (B)----(C)---(D)
	 * 							   \    / \   /
	 * 								(E)	   (F)
	 * 			
	 * 			Represented using a queue: EnQ() vertices that are
	 * 
	 * 			* Starting from A (turned red to begin with), check all the branches adjacent to A:
	 * 				First explore B (turn it red), then explore C (turn it red), and then D (turn it red)
	 * 
	 * 								(A)---____
	 * 							   /   \	  \
	 * 							 (B)----(C)---(D)
	 * 
	 * 			* Since D is the last vertex Adjacent to A, A turns black and is DeQ from the queue
	 * 			* now continuing to B (newest base)
	 * 			* C was already discovered (in the queue), create blue link from B to C
	 * 			* From B, explore E (turn E red), which turns B black (DeQ's B) because all of B's branches are explored
	 * 			* C becomes the new base: explore all branches of C
	 * 				- The current goal will be to find all the adjacent vertices of C until there's an undiscovered vertex
	 * 			* The lines from C to E, C to D, and C to F become blue (since F is explored, it becomes red) 
	 * 
	 * 								(Black)---____			A and B have their branches completely explored,
	 * 							   /   		\	  \				now C, D, E, and F are red
	 * 							 (Black)----(C)---(D)
	 * 							   \    	/ \   /
	 * 								  (E)	   (F)
	 * 			
	 * 			* All branches of C are explored, so C becomes black
	 * 			* D becomes the newest base, and explores all vertices adjacent to D, thus the
	 * 					line from D to F becomes blue
	 * 
	 * 			* now that all paths are explored, DeQ every vertex from bottom of queue to top
	 * 
	 * 		Queue representation
	 * 
	 * 				[ ]
	 * 				[ ]
	 * 				[ ]
	 * 				[ ]
	 * 				[ ]
	 * 				[ ]
	 * 				Queue 
	 * 
	 * 				[ ]
	 * 				[ ]
	 * 				[D]	* D is the last vertex that's adjacent to A, thus A is Dequeued from Queue
	 * 				[C]
	 * 				[B]
	 * 				[A]
	 * 	
	 * 				[ ]
	 * 				[E]
	 * 				[D]	* B becomes the newest base, and now will search all adjacent vertices from B
	 * 				[C]
	 * 				[B]
	 * 				[XA]
	 * 
	 * 				[F]
	 * 				[E]
	 * 				[D]	* C becomes the newest base, and now will search all adjacent vertices from C
	 * 				[C]
	 * 				[XB]
	 * 				[XA]
	 * 
	 * 				[F]
	 * 				[E]
	 * 				[D]	* D becomes the newest base, and now will search all adjacent vertices from D
	 * 				[XC]
	 * 				[XB]
	 * 				[XA]
	 * 
	 * 				[XF]
	 * 				[XE] * Since the line from D to F becomes blue, all the branches are explored, now DeQ every vertex in Queue
	 * 				[XD]		DeQ A, DeQ B, DeQ C, DeQ D, DeQ E, DeQ F
	 * 				[XC]
	 * 				[XB]
	 * 				[XA]
	 * 
	 * 		BFS Pseudocode
	 * 
	 * 			BFS (G, s)
	 * 				for each u in v
	 * 					u.color = GRAY //Initialize the vertex
	 * 				s.color = RED		//Starting vertex (A) will be red
	 * 				Q.enqueue(s)		//the first element will be in the queue
	 * 			while Q is not empty
	 * 				u = Q.dequeue()
	 * 				for each v in u.Adj
	 * 					if(v.color == GRAY)
	 * 						v.color = RED
	 * 						Q.enqueue(v)
	 * 				u.color = BLACK
	 * 
	 * 
	 * 		Application: Shortest Paths on an unweighted graph (Dijikstra's)
	 * 
	 */
	
	/**	Shortest Path
	 * 
	 * 		Shortest Path on Weighted Graphs
	 * 			* For unweighted graph, BFS finds shortest paths from source node s to every vertex v in graph
	 * 			* here the length of path is the number of edges on path
	 * 
	 * 			Left: unweighted graph				Right: weighted graph (different "costs")
	 * 													10
	 * 			(D)--------(C)						(D)--------(C)
	 * 			 |			|						 |			|
	 * 			 |			|						 | 4		| 1
	 * 			 |			|						 |			|
	 * 			(A)--------(B)						(A)--------(B)
	 * 														2
	 * 		Shortest Path from A to C				Shortest Path from A to C
	 * 		*A-B-C or A-D-C							*A-B-C
	 * 
	 * 		Shortest Path Properties
	 * 			Optimal Substructure
	 * 				* A subpath of shortest path itself is shortest path
	 * 
	 * 			Shortest Path Tree
	 * 				* There's a tree of shorted paths from start vertex to all the other vertices
	 * 
	 * 			Note: shortest path trees are not necessarily unique
	 * 
	 * 			Consider negative cycle?
	 * 				Assume there's no negative edges
	 * 
	 */
	
	/**	Dijikstra's Algorithm
	 * 		* Applies to general, weighted, directed, or undirected graph (may contain cycles)
	 * 		* But, weights must be non-negative (can be 0)
	 * 
	 * 		Recall BFS:
	 * 
	 * 		BFS(G, s)				// s is vertex to start 
	 * 			for each u in v			// Initialize vertex
	 * 				u.d = ∞;			//Record the shortest distance between s and u
	 * 				u.p = null;			//Record the parent of u on the shortest path from s to u
	 * 				u.color = GREY;
	 * 
	 * 			s.color = RED;
	 * 			s.d = 0;
	 * 			Queue.enQ(s);						//Search as wide as possible, use a Queue (FIFO)
	 * 
	 * 			while(!Queue.isEmpty())
	 * 				u = Queue.deQ();
	 * 				for each v in u.adj
	 * 					if(v.color == GREY)
	 * 						v.color = RED;
	 * 						v.d = u.d + 1;
	 * 						v.p = u;
	 * 						Queue.enQ(v);
	 * 				u.color = BLACK
	 * 
	 * 
	 * 		Visualization: starting vertex will be 0, but all other vertices will be infinity: they will change weight depending on the path
	 * 
	 * 			Goal: Find the shortest path from a to g, depending on the weights 
	 * 					 0
	 * 					(a)_
	 * 					|	\_ 7
	 * 					|	  \_
	 * 					|		\_  ∞   8		∞
	 * 				   	|5		  (b)----------(c)
	 * 					| 	9 ____/ \_ 7     __/
	 * 				   	|	_/		  \_   _/   5
	 * 					| _/	15		\ /
	 * 				  ∞(d)--------------(e)∞
	 * 					 \__          __/ \_
	 * 						\__	  8__/		\__9
	 * 						6  \  /            \
	 * 							(f)------------(g)
	 * 							∞		11		∞
	 * 
	 * 					 0
	 * 					(a)_
	 * 					|	\_ 7
	 * 					|	  \_
	 * 					|		\_  7   8		∞
	 * 				   	|5		  (b)----------(c)
	 * 					| 	9 ____/ \_ 7     __/
	 * 				   	|	_/		  \_   _/   5
	 * 					| _/	15		\ /
	 * 				  5(d)--------------(e)14
	 * 					 \__          __/ \_
	 * 						\__	  8__/		\__9
	 * 						6  \  /            \  25
	 * 							(f)------------(g)
	 * 							11		11		22
	 * 
	 * 				*Shortest path will be a, d, f, g: will have a total weight of 22 instead of 25
	 * 
	 * 		* Instead of searching every edge of the graph, optimize by minimizing the 
	 */ 
	/* 		Dijkstra's Algorithm Pseudocode (three methods)
	 * 
	 * 		initialize(G, s)			//d is the current distance/weight number of the vertex
	 * 			
	 * 			for each v in V
	 * 				v.d = infinite
	 * 				d.p = null;				
	 * 				s.d = 0
	 */ 
	
	/* 		relax(u, v, w)			//Updating method
	 * 			if (v.d > u.d + w(u,v))	//If becomes greater than weight of edge
	 * 				v.d = u.d + w(u.v)
	 * 				v.p = u
	 */
	
	/* 		initialize(G, s)
	 * 			S = 0
	 * 			Q = V
	 * 			while Q is not empty
	 * 				u = Extract_Min(Q)
	 * 				S = S union u
	 * 				for each v in u.adj
	 * 					relax(u, v, w)
	 * 		
	 */
	/**	Dijkstra's Algorithm Properties
	 * 		Use Priority Queue*
	 * 		Runtime is O(mlog(n))
	 */
	
	public static void main(String[] args) {
		for(int i = 0; i < 10; i++) {
			for(int j = 0; j < 10; j++) {
				if( (i == 0 || i == 9) || (i > 0 && i < 9 && (j == 0 || j == 9)) )
					System.out.print("0 ");
				else {
					System.out.print("1 ");
				}
			}
			System.out.println();
		}
		
		//Window Sliding Technique
		int[] list = {2, 4, 6, 8, 10, 12, 14};
		int N = 3;
		for(int i = N-1; i<list.length; i++) {
			System.out.print("("+list[i-N+1]+","+list[i-1]+","+list[i]+")==>");
		}
	}

}
