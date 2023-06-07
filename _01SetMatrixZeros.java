// LEETCODE 73

/*
Given an m x n integer matrix matrix, if an element is 0, set its entire row and column to 0's.
You must do it in place.
*/

// Input:
/*
3 3
1 2 3
4 0 6
7 8 9
*/

// Output:
/*
1 0 3
0 0 0
7 0 9
*/

//Approach:
/*
Approach 1: In this, we create 2 array one for the row and other for the columns. If at any index, zero is present in the matrix then mark the
corresponding row and column as true. 
And, again iterate and check if the corresponding row and column is marked in the array or not, if marked, then replace the value of the index 
to zero.

Time Complexity: O(m*n)
Space Complexity: O(m) + O(n)

Approach 2: In the previous approach, rather than creating 2 array, use the first row and col as the marking itself. Moreover, the first cell(0,0)
comes in both the first row and first column, so create another variable to store the marking of first column numbers.

So, if there is 0 in the first column, then mark the variable created (col0 as true). And, if there is 0 anywhere else b/w columns 1 to end, then 
mark the corresponding first row and first col as 0.

Now, starting from second row and second column(1,1), check if the corresponding first row or column (i,0)/(0,j) is marked as 0 or not, if it is 
marked then replace the number as 0.

Now, first row and first col needs to be replaced if possible. As col0 variable is marked if 0 was there in the first column and moreover if the 
first cell is 0, then also all the elements in the first row needs to 0. So, we initial check for (0,0) cell and if it is zero, then mark the 
whole first row as 0. And, later on check for col0 variable, and if it is true, then mark the whole first column as 0.

Time Complexity: O(mn)
Space Complexity: O(1)

*/

import java.util.*;

class _01SetMatrixZeros {

    public static void main(String[] args) {

        try(Scanner scn = new Scanner(System.in)) {
            int m = scn.nextInt();
            int n = scn.nextInt();

            int[][] matrix = new int[m][n];

            for(int i = 0;i<m;i++) {
                for(int j = 0;j<n;j++) {
                    matrix[i][j] = scn.nextInt();
                }
            }

            // setZeros1(matrix);
            
            setZeros2(matrix);
            display(matrix);

        }
    }

    // Approach 1:
    public static void setZeros1(int[][] matrix) {
        
        int m = matrix.length,n = matrix[0].length;
        
        boolean[] row = new boolean[m];
        boolean[] col = new boolean[n];

        for(int i = 0;i<m;i++) {
            for(int j = 0;j<n;j++) {
                if(matrix[i][j] == 0) {         // If cell is 0
                    row[i] = true;              // Mark row 
                    col[j] = true;              // Mark col
                }
            }
        }

        for(int i = 0;i<m;i++) {
            for(int j = 0;j<n;j++) {
                if(row[i] || col[j]) {          // Either row or col is marked
                    matrix[i][j] = 0;           // then, set the cell as 0.
                }
            }
        }
    }

    // Approach 2:
    public static void setZeros2(int[][] matrix) {
        
        int m = matrix.length,n = matrix[0].length;
        
        boolean col0 = false;               // for storing of 0 in first col

        for(int i = 0;i<m;i++) {

            if(matrix[i][0] == 0) {         // if first col contains 0
                col0 = true;                // then, mark col0 as true
            }

            for(int j = 1;j<n;j++) {        
                if(matrix[i][j] == 0) {     // If cell is zero
                    matrix[i][0] = 0;       // mark the first col
                    matrix[0][j] = 0;       // mark the first row
                }
            }
        }

        for(int i = 1;i<m;i++) {            
            for(int j = 1;j<n;j++) {
                if(matrix[i][0] == 0 || matrix[0][j] == 0) {
                    matrix[i][j] = 0;
                }
            }
        }

        // If first cell is 0, then mark all the elements in the first row as 0.
        if(matrix[0][0] == 0) {
            for(int j = 1;j<n;j++) {
                matrix[0][j] = 0;
            }
        }

        // If any of the element in the first col is 0, then mark the whole col as 0.
        if(col0) {
            for(int i = 0;i<m;i++) {
                matrix[i][0] = 0;
            }
        }
    }

    public static void display(int[][] matrix) {
        for(int i = 0;i<matrix.length;i++) {
            for(int j = 0;j<matrix[0].length;j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
    }
}