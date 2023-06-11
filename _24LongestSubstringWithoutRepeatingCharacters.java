// LEETCODE 3

/*
Given a string s, find the length of the longest substring without repeating characters.
*/

// Input:
/*
abcabcbb
*/

// Output:
/*
3
*/

// Approach:
/*
First approach is to generate all possible substring and then check each substring whether there are repeating character or not using HashSet.
If they are, then update the max variable that is created to store the answer if possible. Time Complexity -> O(n^3), Space -> O(n)

In other approach,we use the concept of sliding window along with HashMap. In this, we initialize two pointers (left and right) to the beginning
of the array and then check if the current right already contains in the hashmap or not. If it doesn't exist, then that means till now, there is
no occurance of the given character, So, we maximize the answer variable max if it is greater than (right - left + 1) [or, greater than the total
number of elements in between]. And, add the current character into the hashmap along with the index at which it is found. And, then shift the 
right pointer by 1. Now, if the character already exists in the hashmap, then we update the left pointer and place it to the index where the last
time the current character is found if it is greater than left, or we can say that if left < hm.get(s.charAt(right)), then move the left to 
hm.get(s.charAt(right)) + 1. And, if left > val, then it is considered that we are not concerned with the right previous occurance as it is out 
of bound. Thus, it can be infered that left is set to the max value between left and hm.get(s.charAt(i)) + 1.

DRY RUN: 
a b c a b c
0 1 2 3 4 5
left - 0, right - 0, HashMap - {}
at index 0: left - 0,HashMap - {[a,0]},right - 1
at index 1: left - 0,HashMap -{[a,0],[b,1]}, right - 2
at index 2: left - 0,HashMap - {[a,0],[b,1],[c,2]}, right - 3
at index 3: As 'a' already exist in the array and it occur at index 0, so we move the left to 1, HashMap - {[a,3],[b,1],[c,2]}, right - 4
at index 4: As 'b' already exist in the hashmap and it occurs at index 1, thus left moves to 2, HashMap - {[a,3],[b,4],[c,2]}, right - 5
and so on.
*/

import java.util.*;

public class _24LongestSubstringWithoutRepeatingCharacters {
    public static void main(String[] args) {
        try(Scanner scn = new Scanner(System.in)) {
            
            String s = scn.nextLine();
            
            int max = lengthOfLongestSubstring(s);
            System.out.println(max);
        }
    }

    public static int lengthOfLongestSubstring(String s) {
        int max = 0;
        
        HashMap<Character,Integer> hm = new HashMap<>();
        int left = 0,right = 0;

        while(right < s.length()) {
            if(hm.containsKey(s.charAt(right))) 
                left = Math.max(hm.get(s.charAt(right)) + 1,left);

            max = Math.max(max,right-left+1);
            hm.put(s.charAt(right),right);
            right++;
        }

        return max;
    }
}