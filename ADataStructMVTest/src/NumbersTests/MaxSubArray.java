package NumbersTests;

import java.util.*;

public class MaxSubArray {

	public static void main(String[] args) {
		int[] array = {-2,1,-3,4,-1,2,1,-5,4};
		//Max sub array will be the sum of each element; if the element sum is negative, make the sum zero and continue adding the following elements
		/**
		 * Ex: -2 => 0 + 1 - 3 (-2) => 0 + 4 - 1 + 2 + 1 - 5 (2) + 4 ==> 6	(returns six)
		 */
		System.out.println(maxSubArray(array));
	}
	
	public static int maxSubArray(int[] nums) {
//        int localMax = Integer.MIN_VALUE;
//        int max = nums[0];
//        for(int i = 0; i < nums.length; i++){
//            localMax = Math.max(nums[i], nums[i]+localMax);
//            if(localMax > max){max = localMax;}
//        }
//        return max;
		int maxSub = nums[0];
		int currentSum = 0;
		
		for(int n = 0; n < nums.length; n++) {
			if(currentSum < 0) {currentSum = 0;}
			currentSum += nums[n];
			maxSub = Math.max(currentSum, maxSub);
		}
		return maxSub;
    }

}
