package TreesTest;

public class ArrayBinaryTreePractice {
	/**
	 * 								  0						Rank are 0-12
	 *		0						 [A]
	 *							1	/	\	2
	 *		1					[B]		  [C]		
	 *						3  /  \	4	5 /  \	6
	 *		2				[D]	  [E]	[F]	  [G]
	 *						/  \		/ 		\
	 *		3			 [H]  [I]	   [J]		[K]		
	 *					  9		10		11		12
	 * 
	 */
	
	/**NOTE: This Code DOES NOT Use Recursion*/
	
	public static void main(String[] args) {
		ArrayBinaryTree tree = new ArrayBinaryTree('A');
		
		tree.addLeft(0, 'B');
		tree.addRight(0, 'C');
		tree.addLeft(1, 'D');
		tree.addRight(1, 'E');
		tree.addLeft(2, 'F');
		tree.addRight(2, 'G');
		tree.addLeft(3, 'H');	//Only to F with capacity
		tree.expandCapacity();
		tree.addRight(3, 'I');
		tree.addLeft(4, 'J');
		tree.addRight(4, 'K');
		tree.addLeft(5, 'L');
		tree.addRight(5, 'M');
		
		System.out.println(tree.tree.length);
		//System.out.println("Capacity is " + tree.capacity);
		
		tree.display();
		//System.out.println(tree.hasLeft(3));
	}
	
	private static class ArrayBinaryTree {
		char[] tree;
		int size;
		int capacity = 20;

		public ArrayBinaryTree() {
			this.size = 0;
			this.tree = new char[this.capacity];
			for (int i = 0; i < this.capacity; i++) {
				tree[i] = '*';
			}
		}

		public ArrayBinaryTree(char root) {
			tree = new char[this.capacity];
			tree[0] = root;
			for (int i = 1; i < this.capacity; i++) {
				tree[i] = '*';
			}
			size = 1;
		}

		public void expandCapacity() {
			this.capacity *= 2;
			char[] temp = new char[this.capacity];
			for (int i = 0; i < tree.length; i++)
				temp[i] = tree[i];
			for(int i = tree.length; i < this.capacity; i++) {
				temp[i] = '*';
			}
			tree = temp;
		}

		public char root() {
			if (this.size > 0)
				return this.tree[0];
			else
				return '*';
		}

		public boolean isEmpty() {
			return (size == 0);
		}

		public int size() {
			return size;
		}

		public int parent(int rankOfNode) {
			return (rankOfNode - 1) / 2;			// The node at branch 3 (3-1)/2 will be 1 (parent is 1)
		} // return index

		public int left(int rankOfNode) {		//Rank is the root node's index (code returns the index of left node from this root)
			return 2 * rankOfNode + 1;				//Left are the odd branches; branch 3 (2*3+1 = 7 is the rank)
		} // return index

		public int right(int rankOfNode) {
			return 2 * rankOfNode + 2;
		} // return index

		public boolean hasLeft(int rankOfNode) {
			int rankOfLeftChild = left(rankOfNode);
			return (rankOfLeftChild < tree.length && this.tree[rankOfLeftChild] != '*');
		}

		public boolean hasRight(int rankOfNode) {
			int rankOfRightChild = right(rankOfNode);
			return (rankOfRightChild < tree.length && this.tree[rankOfRightChild] != '*');
		}

		public void addLeft(int rankOfNode, char element) {
			int rankOfLeftChild = left(rankOfNode);
			if (rankOfLeftChild < tree.length && this.tree[rankOfLeftChild] == '*') {
				this.tree[rankOfLeftChild] = element;
				System.out.println("Inserted " + element + " as left child under the root at index " + rankOfNode);
			}
			size++;
		}

		public void addRight(int rankOfNode, char element) {
			int rankOfRightChild = right(rankOfNode);
			if (rankOfRightChild < tree.length && this.tree[rankOfRightChild] == '*') {
				this.tree[rankOfRightChild] = element;
				System.out.println("Inserted " + element + " as right child under the root at index " + rankOfNode);
			}
			size++;
		}

		public void display() {
			System.out.println("Displaying all tree elements according to their array indices:");
			for (int i = 0; i < tree.length; i++)
				System.out.print(tree[i] + " ");
			System.out.println();
		}
	}
}
