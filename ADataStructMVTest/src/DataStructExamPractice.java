
public class DataStructExamPractice {

	public static void main(String[] args) {
//		int[] arr = {2, 4, 6, 8, 10};
//		printArray(arr);
//		arr = prefixAvg(arr);
//		printArray(arr);
		
		getCurrTime();
	}

	public static void printArray(int[] arr) {
		for(int i : arr) {
			System.out.print(i + " ");
		}
		System.out.println();
	}
	
	public static void getCurrTime() {
		long totalMilliSec = System.currentTimeMillis();	//Total Milliseconds after Jan, 1970
		
		long TotalSec = totalMilliSec / 1000;
		long CurrSec = TotalSec % 60;
		
		System.out.println(CurrSec);
	}
	
	//Prefix average returns a new array of integers for each element being modified:
	/*
	 * Prefix Array:
	 * 	A[i] = (A[0] + A[1] + ... + A[i]) / (i + 1)
	 * 
	 */
	public static int[] prefixAvg(int[] array) {
		int[] prefixArray = new int[array.length];
		int k = 0;
//		for(int i = 0; i < array.length; i++) {
//			int A = array[0];
//			for(int j = 1; j <= i; j++)
//				A += array[j];
//
//			prefixArray[i] = A/(i + 1);
//		}
		for(int i = 0; i < array.length; i++) {
			k += array[i];
			prefixArray[i] = k/(i + 1);
			k++;
		}
		return prefixArray;
	}
}
