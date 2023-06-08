// LEETCODE 62

/*
There is a robot on an m x n grid. The robot is initially located at the top-left corner (i.e., grid[0][0]). The robot tries to move to the 
bottom-right corner (i.e., grid[m - 1][n - 1]). The robot can only move either down or right at any point in time.

Given the two integers m and n, return the number of possible unique paths that the robot can take to reach the bottom-right corner.
*/

// Input:
/*
53
4
*/

// Output:
/*

*/

//Approach:
/*
Approach 1: Using recursion
In this problem, we start from begining index 0,0 and traverse left or down and all the ways from these two paths will be the answer and we 
add it. if either index bound out of range, then return 0. if i,j reaches last index, then return 1. 

Approach 2: Using Dynamic Programming (Recursive) Same as above method, just uses a 2D dp array which is used to store the values that the i,j 
index takes to reach to bottom right. 

Approach 3: Using Dynamic Programming (Iterative) In this, a 2D dp array is created and in the bottom row and the last column, there will be
always one as there will be 1 path only if bottom row (right) and last column (down). For all other index, the number stored in the just adjacent
bottom and left will be the answer for that index. So, meaning of dp will be the the number of path from the current index to reach the bottom 
right. So, the small problem will be at bottom right and large problem will be at top left.

Approach 4: Using Mathematics Concept 
Now, if we consider that there are m rows and n columns and only we can move either left or bottom, so there will be overall m-1 down moves and
n-1 left moves. So, in total there will be (m-1)+(n-1) = (m+n-2) moves. Now, out of these total moves we have to perform m-1 downs or n-1 left 
moves. Thus, selecting (m-1) moves from (m+n-2)moves can be calculated using the concept of nCr as (m+n-2)C(m-1) = (m+n-2)C(r-1). 
So, we need to calculate nCr. As both nCr and nC(n-r) are same, so we can consider r as Math.min(r,n-r) to save a little bit of time complexity.
*/

import java.util.*;

public class _17UniqueGridPaths {
    
    public static void main(String[] args) {
        try(Scanner scn = new Scanner(System.in)) {
            int m = scn.nextInt();
            int n = scn.nextInt();

            System.out.println(uniquePaths4(m,n));

        }
    }

    public static int uniquePaths1(int i,int j,int m, int n) {
        if(i == m-1 && j == n-1) {
            return 1;
        } 
        
        if(i == m || j == n) return 0;
        return uniquePaths1(i+1, j, m, n) + uniquePaths1(i, j+1, m, n);
    }

    public static int uniquePaths2(int i,int j,int m, int n,int[][] dp) {
        if(i == m-1 && j == n-1) {
            return 1;
        } 
        
        if(i == m || j == n) return 0;
        if(dp[i][j] != -1) return dp[i][j];
        return dp[i][j] = uniquePaths1(i+1, j, m, n) + uniquePaths1(i, j+1, m, n);
    }

    public static int uniquePaths3(int m, int n) {
        int dp[][] = new int[m][n];

        for(int i = m-1;i>=0;i--) {
            for(int j = n-1;j>=0;j--) {
                if(i == m-1 && j == n-1) dp[i][j] = 1;
                else if(i == m-1 || j == n-1) dp[i][j] = 1;
                else {
                    dp[i][j] = dp[i+1][j] + dp[i][j+1];
                }
            }
        } 

        return dp[0][0];
    }
    
    public static int uniquePaths4(int m, int n) {
        
        int totalMoves = (m - 1) + (n - 1);
        int downMoves = Math.min(m - 1,n - 1);

        // totalMoves C downMoves
        double res = 1;
        for(int i = 1;i<=downMoves;i++) {
            res = res*(totalMoves - downMoves+i)/i;
        } 

        return (int)res;
    }
}


    
            