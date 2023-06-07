// LEETCODE 88

/*
You are given two integer arrays nums1 and nums2, sorted in non-decreasing order, and two integers m and n, representing the number of elements
in nums1 and nums2 respectively.
Merge nums1 and nums2 into a single array sorted in non-decreasing order.
The final sorted array should not be returned by the function, but instead be stored inside the array nums1. To accommodate this, nums1 has 
a length of m + n, where the first m elements denote the elements that should be merged, and the last n elements are set to 0 and should be 
ignored. nums2 has a length of n.
*/

// Input:
/*
3 3 
1 2 3 
2 5 6
*/

// Output:
/*
[1,2,2,3,5,6]
*/

//Approach:
/*
In this problem, we keep 2 pointers i and j to point the numbers in the 2 array and initially both are set to 0. Now, we check if the number in 
array 2(j) is less the number in array 1(i), if it is then we have to place the number of arr2 at this particular index. So, we shift all the 
remaining elements of array 1 to the right by 1.So, shifting will take place from m upto i. And, later on place the jth index of arr2 on the 
ith index of arr1 and increase jth index.
*/

import java.util.*;

public class _09MergeSortedArray {

    public static void main(String[] args) {
        try(Scanner scn = new Scanner(System.in)) {
            int m = scn.nextInt();
            int n = scn.nextInt();

            int[] nums1 = new int[m+n];
            int[] nums2 = new int[n];

            for(int i = 0;i<m;i++) {
                nums1[i] = scn.nextInt();
            }
            
            for(int i = 0;i<n;i++) {
                nums2[i] = scn.nextInt();
            }

            merge(nums1,m,nums2,n);

            for(int i = 0;i<nums1.length;i++) {
                System.out.print(nums1[i] + " ");
            }
        }
    }
    public static void merge(int[] nums1, int m, int[] nums2, int n) {
        
        int i = 0,j = 0;

        while(j<n) {

            if(i >= m) {
                nums1[i] = nums2[j];
                j++;
            } else if(nums1[i] > nums2[j]) {
                shift(nums1,i,m);
                nums1[i] = nums2[j];
                j++;
                m++;
            }
            i++;
        }
    }

    private static void shift(int[] nums1,int i,int m) {

        for(int j = m;j>i;j--) {
            nums1[j] = nums1[j-1]; 
        }
    }
}