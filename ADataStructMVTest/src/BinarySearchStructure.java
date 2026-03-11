
public class BinarySearchStructure {

	public static void main(String[] args) {
		int[] arr = {2, 5, 8, 9, 15, 28, 32};
		
		System.out.println(BinarySearch(arr, 15));
		
		System.out.println(RecBinarySearch(arr, 0, arr.length - 1, 15));

	}
	
	//Normal Binary Search
	public static int BinarySearch(int[] arr, int key) {
		int low = 0;
		int high = (arr.length - 1);
		int midIndex = high / 2;
		
		while(high >= low) {
			midIndex = (high + low) / 2;
			if(key == arr[midIndex]) {
				return midIndex;
			}else if(key > arr[midIndex]) {
				low = midIndex + 1;
			}else {
				high = midIndex - 1;
			}
		}
		
		return midIndex;
	}

	//Recursive Binary Search
	public static int RecBinarySearch(int[] array, int left, int right, int key) {
		int middle = (left + right) / 2;
		
		if(right < left) {
			return -1;
		}
		
		if(key == array[middle]) {
			return middle;
		}else if(key < array[middle]) {
			return RecBinarySearch(array, left, middle - 1, key);
		}else {
			return RecBinarySearch(array, middle + 1, right, key);
		}
	}
	
}
