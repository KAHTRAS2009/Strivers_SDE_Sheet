// LEETCODE 493

/*
Given an integer array nums, return the number of reverse pairs in the array.

A reverse pair is a pair (i, j) where:
-> 0 <= i < j < nums.length
-> nums[i] > 2 * nums[j].
*/

// Input:
/*
5
2 4 3 5 1
*/

// Output:
/*
3
*/

//Approach:
/*
This problem is same as Count Inversion where we find the number of element in the left array greater than the number of elements in the right
array using merge sort. But, here number in the left array must be more than twice the number in right array. So, while merging the left and 
right array, we check the number of elements in the left array that satisfy the given criteria and laer on merge the two array. So, to check 
if the twice condition is met, we iterate over the left array and in the right array we keeps on increasing the pointer until the given criteria
is satisfied.At the point where the condition not satisfy, then increase that much numbers/ index of j of the right array into a global variable 
that keeps track of the condition. For the next number in the left array, we check from the previous right array element, because both the array
sorted. Hence, the answer is found.
*/

import java.util.Scanner;

public class _18ReversePair {

    public static void main(String[] args) {
        try(Scanner scn = new Scanner(System.in)) {
            int n = scn.nextInt();
            int[] nums = new int[n];

            for(int i = 0;i<n;i++) {
                nums[i] = scn.nextInt();
            }

            reversePairs(nums);

            System.out.println(cnt);
        }
    }
    
    private static int cnt;
    public static int reversePairs(int[] nums) {
        cnt = 0;

        mergeSort(nums,0,nums.length-1);

        return cnt;
    }

    private static int[] mergeSort(int[] nums,int lo,int hi) {
        if(lo == hi) {
            return new int[]{nums[lo]};
        }

        int mid = (lo+hi)/2;

        int[] a = mergeSort(nums,lo,mid);
        int[] b = mergeSort(nums,mid+1,hi);
        int[] ans = merge(a,b);

        return ans;
    }

    private static int[] merge(int[] a,int[] b) {
        int i = 0,j = 0,k = 0;
        int[] ans = new int[a.length+b.length];

        int n = 0;
        int temp = 0;
        for(int m = 0;m<a.length;m++) {
            while(n<b.length && a[m] > 2*(long)b[n]) {
                n++;
            }
            temp += n;
        }

        cnt += temp;

        while(i<a.length &&  j<b.length) {
            if(a[i]<=b[j]) {
                ans[k++] = a[i++];
            } else {
                ans[k++] = b[j++];
            }
        }

        while(i < a.length){
            ans[k++] = a[i++];
        }

        while(j < b.length) {
            ans[k++] = b[j++];
        }

        return ans;
    }
}