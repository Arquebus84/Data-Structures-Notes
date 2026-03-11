package AdvancedListExamples;

public class MaxHeapTest {

	public static void main(String[] args) {
		int[] array = { 1, 3, 5, 4, 6, 13, 10, 9, 8, 15, 17 };
		MaxHeap heap = new MaxHeap(11);
		for(int i = 0; i < array.length; i++) {
			heap.insert(array[i]);
		}
		//heap.insert(3); heap.insert(8); heap.insert(15); heap.insert(4);
//		for(int i = 0; i < heap.maxSize; i++) {
//			heap.maxHeapify(i);
//		}
		
		printArray(array);
		heap.printHeap();
	}
	
	public static void printArray(int[] array) {
		for(int i = 0; i < array.length; i++) {
			System.out.print(array[i] + " ");
		}
		System.out.println();
	}

	private static class MaxHeap{
		int[] heap;
		int size;
		int maxSize;
		
		public MaxHeap(int maxSize) {
			this.maxSize = maxSize;
			this.size = 0;
			heap = new int[this.maxSize];
		}
		
		public MaxHeap(int[] heap) {		//If given an array, sort it
			//this.maxSize = heap.length;
			this.size = heap.length;
			this.heap = heap;
		}
		
		public int parent(int rootIndex) {return (rootIndex) / 2;}
		public int leftChild(int rootIndex) {return (2*rootIndex) + 1;}		//Left and right child of their parent's node index (root node index)
		public int rightChild(int rootIndex) {return (2*rootIndex) + 2;}
		
		public boolean isExternal(int index) {		//If the node at this index is a leaf (has no children) return true
			if(index >= (size>>1) && index < size) {	//If the index is between half the size and less than last index
				return true;
			}
			return false;							//If node index is greater than size, it is not external
		}
		
		public void swap(int indexFirst, int indexSecond) {	//Swap the two nodes
			int temp = heap[indexFirst];
			heap[indexFirst] = heap[indexSecond];
			heap[indexSecond] = temp;
		}
		
		public void maxHeapify(int index) {
			if(isExternal(index)) {
				return;
			}
			//If the root of the tree (or root of the inner subtree) is less than its children, swap it with one of its children
			if(heap[index] < heap[leftChild(index)] || heap[index] < heap[rightChild(index)]) {
				
				if(heap[leftChild(index)] > heap[rightChild(index)]) {		// If root is less, check between right and left child
					swap(index, heap[leftChild(index)]);					// If left child is greater than right child, root will be left
					maxHeapify(leftChild(index));
																			/**if(heap[rightChild(index)] > heap[leftChild(index)])*/
				}else {														// If root is less, check between right and left child
					swap(index, heap[rightChild(index)]);					// If right child is greater than left child, root will be right
					maxHeapify(rightChild(index));
				}
			}
		}
		
		public void insert(int element) {
			heap[size] = element;
			
			int current = size;
			while(heap[current] > heap[parent(current)]) {
				swap(current, parent(current));
				current = parent(current);
				maxHeapify(current);
			}
			size++;
		}
		public void printHeap() {
			for(int i = 0; i < size; i++) {
				System.out.print(heap[i] + " ");
			}
			System.out.println();
		}
	}
}
