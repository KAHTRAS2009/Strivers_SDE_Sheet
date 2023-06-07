// LEETCODE 74

/*
You are given an m x n integer matrix matrix with the following two properties:
1) Each row is sorted in non-decreasing order.
2) The first integer of each row is greater than the last integer of the previous row.
Given an integer target, return true if target is in matrix or false otherwise.

You must write a solution in O(log(m * n)) time complexity.
*/

// Input:
/*
4 3 
1 3 5 7
10 11 16 20
23 30 34 60
13
*/

// Output:
/*
false
*/

//Approach:
/*
In this problem, it is mentioned that the first element of the column is greater than the last element of the previous row. So, instead of
considering this problem as a 2D array, we can consider it as 1D array, and applying binary Search over it to find whether target exist or not.
Now, if arr is as with index as:

0 1 2 3
4 5 6 7
8 9 10 11
12 13 14 15
 
then any index can be converted into equivalent row and col by dividing number of col by index to get row index and modulos the index with the
number of columns to get the columns index,i.e.,

row = index/m;
col = index%m;

So, in this way, we can easily access the 2D matrix or we can say we have transform the 2D matrix into 1D matrix.
*/

import java.util.*;

public class _13SearchA2DMatrix {
    
    public static void main(String[] args) {
        try(Scanner scn = new Scanner(System.in)) {
            int n = scn.nextInt();
            int m = scn.nextInt();

            int[][] matrix = new int[n][m];

            for(int i = 0;i<n;i++) {
                for(int j = 0;j<m;j++) 
                    matrix[i][j] = scn.nextInt();
            }

            int target = scn.nextInt();

            System.out.println(searchMatrix(matrix,target));
        }
    }

    public static boolean searchMatrix(int[][] matrix, int target) {

        int i = 0,j = matrix.length*matrix[0].length-1;

        while(i<=j) {
            int mid = (i+j)/2;

            int row = mid/matrix[0].length;
            int col = mid%matrix[0].length;

            if(matrix[row][col] == target) {
                return true;
            } else if (matrix[row][col] > target) {
                j = mid - 1;
            } else {
                i = mid + 1;                
            }
        } 

        return false;
    }
}
