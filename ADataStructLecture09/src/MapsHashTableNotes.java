
import java.util.*;
public class MapsHashTableNotes {
	
	/**				Hashmap and Hashtable isn't on exam, and the bucket sort (3 sorting methods for trees are not on exam) 
	 * 
	 * 	IDEA: used to model arrays far more efficient; finding an element
	 * 			Arrays use *indices* to find, insert, and delete items in list
	 * 
	 * 		Map
	 * 			* Models a *searchable* collection of *key-value* entries
	 * 			* The main operations of a map are for searching, inserting, and deleting items
	 * 
	 *  		* Multiple Entries with the same key are not allowed
	 *  			Map<K, V>{
	 *  				Size();
	 *  				boolean isEmpty();
	 *  				get(K key);
	 * 					put(K key, V value);
	 * 					remove(K key)
	 * 				}
	 * 
	 * 		Simple List-Based Map
	 * 			* Implement a map using an unsorted list
	 * 
	 * 		[X]<----->[*][*][*]<---->[*][*][*]<---->[*][*][*]<---->[X]
	 * 					  |				 |				|
	 * 					  V				 V				V
	 * 					[9][c]		  [6][a]		  [8][c]		entries
	 * 
	 * 		Algorithm get(k):									// Problem: when deleting key, it has to be O(n); no way to reduce the order
	 * 			B = S.header
	 * 			while(B.hasNext()) do
	 * 				p = B.next()		//Next position in B
	 * 				if(p.element().getKey() = k) then
	 * 					return p.element().getValue()
	 * 			return null;			//There is no entry with key equal to k
	 * 
	 * 
	 * 		Algorithm put(k, v)
	 * 			B = S.header
	 * 			while(B.hasNext()) do
	 * 				p = B.next()		//Next position in B
	 * 				if(p.element().getKey() = k) then
	 * 					t = p.element().getValue()
	 * 					S.set(p, (k,v))
	 * 					return t 		//return old value
	 * 			return null;			//There is no entry with key equal to k
	 * 
	 * 
	 * 
	 * 		*Performance:
	 * 			- O(n)
	 */
	
	/**		Hash Table
	 * 			* Data structure to make map operations faster
	 * 
	 * 			* While worst-case is still O(n), average case is typically O(1)
	 * 	
	 * 			*Main Idea:
	 * 				-Indexing into array takes O(1) time
	 * 				-Mapping keys onto integers (or indices)
	 * 			*Example:
	 * 				-Search word in dictionary
	 * 				-Search phone number in contact list
	 * 
	 * NOTE: Hash becomes order 1 O(1) when the number of groups matches the number of members in each group:
	 * 			[A]---->[B]---->[C]---->[D]--...--->[N] 
	 * 			 |		 |		 |		 |			 |
	 * 			 V		 V		 V		 V			 V
	 * 			[1]		[1]		[1]		[1]			[1]
	 * 			[2]		[2]		[2]		[2]			[2]
	 * 			...		...		...		...			...
	 * 			[n]		[n]		[n]		[n]			[n]
	 * 
	 * *Example: in a table of 24 groups, each group has 24 members, therefore will have O(1)
	 * 
	 * 		Has Functions and Has Tables
	 * 			*The integer h(x) is called the hash value of key x
	 * 			Hash Table
	 * 				-A hash table for a given key type consists of
	 * 					>Hash function h
	 * 					>Array(called table) of size N
	 * 			
	 * 			*When implementing a map with hash table, 
	 * 				goal is to store item (k,v) at index i = h(k)
	 * 
	 * 			Hash function
	 * 				-A hash function h maps keys of given type to integers in a fixed interval [0, N-1]
	 * 			
	 * 			*Example: h(x) = x mod N		(h(x) = x%N)
	 * 			
	 * 
	 * 		Simulation:
	 * 		Hash Functions
	 * 				h(x) = h_2(h_1(x))
	 * 			is usually specified as the composition of two functions
	 * 				* Hash code:
	 * 					h_1: keys --> integers			//Code converts the keys into integers c will have an integer value
	 * 
	 * 				* Compression function				//Code converts integers into a range
	 * 					h_2: integers --> [0, N-1]
	 * 
	 * 				Goal of hash function is to *disperse the keys* in an apparently *random way*
	 * 
	 * 		Hash Codes
	 * 			Hash code: h_1: keys --> integers
	 * 				*Memory Address
	 * 				*Binary Representation (Integer cast)
	 * 				*Component sum
	 * 					-We partition the bits of the key into components of fixed length (e.g. 16 or 32 bits)
	 * 						and we sum the components (ignoring overflows)
	 * 
	 */
	
	/**	Hash Table: Collision Handling
	 * 
	 * 	Collision Handling: Separate Chaining
	 * 		*Collisions: Different elements are mapped to the same cell
	 * 		*Separate Chaining: let each cell in the table point to a linked list of entries that mpa there
	 * 				0[X]
	 * 				1[*]----->e_1
	 * 				2[X]
	 * 				3[X]
	 * 				4[*]----->e_2----->e_3
	 * 
	 * 		*Separate chaining is simple, but requires additional memory outside the table
	 * 
	 * 	Collision Handling: Open Addressing
	 * 		*Linear Probing: Handles collisions by placing the colliding item in the next
	 * 			(circularly) available table cell 
	 * 
	 * 		*Double Hashing: Uses a secondary hash function d(k) and handles collisions by
	 * 			placing an item in the first available cell of the series
	 * 			(h(k) + jd(k)) mod N for j = 0, ..., N-1
	 * 
	 * 		Example: Linear Probing (array of size 13) place collisions to the next cell
	 * 			*h(x) = x mod 13
	 * 			*Insert keys: 18, 41, 22, 44, 59, 32, 31, 73, in this order
	 * 
	 * 			 0    1   2   3   4   5   6   7   8   9  10  11  12
	 * 			[  ][  ][  ][  ][  ][  ][  ][  ][  ][  ][  ][  ][  ]
	 * 
	 * 		18 mod 13 = 5 (place integer 18 in index 5)
	 * 			 0    1   2   3   4   5   6   7   8   9  10  11  12
	 * 			[  ][  ][  ][  ][  ][18][  ][  ][  ][  ][  ][  ][  ]
	 * 
	 * 		41 mod 13 = 2 (place in index 2)
	 * 		22 mod 13 = 9 (place in index 9)
	 * 		44 mod 13 = 5 (place in index 5 + 1 since 5 is occupied) collision is detected
	 * 			0    1   2   3   4   5   6   7   8   9  10  11  12
	 * 		   [  ][  ][41][  ][  ][18][44*][  ][  ][22][  ][  ][  ]
	 * 
	 * 		59 mod 13 = 7 (place in index 7)
	 * 		32 mod 13 = 6 (place in index 8)	Collision
	 * 		31 mod 13 = 5 (place in index 10)	Collision
	 * 		73 mod 13 = 8 (place in index 8)	Collision
	 * 			0    1   2   3   4   5   6   7    8   9   10   11   12
	 * 		   [  ][  ][41][  ][  ][18][44*][59][32*][22][31*][73*][  ]
	 * 
	 * 		The number* was moved due to it colliding with another integer 
	 * 			
	 * 
	 * 
	 * 		Double Hashing Notes:
	 * 			*Double Hashing: Uses a secondary hash function d(k) and handles collisions by
	 * 			placing an item in the first available cell of the series
	 * 			(h(k) + jd(k)) mod N for j = 0, ..., N-1
	 * 			
	 * 			*The secondary hash function d(k) cannot have zero values
	 * 			*Table size N must be a prime top allow probing of all the cells
	 * 		Common Choice: d(k) = q - k mod q, where q < N, q is a prime
	 * 
	 * 		Example: Double Hashing:
	 * 			*N = 13, h(k) = k mod 13, d(k) = 7 - k mod 7
	 * 			*Insert keys: 18, 41, 22, 44, 59, 32, 31, 73, in this order
	 * 
	 * 		18 mod 13 = 5, d(18) = 3 (place integer 18 in index 5)
	 * 			 0    1   2   3   4   5   6   7   8   9  10  11  12
	 * 			[  ][  ][  ][  ][  ][18][  ][  ][  ][  ][  ][  ][  ]
	 * 
	 * 		41 mod 13 = 2, d(41) = 1 (place in index 2)
	 * 		22 mod 13 = 9, d(22) = 6 (place in index 2)
	 */
	
	/**	Performance of Hashing
	 * 		*Worst case: searches, insertions, and removals take O(n) time
	 * 		*Worst case occurs when all the keys collide
	 * 		*Load factor alpha = n/N affects the performance of hash table
	 * 		*Assuming that the hash values are like random numbers, it can be shown that 
	 * 			the expected number of probes for an insertion with open addressing is 1/(1 - alpha)
	 * 
	 * 		*The expected running time of all the map ADT operations in a hash table is O(1)
	 * 		
	 * 		*In practice, hashing is very fast provided alpha<<100% 
	 */

	public static void main(String[] args) {
		//HashTable<Integer> table = new List<>();
		
		HashMap<Integer, Integer> map = new HashMap<>();
		map.put(2, 0); map.put(0, 1); map.put(4, 2); map.put(5, 3);
		System.out.println(map.toString());
		
	}
	
}
