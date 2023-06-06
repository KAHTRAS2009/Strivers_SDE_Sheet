// LEETCODE 118

/*
Given an integer numRows, return the first numRows of Pascal's triangle.

In Pascal's triangle, each number is the sum of the two numbers directly above it.
*/

// Input:
/*
5
*/

// Output:
/*
[[1],[1,1],[1,2,1],[1,3,3,1],[1,4,6,4,1]]
*/

// Approach:
/*
Approach 1: Use DP tabulation, as the middle number are sum of above two numbers.

Time Complexity: O(n^2)
Space Complexity: O(n^2)


Approach 2: Use Combinations (nCr) Now, consider the 5th row (1-4-6-4-1), which is same as 4C0,4C1,4C2,4C3,4C4. So, if somehow, we create this
for each row, and then append it, then it will be the result. 

Now, we have to generate a list for 4C0,4C1,4C2,4C3,4C4. So, create a separate function.

4C0  -> 4C1 -> 4C2  ->  4C3   ->  4C4
 1   ->  4/ -> 4*3/ -> 4*3*2/ -> 4*3*2*1/       (NUMERATOR)/
         1  -> 1*2  -> 1*2*3  -> 1*2*3*4        (DENOMINATOR)


So, we create a temp list and add 1 to it. Thereafter, assign a variable ans to 1; And, then loop i from 1 to 4, and multiply ans by (4-i) and
then divide by i. And, each time add the ans to the temp list.

Loop this function for row from 1 to the given number.

Time Complexity: O(n^2)
Space Complexity: O(n^2)
*/

import java.util.*;

public class _2PascalTriangle {

    public static void main(String[] args) {
        try(Scanner scn = new Scanner(System.in)) {
            int n = scn.nextInt();

            System.out.println(generate1(n));
            System.out.println(generate2(n));

        }
    }

    // Approach 1:
    public static List<List<Integer>> generate1(int numRows) {
        List<List<Integer>> ans = new ArrayList<>();
        
        for(int i = 1;i<=numRows;i++) {
            List<Integer> temp = new ArrayList<>();

            temp.add(1);

            if(i == 1) {
                ans.add(temp);
                continue;
            }

            List<Integer> pre = ans.get(ans.size()-1);
            for(int j = 1;j<i-1;j++) {
                temp.add(pre.get(j)+pre.get(j-1));
            }

            temp.add(1);
            ans.add(temp);

        }

        return ans;
    }

    // Approach 2:
    public static List<List<Integer>> generate2(int numRows) {
        List<List<Integer>> ans = new ArrayList<>();

        for(int i = 1;i<=numRows;i++) {
            ans.add(getNthRow(i));
        }

        return ans;
    }

    private static List<Integer> getNthRow(int n) {
        
        List<Integer> temp = new ArrayList<>();

        temp.add(1);
        int pre = 1;
    
        for(int i = 1;i<n;i++) {
            pre *= (n-i);
            pre /= i;
            temp.add(pre);
        }

        return temp;
    }
}