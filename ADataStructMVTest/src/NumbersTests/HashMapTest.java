package NumbersTests;
import java.util.*;
public class HashMapTest {
	//TODO Given an array on ints and target int, find the indices that add up to the target: 
	//		Initially test it in quadratic time complexity			  O(n^2)
	//		Then use hashmap<> to find the two indices in linear time O(n)

	public static void main(String[] args) {
		int[] arr = {1, 7, 3, 5, 2};
		int target = 9;

		displayArray(targetIndices(arr, target));
		displayArray(twoSum(arr, target));
		
		String[] str = {"ab", "a"};
		System.out.println(LCP(str));
	}
	public static void displayArray(int[] array) {
		for(int i : array) {
			System.out.print(i + " ");
		}
		System.out.println();
	}
	
	/**Delete Later*/
	public static String LCP(String[] str) {
		if(str.length == 0 || str == null) {return "";}
		//First check the characters in the first string
		for(int i = 0; i < str[0].length(); i ++) {
			char ref = str[0].charAt(i);
			for(int j = 1; j < str.length; j++) {
				if( i >= str[j].length()||ref != str[j].charAt(i)) {
					return str[0].substring(0, i);
				}
			}
		}
		return str[0];
	}
	/***/
	public static int[] targetIndices(int[] array, int target) {		
		for(int i = 0; i < array.length; i++) {
			for(int j = i + 1; j < array.length; j++) {
				if(array[i] + array[j] == target && i != j) {
					return new int[] {i, j};
				}
			}
		}
		return new int[] {0, 0};
	}
	
	public static int[] twoSum(int[] array, int target) {
		HashMap<Integer, Integer> map = new HashMap<>();
		
//		for(int i = 0; i < array.length; i++) {
//			map.put(i, array[i]);
//		}
		int current = 0;
		for(int i = 0; i < array.length; i++) {
			int complementary = target - array[i];
			if(map.containsKey(complementary)) {
				return new int[] {map.get(complementary), i};
			}
			map.put(array[i], i);
//			if(map.get(current) + array[i] == target) {
//				return new int[] {i, current};
//			}
//			current++;
		}
		//return new int[] {0, 0};
		throw new IllegalArgumentException();
	}

}
