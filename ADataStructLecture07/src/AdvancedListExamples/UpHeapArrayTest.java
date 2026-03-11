package AdvancedListExamples;

import java.util.Arrays;

public class UpHeapArrayTest {

	public static void main(String[] args) {
		System.out.println("Before");
		int[] array = { 1, 3, 5, 4, 6, 13, 10, 9, 8, 15, 17 };
		printArray(array);
		
		buildMyMaxHeapFromArray(array, array.length);
		
		System.out.println("After");
		printArray(array);
	}

	// Implement Swap function
	public static void swap(int[] array, int leftIndex, int rightIndex) {
		int temp = array[leftIndex];
		array[leftIndex] = array[rightIndex];
		array[rightIndex] = temp;
	}
	// check for both the left and right nodes indices (L: (2 * index) + 1 and R: (2
	// * index) + 2) from their parent node
		public static int parent(int index) { return ((index-1) / 2);}
		public static int left(int index) { return (2 * index) + 1;}
		public static int right(int index) { return (2 * index) + 2;}

	static void maxHeapify(int arr[], int N, int i) {
		// COMPLETE THIS BLOCK
		// WRITE YOUR CODE HERE
		// DO NOT DELETE ANY OF THE TEMPLETE CODE

		// heapify a subtree rooted with node i which is an index in arr[].
		// N is size of heap
		// Do you need recursion??
		//int current = i;
		
//		if(arr[parent(i)] < arr[right(i)] && right(i) < N)
//			swap(arr, parent(i), right(i));
//		
//		if(arr[parent(i)] < arr[left(i)] && left(i) < N)
//			swap(arr, parent(i), left(i));
//		
		while(arr[parent(i)] < arr[i]) {
			swap(arr, parent(i), i);
			i = parent(i);
		}
		
	}

	static void buildMyMaxHeapFromArray(int arr[], int N) {

		// COMPLETE THIS BLOCK ALSO, IT IS ONLY PARTIALLY COMPLETE
		// FILL IN THE MISSING PIECES
		int indexOfLastNonLeafNode = N-1; // N must be the last element added

		// you need traversal to root and max-heapify with this loop here
		for (int i = indexOfLastNonLeafNode; i >= 0; i--) { // Insert the last values first and then sort them with
															// maxHeapify
			maxHeapify(arr, N, i);
		}
//			for (int i = 0; i < arr.length; i++) {					Going forwards will 
//				maxHeapify(arr, N, i);
//			}
	}
	
	public static void printArray(int[] array) {
		for(int i = 0; i < array.length; i++) {
			System.out.print(array[i] + " ");
		}
		System.out.println();
	}
	
}
