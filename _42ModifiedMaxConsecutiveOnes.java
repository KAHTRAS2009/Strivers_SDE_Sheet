/*
Given a binary array of size 'n', your task is to find the longest subsequence of continuous 1's that can be formed by replacing at-most 'K' 
zeros by ones. Return the length of this longest subsequence of continuous 1's.
*/

// Input:
/*
10
1 0 0 1 0 1 0 1 0 1
2
*/

// Output:
/*
5
*/

// Approach: 
/*
In this problem, we initialize a variable as cnt0 which will store the number of zero found so far. Now, we use 2 pointer approach i and left,
and initialize the two pointer to zero. Now, whenever we encounter 0 in the array, we increase the cnt0 by 1. Also, if cnt0 exceeds the k, 
then check if at left it is one or zero, if it is zero then only reduce the cnt0 and if not then move to the next left and continue this until
cnt0 is more than k. And update the result by storing maximum of result and i-left+1.
*/

import java.util.*;

public class _42ModifiedMaxConsecutiveOnes {
    public static void main(String[] args) {
        try(Scanner scn = new Scanner(System.in)) {
            int n = scn.nextInt();

            int[] nums = new int[n];
            for(int i = 0;i<n;i++) {
                nums[i] = scn.nextInt();
            }

            int k = scn.nextInt();
            
            int ans = longestSubSeg(nums,n,k);
            System.out.println(ans);
        }
    }

    public static int longestSubSeg(int[] nums , int n, int k) {
		
		int cnt0 = 0,res = 0;
		int left = 0;

		for(int i = 0;i<n;i++) {
			if(nums[i] == 0){
				cnt0++;
			} 

			while(cnt0 > k) {
				if(nums[left] == 0) {
					cnt0--;
				}
				left++;
			}

			res = Math.max(res,i-left+1);
		}

		return res;
	}
}