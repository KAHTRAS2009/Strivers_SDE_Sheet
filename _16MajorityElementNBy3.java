// LEETCODE 229

/*
Given an integer array of size n, find all elements that appear more than ⌊ n/3 ⌋ times.

*/

// Input:
/*
Problem 1:
7
3 2 2 1 5 2 3


Problem 2:
5
7 4 4 9 7
*/

// Output:
/*
Solution 1: 2
Solution 2: 4 7
*/

//Approach:
/*
Important Observation : 
If we think a little bit, then consider the array has 10 elements, then the majority mark will be 10/3 = 3.33 ==> 4. Thus, an element has to
occur atleast 4 times to become majority, and as there are 10 numbers so at max 2 majority numbers are possible. Similarly, if n = 12, then n/3
= 12/3 = 4, so, the majority mark will be 5. Now, in this case also, at max 2 elements are in majority.

Now, in can be said that in this n/3 majority mark, at max 2 elements can be there.

Approach 1: Use a HashMap and determine the occurance of each element and if the occurance exceeds n/3, then it is the majority element and add 
it to the ans ArrayList. 
Time Complexity: O(n)
Space Complexity: O(n) 


Approach 2: Modified BOYER-MOORE MAJORITY VOTING ALGORITHM
In this modified version, we initially set the two cnt variable to zero, and initialize the 2 element variable that corresponds to the the majority
number at Integer min value. Now, if cnt1 is zero, then we initialize it with 1 and set the ele1 to the value at i index, and similarly for the
cnt2. Now, if the number at i index is equal to ele1, then we increase the cnt1 by 1(priority of this element increases) and if the number at
ith index is equal to ele2, then we increase the priority of this number by increasing the cnt2 by 1. And, if the number at index i is not equal
to both cnt1 and cnt2, then we decrease the priority of both the elements. 

An important case to consider here is that if cnt1 = 1 for ele1 = 1, 
then cnt2 can never be set to 1 for element 1.Thus, we have to keep in mind that the number that is placed for the a particular cnt must not be
part of other cnt.

At the end, we again check if the selected element occur for more than n/3 times and if they are, then append it to the answer.

Time Complexity: O(n)
Space Complexity: O(1) 
*/

import java.util.*;

public class _16MajorityElementNBy3 {
    
    public static void main(String[] args) {
        try(Scanner scn = new Scanner(System.in)) {
            int n = scn.nextInt();

            int[] nums = new int[n];

            for(int i = 0;i<n;i++) {
                nums[i] = scn.nextInt();
            }

            List<Integer> ans = majorityElement(nums);
            for(int val:ans) {
                System.out.print(val + " ");
            }

        }
    }

    public static List<Integer> majorityElement(int[] nums) {
        
        int cnt1 = 0,cnt2 = 0;
        int ele1 = Integer.MIN_VALUE,ele2 = Integer.MIN_VALUE;

        for(int num:nums) {
            if(cnt1 == 0 && num != ele2) {
                cnt1 = 1;
                ele1 = num;
            } else if (cnt2 == 0 && num != ele1) {
                cnt2 = 1;
                ele2 = num;
            } 
            else if (num == ele1) cnt1++;
            else if (num == ele2) cnt2++;
            else {
                cnt1--;cnt2--;
            }
        }

        cnt1 = 0;cnt2 = 0;

        for(int num:nums) {
            if(num == ele1) {
                cnt1++;
            }
            if(num == ele2) {
                cnt2++;
            }
        }

        List<Integer> ans = new ArrayList<>();
        if(cnt1 > nums.length/3) {
            ans.add(ele1);
        }

        if(cnt2 > nums.length/3) {
            ans.add(ele2); 
        }

        return ans;
    }
}