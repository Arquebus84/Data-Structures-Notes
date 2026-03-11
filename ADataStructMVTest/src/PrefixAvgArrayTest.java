
public class PrefixAvgArrayTest {
	/*
	 * Prefix Array:
	 * 	A[i] = (A[0] + A[1] + ... + A[i]) / (i + 1)
	 * 
	 */

	public static void main(String[] args) {
		//Prompt array size
		int[] arrayA = {2, 4, 5, 89, 6, 9, 43};
		
		int[] array1 = prefixAvg1(arrayA);
		int[] array2 = prefixAvg2(arrayA);
		
		printArray(array1);
		printArray(array2);

	}
	public static void printArray(int[] array) {
		for(int i = 0; i < array.length; i++) {
			System.out.print((i < array.length - 1)? array[i] + ", " : array[i] + "\n");
		}
	}
	
	//Creates O(n^2) time
	public static int[] prefixAvg1(int[] arr) {
		int[] A = new int[arr.length];
		for(int i = 0; i < arr.length; i++) {
			int s = arr[0];
			for(int j = 1; j <= i; j++) {
				s += arr[j];
			}
			A[i] = s / (i + 1);
		}
		return A;
	}
	
	//Creates O(n) time (An Optimization!)***
	public static int[] prefixAvg2(int[] arr) {
		int[] A = new int[arr.length];
		int s = 0;
		for (int i = 0; i < arr.length; i++) {
			s += arr[i];
			
			A[i] = s / (i + 1);
		}
		return A;
	}

}
