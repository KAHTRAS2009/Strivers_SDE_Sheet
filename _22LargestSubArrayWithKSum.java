/*
Given an array of integers nums and an integer k, return the length of longest subarrays whose sum equals to k.
A subarray is a contiguous non-empty sequence of elements within an array.
*/

// Input:
/*
6
15 -2 2 -8 1 7
0
*/

// Output:
/*
4
*/

// Approach:
/*
In this problem, we can either iterate through each subarray and generate the sum and check if the sum is equal to 'k' and if it is, then update
the longest variable as per the length of the subArray. In this, Time complexity is O(n^2) and space complexity is O(1).

Other method is to use HashMap. Consider that a array has index as 01234567. And, if we are at index 5, and sum upto index 5 is sum and target is
k, then we check if there exist a sum in the previous iteration that result in the sum as sum - k. Say, if sum - k was found at index 2. So, 
sum - k was found at index 2 and sum is found at index 5, then definately, the subarray from 3 to 5 will have sum as k.So, its length will be 
5 - (index at which sum was sum - k) ==> 5 - 2 = 3. And,we update the largest with this if it is largest. And, then we add the current sum if it
doesn't exist in the hashmap only.Cause if it exist then we tries to maximise the subarray and we tries to keep the sum as much as towards left.
*/

import java.util.*;

public class _22LargestSubArrayWithKSum {
    public static void main(String[] args) {
        try(Scanner scn = new Scanner(System.in)) {
            int n = scn.nextInt();
            int[] nums = new int[n];

            for(int i = 0;i<n;i++) {
                nums[i] = scn.nextInt();
            }

            int k = scn.nextInt();
            int l = LongestSubsetKSum(nums, k);

            System.out.println(l);
            
        }
    }

    public static int LongestSubsetKSum(int[] arr,int k) {

		long sum = 0;               // to store the sum of elements                                   
		int longest = 0;            // to store the largest subarray with k sum

        // This hashmap will contain key:value pair as sum:index at which this sum is found
		HashMap<Long,Integer> hm = new HashMap<>();

		for(int i = 0;i<arr.length;i++) {
			sum += arr[i];          // add the element into the sum.

			if(sum == k) longest = i+1;     // check if the sum is equal to the target, if it is the subarray from the beginning and thus, it is maximum.

			if(hm.containsKey(sum-k)) {     // Check if there exist a sum as sum - k [0123 - 456]. Consider that 0123 forms sum-k, then 456 will
                                            // definately forms k as target only thereafter sum - k + k will results in sum.
				longest = Math.max(longest,i-hm.get(sum-k));        // update the longest as max among longest and curr - index at which sum was
                                                                    // sum-k
			} 


            // If hashmap already does not contains sum, then add it to the hashmap at ith index.
            // reason being, we want the largest subarray and as i is moving forward, thus if there are two index that results in sum as 'S' 
            // (say,1 and 3),then 1 should be there as sum 'S' rather than 3.
            if(!hm.containsKey(sum)){   
				hm.put(sum,i);
			}			
		}

		return longest;

	}
}