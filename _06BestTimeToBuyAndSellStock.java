// LEETCODE 121

/*
You are given an array prices where prices[i] is the price of a given stock on the ith day.
You want to maximize your profit by choosing a single day to buy one stock and choosing a different day in the future to sell that stock.
Return the maximum profit you can achieve from this transaction. If you cannot achieve any profit, return 0.
*/

// Input:
/*
6
7 1 5 3 6 4
*/

// Output:
/*
5
*/

//Approach:
/*
In this problem, we assume that the first day stock price is the minimum price, and Initialize our max profit as 0. Then, iterate over the array
and check if the current stock price is less than the min Stock price, if it is then update the min Stock price, if it is not, then we try to
sell the stock on this day and check if the price of selling is greater than the maxProfit and update it accordingly.
*/

import java.util.*;

public class _06BestTimeToBuyAndSellStock {

    public static void main(String[] args) {
        try(Scanner scn = new Scanner(System.in)) {
            int n = scn.nextInt();

            int[] prices = new int[n];

            for(int i = 0;i<n;i++) {
                prices[i] = scn.nextInt();
            }
            
            System.out.println(maxProfit(prices));
            
        }
    }

    public static int maxProfit(int[] prices) {
        
        int minStock = prices[0];
        int maxP = 0;

        for(int i = 1;i<prices.length;i++) {
            if(prices[i] < minStock) {
                minStock = prices[i];
            } else {
                int profit = prices[i] - minStock;
                maxP = Math.max(maxP,profit);
            }
        }

        return maxP;
    }
}