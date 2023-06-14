// LEETCODE 42

/*
Given n non-negative integers representing an elevation map where the width of each bar is 1, compute how much water it can trap after raining.
*/

// Input:
/*
12
0 1 0 2 1 0 1 3 2 1 2 1
[0,1,0,2,1,0,1,3,2,1,2,1]
*/

// Output:
/*
6
*/

// Approach: 
/*
There are two approaches to solve this problem, one is to determine the greatest element to the left and greatest element to the right. And,
then the answer will be the summation of minimum of the greatest left and right element to the right - height at curr index.
Time Complexity: O(n)
Space Complexity: O(n)

Another approach is to use 2 Pointer approach.
In this method, we use 2 pointer left and right and also we take two variable to set the maximum value from left and maximum value from right.
initially, the left pointer is set to 0 and the right pointer at the end.Also, initially set the leftMax and rightMax to 0. Now, we loop till
left <= right, we check if the value at left pointer is less than the value at right pointer and if it is then we check if the leftMax is greater
than the current value of left, then water can trap in this block and if the leftMax is smaller than the current value at left, then we update the
leftMax and then move the left by 1 towards left. Now, if the value at the right pointer is less than the pointer at the left pointer, then again 
check if the maximum from the right (rightMax) is greater than the current value at right pointer,then water can trap in this block and add this 
to the result and if the rightMax is smaller than the current right pointer then update the rightMax and move the right pointer by 1 towards right.
Time Complexity: O(n)
Space Complexity: O(1)
*/

import java.util.*;

public class _40TrappingRainWater {

    public static void main(String[] args) {
        try(Scanner scn = new Scanner(System.in)) {
            int n = scn.nextInt();

            int[] height = new int[n];
            for(int i = 0;i<n;i++) {
                height[i] = scn.nextInt();
            }

            int water = trap(height);
            System.out.println(water);

            System.out.println(trap2(height));
        }
    }

    
    public static int trap(int[] height) {
        int res = 0;
        int left = 0,right = height.length-1;           
        int leftMax = 0,rightMax = 0;               // to store the max from the left pointer and max from the right pointer

        while(left <= right) {
            if(height[left] <= height[right]) {             // if left pointer value is less than the right pointer value.

                if(height[left] >= leftMax) leftMax = height[left];         // then, check if there is any maximum in the left, if not then update the leftMax
                else res += leftMax-height[left];                           // else, add the leftMax- height[left].

                left++;                                                     // move the left pointer.
            } else {                                                        // if the right pointer value is less than left pointer value,
                if(rightMax<=height[right]) rightMax = height[right];       // then, check the same.
                else res += rightMax - height[right];
                right--;
            }
        }

        return res;
    }    
    
    public static int trap2(int[] height) {
        int n = height.length;
    
        int[] left = new int[n];            // to store the greatest element to the left.
        int[] right = new int[n];           // to store the greatest element to the right.
        
        left[0] = height[0];                
        for(int i = 1;i<n;i++){
            left[i] = Math.max(left[i-1],height[i]);            // max of the curr and the previous value
        }
    
        right[n-1] = height[n-1];
        for(int i = n-2;i>=0;i--){
            right[i] = Math.max(right[i+1],height[i]);          // max of the curr and the next value
        }
    
        int res = 0;
    
        for(int i = 0;i<n;i++) {
            res += Math.min(left[i],right[i]) - height[i];      // Min value of the two extremes - height[curr].
        }
    
        return res;
    }
}
