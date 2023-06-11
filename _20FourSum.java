// LEETCODE 18

/*
Given an array nums of n integers, return an array of all the unique quadruplets [nums[a], nums[b], nums[c], nums[d]] such that:

0 <= a, b, c, d < n
a, b, c, and d are distinct.
nums[a] + nums[b] + nums[c] + nums[d] == target
You may return the answer in any order.
*/

// Input:
/*
6
1 0 -1 0 -2 2
0
*/

// Output:
/*
[[-2, -1, 1, 2], [-2, 0, 0, 2], [-1, 0, 0, 1]]
*/

//Approach:
/*
In this problem, we use the concept that was used in the two sum pair problem.Initially, we sort the array and then we iterate i from 0 to end 
and j from i+1 to end. Now, after that we use 2 pointer approach in the remaining numbers that is we keep one pointer at j+1 (say,k) and other 
pointer at the end (say,l), and then check if the sum of i,j,k,lth index element is equal to target, if so, then we add all these numbers into 
the answer, if the sum exceeds the target then we decrease the latter pointer (k), and if sum is less than target, then increase the beginning
pointer(l). Now, to avoid the duplicate values, for i, we skip all those numbers that are same as the previous one except the first one and 
similarly, for the j also. Now, for k and l, we skip the numbers if k<l and for k -> nums[k] == nums[k-1] and for l -> nums[l] == nums[l+1].
*/

import java.util.*;

public class _20FourSum {
    
    public static void main(String[] args) {
        try(Scanner scn = new Scanner(System.in)) {
            int n = scn.nextInt();
            int [] arr = new int[n];

            for(int i = 0;i<n;i++) {
                arr[i] = scn.nextInt();
            }

            int target = scn.nextInt();

            List<List<Integer>> ans = fourSum(arr,target);

            System.out.println(ans);
            
        }
    }    
    public static List<List<Integer>> fourSum(int[] nums, int target) {

        List<List<Integer>> ans = new ArrayList<>();

        Arrays.sort(nums);

        for(int i = 0;i<nums.length;i++) {
            if(i != 0 && nums[i] == nums[i-1]) continue;
            for(int j = i+1;j<nums.length;j++) {
                if(j != i+1 && nums[j] == nums[j-1]) continue;
                
                int k = j+1;
                int l = nums.length - 1;

                while(k<l) {
                    long sum = nums[i];
                    sum += nums[j];
                    sum += nums[k];
                    sum += nums[l];

                    if(sum == target) {
                        List<Integer> tmp = new ArrayList<>(List.of(nums[i],nums[j],nums[k],nums[l]));
                        ans.add(tmp);
                        k++;l--;

                        while(k<l && nums[k] == nums[k-1]) k++;
                        while(k<l && nums[l] == nums[l+1]) l--;
                    } else if(sum > target) l--;
                    else k++;
                }
            }
        }
        
        return ans;
    }
}
