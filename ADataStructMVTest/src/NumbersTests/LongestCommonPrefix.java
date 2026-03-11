package NumbersTests;
import java.util.*;

public class LongestCommonPrefix {

	public static void main(String[] args) {
		// TODO: first test an array of strings that contain a common prefix
		String[] common = {"strand", "string", "straw"};
		System.out.println(commonPrefix(common));
		
		
		
		//If array1 = {233, 45457, 224, 9090},	and array2 = {33, 90, 776, 34, 45456}
		//	find the largest element from both arrays and find it's LCP size
		
		int[] arr1 = {233, 45457, 224, 9090}, arr2 = {33, 90, 776, 34, 45456};
		System.out.println(LCPTest(arr1, arr2));
		//System.out.println(commonNumPrefix(LCP(arr1, arr2)));
		System.out.println(LongestCommonPrefix(arr1, arr2));
	}
	
	// Horizontal scanning: each character in the first string of the array is compared to all the other 
	//	characters in the following strings;
	//		if the array of strings contain the same first few prefixes, return that prefix
	public static String commonPrefix(String[] arr) {			/**NOTE: Check the length FIRST to prevent OutOfBoundsException**/
		if(arr.length == 0 || arr == null) {return "";}
		
		for(int i = 0; i < arr[0].length(); i++) {	//Iterate through each character in the first string
			char currentChar = arr[0].charAt(i);
			
			for(int j = 0; j < arr.length; j++) {		//Compare the character to the following strings' chars
				if(i >= arr[j].length() || currentChar != arr[j].charAt(i)) {	//If the currentChar doesn't match the other array character or
					return arr[0].substring(0, i);								// if the index i exceeds greater than the comparison string's length, then
				}																// return the substring from 0 to the current character
			}		/**In other words: While the inner for loop is true, continue the iteration*/
		}
		
		return arr[0];
	}
	
	public static int LCPTest(int[] array1, int[] array2) {
		//int length = (array1.length > array2.length)? array1.length : array2.length;
		int largest = Integer.MIN_VALUE;
		for(int i = 0; i < array1.length; i++) {
			if(array1[i] > largest) {
				largest = array1[i];
			}
		}
		for(int i = 0; i < array2.length; i++) {
			if(array2[i] > largest) {
				largest = array2[i];
			}
		}
				
		return largest;
	}
	public static int commonNumPrefix(int largest) {
//		int first = 0;
//		int size = 0;
//		int largeTest = largest;
//		while(largeTest > 1) {
//			first = largeTest%10;
//			largeTest /= 10;
//			size++;
//		}
		return 0;
	}

	public static int LongestCommonPrefix(int[] array1, int[] array2) {
		HashSet<String> seen = new HashSet<>();
		
		for(int i : array1) {
			String strNum = i+"";
			StringBuilder prefix = new StringBuilder();
			for(char c : strNum.toCharArray()) {
				prefix.append(c);
				seen.add(prefix.toString());
			}			
		}
		int length = 0;
		for(int j : array2) {
			String strNum =j+"";
			StringBuilder prefix = new StringBuilder();
			for(char c : strNum.toCharArray()) {
				prefix.append(c);
				if(seen.contains(prefix.toString())) {
					length = Math.max(length, prefix.length());
				}
			}
		}
		return length;
	}
}
