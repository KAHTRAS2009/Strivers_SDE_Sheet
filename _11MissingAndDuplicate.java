/*
1. You are given an array of length n containing numbers from 1 to n.
2. One number is present twice in array and one is missing.
3. You have to find these two numbers.
*/

// Input:
/*
7
1 3 4 5 1 6 2
*/

// Output:
/*
1 7
*/

//Approach:
/*
In this problem, there are numbers supposed to from 1 to n, but 1 number is missing and 1 is occuring twice. So, if we XOR all the elements 
of the array, then we will get 1^3^4^5^1^6^2 = 3^4^5^6^2. Now, if XOR this with the numbers from 1 to n(7) as the numbers supposed to be, we get
3^4^5^6^2^1^2^3^4^5^6^7 = 1^7, which is same as now finding the all Repeating Even Number of times except two which occur odd number of times in 
the sequence [1,3,4,5,1,6,2,1,2,3,4,5,6,7]. So, we first find the rSB of 1^7 and then iterate over both the array and numbers from 1 to n, and
XOR those elements that have bit at the rSb as 1 and XOR those elements that have bit at rSB as 0. And, at last, we get the 2 numbers, Now to 
find which one is missing and which one is repeating, we iterate again over the array and check if the num1 is present or not. If not, 
If it is there, then it is Repeating number and the other number will be Missing,otherwise Opposite.
*/

import java.util.*;

public class _11MissingAndDuplicate {
    
    public static void main(String[] args) {
        try(Scanner scn = new Scanner(System.in)) {
            int n = scn.nextInt();

            int[] nums = new int[n];

            for(int i = 0;i<n;i++) {
                nums[i] = scn.nextInt();
            }

            int[] ans = missingAndDuplicate(nums);
            System.out.println(ans[0] + " " + ans[1]);
        }
    }

    private static int[] missingAndDuplicate(int[] A) {
        int xor = 0;
        
        for(int i = 0;i<A.length;i++) {
            xor ^= A[i];
        }
        
        for(int i = 1;i<=A.length;i++) {
            xor ^= i;
        }
        
        int m = 0,r = 0;
        int mask = xor & -xor;
        
        for(int i = 0;i<A.length;i++) {
            if((A[i] & mask) == 0) {
                m ^= A[i];
            } else {
                r ^= A[i];
            }
        }
        
        for(int i = 1;i<=A.length;i++) {
            if ((i&mask) == 0) {
                m ^= i;
            } else {
                r ^= i;
            }
        }
        
        for(int ele:A) {
            if(ele == m) {
                return new int[]{m,r};
            }
        }
        
        return new int[]{r,m};
    }
}