/*
Given an array of N integers, count the inversion of the array (using merge-sort).
What is an inversion of an array? Definition: for all i & j < size of array, if i < j then you have to find pair (A[i],A[j]) 
such that A[j] < A[i].
*/

// Input:
/*
5
5 3 2 1 4
*/

// Output:
/*
7
*/

//Approach:
/*
In this problem, we have to determine the number of pairs that are i < j and the number on the left must be greater than the number on the right.
So, in this problem we have to determine the number of smaller elements to the right at a index and summation of all these values will be the
answer. Now, we can think of the array as two array one left and other right - both of them being in sorted order and if we have to follow the 
criteria as i is from left array, j is from right array and satisfying the criteria as number in left is less than number in right, then our 
criteria is satisfied. Now, we have to formulate two array in half way both of them as sorted then we must the criteria of MERGE sort.
Thus, we create a global variable as cnt and whenever the element in the left array is more than the element in the right array, then we increment
the cnt by the number of elements in the left array from and including the current index as both the array are sorted.(Consider left arr as
3,5,6 and right array as 4,6,8. So, if 5 is greater than 4, then definately 6 is also going to be greater than 4).
*/

import java.util.*;

public class _12CountInversions {

    public static void main(String[] args) {
        try(Scanner scn = new Scanner(System.in)) {
            int n = scn.nextInt();

            int[] nums = new int[n];

            for(int i = 0;i<n;i++) {
                nums[i] = scn.nextInt();
            }

            mergeSort(nums,0,n-1);

            System.out.println(cnt);
        }
    }
    
    private static int cnt = 0;

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

        int[] ans = new int[a.length+b.length];

        int i = 0,j = 0,k = 0;
        while(i<a.length && j<b.length) {
            if(a[i] < b[j]) {
                ans[k++] = a[i];
                i++;
            } else {
                ans[k++] = b[j];
                cnt += a.length - i;        // all the remaining elements will also be greater than jth index element of arr b.
                j++;
            }
        }

        while(i < a.length) {
            ans[k++] = a[i++];
        }

        while(j < b.length) {
            ans[k++] = b[j++];
        }

        return ans;
    }

}