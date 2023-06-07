// LEETCODE 75

/*
Given an array nums with n objects colored red, white, or blue, sort them in-place so that objects of the same color are adjacent, with the colors in the order red, white, and blue.
We will use the integers 0, 1, and 2 to represent the color red, white, and blue, respectively.
You must solve this problem without using the library's sort function.
*/

// Input:
/*
6
2 0 2 1 1 0
*/

// Output:
/*
0 0 1 1 2 2 
*/

//Approach:
/*
In this problem, we have to sort the numbers as 0,1,2, then it is sufficient to place all the 0s in the begining and 2s in the end. So, just 
create 2 variable- one in the begining and other at the end. Now, start loop from begining to the end variable created - if num is 2, then swap
the current num with the end variable and decrement the end by 1 also decrease i also by 1 so, to check the currently swap number also.
If num is 0, then swap it with the begining index and increment the begining by 1.

At last, the array will be sorted in order 0 -> 1 -> 2
*/

import java.util.*;

public class _05Sort012 {
    public static void main(String[] args) {
        try(Scanner scn = new Scanner(System.in)) {
            int n = scn.nextInt();

            int[] nums = new int[n];

            for(int i = 0;i<n;i++) {
                nums[i] = scn.nextInt();
            }
            
            sortColors(nums);
            
            for(int i = 0;i<n;i++) {
                System.out.print(nums[i] + " ");
            }
        }
    }
    
    public static void sortColors(int[] nums) {
        int beg = 0,end = nums.length-1;

        int i = 0;
        while(i<end){

            if(nums[i] == 0) {
                swap(nums,i,beg);
                beg++;
                i++;
            } else if (nums[i] == 2) {
                swap(nums,i,end);
                end--;
            } else {
                i++;
            }
        }
    }

    private static void swap(int[] nums,int i,int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}