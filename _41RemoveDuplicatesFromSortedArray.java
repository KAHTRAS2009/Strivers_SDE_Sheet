// LEETCODE 26

/*
Given an integer array nums sorted in non-decreasing order, remove the duplicates in-place such that each unique element appears only once. 
The relative order of the elements should be kept the same. Then return the number of unique elements in nums.

Consider the number of unique elements of nums to be k, to get accepted, you need to do the following things:

Change the array nums such that the first k elements of nums contain the unique elements in the order they were present in nums initially. 
The remaining elements of nums are not important as well as the size of nums.
Return k.
*/

// Input:
/*
10
0 0 1 1 1 2 2 3 3 4
*/

// Output:
/*
5
*/

// Approach: 
/*
In this problem, we use two pointer approach and set the initially the pointer i to 1 and j to 1. And we iterate till i reaches end. 
And, we check if the element at i is same as i-1 or not,if it is, then just move the i pointer and if element at i is not equal to i - 1, 
then place the element at the i index to the jth index and move the jth and ith pointer by 1.
At last, return the j.
*/

import java.util.*;

public class _41RemoveDuplicatesFromSortedArray {

    public static void main(String[] args) {
        try(Scanner scn = new Scanner(System.in)) {
            int n = scn.nextInt();

            int[] nums = new int[n];
            for(int i = 0;i<n;i++) {
                nums[i] = scn.nextInt();
            }

            int ans = removeDuplicates(nums);
            System.out.println(ans);

            display(nums);
        }
    }

    public static int removeDuplicates(int[] nums) {

        int i = 1,j = 1;

        while(i<nums.length) {
            if(nums[i] == nums[i-1]) {
                i++;
            } else {
                nums[j] = nums[i];
                i++;
                j++;
            }
        }

        return j;
        
    }

    public static void display(int[] nums) {
        for(int num:nums) {
            System.out.print(num + " ");
        }
        System.out.println();
    }
    
}
