// LEETCODE 48

/*
Problem 1: You are given an n x n 2D matrix representing an image, rotate the image by 90 degrees (clockwise).
You have to rotate the image in-place, which means you have to modify the input 2D matrix directly. DO NOT allocate another 2D matrix 
and do the rotation.

Problem 2: Given a 2D matrix of size N X M, rotate the elements of the matrix clockwise only once.
*/

// Input:
/*
For Problem 1:
3 
1 2 3 
4 5 6
7 8 9

For Problem 2:
3 3 
1 2 3
4 5 6
7 8 9
*/

// Output:
/*
For Problem 1:
7 4 1
8 5 2
9 6 3

For Problem 2:
4 1 2
7 5 3
8 9 6
*/

//Approach:
/*
If the problem is as rotate by 90 degree, then 1 row become last column, 2 row will become 2nd last column,3rd row will become 3rd last column
and so on. So, is easier to transform the matrix in such a way that 1 row will become 1 column, 2 row will become 2nd column and so on by 
Transponsing the matrix and then swapping the elements of 1st column with the last column and 2nd column with the 2nd last column and so on.
Hence our matrix will rotate by 90.

Now, if the problem is as to rotate clockwise once, that means that the answer should be as:
4 1 2
7 5 3
8 9 6

Then, in this case we consider that we have to move element once in all the loop (rectangles) upto the loop of Math.min(m,n)/2.
So, we create a tmp variable initialize it with 1st value to be replaced, then change the value of first loop row, then last loop column
then last loop row, and ultimately first loop col. In this manner, all the elements are shifted by one. Again, we apply the looping for the 
inner loop.
And, the array will be modified.
*/

import java.util.*;

public class _07RotateMatrix {
    

    public static void main(String[] args) {
        try(Scanner scn = new Scanner(System.in)) {
            
            System.out.println("Input for Problem 1: ");
            
            int n = scn.nextInt();
            int[][] matrix = new int[n][n];
            for(int i = 0;i<n;i++) {
                for(int j = 0;j<n;j++) {
                    matrix[i][j] = scn.nextInt();
                }
            }
            
            rotateby90(matrix);
            
            System.out.println("Output for Problem 1: ");

            for(int i = 0;i<n;i++) {
                for(int j = 0;j<n;j++)
                    System.out.print(matrix[i][j] + " ");   
                System.out.println();
            }
            
            System.out.println("Input for Problem 2: ");
            int N = scn.nextInt();
            int M = scn.nextInt();
            
            ArrayList<ArrayList<Integer>> mat = new ArrayList<>();
            
            for(int i = 0;i<N;i++) {
                ArrayList<Integer> temp = new ArrayList<>();
                
                for(int j = 0;j<M;j++) {
                    temp.add(scn.nextInt());
                }
                
                mat.add(temp);
            }
            
            rotateMatrix(mat,N,M);
            
            System.out.println("Output for Problem 2: ");
            for(ArrayList<Integer> al:mat) {
                for(int ele:al) {
                    System.out.print(ele + " ");
                }
                System.out.println();
            }
        }
    }

    public static void rotateby90(int[][] matrix) {

        for(int i = 0;i<matrix.length;i++) {
            for(int j = i+1;j<matrix.length;j++) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = temp;
            }
        }

        int i = 0,j = matrix.length-1;

        while(i<j) {

            for(int r = 0;r<matrix.length;r++) {
                int temp = matrix[r][i];
                matrix[r][i] = matrix[r][j];
                matrix[r][j] = temp;
            }
            i++;j--;
        }
    }

    public static void rotateMatrix(ArrayList<ArrayList<Integer>> mat, int n, int m) {
        // Write your code here.

        if(n == 1 || m == 1) return;
        
        int loop = Math.min(m,n)/2,i = 0;

        while(i < loop) {


            int tmp = mat.get(i+1).get(i);

            rotate(mat,i,n-i-1,i,m-i-1,tmp);
            i++;
        }
    }

    private static void rotate(ArrayList<ArrayList<Integer>> mat,int rStrt,int rEnd,int cStrt,int cEnd,int tmp) {


        for(int i = cStrt;i<=cEnd;i++) {
            int temp = mat.get(rStrt).get(i);
            mat.get(rStrt).set(i,tmp);
            tmp = temp;
        }

        rStrt++;
        for(int j = rStrt;j<=rEnd;j++) {
            int temp = mat.get(j).get(cEnd);
            mat.get(j).set(cEnd,tmp);
            tmp = temp;
        }
        cEnd--;

        for(int i = cEnd;i>=cStrt;i--) {
            int temp = mat.get(rEnd).get(i);
            mat.get(rEnd).set(i,tmp);
            tmp = temp;
        } 

        rEnd--;
        for(int i = rEnd;i>=rStrt;i--) {
            int temp = mat.get(i).get(cStrt);
            mat.get(i).set(cStrt,tmp);
            tmp = temp;
        }
        cStrt++;
    }

}