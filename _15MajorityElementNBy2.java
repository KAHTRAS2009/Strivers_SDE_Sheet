// LEETCODE 169

/*
Given an array nums of size n, return the majority element if exist else return -1.

The majority element is the element that appears more than ⌊n / 2⌋ times.
*/

// Input:
/*
Problem 1:
5
2 3 9 2 2

Problem 2:
4
8 5 1 9 
*/

// Output:
/*
Solution 1: 2
Solution 2: -1
*/

//Approach:
/*
Approach 1: Use a HashMap and determine the occurance of each element and if the occurance exceeds n/2, then it is the majority element. 
Time Complexity: O(n)
Space Complexity: O(n) 

Approach 2: BOYER-MOORE MAJORITY VOTING ALGORITHM

This algorithm works on the fact that if an element occurs more than N/2 times, it means that the remaining elements other than this would 
definitely be less than N/2. So let us check the proceeding of the algorithm.

First, choose a candidate from the given set of elements if it is the same as the candidate element, increase the votes. Otherwise, 
decrease the votes. If votes become 0, select another new element as the new candidate.

Intuition Behind Working :
When the elements are the same as the candidate element, votes are incremented whereas when some other element is found (not equal to the 
candidate element), we decreased the count. This actually means that we are decreasing the priority of winning ability of the selected candidate,
since we know that if the candidate is in majority it occurs more than N/2 times and the remaining elements are less than N/2. We keep 
decreasing the votes since we found some different element(s) than the candidate element. When votes become 0, this actually means that 
there are the equal  number of votes for different elements, which should not be the case for the element to be the majority element. So 
the candidate element cannot be the majority and hence we choose the present element as the candidate and continue the same till all the 
elements get finished. The final candidate would be our majority element. We check using the 2nd traversal to see whether its count is greater 
than N/2. If it is true, we consider it as the majority element.

Time Complexity: O(n)
Space Complexity: O(1) 
*/

import java.util.*;

public class _15MajorityElementNBy2 {
    
    public static void main(String[] args) {
        try(Scanner scn = new Scanner(System.in)) {
            int n = scn.nextInt();

            int[] nums = new int[n];

            for(int i = 0;i<n;i++) {
                nums[i] = scn.nextInt();
            }

            System.out.println(findMajority(nums, n));
            
        }
    }

    public static int findMajority(int[] arr, int n) {
		// Write your code here.

        if(n == 1) {
            return arr[0];
        }
        
        int cnt = 0;
        Integer candidate = null;

        for(int num:arr) {
            if(cnt == 0) {
                candidate = num;                    // If cnt is zero, taking present num as possible candidate.
            }

            cnt += (num == candidate) ? 1 : -1;     // If num is same as candidate, increase the votes, else decrease.
        }

		// Again, iterating to check if the possible candidate has clear majority.
        // if a majority element is confirmed to be present in an array just return candidate in that case.
        cnt = 0;                                    
        for(int num:arr) {
			if(num == candidate) cnt++;
		}

		return (cnt > n/2)?candidate:-1;
	}
}