// LEETCODE 15

/*
Given an integer array nums, return all the triplets [nums[i], nums[j], nums[k]] such that i != j, i != k, and j != k, and 
nums[i] + nums[j] + nums[k] == 0.

Notice that the solution set must not contain duplicate triplets.
*/

// Input:
/*
6
-1 0 1 2 -1 -4
*/

// Output:
/*
[[-1,-1,2],[-1,0,1]]
*/

// Approach: 
/*
This problem resembles to the 4 sum problem. In this, we initially sort the array and then take three pointers one pointer,i at the beginning 
and it moves till end.Now, the 2pointer approach is used inside the remaining elements by keeping one pointer j at the next of i and k pointer 
at the end, Now, sum the values at i,j and k pointer and if they add up to 0, then it is one of the pair, and add it up to the answer. And, shift 
the j pointer by 1 ahead(j++) and k pointer by 1 behind(k--). If the sum is greater than 0, then shift the k pointer else the sum is less than 0,
move the j pointer. Also, to avoid duplicacy, check if the curr element is same as the previous element for i and j pointer and curr and the next
value for the k pointer. 
*/

import java.util.*;

public class _39ThreeSum {

    public static void main(String[] args) {
        try(Scanner scn = new Scanner(System.in)) {
            int n = scn.nextInt();

            int[] nums = new int[n];
            for(int i = 0;i<n;i++) {
                nums[i] = scn.nextInt();
            }

            List<List<Integer>> ans = threeSum(nums);
            System.out.println(ans);
        }
    }

    public static List<List<Integer>> threeSum(int[] nums) {

        List<List<Integer>> ans = new ArrayList<>();

        Arrays.sort(nums);

        for(int i = 0;i<nums.length-2;i++) {
            int j = i+1,k = nums.length-1;

            if(nums[i] + nums[j]+nums[j+1] > 0) break;
            while(j<k) {
                if(nums[j]+nums[k] == -nums[i]) {
                    List<Integer> tmp = new ArrayList<>(List.of(nums[i],nums[j],nums[k]));
                    ans.add(tmp);
                    j++;k--;
                    
                    while(j<k && nums[j] == nums[j-1]) j++;
                    while(j<k && nums[k] == nums[k+1]) k--; 

                } else if(nums[j] + nums[k] > -nums[i]) {
                    k--;
                    while(j<k && nums[k] == nums[k+1]) k--;
                } else {
                    j++;
                    while(j<k && nums[j] == nums[j-1]) j++;
                }
            }

            while(i+1<nums.length && nums[i] == nums[i+1]) i++;
        }

        return ans;
        
    }    
}
