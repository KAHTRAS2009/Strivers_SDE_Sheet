// LEETCODE 128

/*
Given an unsorted array of integers nums, return the length of the longest consecutive elements sequence.
You must write an algorithm that runs in O(n) time.
*/

// Input:
/*
6
100 4 200 1 3 2
*/

// Output:
/*
4
*/

// Approach:
/*
One straight approach is to start from each index and then look for the next value in the array and update the counter accordingly. So, the time
complexity will be around O(n^2). So, to avoid this we create a hashset and then for all the values in the hashset we look whether there exist 
a previous value or not. If it exist, then it is guaranteed that the sequence from this number can never be longest. And, if the previous value
doesn't exist, then set a counter to zero and start checking for the next value and updating the cnt and the element as well. At the end, update
the longest variable created with the max value of counter and longest.

Time Complexity: O(n)
Space Complexity: O(n)
*/

import java.util.*;

public class _21LongestConsecutiveSequence {
    
    public static void main(String[] args) {
        try(Scanner scn = new Scanner(System.in)) {
            int n = scn.nextInt();
            int[] nums = new int[n];

            for(int i = 0;i<n;i++) {
                nums[i] = scn.nextInt();
            }

            int max = longestConsecutive(nums);

            System.out.println(max);

        }
    }
    
    public static int longestConsecutive(int[] nums) {
            if(nums.length == 0) return 0;
            if(nums.length == 1) return 1;

            Set<Integer> uniqSet = new HashSet<>();
    
            for(int num:nums) {
                uniqSet.add(num);
            }
    
            int longest = 1;
    
            for(int x : uniqSet) {
    
                if(!uniqSet.contains(x-1)) {            // if no previous element exist
    
                    int cnt = 0;                        // Initialize a variable to track the length
                    while(uniqSet.contains(x)) {        // While the element exist, update the cnt and x by 1.
                        cnt++;
                        x++;
                    }
    
                    longest = Math.max(longest,cnt);    // update the longest
                }
    
            }
    
            return longest;
        }
}