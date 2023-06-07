// LEETCODE 50

/*
Implement pow(x, n), which calculates x raised to the power n (i.e., x^n).
*/

// Input:
/*
2.10000
3
*/

// Output:
/*
9.26100
*/

//Approach:
/*
It can be considered that x^n can be assumed as (x^2)^(n/2) if it is even and as x*(x)^(n-1), if n is odd and after this n-1 will become even.
And the process goes on until n is greater than 0. So, we create a variable as ans, and check if n is greater than 0, if it is then we check 
whether it is odd or even -> 
ODD -> reduce n by 1 and product the x into ans
EVEN -> reduce the n by half and multipy x with itself.

As, n can be negative, so we create a variable nn with long type and assign -1*n if n is negative since |Integer.MIN_VALUE| > Integer.MAX_VALUE
And, apply the same process on this nn. At last, we again check if n is negative, if it is then ans will become 1/ans [since x^(-n) = 1/x^n]s
*/

import java.util.*;

public class _14PowX_N {
    
    public static void main(String[] args) {
        try(Scanner scn = new Scanner(System.in)) {
            
            double x = scn.nextDouble();
            int n = scn.nextInt();
            int m = scn.nextInt();

            System.out.println(myPow(x,n)%m);
        }
    }

    public static double myPow(double x, int n) {
       
        if(n == 0) return 1; 
        
        long nn = n;
        
        if(nn<0) nn = -1*nn;    // If n is negative, then make it positive.
        double ans = 1;         // to store the answer.
        
        while(nn > 0) {         
            if(nn%2 == 0) {     // If n is even, then
                x *= x;         // make x^n as (x^2)^(n/2) => (x*x)^(n/2)
                nn /= 2;
            } else {            // else,
                ans *= x;       // Make x^n as x^(n-1) and take the other x into ans.
                nn -= 1;        // say, 2^5 ==> 2*(2^4) ==> 2*(2*2)^(4/2) ==> 2*((4)^2) and so on.
            }
        }

        if(n<0) ans = 1/ans;    // If n is negative then invert the answer.

        return ans;
    }
}