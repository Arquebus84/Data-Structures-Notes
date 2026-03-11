package TreesNotes;

public class ArrayBinaryTree {

	//Array Binary Tree:
	/**
	 * 	Parent index: (childIndex / 2)
	 * 	RightChild index:	(parentIndex * 2) + 2
	 * 	LeftChild index:	(parentIndex * 2) + 1
	 * 
	 * 
	 * 		Array-Based with increase size capacity
	 * 				  0
	 * 				 [A]
	 * 			1	/  \2
	 * 		  	 [B] 	[C]
	 * 		    3/ \4  5/ \6
	 * 	       [D] [E] [F] [G]
	 * 
	 */
	public static void main(String[] args) {
		ArrayTree tree = new ArrayTree(5);
		tree.insertLeft("A", 0); tree.insertLeft("B", 0); tree.insertRight("C", 0); tree.insertRight("E", 1); tree.insertRight("G", 2);
		//tree.insert("A", 0); tree.insert("B", 0); tree.insert("C", 0); 
		tree.displayTree();
	}
	
	private static class ArrayTree {//<E extends Comparable<E>>{
		int root = 0;
		int size = 0;
		static int maxSize = 20;
		String[] data;
		
		public ArrayTree() {
			this(maxSize);
		}
		public ArrayTree(int size) {
			this.maxSize = size;
			data = new String[this.maxSize];
		}
		public int Size() {
			return this.size;
		}		
		public String getFirst() {
			return data[root];
		}		
		private void increaseSize(int size) {
			String[] newData = new String[size];
			for(int i = 0; i < data.length; i++) {
				newData[i] = data[i];
			}
			data = newData;
		}
		
		public void insertRight(String element, int index) {
			if((index * 2) + 2 >= maxSize || size >= maxSize) {increaseSize(maxSize*2);}
			
			if(data[root] == null) {
				data[root] = element;
			}else {
				data[(index * 2) + 2] = element;
			}
		}
		public void insertLeft(String element, int index) {
			if((index * 2) + 1 >= maxSize || size >= maxSize) {increaseSize(maxSize*2);}
			
			if(data[root] == null) {
				data[root] = element;
			}else {
				data[(index * 2) + 1] = element;
			}
			//size++;
		}
		public void insert(String element, int index) {
			if(index * 2 + 2 >= maxSize || index * 2 + 2 >= maxSize || size >= maxSize ) {increaseSize(maxSize*2);}
			
			if(data[root] == null) {
				data[root] = element;
			}
			
			if(element.compareTo(data[index]) < 0) {
				data[(index * 2) + 1] = element;		//Add Left
			}else if(element.compareTo(data[index]) > 0) {
				data[(index * 2) + 2] = element;		//Add Right
			}
		}
		
		public void displayTree() {
			for(String element : data) {
				System.out.print(element + " ");
			}
		}
	}

}
