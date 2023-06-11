/*
Given an array of integers nums and an integer k, return the number of subarrays whose elements bitwise XOR equals to k.
A subarray is a contiguous non-empty sequence of elements within an array.
*/

// Input:
/*
5
4 2 2 6 4
6
*/

// Output:
/*
4
*/

// Approach:
/*
In this problem, we can either iterate through each subarray and generate the xor and check if the xor is equal to 'k' and if it is, then update
the cnt variable (which stores the number of subarrays having xor as k) by 1. In this, Time complexity is O(n^2) and space complexity is O(1).

Other method is to use HashMap. Consider that a array has index as 01234567. And, if we are at index 5, and xor upto index 5 is 'X' and target is
'k', then we check if there exist a xor in the previous iteration that result in the xor as X^k. 
Consider array indices as 01234567 and we are at index 5, and xor of elements upto 5th index is 'X'.Now, we look if there is a xor value as X^k 
in the previous iteration, say it was at index 2. So, in 012-345, 0^1^2 = x and 345 is k, then X = x^k ==> X^k = (x^k)^k ==> x = X^k.Thus, if in
previous iterations there is X^k and in the current iteration we are getting X as xor, then definately, there will be a subarray with k as xor 
because then only (X^k)^k will result in X.

So, to keep a track of xor value of different elements along with the number of times we are getting this xor, we use a hashmap. So, we xor all
the elements and check if it is equal to 'k' and if it is, then we increase the cnt by 1. And, then we check if in the previous iteration, have 
we found xor value as X^k and if we have found it, then increase the cnt by the number of times X^k has occured. And, after this, add the xor
value in the hashmap along with increasing its occurance time.
*/

import java.util.*;

public class _23CountSubArrayWithGivenXOR {
    
    public static void main(String[] args) {
        try(Scanner scn = new Scanner(System.in)) {
            int n = scn.nextInt();
            int[] nums = new int[n];

            for(int i = 0;i<n;i++) {
                nums[i] = scn.nextInt();
            }
            
            int k = scn.nextInt();
            
            int cnt = subarraysXor(nums,k);

            System.out.println(cnt);

        }
    }

    public static int subarraysXor(int[] arr, int x) {
		
		int cnt = 0;
		HashMap<Integer,Integer> hm = new HashMap<>();          // Key:Value ==> xor value:occurance of this xor value
		int xor = 0;                                            // xor of elements

		for(int i = 0;i<arr.length;i++) {
			xor ^= arr[i];                                      // xor the current element

			if(xor == x) {                                      // if xor is now equal to the target,then increase cnt by 1
				cnt++;
			}

			if(hm.containsKey(xor^x)) {                         // if in previous iteration, there was X^k and currently, xor is 'X', then
                                                                // definately, there is xor of 'k' in between [(X^k) ^ k = X].
				cnt += hm.get(xor^x);                           // increase the number of times X^k occured.
			}

			hm.put(xor,hm.getOrDefault(xor,0) + 1);     // put the xor into the hashmap and add its occurance by 1.
		}

		return cnt;
	}
}
