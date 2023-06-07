// LEETCODE 287

/*
Given an array of integers nums containing n + 1 integers where each integer is in the range [1, n] inclusive.
There is only one repeated number in nums, return this repeated number.
You must solve the problem without modifying the array nums and uses only constant extra space.
*/

// Input:
/*
10 
2 5 9 6 9 3 8 9 7 1
*/

// Output:
/*
9
*/

//Approach:
/*
In this problem, it is mentioned that the size of array is n + 1 and the numbers in the array have to be in the range of [1,n]. Also, a number
is occuring more than once(2,3,.. times) and we have to determine that only. Now, consider the array as

Index : 0 1 2 3 4 5 6 7 8 9
Array : 2 5 9 6 9 3 8 9 7 1

Now, carefully look the array must contains elements between 1 to 9 only and there are 10 numbers, so 1 number must repeat at least one as per
pigeonhole principle. Now, we start from index 0 and then move on to the element that is present in the arr[0],i.e., 2. At index 2, there is 9
so we move to 9 and at index 9, there is 1 so we move to index 1 and in the same manner. So, traversing will look like -> 

2 -> 9 -> 1 -> 5 -> 3 -> 6 -> 8 -> 7 -> 9. 

So, at index 7, there was 9 and 9 was already present in the sequence, thus a cycle is formed. So, a cycle will always be formed in this manner.


arr[0] -> n -> n -> M -> n -> n -> n -> I -> n -> M

Now, consider a general case of this cycle, where initial node will be arr[0], M is the begining of cycle and I is the meeting point of the 
two pointers. Now, clearly, fast pointer moves a distance twice that was covered by slow pointer. So, if ->
F => distance b/w arr[0] and M
a => distance b/w M and I
C => length of cycle.

Distance covered by slow pointer = F + a
Distance covered by fast pointer = F + nC + a, where n is revolutions made by fast pointer

Thus, 2(F+a) = F+nC+a 
==> F+a = nC

Now, if we place the slow pointer at begining i.e., at arr[0]. Then, it will take F distance to reach at M.
Also, fast was at I only, so in this F distance it will reach at F+a in the cycle (since it was at a distance of a from M). As F+a = nC
So, it will reach at M only, and it is ultimately the begining of cycle and the duplicate number in the array. 
*/

import java.util.*;

public class _10FindDuplicateNoInArray {

    public static void main(String[] args) {
        try(Scanner scn = new Scanner(System.in)) {
            int n = scn.nextInt();

            int[] nums = new int[n];

            for(int i = 0;i<n;i++) {
                nums[i] = scn.nextInt();
            }

            System.out.println(findDuplicate(nums));
        }
    }

    public static int findDuplicate(int[] nums) {

        int tort = nums[0];
        int rab = nums[0];

        do {
            tort = nums[tort];
            rab = nums[nums[rab]];
        } while(tort != rab);

        tort = nums[0];

        while(tort != rab) {
            tort = nums[tort];
            rab = nums[rab];
        }

        return tort;
    }
}