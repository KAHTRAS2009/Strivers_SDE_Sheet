// LEETCODE 31

/*
A permutation of an array of integers is an arrangement of its members into a sequence or linear order.

For example, for arr = [1,2,3], the following are all the permutations of arr: [1,2,3], [1,3,2], [2, 1, 3], [2, 3, 1], [3,1,2], [3,2,1].
The next permutation of an array of integers is the next lexicographically greater permutation of its integer. More formally, if all the permutations of the array are sorted in one container according to their lexicographical order, then the next permutation of that array is the permutation that follows it in the sorted container. If such arrangement is not possible, the array must be rearranged as the lowest possible order (i.e., sorted in ascending order).

For example, the next permutation of arr = [1,2,3] is [1,3,2].
Similarly, the next permutation of arr = [2,3,1] is [3,1,2].
While the next permutation of arr = [3,2,1] is [1,2,3] because [3,2,1] does not have a lexicographical larger rearrangement.
Given an array of integers nums, find the next permutation of nums.

The replacement must be in place and use only constant extra memory.
*/

// Input:
/*
2 1 5 4 3 0 0
*/

// Output:
/*
2 3 0 0 1 4 5
*/

//Approach:
/*
In this problem, look from the end for a position where the value takes a dip i.e., the next is greater than the current value, reason for this 
is simple that we have to determine the next permutation and it will be greater than the current one if the current permutation is not the last
one. Thus, we have to replace at a index from end with the just next greater element to the right. For example,
2 1 5 4 3 0 0 
for last number, 0 there is no element in the right.
for 2nd last num, 0 there is 0 to its right, but it is not greater than it.
for 3, there are [0,0] and none is greater than it.
for 4, there are [3,0,0] and none is greater than it.
for 5, there are [4,3,0,0] and none is greater than it.
for 1, there are [5,4,3,0,0], in this case 5 is greater than 1 and 5 was greater than 4 and 3 also.

So, we check for immediate right element and if it is greater than the current one, then we have find the element that needs to be replaced.

for 1, there are 3 elements that are greater than it [5,4,3], and as we have to find the next permutation, we look for the just greater value than 
the current value from the end,i.e, 3 and swap these two values, so the array becomes -> 2 3 5 4 1 0 0, but the next permutaion will be 2 3 0 0 1 4 5
So, we need to reverse/sort the remaining elements from the index where we found a dip.
*/

import java.util.*;

public class _3NextPermutation {

    public static void main(String[] args) {
        try(Scanner scn = new Scanner(System.in)) {
            int n = scn.nextInt();
            int[] nums = new int[n];

            for(int i = 0;i<n;i++) {
                nums[i] = scn.nextInt();
            }

            nextPermutation(nums);

            for(int i = 0;i<n;i++) {
                System.out.print(nums[i] + " ");
            }
        }
    }

    public static void nextPermutation(int[] nums) {
        
        int i = nums.length - 2;
        while(i >= 0 && nums[i] >= nums[i+1]) {             // Check if there is any dip from the end.
            i--;
        }

        if(i >= 0) {                                        // If there is dip anywhere and the permutation given is not the last permutation.
            for(int j = nums.length-1;j>i;j--) {
                if(nums[j]>nums[i]) {                       // Look for value that is just greater than the dip current value.
                    swap(nums,i,j);
                    break;                                  // break as we looking from the end itself. say arr = [2,1,5,3,4,0,0] then dip occur 
                                                            // at 3 instead of 1.
                }
            }
        }

        reverse(nums,i+1);                                  // reverse the remaining numbers, if the arr represents the last permutation, then
                                                            // i will be -1 and reverse take place from begining.
    }

    private static void reverse(int[] nums,int i) {
        int j = nums.length-1;
        while(i<j) {
            swap(nums,i,j);
            i++;
            j--;
        }
    }

    private static void swap(int[] nums,int i,int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}