// LEETCODE 53

/*
Given an integer array nums, find the subarray with the largest sum, and return its sum.
*/

// Input:
/*
8
-2 -3 4 -1 -2 1 5 -3
*/

// Output:
/*
7
*/

//Approach:
/*
In this problem create a max variable to store the maximum sum of subArray and create another variable to store 
the sum of subArray. Now, start from begining and add the element to the sum variable and if it is greater than
max variable then update the max. Also, if sum becomes less than 0, then update the sum to 0. As, if the sum is
negative, then also if the next element is positive adding the negative sum will result in a value which is less
than the element value, say if sum is -2 and next element is 10, then overall sum becomes 8, which is less than 10.
So, it's better to drop off the sum to 0.
*/

import java.util.*;

public class _04MaxSumSubArray {
    
    public static void main(String[] args) {
        try(Scanner scn = new Scanner(System.in)) {
            int n = scn.nextInt();
            int[] nums = new int[n];

            for(int i = 0;i<n;i++) {
                nums[i] = scn.nextInt();
            }

            System.out.println(maxSubarraySum(nums));
        }
    }

    public static long maxSubarraySum(int[] arr) {
		// write your code here
		int max = 0,sum = 0;

		for(int i = 0;i<arr.length;i++) {
			sum += arr[i];

			if(max < sum) max = sum;

			if(sum < 0) sum = 0;
		}

		return max;
	}
}
