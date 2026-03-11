package CQ6_myMaxHeap;

//Java program for Max-Heap from Array

/*

//===============================================
//CSCI 3230A Data Structures
//Fall 2025
//Instructor: M Arif Rahman, Ph.D.
//CQ-6; Closed-Resources, Individual Test.

//==============****FILL IN YOUR INFO HERE****======================
//Name:		Matteo Ventura
//Eagle ID:	901405780
//==============****FILL IN YOUR INFO HERE****======================

//======================= IMPORTANT NOTES ======================== 
//1. DO NOT DELETE ANY COMMENT OR ANY CODE FROM THE TEMPLATE PROVIDED!!! 
//2. DO NOT MAKE ANY CHANGES TO THE MAIN FUNCTION!!!
//3. You will only need to fill in the blocks with comment "COMPLETE THIS BLOCK".
//4. Modify the file name to "myMaxHeap.java" before compiling and submitting it.
//5. DON’T FORGET TO INPUT YOUR NAME AND EAGLE ID AT THE TOP!!!

//====================== Requirements ========================
//Implement Max-Heapify functionality in an Array of integers. 
//which was illustrated during the lecture slides "Lecture7-SpecialsQueuesHeaps.pdf".
//That is, you need to translate the following algorithm to Java code for the Max-Heapify.


I.  Algorithm Max-HeapifyFromArray ( Array arr[] )
1.	You are given an array, arr[] of integers representing the complete binary tree. That is, 
		the left and the right child of ith node are in indices 2*i+1 and 2*i+2 in the array.
2.	We set the index of the current element, i, as the ‘MAXIMUM’.
3.	If arr[2 * i + 1] > arr[i], i.e., the left child is larger than the current value, it is set as ‘MAXIMUM’.
4.	Similarly if arr[2 * i + 2] > arr[i], i.e., the right child is larger than the current value, it is set as ‘MAXIMUM’.
5.	Swap the ‘MAXIMUM’ with the current element.
6.	Repeat steps 2 to 5 till the property of the heap is restored.


//Please use appropriate data types and access modifiers
//to demonstrate the correctness of your code.
//
//Inputs are already provided in the driver Main() function. 
//Your output will look as follows if your implementation is correct:
//------------------------------------
//Before MaxHeapify:
//1 3 5 4 6 13 10 9 8 15 17 
//After MaxHeapify:
//17 15 13 9 6 5 10 4 8 3 1

*/

/***							Root Node is largest of all the nodes
 * 								parent = (n - 1)/2		left = (2 * i) + 1		right = (2 * i) + 2
 * 								parent = 4/2 = 2
 * 
 * 										 	 0					Index:	Right		Left		Maximum		i
 * 											[1]										5			2			2	//Max is initialized to i
 * 										1 /    \	2					2			5			5			2	//array[5] > array[Max] 10 > 9
 * 										[12]	[9]												2			5	//Swap Max and i
 * 									 3 / \ 4   5/ 						
 * 									 [5]  [6]  [10]  
 * 
 * 											 0	
 * 											[1]							1						2			5	// array[1] > array[Max]
 * 										1 /    \	2
 * 										[12]	[10]												
 * 									 3 / \ 4   5/ 
 * 									 [5]  [6]  [9]  
 */

public class myMaxHeap {
	
	// Implement Swap function
	public static void swap(int[] array, int leftIndex, int rightIndex) {
		int temp = array[leftIndex];
		array[leftIndex] = array[rightIndex];
		array[rightIndex] = temp;
	}
	//check for both the left and right nodes indices (L: (2 * index) + 1	and R: (2 * index) + 2) from their parent node
//	public static int parent(int index) { return ((index-1) / 2);}
//	public static int left(int index) { return (2 * index) + 1;}
//	public static int right(int index) { return (2 * index) + 2;}
	
	static void maxHeapify(int arr[], int N, int i) {

		// heapify a subtree rooted with node i which is an index in arr[].
		// N is size of heap
		// Do you need recursion??
		
		int maximum = i;			// Set the current value maximum to i
		int left = (2 * i) + 1;		// Left child will be index*2 + 1
		int right = (2 * i) + 2;	// Right child will be index*2 + 2
		
		if(right < N && arr[right] > arr[maximum]) {		//Check if right element ((2*i) + 1) is greater than the current max (i) 
			maximum = right;							
		}
		
		if(left < N && arr[left] > arr[maximum]) {
			maximum = left;
		}
		
		if(maximum != i) {				// If the largest element is greater than the root node, then swap them until the maximum reaches 0
			swap(arr, maximum, i);
			maxHeapify(arr, N, maximum);
		}		
	}

	static void buildMyMaxHeapFromArray(int arr[], int N) {	// Depth first

		//int indexOfLastNonLeafNode = N;		//N must be the last element added (or even parent index)
		int indexOfLastNonLeafNode = (N-1)>>1;	//Parent node makes it faster
		
		// you need traversal to root and max-heapify with this loop here
																			// Check the last elements (depth first), and then gradually move up the tree
		for (int i = indexOfLastNonLeafNode; i >= 0; i--) {					//Insert the last values first and then sort them with maxHeapify
			maxHeapify(arr, N, i);
		}
//		for (int i = 0; i < arr.length; i++) {					// Going forwards will only sort the left and right nodes, not the root
//			maxHeapify(arr, N, i);
//		}
	}

	// Driver
	public static void main(String args[]) {
		int randomArray[] = { 1, 3, 5, 4, 6, 13, 10, 9, 8, 15, 17 }; // test purposes
		int N = randomArray.length; // size of Array

		System.out.println("Before MaxHeapify:");
		for (int i = 0; i < N; i++)
			System.out.print(randomArray[i] + " ");
		System.out.println();

		// Initial heap of input array
		// 		1			0
		// 	   / \
		// 	  3   5			1 2
		// 	 / \  / \
		// 	4   6  13 10	3 4 5 6
		// / \ / \
		// 9 8 15 17		7 8 9 10

		buildMyMaxHeapFromArray(randomArray, N);

		System.out.println("After MaxHeapify:");
		for (int i = 0; i < N; i++)
			System.out.print(randomArray[i] + " ");
		System.out.println();

		// Final Heap:
		//      17			0
		// 	   / \
		//    15 13			1 2
		//   / \ / \
		//   9 6 5 10		3 4 5 6
		//  / \ / \
		//  4 8 3 1			7 8 9 10
	}
}
